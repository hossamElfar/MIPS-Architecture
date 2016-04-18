import java.io.IOException;


public class Datapath {
	
	String pc;
	Adder A1 , A2;
	MUX M1 , M2 , M3 , M4;
	InstructionMemory IM;
	IFID FD;
	ALUControl AC;
	ALU A;
	RegisterFile RF;
	IDEX DX;
	ShiftLeft2 SL;
	SignExtender Se;
	MEMWB MW;
	EXMEM EM;
	int clockcycle;
	
	public Datapath() throws IOException{
		pc = "00000000000000000000000000000000";
		IM = new InstructionMemory("inst.txt");
		A = new ALU();
		A1 = new Adder();
		AC = new ALUControl();
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
		
		//Ctrl Signals
		
		
		
		
		
		
	}
	public void execute(String adderResult, String ALUResult, int i, String m3Out, boolean b, boolean c, boolean d, boolean e, boolean f, String ReadValue){
		EM.setBranchAddress(adderResult);
		EM.setALUresult(ALUResult);
		EM.setRd(m3Out);
		
		boolean zero = false;
		if(i == 1) zero = true;
		EM.setZeroFlag(zero);
		
		EM.setValueToMem(ReadValue);
		EM.setMemTowrite(b);
		EM.setRegWrite(c);
		EM.setBranch(d);
		EM.setMemRead(e);
		EM.setMemWrite(f);
		
		
	}
	public void writeback(){
		
	}
	
	
	public void main(String[] args) throws Exception{
		int c = 0;
		fetch();
		RF.setRegWrite(MW.isRegWrite());
		if(MW.isMemToReg())c = 1;
		RF.WriteData(MW.rd,M4.select(c));
		
		decode();
		//Decode part over-- begining of the Execute
		
		
		String ShiftResult = SL.shiftLeft(DX.getSignExtend());
		String adderResult = A2.add(DX.getIncrementedPC(), ShiftResult );
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
		
		execute(adderResult,ALUResult,A.getZero(),M3Out,DX.isRegWrite(),DX.isMemToReg(),DX.isBranch(),DX.isMemRead(),DX.isMemWrite(),DX.getReadValue2());
		///execute Done -- Start of write back
		
		
		
		
		
		
	}
}
