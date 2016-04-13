
public class IDEX {
//32-Bit registers
String IncrementedPC;
String ReadValue1;
String ReadValue2;
String SignExtend;
//5-bits fields
String Rd;
String Rt;
//EX
boolean ALUsrc;
boolean RegDestination;
String ALUop;
//WB
boolean RegWrite;
boolean MemToReg;
//MEM
boolean MemRead;
boolean MemWrite;
boolean branch;



//Getters & Setters
	public String getIncrementedPC() {
	return IncrementedPC;
}

public void setIncrementedPC(String incrementedPC) {
	IncrementedPC = incrementedPC;
}

public String getReadValue1() {
	return ReadValue1;
}

public void setReadValue1(String readValue1) {
	ReadValue1 = readValue1;
}

public String getReadValue2() {
	return ReadValue2;
}

public void setReadValue2(String readValue2) {
	ReadValue2 = readValue2;
}

public String getSignExtend() {
	return SignExtend;
}

public void setSignExtend(String signExtend) {
	SignExtend = signExtend;
}

public String getRd() {
	return Rd;
}

public void setRd(String rd) {
	Rd = rd;
}

public String getRt() {
	return Rt;
}

public void setRt(String rt) {
	Rt = rt;
}

public boolean isALUsrc() {
	return ALUsrc;
}

public void setALUsrc(boolean aLUsrc) {
	ALUsrc = aLUsrc;
}

public boolean isRegDestination() {
	return RegDestination;
}

public void setRegDestination(boolean regDestination) {
	RegDestination = regDestination;
}

public String getALUop() {
	return ALUop;
}

public void setALUop(String aLUop) {
	ALUop = aLUop;
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

public boolean isMemRead() {
	return MemRead;
}

public void setMemRead(boolean memRead) {
	MemRead = memRead;
}

public boolean isMemWrite() {
	return MemWrite;
}

public void setMemWrite(boolean memWrite) {
	MemWrite = memWrite;
}

public boolean isBranch() {
	return branch;
}

public void setBranch(boolean branch) {
	this.branch = branch;
}

}
