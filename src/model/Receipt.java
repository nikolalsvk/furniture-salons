package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Receipt implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ShoppingCartItem> furnitureItems;
	private Double tax;
	private Double finalPrice;
	private String date;
	private String user;
	private String additionalService;

	public Receipt() {

	}

	/**
	 * @param furnitureItems
	 * @param priceOfFurniture
	 * @param quantity
	 * @param tax
	 * @param finalPrice
	 * @param date
	 * @param user
	 */
	public Receipt(ArrayList<ShoppingCartItem> furniture, Double tax,
			Double finalPrice, Date date, String user) {
		super();
		this.furnitureItems = furniture;
		this.tax = tax;
		this.finalPrice = finalPrice;
		DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		this.date = df.format(date);
		this.user = user;
		this.additionalService = "";
	}

	/**
	 * @return the furnitureItems
	 */
	public ArrayList<ShoppingCartItem> getFurnitureItems() {
		return furnitureItems;
	}

	/**
	 * @param furnitureItems
	 *            the furnitureItems to set
	 */
	public void setFurnitureItems(ArrayList<ShoppingCartItem> furniture) {
		this.furnitureItems = furniture;
	}

	/**
	 * @return the tax
	 */
	public Double getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}

	/**
	 * @return the finalPrice
	 */
	public Double getFinalPrice() {
		return finalPrice;
	}

	/**
	 * @param finalPrice
	 *            the finalPrice to set
	 */
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		this.date = df.format(date);
	}
	
	public void setDateUsual(String date) {
		this.date = date;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	
	public boolean isBetweenDates(String startDate, String endDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat df_other = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		Date startDate_conv = df.parse(startDate);
		Date endDate_conv = df.parse(endDate);
		Date currentDate = df_other.parse(date);
		
		if(startDate_conv.after(currentDate) || endDate_conv.before(currentDate)) {
			return false;
		} else {
			return true;
		}
	}
	
	public ArrayList<ShoppingCartItem> getItemWithCategory(String category) {
		ArrayList<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
		for (ShoppingCartItem shoppingCartItem : furnitureItems) {
			if(shoppingCartItem.getFurnitureItem().getCategory().contains(category)) {
				items.add(shoppingCartItem);
			}
		}
		
		return items;
	}

	public String getAdditionalService() {
		return additionalService;
	}

	public void setAdditionalService(String additionalService) {
		this.additionalService = additionalService;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
