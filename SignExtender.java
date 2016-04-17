public class SignExtender {
	String input;
	String output;
	public SignExtender(){
		
	}
	public String extend(String Data){
		if (Data.length()==32){
			return Data;
		}
		else if(Data.length()>32){
			throw new Exception ("Input bigger than 32 bits!");
		}
		int temp=Integer.parseInt(Data,2);
		if(temp<0){
		//if negative then extend rest with 1's
			while(Data.length()!=32){
				Data = 1+Data;
			}
		}
		else while(Data.length()!=32){
			 Data = 0+Data;
		}
		return Data;
	}
	
}
