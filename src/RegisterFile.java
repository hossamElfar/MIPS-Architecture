
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
		for(int i = 1; i < 32 ;i++ ){
			Registers[i] = "00000000000000000000000000000000";
		}
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
		System.out.println("HERE!");
		if(Integer.parseInt(index,2) == 0 || !RegWrite)return;
		System.out.println(index);
		System.out.println(value);
		
		Registers[Integer.parseInt(index,2)] = value;
		
	}
	
	
	
	
}
