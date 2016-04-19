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
			instructions.add(line.substring(0,4));
			instructions.add(line.substring(4,8));
			instructions.add(line.substring(8,12));
			instructions.add(line.substring(12,16));
		}
		br.close();
	}
	
	public String fetch(int pc){
		if(instructions.size() == 0){
			return null;
		}
		return instructions.get(pc) + instructions.get(pc+1) + instructions.get(pc+2) + instructions.get(pc+3);
	}
	
	public String toString(){
		return instructions.toString();
	}
	
	public static void main(String[] args) throws IOException {
		InstructionMemory memory = new InstructionMemory("inst.txt");
		System.out.println(memory.fetch(0));
		System.out.println(memory.fetch(1));
	}
	
}