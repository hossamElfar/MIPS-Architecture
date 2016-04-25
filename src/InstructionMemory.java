import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InstructionMemory {
	
	ArrayList<String> instructions;
	
	public InstructionMemory(String fileName) throws IOException{
		instructions = new ArrayList<String>();
		readTextFile(fileName);
	}
	
	public void readTextFile(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while(true){
			line = br.readLine();
			if(line == null){
				break;
			}
			instructions.add(line.substring(0,8));
			instructions.add(line.substring(8,16));
			instructions.add(line.substring(16,24));
			instructions.add(line.substring(24,32));
		}
		br.close();
	}
	
	public String fetch(int pc){
		if(instructions.size() == 0){
			return null;
		}
		if(pc == instructions.size())return null;
		return instructions.get(pc) + instructions.get(pc+1) + instructions.get(pc+2) + instructions.get(pc+3);
	}
	
	public String toString(){
		return instructions.toString();
	}
	
	
}