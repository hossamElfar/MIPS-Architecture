
public class Adder {

	
public String add(String in1 , String in2){
	int n1 = Integer.parseInt(in1, 2) , n2 = Integer.parseInt(in2, 2);
	return Integer.toBinaryString(n1+n2);
}

}
