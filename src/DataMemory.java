import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;

public class DataMemory implements Serializable {
	Hashtable<String, String> memory;
	boolean MemWrite;
	boolean MemRead;



	public DataMemory() {

		this.memory = new Hashtable<String, String>();
		for(int i=0;i<32;i++){
			String a = Integer.toBinaryString(i);
			memory.put(a, "00000000000000000000000000000000");
		}
		MemWrite = false;
		MemRead = false;
	}


	public String memory(String add,String data) {
		if(MemRead)
		return memory.get(add);
		else {memory.put(add,data); return null;}
	}



	public boolean isMemWrite() {
		return MemWrite;
	}

	public void setMemWrite(boolean memWrite) {
		MemWrite = memWrite;
	}

	public boolean isMemRead() {
		return MemRead;
	}

	public void setMemRead(boolean memRead) {
		MemRead = memRead;
	}


}
