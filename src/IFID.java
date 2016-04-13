
public class IFID {

	private String instruction;
	private String incrementedPC; //PC+4
	
	
	
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getIncrementedPC() {
		return incrementedPC;
	}
	public void setIncrementedPC(String nextInstruction) {
		this.incrementedPC = nextInstruction;
	}
	
	
	
	
}
