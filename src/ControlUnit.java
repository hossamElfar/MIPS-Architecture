import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ControlUnit implements Serializable{

	boolean PCSrc;
	boolean ALUSrc;
	String ALUOperation;
	boolean MemWrite;
	boolean MemtoReg;
	boolean MemRead;
	boolean RegWrite;

	public ControlUnit() {
		this.PCSrc = false;
		this.ALUSrc = false;
		this.ALUOperation = "00";
		this.MemWrite = false;
		this.MemtoReg = false;
		this.MemRead = false;
		this.RegWrite = false;
	}

	public boolean isPCSrc() {
		return PCSrc;
	}

	public void setPCSrc(boolean pCSrc) {
		PCSrc = pCSrc;
	}

	public boolean isALUSrc() {
		return ALUSrc;
	}
	public void save() throws IOException {
		String fileLocation = "CU.ser"; // To be Modified
		FileOutputStream fileOut = new FileOutputStream(fileLocation);
		ObjectOutputStream os = new ObjectOutputStream(fileOut);
		os.writeObject(this);
	}

	public void setALUSrc(boolean aLUSrc) {
		ALUSrc = aLUSrc;
	}

	public String getALUOperation() {
		return ALUOperation;
	}

	public void setALUOperation(String aLUOperation) {
		ALUOperation = aLUOperation;
	}

	public boolean isMemWrite() {
		return MemWrite;
	}

	public void setMemWrite(boolean memWrite) {
		MemWrite = memWrite;
	}

	public boolean isMemtoReg() {
		return MemtoReg;
	}

	public void setMemtoReg(boolean memtoReg) {
		MemtoReg = memtoReg;
	}

	public boolean isMemRead() {
		return MemRead;
	}

	public void setMemRead(boolean memRead) {
		MemRead = memRead;
	}

	public boolean isRegWrite() {
		return RegWrite;
	}

	public void setRegWrite(boolean regWrite) {
		RegWrite = regWrite;
	}

}
