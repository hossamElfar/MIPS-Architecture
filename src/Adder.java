
public class Adder {

	
public String add(String in1 , String in2){
	int n1 = Integer.parseInt(in1, 2) , n2 = Integer.parseInt(in2, 2);
	String padding = "";
	int size = n1 + n2;
	for(int i = size ; i <= 32 ;i++ ){
		padding = "0" + padding;
		
		}
	padding = padding + Integer.toBinaryString(size);
	return padding;
}

}
