
public class RegisterFile {
	int Registers[];
	boolean RegWrite;
	
	
	
	public RegisterFile(){
		this.Registers = new int[32];
		Registers[0] = 0;
	}
	public int ReadReg1(String index){
		return Registers[Integer.parseInt(index,2)];
	}
	public int ReadReg2(String index){
		return Registers[Integer.parseInt(index,2)];
	}
	public void WriteData(String index , int value){
		if(Integer.parseInt(index,2) == 0 || !RegWrite)return;
		Registers[Integer.parseInt(index,2)] = value;
		
	}
	
	
	
	
}
