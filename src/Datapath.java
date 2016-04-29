import java.io.IOException;
import java.util.ArrayList;

public class Datapath {
	
	static String pc;
	static ArrayList<String> status;
	static Adder A1 , A2;
	static MUX M1 , M2 , M3 , M4;
	static InstructionMemory IM;
	static IFID FD;
	static ALUControl AC;
	static ALU A;
	static ControlUnit C;
	static RegisterFile RF;
	static IDEX DX;
	static ShiftLeft2 SL;
	static SignExtender Se;
	static DataMemory DM;
	static MEMWB MW;
	static EXMEM EM;
	static int clockcycle;
	static String M4Result;
	static int c = 0;
	static boolean PcSrc;
	static String adderResult;
	
	public Datapath() throws IOException{
		pc = "00000000000000000000000000000000";
		//Text file FOR IM;
		IM = new InstructionMemory("inst.txt");
		
		
		status = new ArrayList<String>();
		for(int k = 0; k < IM.instructions.size()/4 ; k++){
			status.add("Pending");
		}
		
		A = new ALU();
		A1 = new Adder();
		A2 = new Adder();
		DM = new DataMemory();
		AC = new ALUControl();
		C = new ControlUnit();
		Se = new SignExtender();
		SL = new ShiftLeft2();
		RF = new RegisterFile();
		FD = new IFID();
		DX = new IDEX();
		EM = new EXMEM();
		MW = new MEMWB();
		clockcycle = 5 + ((IM.instructions.size())/4 - 1);
		M1 = new MUX();
		M2 = new MUX();
		M3 = new MUX();
		M4 = new MUX();
		
		//These are the variables that we do not have in the 1st 4 clock cycles
		adderResult = pc;
		EM.branchAddress = pc;
		MW.rd = "00000";
		
		
	}
	
	public static void fetch(){
		c = 0;
		M1.setInputs(adderResult, EM.branchAddress);
		if(PcSrc) c = 1;
		M1.select(c);
		int index = Integer.parseInt(pc,2);
		String inst = IM.fetch(index);	
		String in1 = Integer.toBinaryString(4);
		pc = (A1.add(in1, pc));
		FD.setInstruction(inst);
		FD.setIncrementedPC(pc);
    }
	
	public static void decode() throws Exception{
		String inst = FD.getInstruction();
		String pc = FD.getIncrementedPC();
		DX.setIncrementedPC(pc);
		DX.setReadValue1(RF.ReadReg1(inst.substring(21,26)));
		DX.setReadValue2(RF.ReadReg2(inst.substring(17,21)));
		System.out.println(Se.extend(inst.substring(18, 32)));
		DX.setSignExtend(Se.extend(inst.substring(18, 32)));
		DX.setRt(inst.substring(11, 16));
		DX.setRd(inst.substring(16, 21));
		C.action(inst.substring(0, 6));
		DX.setMemRead(C.MemRead);
		DX.setMemWrite(C.MemWrite);
		DX.setALUop(C.ALUOperation);
		DX.setBranch(C.branch);
		DX.setMemToReg(C.MemtoReg);
		DX.setALUsrc(C.ALUSrc);
		DX.setRegDestination(C.RegDest);
		DX.setRegWrite(C.RegWrite);
	}
	
	
	
	public static void execute() throws Exception{
		String ShiftResult = SL.shiftLeft(DX.getSignExtend());
		adderResult = A2.add(DX.getIncrementedPC(), ShiftResult );
		System.out.println(DX.getSignExtend().substring(26, 32));
		String code = AC.decide(DX.getALUop(), DX.getSignExtend().substring(26, 32));
		M2.setInputs(DX.getReadValue2(),DX.getIncrementedPC());
		c = 0;
		if(DX.isALUsrc())c = 1;
		String M2Out = M2.select(c);
		System.out.println(DX.ReadValue1);
		System.out.println(M2Out);
		System.out.println(code);
		
		String ALUResult = A.Activate(DX.ReadValue1, M2Out, code);
		c = 0;
		M3.setInputs(DX.getRt(), DX.getRd());
		if(DX.isRegDestination())c = 1;
		String M3Out = M3.select(c);
		EM.setBranchAddress(adderResult);
		EM.setALUresult(ALUResult);
		EM.setRd(M3Out);
		EM.setZeroFlag(A.isZero());
		EM.setValueToMem(DX.getReadValue2());
		EM.setMemTowrite(DX.RegDestination);
		EM.setRegWrite(DX.isRegWrite());
		EM.setBranch(DX.isBranch());
		EM.setMemRead(DX.isMemRead());
		EM.setMemWrite(DX.isMemWrite());
	}
	
