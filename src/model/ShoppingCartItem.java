package model;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FurnitureItem furnitureItem;
	private Integer count;
	private Double total;

	public ShoppingCartItem() {

	}

	public ShoppingCartItem(FurnitureItem furnitureItem, Integer count) {
		this.furnitureItem = furnitureItem;
		this.count = count;
	}

	public FurnitureItem getFurnitureItem() {
		return furnitureItem;
	}

	public void setFurnitureItem(FurnitureItem furnitureItem) {
		this.furnitureItem = furnitureItem;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public double getTotal() {
		if (furnitureItem.isDiscounted()) {
			total = furnitureItem.getDiscountedPrice() * count;
		} else {
			total = furnitureItem.getPrice() * count;
		}
		return total;
	}
}
