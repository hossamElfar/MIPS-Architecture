
public class ALU {
//	private String input1; //not sure if needed since could use input from method
//	private String input2;
	private int zero; 
	private int carry; // used carry in add , subtract , shiftLeft and I also truncated rest
	private String output;
	
//	public ALU(){
//		
//	}

	public String shiftLeft(String Data) throws Exception{
		if(Data.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp=Integer.parseInt(Data,2);
		temp = temp*2;
		this.output= Integer.toBinaryString(temp);
		while(output.length()>32){
			if(output.length()==33){
				carry= Integer.parseInt(output.charAt(0)+"");
			}
			output=output.substring(1); //cuz big endien
		}
		return this.output;
	}
	public String shiftRight(String Data) throws Exception{
		if(Data.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp=Integer.parseInt(Data,2);
		temp = temp/2;
		this.output= Integer.toBinaryString(temp);
		while(output.length()>32){
			output=output.substring(0,31); //cuz big endien and 31 cuz zero based
		}
		return this.output;
	}
	public String divide (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1/temp2; //integer division only ?
		output= Integer.toBinaryString(res); 
//		while(output.length()>32){ //truncate ?
//			output=output.substring(1); //cuz big endien
//		}
		return output;
	}
	public String multiply (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1*temp2; //integer division only ?
		output= Integer.toBinaryString(res); 
//		while(output.length()>32){ //truncate ?
//		output=output.substring(1); //cuz big endien
//	}
		return output;
	}
	public int compare (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1-temp2;
		zero= (res==0)?1:(res>0)?0:-1; //if equals -> 1 , bigger -> 0 , smaller -> -1 
		return zero;
	}
	public String add (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1+temp2; //integer division only ?
		output= Integer.toBinaryString(res); 
		while(output.length()>32){ //truncate ?
		if(output.length()==33){
			carry= Integer.parseInt(output.charAt(0)+"");
		}
			output=output.substring(1); //cuz big endien
	}
		return output;
	}
	public String subtract (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1-temp2; //integer division only ?
		output= Integer.toBinaryString(res); 
		while(output.length()>32){ //truncate ?
		if(output.length()==33){
			carry= Integer.parseInt(output.charAt(0)+"");
		}
			output=output.substring(1); //cuz big endien
	}
		return output;
	}
//	public String getInput1() {
//		return input1;
//	}
//	public void setInput1(String input1) {
//		this.input1 = input1;
//	}
//	public String getInput2() {
//		return input2;
//	}
//	public void setInput2(String input2) {
//		this.input2 = input2;
//	}
	public int getZero() {
		return zero;
	}
	public void setZero(int zero) {
		this.zero = zero;
	}
	public int getCarry() {
		return carry;
	}
	public void setCarry(int carry) {
		this.carry = carry;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
}
