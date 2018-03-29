package com.lsct.edp.business;

import java.util.HashMap;
import java.util.Map;

import javax.naming.ConfigurationException;

import com.lsct.edp.models.ProductTaxClass;

public class ProductTaxCalculatorFactory {

	private Map<ProductTaxClass, IProductTaxCalculator> factoryCache;

	public ProductTaxCalculatorFactory() {
		factoryCache = new HashMap<ProductTaxClass, IProductTaxCalculator>();
	}

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
