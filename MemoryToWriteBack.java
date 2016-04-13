
public class MemoryToWriteBack {
	
	//boolean memRead , memWrite , MemToReg;
	
	String aluResult;      //32 bits
	String memoryRead;     //32 bits 
	String rd;             //5 bits
	String writebackControlSignals; //2 bits
	
	public String getAluResult() {
		return aluResult;
	}
	public void setAluResult(String aluResult) {
		this.aluResult = aluResult;
	}
	public String getMemoryRead() {
		return memoryRead;
	}
	public void setMemoryRead(String memoryRead) {
		this.memoryRead = memoryRead;
	}
	public String getRd() {
		return rd;
	}
	public void setRd(String rd) {
		this.rd = rd;
	}
	public String getControlSignals() {
		return writebackControlSignals;
	}
	public void setControlSignals(String controlSignals) {
		this.writebackControlSignals = controlSignals;
	}
	
}
