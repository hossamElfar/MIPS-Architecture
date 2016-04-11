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
	String adress;
	String data;

	public DataMemory(boolean memWrite,
			boolean memRead, String adress, String data) {
		super();
		this.memory = new Hashtable<String, String>();
		MemWrite = memWrite;
		MemRead = memRead;
		this.adress = adress;
		this.data = data;
	}
	public DataMemory() {
		super();
		this.memory = new Hashtable<String, String>();
		MemWrite = false;
		MemRead = false;
		this.adress = "";
		this.data = "";
	}

	public Hashtable<String, String> getMemory() {
		return memory;
	}

	public void store() {
		memory.put(adress, data);
	}

	public void store(String add) {
		memory.put(add, data);
	}

	public String read() {
		return memory.get(adress);
	}

	public String read(String add) {
		return memory.get(add);
	}

	public void save() throws IOException {
		String fileLocation = "memory.ser"; // To be Modified
		FileOutputStream fileOut = new FileOutputStream(fileLocation);
		ObjectOutputStream os = new ObjectOutputStream(fileOut);
		os.writeObject(this);
	}

	public void setMemory(Hashtable<String, String> memory) {
		this.memory = memory;
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

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
