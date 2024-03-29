
public class ALU {
//	private String input1; //not sure if needed since could use input from method
//	private String input2;
	boolean zero; 
	private int carry; // used carry in add , subtract , shiftLeft and I also truncated rest
	private String output;
	
	
	public String Activate(String Input1 , String Input2 , String Code) throws Exception{
		switch (Code){
		case "0000" : output = And(Input1,Input2) ;break;
		case "0001" : output = Or(Input1,Input2);break;
		case "0010" : output = add(Input1,Input2);System.out.println("HERE3");break;
		case "0110" : output = subtract(Input1,Input2);break;
		case "0111" : output = compare(Input1,Input2);break;
		}
		return output;
	}
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
	public String compare (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1-temp2;
		int comp;
		comp= (res==0)?1:(res>0)?0:-1; //if equals -> 1 , bigger -> 0 , smaller -> -1 
		if(comp == 1){ zero = true;return "1";}
		else {zero = false;return "0";}
	}
	public String add (String input1, String input2) throws Exception{
		if(input1.length()>32|| input2.length()>32) throw new Exception("Over flow input! bigger than 32 bits!");
		int temp1=Integer.parseInt(input1,2);
		int temp2=Integer.parseInt(input2,2);
		int res= temp1+temp2; //integer division only ?
		output= Integer.toBinaryString(res); 
		System.out.println(output);
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
	
	public String Or (String input1,String input2){
		String output = "00000000000000000000000000000000";
		for(int i = 0; i < input2.length() ; i++){
			if(input1.charAt(i) == '1' || input2.charAt(i) == '1'){
				output.replace(output.charAt(i), '1');
			}
		}
		return output;
	}
	public String And (String input1,String input2){
		String output = "00000000000000000000000000000000";
		for(int i = 0; i < input2.length() ; i++){
			if(input1.charAt(i) == '1' && input2.charAt(i) == '1'){
				output.replace(output.charAt(i), '1');
			}
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
	public boolean isZero() {
		return zero;
	}
	public void setZero(boolean zero) {
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

