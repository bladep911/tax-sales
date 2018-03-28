/**
 * 
 */
package com.lsct.edp.models;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author andrea.boggia
 *
 */
public class Purchase {

	private List<PurchaseItem> products;

	/**
	 * @return the items
	 */
	public List<PurchaseItem> getProducts() {
		return products;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setProducts(List<PurchaseItem> items) {
		products = items;
	}

	public double getTotalTaxAmount() {
		return products.stream().mapToDouble(f -> f.getTotalTaxAmount()).sum();
	}

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
