package com.lsct.edp.business;

import java.util.HashMap;
import java.util.Map;

import javax.naming.ConfigurationException;

public class ProductTaxCalculatorFactory {

	private Map<TaxClass, IProductTaxCalculator> factoryCache;

	public ProductTaxCalculatorFactory() {
		factoryCache = new HashMap<TaxClass, IProductTaxCalculator>();
	}

	public IProductTaxCalculator getTaxCalculator(TaxClass productTaxClass) throws ConfigurationException {
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
