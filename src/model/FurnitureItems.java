/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author nikolalsvk
 *
 */
public class FurnitureItems {
	private HashMap<String, FurnitureItem> furnitureItems = new HashMap<String, FurnitureItem>();
	private Boolean saved = true;

	@SuppressWarnings("unchecked")
	public FurnitureItems(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
//		BufferedReader in = null;
//		try {
//			File file = new File(path + "/beans_furniture_items.txt");
//			System.out.println(file.getCanonicalPath());
//			in = new BufferedReader(new FileReader(file));
//			readFurnitureItems(in);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (Exception e) {
//				}
//			}
//		}
		
		ObjectInputStream objectInputStream = new ObjectInputStream(
		        new FileInputStream(path + "/beans_furniture_items.txt"));
		this.furnitureItems = (HashMap<String, FurnitureItem>) objectInputStream.readObject();
		
		objectInputStream.close();
	}

	@SuppressWarnings("unused")
	private void readFurnitureItems(BufferedReader in) {
		String id = "";
		String name = "";
		String color = "";
		String originCountry = "";
		String manufacturerName = "";
		Double price = -1.0;
		Integer inStock = -1;
		String category = "";
		Integer yearOfProduction = -1;
		String salon = "";
		String image = null;

		String line;

		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = st.nextToken().trim();
					name = st.nextToken().trim();
					color = st.nextToken().trim();
					originCountry = st.nextToken().trim();
					manufacturerName = st.nextToken().trim();
					price = Double.parseDouble(st.nextToken().trim());
					inStock = Integer.parseInt(st.nextToken().trim());
					category = st.nextToken().trim();
					yearOfProduction = Integer.parseInt(st.nextToken().trim());
					salon = st.nextToken().trim();
				}
				furnitureItems.put(id, new FurnitureItem(id, name, color,
						originCountry, manufacturerName, price, inStock,
						category, yearOfProduction, salon, image));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Collection<FurnitureItem> getValues() {
		return furnitureItems.values();
	}

	public ArrayList<FurnitureItem> getFurnitureItemsFromSalon(String name,
			String color, String originCountry, String manufacturerName,
			String price, String inStock, String yearOfProduction,
			String salonName, String category) {
		ArrayList<FurnitureItem> retVal = new ArrayList<FurnitureItem>();
		
		Double priceLower = 0.0;
		Double priceHigher = Double.MAX_VALUE;
		if (!price.equals("")) {
			priceLower = Double.parseDouble(price.split("-")[0]);
			priceHigher = Double.parseDouble(price.split("-")[1]);
		}

		Iterator<FurnitureItem> it = furnitureItems.values().iterator();
		while (it.hasNext()) {
			FurnitureItem item = it.next();
			if (item.getSalon().contains(salonName)
					&& item.getCategory().toLowerCase()
							.contains(category.toLowerCase())
					&& item.getName().toLowerCase().contains(name)
					&& item.getColor().toLowerCase().contains(color)
					&& item.getOriginCountry().toLowerCase()
							.contains(originCountry)
					&& item.getManufacturerName().toLowerCase()
							.contains(manufacturerName)
					&& (item.getPrice() >= priceLower && item.getPrice() <= priceHigher)
					&& (item.getInStock().toString()).toLowerCase().contains(
							inStock)
					&& (item.getYearOfProduction().toString()).toLowerCase()
							.contains(yearOfProduction)) {
				retVal.add(item);
			}
		}

		return retVal;
	}

	public FurnitureItem getFurnitureItem(String id) {
		return furnitureItems.get(id);
	}

	public FurnitureItem addFurnitureItem(FurnitureItem item) {
		return furnitureItems.put(item.getId(), item);
	}

	public void saveFurnitureItems(String path) throws IOException {
//		PrintWriter pw = new PrintWriter(new FileWriter(path
//				+ "/beans_furniture_items.txt"));
//
//		Iterator<FurnitureItem> it = furnitureItems.values().iterator();
//		while (it.hasNext()) {
//			FurnitureItem item = it.next();
//			StringBuilder sb = new StringBuilder();
//			sb.append(item.getId());
//			sb.append(";");
//			sb.append(item.getName());
//			sb.append(";");
//			sb.append(item.getColor());
//			sb.append(";");
//			sb.append(item.getOriginCountry());
//			sb.append(";");
//			sb.append(item.getManufacturerName());
//			sb.append(";");
//			sb.append(item.getPrice());
//			sb.append(";");
//			sb.append(item.getInStock());
//			sb.append(";");
//			sb.append(item.getCategory());
//			sb.append(";");
//			sb.append(item.getYearOfProduction());
//			sb.append(";");
//			sb.append(item.getSalon());
//			sb.append(";");
//			sb.append(item.getDiscount());
//			sb.append(";");
//			sb.append(item.getDiscountedPrice());
//			sb.append(";");
//			sb.append(item.isDiscounted());
//			sb.append("\n");
//			pw.write(sb.toString());
//		}
//
//		pw.close();
//		saved = true;
//		return saved;
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path + "/beans_furniture_items.txt"));
		
		objectOutputStream.writeObject(furnitureItems);
		objectOutputStream.flush();
		objectOutputStream.close();
	}

	public Boolean getSaved() {
		return saved;
	}

	public void setSaved(Boolean saved) {
		this.saved = saved;
	}

	public FurnitureItem deleteFurnitureItem(String id) {
		return furnitureItems.remove(id);
	}
}
