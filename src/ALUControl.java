
public class ALUControl {
	
	public String decide(String ALUOp, String function){
		
		
		if(ALUOp.equals("00"))
			 return "0010"; 
		
		else if(ALUOp.equals("01"))
			 return "0110";
		else if(ALUOp.equals("10")){
			
				if(function.equals("100000"))
					return "0010";
				if(function.equals("100010"))
					return "0110";
				if(function.equals("100100"))
					return "0000";
				if(function.equals("100101"))
					return "0001";
				if(function.equals("101010"))
					return "0111";
		  }
		return "Unmatched Function";
	}
	
}


