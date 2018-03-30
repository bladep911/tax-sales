package com.lsct.edp.business;

import com.lsct.edp.models.Product;

/**
 * Interface class for product tax calculator.
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public interface IProductTaxCalculator {
	/**
	 * Calculate the tax amount of the given product
	 * @param product Product used to calculate the tax cost
	 * @return a double representing the product tax cost
	 */
	public double calculateTax(Product product);
	
}
