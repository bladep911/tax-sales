/**
 * 
 */
package com.lsct.edp.models;

/**
 * @author andrea.boggia
 *
 */
public class PurchaseItem {

	private int amount;
	
	private Product product;

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int qty) {
		amount = qty;
	}

	/**
	 * @return the item
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param item the item to set
	 */
	public void setProduct(Product item) {
		product = item;
	}
	
	public double getTotalTaxAmount() {
		return product.getPriceTax() * amount;
	}
	
	public double getTotalPrice() {
		return product.getTotalPrice() * amount;
	}
	
	public String toString() {
		return String.format("%d %s", amount, product.toString());
	}
}
