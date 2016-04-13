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
	boolean RegDest;
	boolean branch ;

	public ControlUnit() {
		this.PCSrc = false;
		this.ALUSrc = false;
		this.ALUOperation = "00";
		this.MemWrite = false;
		this.MemtoReg = false;
		this.MemRead = false;
		this.RegWrite = false;
		this.RegDest=false;
		this.branch=false;
	}

	public boolean isPCSrc() {
		return PCSrc;
	}

	public void setPCSrc(boolean pCSrc) {
		PCSrc = pCSrc;
	}
	public void action(){
		switch(this.ALUOperation){
		case "00": //TO be focused on 
			this.PCSrc = false;
			this.ALUSrc = false;
			this.MemWrite = false;
			this.MemtoReg = false;
			this.MemRead = false;
			this.RegWrite = false;
			this.RegDest=false;
			this.branch=false;
			;break;
		case "01":     
			this.PCSrc = false;
			this.ALUSrc = false;
			this.MemWrite = false;
			this.MemtoReg = false;
			this.MemRead = false;
			this.RegWrite = false;
			this.RegDest=false;
			this.branch=true;

			;break;
		case "10":     
			this.PCSrc = false;
			this.ALUSrc = false;
			this.MemWrite = false;
			this.MemtoReg = false;
			this.MemRead = false;
			this.RegWrite = true;
			this.RegDest=true;
			this.branch=false;
			;break;
		
		}
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
