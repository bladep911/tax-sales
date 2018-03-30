package com.lsct.edp.business;

import com.lsct.edp.models.Product;
import javax.naming.ConfigurationException;

/**
 * Tax calculator implementation for imported products.
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class ImportedProductTaxCalculator extends BasicProductTaxCalculator {
	/**
	 * Property key used to get the tax rate
	 */
	private static String CONFIG_IMPORT_TAX_RATE = "IMPORTED";
	/**
	 * Tax rate for basic products
	 */
	private double importTaxRate;
	/**
	 * Create a new instance of a ImportedProductTaxCalculator
	 * @throws ConfigurationException in case config file is missing or keys not found
	 */
	public ImportedProductTaxCalculator() throws ConfigurationException{
		importTaxRate = PropertyHelper.getRequiredPropertyAsDouble(configProperty, CONFIG_IMPORT_TAX_RATE);
	}
	
	public double calculateTax(Product product) {
		double baseTax = super.calculateTax(product);
		return baseTax + getTaxAmount(product.getShelfPrice(), importTaxRate);
	}
}
