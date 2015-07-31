package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Receipts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Receipt> receipts;
	private ObjectInputStream objectInputStream;
	
	@SuppressWarnings("unchecked")
	public Receipts(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		objectInputStream = new ObjectInputStream(
		        new FileInputStream(path + "/beans_receipts.txt"));
		this.receipts = (ArrayList<Receipt>) objectInputStream.readObject();
//		this.receipts = new ArrayList<Receipt>();
	}

	public Receipts() {
		this.receipts = new ArrayList<Receipt>();
	}

	public ArrayList<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(ArrayList<Receipt> receipts) {
		this.receipts = receipts;
	};
	
	public void saveReceipts(String path) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path + "/beans_receipts.txt"));
		
		objectOutputStream.writeObject(receipts);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
	
	public boolean addReceipt(Receipt receipt) {
		return receipts.add(receipt);
	}
	
}
