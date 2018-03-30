package com.lsct.edp.business;

import java.util.HashMap;
import java.util.Map;

import javax.naming.ConfigurationException;

import com.lsct.edp.models.ProductTaxClass;
/**
 * Factory class for a tax calculator.
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public class ProductTaxCalculatorFactory {

	/**
	 * A dictionary used to store tax calculators 
	 */
	private Map<ProductTaxClass, IProductTaxCalculator> factoryCache;
	/**
	 *  Create a new instance
	 */
	public ProductTaxCalculatorFactory() {
		factoryCache = new HashMap<ProductTaxClass, IProductTaxCalculator>();
	}
/**
 * Gets an instance of tax calculator according with given product tax class
 * @param productTaxClass a ProductTaxClass representing the product tax class
 * @return an implementation instance of IProductTaxCalculator according with given product tax class
	 * @throws ConfigurationException in case config file is missing or keys not found
 */
	public IProductTaxCalculator getTaxCalculator(ProductTaxClass productTaxClass) throws ConfigurationException {
		if (factoryCache.containsKey(productTaxClass))
			return factoryCache.get(productTaxClass);

		IProductTaxCalculator taxCalculator = null;

		switch (productTaxClass) {
			case BASIC:
				taxCalculator = new BasicProductTaxCalculator();
				break;
			case IMPORT:
				taxCalculator = new ImportedProductTaxCalculator();
				break;
			default:
				throw new IllegalArgumentException("Tax Class missing implementation");
		}

		factoryCache.put(productTaxClass, taxCalculator);
		return taxCalculator;
	}
}
