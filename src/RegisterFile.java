
public class RegisterFile {
	String Registers[];
	boolean RegWrite;
	
	
	
	public boolean isRegWrite() {
		return RegWrite;
	}
	public void setRegWrite(boolean regWrite) {
		RegWrite = regWrite;
	}
	public RegisterFile(){
		this.Registers = new String[32];
		Registers[0] = "0";
	}
	public String ReadReg1(String index){
		if(Registers[Integer.parseInt(index,2)] == null)return "00000000000000000000000000000000";
		return Registers[Integer.parseInt(index,2)];
	}
	public String ReadReg2(String index){
		if(Registers[Integer.parseInt(index,2)] == null)return "00000000000000000000000000000000";
		return Registers[Integer.parseInt(index,2)];
	}
	public void WriteData(String index , String value){
		if(Integer.parseInt(index,2) == 0 || !RegWrite)return;
		Registers[Integer.parseInt(index,2)] = value;
		
	}
	
	
	
	
}
