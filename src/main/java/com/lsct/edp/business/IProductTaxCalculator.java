package com.lsct.edp.business;

import com.lsct.edp.models.Product;

/**
 * @author andrea.boggia
 *
 */
public interface IProductTaxCalculator {
	/**
	 * 
	 * @param product
	 * @return
	 */
	public double calculateTax(Product product);
	
}
