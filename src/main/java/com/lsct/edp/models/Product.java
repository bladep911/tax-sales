package com.lsct.edp.models;

/**
 * Represent a product
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class Product {

	/** 
	 * The product name
	 */
	private String name;
	/**
	 * The product description
	 */
	private String description;
	/**
	 * The tax class applied to the product
	 */
	private ProductTaxClass taxClass;
	/**
	 * The product category
	 */
	private ProductCategory category;
	/**
	 * Product price, tax excluded
	 */
	private double shelfPrice;
	/**
	 * The tax amount applied to add at the shelf price
	 */
	private double priceTax;
	
	/**
	 * Gets the product name
	 * @return A string representing the product name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the product name
	 * @param productName A string containing the product name 
	 */
	public void setName(String productName) {
		name = productName;
	}
	
	/**
	 * Gets the product description
	 * @return A string representing the product description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the product description
	 * @param description the description to set
	 */
	public void setDescription(String descr) {
		description = descr;
	}

	/**
	 * Gets the product tax class
	 * @return a ProductTaxClass enum representing the product tax class
	 */
	public ProductTaxClass getTaxClass() {
		return taxClass;
	}

	/**
	 * Sets the product tax category
	 * @param taxClass an ProductTaxClass enum representing the tax class
	 */
	public void setTaxClass(ProductTaxClass taxClass) {
		this.taxClass = taxClass;
	}

	/**
	 * Gets the product category
	 * @return A ProductCategory enum representing the product category
	 */
	public ProductCategory getCategory() {
		return category;
	}

	/**
	 * Sets the product category
	 * @param category an ProductCategory enum representing the product category
	 */
	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	/**
	 * Gets the product shelf price. Shelf price means not including taxes
	 * @return a double representing the product price excluding taxes
	 */
	public double getShelfPrice() {
		return shelfPrice;
	}

	/**
	 * Sets the product shelf price.
	 * @param price a double representing the product shelf price 
	 */	
	public void setShelfPrice(double price) {
		shelfPrice = price;
	}

	/**
	 * Gets the tax amount related to the shelf price
	 * @return the priceTax a double representing the tax amount
	 */
	public double getPriceTax() {
		return priceTax;
	}

	/**
	 * Sets the tax amount related to the shelf price
	 * @param priceTax a double representing the tax amount
	 */
	public void setPriceTax(double tax) {
		priceTax = tax;
	}
	
	/**
	 * Gets the product final price, that is sum between shelf price and tax  
	 * @return a double representing the final cost
	 */
	public double getTotalPrice() {
		return shelfPrice + priceTax; 
	}
	
	public String toString() {
		String text = (description == null || description.isEmpty()) ? name : description;
		return String.format("%s : %.2f", text, getTotalPrice());
	}
}
