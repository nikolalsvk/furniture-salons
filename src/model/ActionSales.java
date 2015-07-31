package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ActionSales {
	ArrayList<ActionSale> actionSales;
	private ObjectInputStream objectInputStream;
	
	public ActionSales() {
		actionSales = new ArrayList<ActionSale>();
	}
	
	@SuppressWarnings("unchecked")
	public ActionSales(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		objectInputStream = new ObjectInputStream(
		        new FileInputStream(path + "/beans_sales.txt"));
		this.actionSales = (ArrayList<ActionSale>) objectInputStream.readObject();
	}
	
	public void saveActionSales(String path) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path + "/beans_sales.txt"));
		
		objectOutputStream.writeObject(actionSales);
		objectOutputStream.flush();
		objectOutputStream.close();
	}

	public ArrayList<ActionSale> getActionSales() {
		return actionSales;
	}

	public void setActionSales(ArrayList<ActionSale> actionSales) {
		this.actionSales = actionSales;
	}
	
	public boolean addActionSale(ActionSale sale) {
		return actionSales.add(sale);
	}
}
