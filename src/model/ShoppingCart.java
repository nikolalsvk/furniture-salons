/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author nikolalsvk
 *
 */
public class ShoppingCart {
	private ArrayList<ShoppingCartItem> shoppingCart = new ArrayList<ShoppingCartItem>();
	private double total;

	public ShoppingCart() {

	}

	public ArrayList<ShoppingCartItem> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ArrayList<ShoppingCartItem> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public boolean addItem(ShoppingCartItem item) {
		return shoppingCart.add(item);
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public double getTotal() {
		// double retVal = 0;
		total = 0;
		for (ShoppingCartItem item : shoppingCart) {
			total += item.getTotal();
		}
		return total;
		// return retVal;
	}

	public ArrayList<FurnitureItem> getFurniture() {
		ArrayList<FurnitureItem> retVal = new ArrayList<FurnitureItem>();
		for (ShoppingCartItem shoppingCartItem : shoppingCart) {
			retVal.add(shoppingCartItem.getFurnitureItem());
		}
		
		return retVal;
	}
}