	public static void memory(){
		DM.setMemRead(EM.isMemRead());
		DM.setMemWrite(EM.isMemWrite());
		String DMResult = DM.read(EM.ALUresult);
		DM.store(EM.getValueToMem());
		if(EM.isBranch() == true && A.isZero() == true)PcSrc = true;
		MW.setAluResult(EM.getValueToMem());
		MW.setMemoryRead(DMResult);
		MW.setRd(EM.getRd());
		MW.setMemToReg(EM.isMemTowrite());
		MW.setRegWrite(EM.isRegWrite());
		
	}
	
	private static void writeback() {
		c = 0;
		M4.setInputs(MW.getMemoryRead(), MW.getAluResult());
		if(MW.isMemToReg()) c = 1;
		M4Result = M4.select(c);
		RF.setRegWrite(MW.isRegWrite());
		if(MW.isMemToReg())c = 1;
		RF.WriteData(MW.rd,M4.select(c));
		
	}
	
	
	public static void printRegData(){
		for(int i = 0; i < RF.Registers.length ; i++)
		System.out.println(RF.Registers[i]);
	}
	public static void printFetch(){
	System.out.println(FD.getIncrementedPC());
	System.out.println(FD.getInstruction());
	}
	
	////Ctrl signals printing
	public static void printDecode(){
	System.out.println(	"AluSrc " +	DX.isALUsrc());
	System.out.println(	"Branch " +	DX.isBranch());
	System.out.println(	"MemRead " +	DX.isMemRead());
	System.out.println(	"MemToReg " +	DX.isMemToReg());
	System.out.println(	"MemWrite " +	DX.isMemWrite());
	System.out.println(	"RegDestination " +	DX.isRegDestination());
	System.out.println(	"RegWrite " +	DX.isRegWrite());
	}
	public static void printExecute(){
		System.out.println(	"Branch " +	EM.isBranch());
		System.out.println(	"MemRead " +	EM.isMemRead());
		System.out.println(	"MemToReg " +	EM.isMemTowrite());
		System.out.println(	"MemWrite " +	EM.isMemWrite());
		System.out.println(	"RegWrite " +	EM.isRegWrite());
		System.out.println(	"Zero " +	EM.isZeroFlag());
	}
	public static void printMemory(){
		System.out.println(	"MemToReg " +	MW.isMemToReg());
		System.out.println(	"RegWrite " +	MW.isRegWrite());
	}
	
	
	
	
	public static void main(String[] args) throws Exception{
		Datapath D = new Datapath();
		int i = 0;
		int j = 0;
		while(j < clockcycle){
		System.out.println("---------------------");
		System.out.println("Clock cycle " + (j + 1));
		while (i <= j){
			
			if(status.get(i) == "Pending"){
				fetch();
				status.set(i, "Fetched");
				System.out.println("Instruction " + (i + 1) +" is fetched");
			}else{
				if(status.get(i) == "Fetched"){
					decode();
					status.set(i, "Decoded");
					System.out.println("Instruction " + (i+ 1) +" is decoded");
				}else{
					if(status.get(i) == "Decoded"){
						execute();
						status.set(i, "Executed");
						System.out.println("Instruction " + (i+ 1) +" is executed");
					}else{
						if(status.get(i) == "Executed"){
							memory();
							status.set(i, "Memory");
							System.out.println("Instruction " + (i+ 1) +" passed MemoryStage");
						}else{
							if(status.get(i) == "Memory"){
								writeback();
								status.set(i, "Done");
								System.out.println("Instruction " + (i+ 1) +" is Done");
							}
						}
						
					}
				}
			}
		if(i + 1 == status.size())break;	
			
		i++;
		}
		j++;
		i = 0;
		}
}

	
}
