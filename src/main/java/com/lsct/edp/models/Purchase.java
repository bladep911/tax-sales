/**
 * 
 */
package com.lsct.edp.models;
import java.util.List;

/**
 * Represent a purchase
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class Purchase {

	/**
	 * List of PurchaseItems representing the purchased products and relative quantity
	 */
	private List<PurchaseItem> products;

	/**
	 * Gets the list of purchased products and related quantity
	 * @return a list of ProductItems representing the purchased products
	 */
	public List<PurchaseItem> getProducts() {
		return products;
	}

	/**
	 * Set the list of purchased products
	 * @param items a list of PurchaseItems representing the purchased products
	 */
	public void setProducts(List<PurchaseItem> items) {
		products = items;
	}

	/**
	 * Gets the total tax costs of the purchase.
	 * It is calculated adding each ProductItem total tax costs. 
	 * @return a double representing the purchase total tax costs 
	 */
	public double getTotalTaxAmount() {
		return products.stream().mapToDouble(f -> f.getTotalTaxAmount()).sum();
	}

	/**
	 * Gets the total price of the purchase.
	 * It's calculated adding each ProductItem total price.
	 * The total price includes taxes.
	 * @return 	 * @return a double representing the purchase total costs 

	 */
	public double getTotalCost() {
		return products.stream().mapToDouble(f -> f.getTotalPrice()).sum();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		products.stream().forEach(p -> {
			builder.append(String.format("%s\n", p.toString()));
		});
		builder.append(String.format("Sales Taxes: %.2f\n", getTotalTaxAmount()));
		builder.append(String.format("Total: %.2f", getTotalCost()));
		return builder.toString();
	}
}
