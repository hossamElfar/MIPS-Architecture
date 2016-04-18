import java.io.IOException;
//pending points:
//1- control unit
//2- clockcycles
//3- 

public class Datapath {
	
	String pc;
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
		clockcycle = 0;
	}
	
	public void fetch(){	
		int index = Integer.parseInt(pc,2);
		String inst = IM.fetch(index);	
		String in1 = Integer.toBinaryString(4);
		pc = (A1.add(in1, pc));
		FD.setInstruction(inst);
		FD.setIncrementedPC(pc);
		System.out.println("Clockcycle "+clockcycle + " instruction "+ inst + "fetched!");
    }
	
	public void decode() throws Exception{
		String inst = FD.getInstruction();
		String pc = FD.getIncrementedPC();
		DX.setIncrementedPC(pc);
		DX.setReadValue1(RF.ReadReg1(inst.substring(21,25)));
		DX.setReadValue2(RF.ReadReg2(inst.substring(20,16)));
		DX.setSignExtend(Se.extend(inst.substring(0, 15)));
		DX.setRt(inst.substring(11, 15));
		DX.setRd(inst.substring(16, 20));
		C.action(inst.substring(0, 5));
		//Ctrl Signals
		DX.setMemRead(C.MemRead);
		DX.setMemWrite(C.MemWrite);
		DX.setALUop(C.ALUOperation);
		DX.setBranch(C.branch);
		DX.setMemToReg(C.MemtoReg);
		DX.setALUsrc(C.ALUSrc);
		DX.setRegDestination(C.RegDest);
		DX.setRegWrite(C.RegWrite);
	}
	public void execute(String adderResult, String ALUResult, boolean zero, String m3Out, boolean b, boolean c, boolean d, boolean e, boolean f, String ReadValue){
		EM.setBranchAddress(adderResult);
		EM.setALUresult(ALUResult);
		EM.setRd(m3Out);
		EM.setZeroFlag(zero);
		EM.setValueToMem(ReadValue);
		EM.setMemTowrite(b);
		EM.setRegWrite(c);
		EM.setBranch(d);
		EM.setMemRead(e);
		EM.setMemWrite(f);
		
		
	}
	public void writeback(String DMResult, String memoryValue, boolean regWrite, boolean MemToReg, String Rd){
		MW.setAluResult(memoryValue);
		MW.setMemoryRead(DMResult);
		MW.setRd(Rd);
		MW.setMemToReg(MemToReg);
		MW.setRegWrite(regWrite);;
	}
	
	
	public void main(String[] args) throws Exception{
		
		if(clockcycle != 0){
		c = 0;
		M1.setInputs(adderResult, EM.branchAddress);
		if(PcSrc) c = 1;
		M1.select(c);
		//PC
		}
		fetch();
		RF.setRegWrite(MW.isRegWrite());
		if(MW.isMemToReg())c = 1;
		RF.WriteData(MW.rd,M4.select(c));
		
		
		decode();
		//Decode part over-- begining of the Execute
		String ShiftResult = SL.shiftLeft(DX.getSignExtend());
		adderResult = A2.add(DX.getIncrementedPC(), ShiftResult );
		String code = AC.decide(DX.getALUop(), DX.getSignExtend().substring(0, 5));
		M2.setInputs(DX.getReadValue2(),DX.getIncrementedPC());
		c = 0;
		if(DX.isALUsrc())c = 1;
		String M2Out = M2.select(c);
		String ALUResult = A.Activate(DX.ReadValue1, M2Out, code);
		c = 0;
		M3.setInputs(DX.getRt(), DX.getRd());
		if(DX.isRegDestination())c = 1;
		String M3Out = M3.select(c);
		
		
		execute(adderResult,ALUResult,A.isZero(),M3Out,DX.isRegWrite(),DX.isMemToReg(),DX.isBranch(),DX.isMemRead(),DX.isMemWrite(),DX.getReadValue2());
		///execute Done -- Start of write back
		DM.setMemRead(EM.isMemRead());
		DM.setMemWrite(EM.isMemWrite());
		String DMResult = DM.read(EM.ALUresult);
		DM.store(EM.getValueToMem());
		
		//Branch And
		if(EM.isBranch() == true && A.isZero() == true)PcSrc = true;
		
		
		writeback(DMResult,EM.getValueToMem(),EM.isRegWrite(),EM.isMemTowrite(),EM.getRd());
		//post MEMWB Register
		c = 0;
		M4.setInputs(MW.getMemoryRead(), MW.getAluResult());
		if(MW.isMemToReg()) c = 1;
		M4Result = M4.select(c);
		
		
		
		
	}
}
