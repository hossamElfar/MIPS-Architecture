import java.io.IOException;
import java.util.ArrayList;

public class Datapath {
	
	String pc;
	ArrayList<String> status;
	Adder A1 , A2;
	MUX M1 , M2 , M3 , M4;
	InstructionMemory IM;
	IFID FD;
	ALUControl AC;
	ALU A;
	ControlUnit C;
	RegisterFile RF;
	IDEX DX;
	ShiftLeft2 SL;
	SignExtender Se;
	DataMemory DM;
	MEMWB MW;
	EXMEM EM;
	int clockcycle;
	String M4Result;
	int c = 0;
	boolean PcSrc;
	String adderResult;
	
	public Datapath() throws IOException{
		pc = "00000000000000000000000000000000";
		//Text file FOR IM;
		IM = new InstructionMemory("inst.txt");
		status = new ArrayList<String>(IM.instructions.size());
		A = new ALU();
		A1 = new Adder();
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
		clockcycle = 5 * (IM.instructions.size() - 1);
	}
	
	public void fetch(){
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
	
	public void decode() throws Exception{
		RF.setRegWrite(MW.isRegWrite());
		if(MW.isMemToReg())c = 1;
		RF.WriteData(MW.rd,M4.select(c));
		String inst = FD.getInstruction();
		String pc = FD.getIncrementedPC();
		DX.setIncrementedPC(pc);
		DX.setReadValue1(RF.ReadReg1(inst.substring(21,26)));
		DX.setReadValue2(RF.ReadReg2(inst.substring(20,17)));
		DX.setSignExtend(Se.extend(inst.substring(0, 16)));
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
	public void execute() throws Exception{
		String ShiftResult = SL.shiftLeft(DX.getSignExtend());
		adderResult = A2.add(DX.getIncrementedPC(), ShiftResult );
		String code = AC.decide(DX.getALUop(), DX.getSignExtend().substring(0, 6));
		M2.setInputs(DX.getReadValue2(),DX.getIncrementedPC());
		c = 0;
		if(DX.isALUsrc())c = 1;
		String M2Out = M2.select(c);
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
	public void writeback(){
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
		c = 0;
		M4.setInputs(MW.getMemoryRead(), MW.getAluResult());
		if(MW.isMemToReg()) c = 1;
		M4Result = M4.select(c);
	}
	
	
	public void main(String[] args) throws Exception{
		int i = 0;
		int j = 1;
		while(j <= clockcycle){
		
		System.out.println("Clock cycle "+ j);	
			
		while(i <= j - 1){
			
		if(status.get(i) == "Done")break;	
			
		if(status.get(i) == null){
		fetch();
		status.set(i, "Fetched");
		System.out.println("Instruction " + i + "has been fetched!");
		}
		else {
			if(status.get(i) == "Fetched"){
				decode();
				status.set(i, "Decoded");
				System.out.println("Instruction " + i + "has been Decoded!");
			}else{
				if(status.get(i) =="Decoded"){
					execute();
					status.set(i, "Executed");
					System.out.println("Instruction " + i + "has been executed!");
				}else{
					if(status.get(i) =="Executed"){
						writeback();
						status.set(i, "Done");
						System.out.println("Instruction " + i + "is DONE!");
					}
					}
					}
					}
		i++;
		}
		j++;
		i = 0;
		}
	}
}
