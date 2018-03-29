/**
 * 
 */
package com.lsct.edp.models;

/**
 * Represent a purchase item that includes oredered product and quantity 
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class PurchaseItem {

	/**
	 * purchased quantity of the ordered product
	 */
	private int amount;
	/**
	 * Ordered product
	 */
	private Product product;

	/**
	 * Gets the purchased quantity of the ordered product
	 * @return the amount an int representing the purchased quantity
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the purchased quantity of the ordered product
	 * @param qty an int representing the purchased quantity
	 */
	public void setAmount(int qty) {
		if(qty<0) {
			throw new IllegalArgumentException("Amount value must be equals or greater than 0");
		}
		amount = qty;
	}

	/**
	 * Get the ordered product
	 * @return a Product representing the ordered product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the ordered product
	 * @param item a Product representing the ordered product
	 */
	public void setProduct(Product item) {
		product = item;
	}
	
	/**
	 * Gets the total tax amount of the purchased quantity of the product.
	 * @return
	 */
	public double getTotalTaxAmount() {
		return product.getPriceTax() * amount;
	}
	
	/**
	 * Get the total price of the purchased quantity of the product.
	 * Total price includes taxes costs.
	 * @return a double representing the total price
	 */
	public double getTotalPrice() {
		return product.getTotalPrice() * amount;
	}
	
	public String toString() {
		return String.format("%d %s", amount, product.toString());
	}
}
