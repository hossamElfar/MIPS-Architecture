import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InstructionMemory {
	
	private ArrayList<String> instructions;
	private int nextIndex;
	
	public InstructionMemory(String fileName) throws IOException{
		instructions = new ArrayList<String>();
		nextIndex = 0;
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
			instructions.add(line);
		}
		br.close();
	}
	
	public String fetch(){
		if(instructions.size() == 0){
			return null;
		}
		return instructions.get(nextIndex++);
	}
	
	public String toString(){
		return instructions.toString();
	}
	
	public static void main(String[] args) throws IOException {
		InstructionMemory memory = new InstructionMemory("inst.txt");
		System.out.println(memory.fetch());
		System.out.println(memory.fetch());
	}
	
}
