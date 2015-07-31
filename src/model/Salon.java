/**
 * 
 */
package model;

import java.util.HashMap;

/**
 * @author nikolalsvk
 *
 */
public class Salon {
	private String salonName;
	private String address;
	private String phoneNumber;
	private String email;
	private String webAddress;
	private String pib;
	private String companyNumber;
	private String accountNumber;
	private HashMap<String, FurnitureItem> furnitureItems = new HashMap<String, FurnitureItem>();

	public Salon() {

	}

	/**
	 * @param salonName
	 * @param address
	 * @param phoneNumber
	 * @param email
	 * @param webAddress
	 * @param pib
	 * @param companyNumber
	 * @param accountNumber
	 */
	public Salon(String salonName, String address, String phoneNumber,
			String email, String webAddress, String pib,
			String companyNumber, String accountNumber) {
		super();
		this.salonName = salonName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.webAddress = webAddress;
		this.pib = pib;
		this.companyNumber = companyNumber;
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the salonName
	 */
	public String getSalonName() {
		return salonName;
	}

	/**
	 * @param salonName
	 *            the salonName to set
	 */
	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the webAddress
	 */
	public String getWebAddress() {
		return webAddress;
	}

	/**
	 * @param webAddress
	 *            the webAddress to set
	 */
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	/**
	 * @return the pib
	 */
	public String getPib() {
		return pib;
	}

	/**
	 * @param pib
	 *            the pib to set
	 */
	public void setPib(String pib) {
		this.pib = pib;
	}

	/**
	 * @return the companyNumber
	 */
	public String getCompanyNumber() {
		return companyNumber;
	}

	/**
	 * @param companyNumber
	 *            the companyNumber to set
	 */
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the furnitureItems
	 */
	public HashMap<String, FurnitureItem> getFurnitureItems() {
		return furnitureItems;
	}

	/**
	 * @param furnitureItems the furnitureItems to set
	 */
	public void setFurnitureItems(HashMap<String, FurnitureItem> furnitureItems) {
		this.furnitureItems = furnitureItems;
	}
}
