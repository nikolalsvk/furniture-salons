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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author nikolalsvk
 *
 */
public class AdditionalServices {
	private HashMap<String, AdditionalService> addServices = new HashMap<String, AdditionalService>();

	public AdditionalServices(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/beans_additional_services.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readAddServices(in);
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

	private void readAddServices(BufferedReader in) {
		// TODO Auto-generated method stub
		String line, name = "", opis = "";
		Double price = -1.0;
		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					name = st.nextToken().trim();
					opis = st.nextToken().trim();
					price = Double.parseDouble(st.nextToken().trim());
				}
				addServices.put(name, new AdditionalService(name, opis, price));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 
	 * @return All additional services
	 */
	public Collection<AdditionalService> getValues() {
		return addServices.values();
	}

	/**
	 * 
	 * @param name
	 *            Unique ID for additional service
	 * @return Additional service that has "name"
	 */
	public AdditionalService getAddService(String name) {
		return addServices.get(name);
	}

	public AdditionalService addAdditionalService(AdditionalService serv) {
		return addServices.put(serv.getName(), serv);
	}

	public void saveFurnitureCategories(String path) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(path
				+ "/beans_additional_services.txt"));

		Iterator<AdditionalService> it = addServices.values().iterator();
		while (it.hasNext()) {
			AdditionalService serv = it.next();
			StringBuilder sb = new StringBuilder();
			sb.append(serv.getName());
			sb.append(";");
			sb.append(serv.getDescription());
			sb.append(";");
			sb.append(serv.getPrice());
			sb.append("\n");
			pw.write(sb.toString());
		}

		pw.close();
	}

	public AdditionalService deleteAdditionalService(String name) {
		return addServices.remove(name);
	}
}
