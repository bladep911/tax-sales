package com.lsct.edp.business;

import com.lsct.edp.models.Product;
import javax.naming.ConfigurationException;

/**
 * @author andrea.boggia
 *
 */
public class ImportedProductTaxCalculator extends BasicProductTaxCalculator {

	private static String CONFIG_IMPORT_TAX_RATE = "IMPORTED";
	
	private double importTaxRate;
	
	public ImportedProductTaxCalculator() throws ConfigurationException{
		importTaxRate = PropertyHelper.getRequiredPropertyAsDouble(configProperty, CONFIG_IMPORT_TAX_RATE);
	}
	
	public double calculateTax(Product product) {
		double baseTax = super.calculateTax(product);
		return baseTax + getTaxAmount(product.getShelfPrice(), importTaxRate);
	}
}
