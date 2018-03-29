/**
 * 
 */
package com.lsct.edp.business;

import java.util.List;
import com.lsct.edp.models.Product;
import com.lsct.edp.models.ProductCategory;
import javax.naming.ConfigurationException;

/**
 * @author andrea.boggia
 *
 */
public class BasicProductTaxCalculator extends AProductTaxCalculator {

	private static String CONFIG_BASIC_TAX_RATE = "BASIC";
	private static String CONFIG_BASIC_EXEPT_LIST = "BASIC_EXEPT";
	
	private double baseTaxRate;
	private List<ProductCategory> exeptCategories;
	
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
