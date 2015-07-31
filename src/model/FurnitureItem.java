/**
 * 
 */
package model;

import java.io.Serializable;

/**
 * @author nikolalsvk
 *
 */
public class FurnitureItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String color;
	private String originCountry;
	private String manufacturerName;
	private Double price;
	private Integer inStock;
	private String category;
	private Integer yearOfProduction;
	private String salon;
	private String image;
	private Double discount;
	private Double discountedPrice;
	private boolean isDiscounted;

	public FurnitureItem() {

	}

	/**
	 * @param id
	 * @param name
	 * @param color
	 * @param originCountry
	 * @param manufacturerName
	 * @param price
	 * @param inStock
	 * @param category
	 * @param yearOfProduction
	 * @param salon
	 * @param image
	 */
	public FurnitureItem(String id, String name, String color,
			String originCountry, String manufacturerName, Double price,
			Integer inStock, String category, Integer yearOfProduction,
			String salon, String image) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.originCountry = originCountry;
		this.manufacturerName = manufacturerName;
		this.price = price;
		this.inStock = inStock;
		this.category = category;
		this.yearOfProduction = yearOfProduction;
		this.salon = salon;
		this.image = image;
		this.discount = 0.0;
		this.discountedPrice = 0.0;
		this.isDiscounted = false;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the originCountry
	 */
	public String getOriginCountry() {
		return originCountry;
	}

	/**
	 * @param originCountry
	 *            the originCountry to set
	 */
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	/**
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * @param manufacturerName
	 *            the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the inStock
	 */
	public Integer getInStock() {
		return inStock;
	}

	/**
	 * @param inStock
	 *            the inStock to set
	 */
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the yearOfProduction
	 */
	public Integer getYearOfProduction() {
		return yearOfProduction;
	}

	/**
	 * @param yearOfProduction
	 *            the yearOfProduction to set
	 */
	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	/**
	 * @return the salon
	 */
	public String getSalon() {
		return salon;
	}

	/**
	 * @param salon
	 *            the salon to set
	 */
	public void setSalon(String salon) {
		this.salon = salon;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
		this.discountedPrice = price * (1 - discount);
	}

	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public boolean isDiscounted() {
		return isDiscounted;
	}

	public void setDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}
}
