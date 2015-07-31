/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author nikolalsvk
 *
 */
public class FurnitureCategories {
	HashMap<String, FurnitureCategory> furnitureCategories = new HashMap<String, FurnitureCategory>();

	public FurnitureCategories(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/beans_furniture_categories.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readFurnitureCategories(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	private void readFurnitureCategories(BufferedReader in) {
		String name = "";
		String description = "";
		String subCategory = "";

		String line;

		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					name = st.nextToken().trim();
					description = st.nextToken().trim();
					if (st.hasMoreTokens()) {
						subCategory = st.nextToken().trim();
					} else {
						subCategory = "";
					}

				}
				furnitureCategories.put(name, new FurnitureCategory(name,
						description, subCategory));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Collection<FurnitureCategory> getValues() {
		return furnitureCategories.values();
	}

	public FurnitureCategory getCategoryByName(String name) {
		return furnitureCategories.get(name);
	}

	public FurnitureCategory deleteCategory(String name) {
		return furnitureCategories.remove(name);
	}

	public void saveFurnitureCategories(String path) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(path
				+ "/beans_furniture_categories.txt"));

		Iterator<FurnitureCategory> it = furnitureCategories.values()
				.iterator();
		while (it.hasNext()) {
			FurnitureCategory cat = it.next();
			StringBuilder sb = new StringBuilder();
			sb.append(cat.getName());
			sb.append(";");
			sb.append(cat.getDescription());
			sb.append(";");
			sb.append(cat.getSubCategory());
			sb.append("\n");
			pw.write(sb.toString());
		}

		pw.close();
	}

	public FurnitureCategory addFurnitureCategory(FurnitureCategory cat) {
		return furnitureCategories.put(cat.getName(), cat);
	}

	public ArrayList<FurnitureCategory> getFurnitureCategoriesForSearch(
			String name, String opis) {
		ArrayList<FurnitureCategory> retVal = new ArrayList<FurnitureCategory>();
		
		Iterator<FurnitureCategory> it = furnitureCategories.values().iterator();
		while (it.hasNext()) {
			FurnitureCategory cat = it.next();
			if(cat.getName().toLowerCase().contains(name.toLowerCase()) &&
					cat.getDescription().toLowerCase().contains(opis.toLowerCase())) {
				retVal.add(cat);
			}
			
		}
		
		return retVal;
	}
}
