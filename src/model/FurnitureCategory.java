/**
 * 
 */
package model;

/**
 * @author nikolalsvk
 *
 */
public class FurnitureCategory {
	private String name;
	private String description;
	private String subCategory;
	private Double discount;
	
	public FurnitureCategory() {
		
	}
	
	/**
	 * @param name
	 * @param description
	 * @param subCategory
	 */
	public FurnitureCategory(String name, String description,
			String subCategory) {
		super();
		this.name = name;
		this.description = description;
		this.subCategory = subCategory;
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
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the subCategory
	 */
	public String getSubCategory() {
		return subCategory;
	}
	/**
	 * @param subCategory the subCategory to set
	 */
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
