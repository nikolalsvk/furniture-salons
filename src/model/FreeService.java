package model;

public class FreeService {
	private String name;
	private String opis;

	public FreeService() {

	}

	/**
	 * @param name
	 * @param opis
	 */
	public FreeService(String name, String opis) {
		super();
		this.name = name;
		this.opis = opis;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the opis
	 */
	public String getOpis() {
		return opis;
	}

	/**
	 * @param opis
	 *            the opis to set
	 */
	public void setOpis(String opis) {
		this.opis = opis;
	}
}
