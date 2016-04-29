
public class ALUControl {
	
	public String decide(String ALUOp, String function){
		
		//LW SW
		if(ALUOp.equals("00"))
			 return "0010"; 
		//BEQ
		else if(ALUOp.equals("01"))
			 return "0110";
		else if(ALUOp.equals("10")){
			//ADD
				if(function.equals("100000"))
					return "0010";
			//Subtract
				if(function.equals("100010"))
					return "0110";
			//AND
				if(function.equals("100100"))
					return "0000";
			//OR
				if(function.equals("100101"))
					return "0001";
			//SLT
				if(function.equals("101010"))
					return "0111";
		  }
		return "Unmatched Function";
	}
	
}


