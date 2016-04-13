import java.io.IOException;


public class Datapath {
	
	String pc;
	Adder A1 , A2;
	MUX M1 , M2 , M3;
	InstructionMemory IM;
	IFID FD;
	RegisterFile RF;
	IDEX DX;
	
	public Datapath() throws IOException{
		pc = "00000000000000000000000000000000";
		IM = new InstructionMemory("inst.txt");
		A1 = new Adder();
		FD = new IFID();
		RF = new RegisterFile();
		DX = new IDEX();
	}
	
	public void fetch(){	
		
		int index = Integer.parseInt(pc,2);
		String inst = IM.fetch(index);	
		
		String in1 = Integer.toBinaryString(4);
		pc = (A1.add(in1, pc));
		FD.setInstruction(inst);
		FD.setIncrementedPC(pc);
    }
	
	public void decode(){
		
		String inst = FD.getInstruction();
		
		
		
	}
	
}
