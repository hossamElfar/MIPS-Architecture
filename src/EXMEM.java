
public class EXMEM {
String branchAddress;
boolean zeroFlag;
String ALUresult;
String ValueToMem;
String Rd;
//WB
boolean MemTowrite;
boolean RegWrite;
//MEM
boolean MemRead;
boolean MemWrite;
boolean branch;
	
public String getBranchAddress() {
	return branchAddress;
}
public void setBranchAddress(String branchAddress) {
	this.branchAddress = branchAddress;
}
public boolean isZeroFlag() {
	return zeroFlag;
}
public void setZeroFlag(boolean zeroFlag) {
	this.zeroFlag = zeroFlag;
}
public String getALUresult() {
	return ALUresult;
}
public void setALUresult(String aLUresult) {
	ALUresult = aLUresult;
}
public String getValueToMem() {
	return ValueToMem;
}
public void setValueToMem(String valueToMem) {
	ValueToMem = valueToMem;
}
public String getRd() {
	return Rd;
}
public void setRd(String rd) {
	Rd = rd;
}
public boolean isMemTowrite() {
	return MemTowrite;
}
public void setMemTowrite(boolean memTowrite) {
	MemTowrite = memTowrite;
}
public boolean isRegWrite() {
	return RegWrite;
}
public void setRegWrite(boolean regWrie) {
	RegWrite = regWrie;
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
