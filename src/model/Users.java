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
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author nikolalsvk
 *
 */
public class Users {
	private HashMap<String, User> users = new HashMap<String, User>();

	public Users(String path) {
		BufferedReader in = null;
		try {
			File file = new File(path + "/beans_users.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readUsers(in);
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

	private void readUsers(BufferedReader in) {
		// TODO Auto-generated method stub
		String line;
		String username = "";
		String password = "";
		String name = "";
		String lastName = "";
		String role = "";
		String phoneNumber = "";
		String email = "";

		StringTokenizer st;
		try {
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					username = st.nextToken().trim();
					password = st.nextToken().trim();
					name = st.nextToken().trim();
					lastName = st.nextToken().trim();
					role = st.nextToken().trim();
					phoneNumber = st.nextToken().trim();
					email = st.nextToken().trim();
				}
				users.put(username, new User(username, password, name,
						lastName, role, phoneNumber, email));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public User getUser(String username) {
		return users.get(username);
	}

	public void saveUsers(String path) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(path
				+ "/beans_users.txt"));

		Iterator<User> it = users.values().iterator();
		while (it.hasNext()) {
			User user = it.next();
			StringBuilder sb = new StringBuilder();
			sb.append(user.getUsername());
			sb.append(";");
			sb.append(user.getPassword());
			sb.append(";");
			sb.append(user.getName());
			sb.append(";");
			sb.append(user.getLastName());
			sb.append(";");
			sb.append(user.getRole());
			sb.append(";");
			sb.append(user.getPhoneNumber());
			sb.append(";");
			sb.append(user.getEmail());
			sb.append("\n");
			pw.write(sb.toString());
		}

		pw.close();
	}
	
	public User addUser(User user) {
		return users.put(user.getUsername(), user);
	}
}
