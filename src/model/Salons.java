/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author nikolalsvk
 *
 */
public class Salons {
	private HashMap<String, Salon> salons = new HashMap<String, Salon>();

	public Salons(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/beans_salons.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readSalons(in);
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

	private void readSalons(BufferedReader in) {
		String line;
		String salonName = "";
		String address = "";
		String phoneNumber = "";
		String email = "";
		String webAddress = "";
		String pib = "";
		String companyNumber = "";
		String accountNumber = "";

		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					salonName = st.nextToken().trim();
					address = st.nextToken().trim();
					phoneNumber = st.nextToken().trim();
					email = st.nextToken().trim();
					webAddress = st.nextToken().trim();
					pib = st.nextToken().trim();
					companyNumber = st.nextToken().trim();
					accountNumber = st.nextToken().trim();
				}
				salons.put(salonName, new Salon(salonName, address,
						phoneNumber, email, webAddress, pib, companyNumber,
						accountNumber));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Collection<Salon> getValues() {
		return salons.values();
	}

	public Salon getSalon(String salonName) {
		return salons.get(salonName);
	}
}
