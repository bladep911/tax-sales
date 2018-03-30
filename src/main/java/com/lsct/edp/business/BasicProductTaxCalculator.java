/**
 * 
 */
package com.lsct.edp.business;

import java.util.List;
import com.lsct.edp.models.Product;
import com.lsct.edp.models.ProductCategory;
import javax.naming.ConfigurationException;

/**
 * Tax calculator implementation for basic products.
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class BasicProductTaxCalculator extends AProductTaxCalculator {

	/**
	 * Property key used to get the tax rate
	 */
	private static String CONFIG_BASIC_TAX_RATE = "BASIC";
	/**
	 * Property key used to get excepted category for basic products
	 */
	private static String CONFIG_BASIC_EXEPT_LIST = "BASIC_EXEPT";
	/**
	 * Tax rate for basic products
	 */
	private double baseTaxRate;
	/**
	 * List of tax free category products
	 */
	private List<ProductCategory> exeptCategories;
	
	/**
	 * Create a new instance of a basic tax calculator
	 * @throws ConfigurationException in case config file is missing or keys not found
	 */
	public BasicProductTaxCalculator() throws ConfigurationException {
		baseTaxRate  = PropertyHelper.getRequiredPropertyAsDouble(configProperty, CONFIG_BASIC_TAX_RATE);
		exeptCategories = getExeptCategories(CONFIG_BASIC_EXEPT_LIST);
	}

	public double calculateTax(Product product) {
		if(exeptCategories != null && exeptCategories.contains(product.getCategory())) {
			return 0;
		}
		return getTaxAmount(product.getShelfPrice(), baseTaxRate);
	}
}
