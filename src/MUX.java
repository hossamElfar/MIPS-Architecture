
public class MUX {
	
	String input1 , input2;
	
	public void setInputs(String in1 , String in2){
		input1 = in1;
		input2 = in2;
	}
	
	public String select(int selector){
		if(selector == 0){
			return input1;
		}
		else{
			return input2;
		}
	}
	
}
