/**
 * 
 */
package model;

/**
 * @author nikolalsvk
 *
 */
public class AdditionalService {
	private String name;
	private String description;
	private Double price;
	
	public AdditionalService() {
		
	}
	
	/**
	 * @param name
	 * @param description
	 * @param price
	 */
	public AdditionalService(String name, String opis, Double price) {
		super();
		this.name = name;
		this.description = opis;
		this.price = price;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String opis) {
		this.description = opis;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
