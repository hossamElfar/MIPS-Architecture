
public class ShiftLeft2 {
	private String Data;
	
	public ShiftLeft2(){
		
	}
	public ShiftLeft2(String Data ,Boolean shift){
		if(shift){
		int temp=Integer.parseInt(Data,2);
		temp = temp*2;
		this.Data=Integer.toBinaryString(temp);
		}
		else this.Data=Data;
	}
	public String shiftLeft(String Data){
		int temp=Integer.parseInt(Data,2);
		temp = temp*2;
		return Integer.toBinaryString(temp);
	}
	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

}
