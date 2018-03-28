/**
 * 
 */
package com.lsct.edp.models;

import com.lsct.edp.business.TaxClass;

/**
 * @author andrea.boggia
 *
 */
public class Product {

	private String name;
	
	private String description;
	
	private TaxClass taxClass;
	
	private ItemCategory category;
	
	private double shelfPrice;
	
	private double priceTax;
	
	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
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
	public void setDescription(String descr) {
		description = descr;
	}

	public TaxClass getTaxClass() {
		return taxClass;
	}

	public void setTaxClass(TaxClass taxCls) {
		taxClass = taxCls;
	}

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory cat) {
		category = cat;
	}

	public double getShelfPrice() {
		return shelfPrice;
	}

	public void setShelfPrice(double price) {
		shelfPrice = price;
	}

	/**
	 * @return the priceTax
	 */
	public double getPriceTax() {
		return priceTax;
	}

	/**
	 * @param priceTax the priceTax to set
	 */
	public void setPriceTax(double tax) {
		priceTax = tax;
	}
	
	public double getTotalPrice() {
		return shelfPrice + priceTax; 
	}
	
	public String toString() {
		String text = (description == null || description.isEmpty()) ? name : description;
		return String.format("%s : %.2f", text, getTotalPrice());
	}
}
