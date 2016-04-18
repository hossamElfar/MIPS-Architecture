
public class MEMWB {
	
	//boolean memRead , memWrite , MemToReg;
	
	String aluResult;      //32 bits
	String memoryRead;     //32 bits 
	String rd;             //5 bits
	boolean RegWrite;
	boolean MemToReg;
	
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
	public boolean isRegWrite() {
		return RegWrite;
	}
	public void setRegWrite(boolean regWrite) {
		RegWrite = regWrite;
	}
	public boolean isMemToReg() {
		return MemToReg;
	}
	public void setMemToReg(boolean memToReg) {
		MemToReg = memToReg;
	}
	
}