/**
 * 
 */
package com.lsct.edp.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.ConfigurationException;
import com.lsct.edp.models.ProductCategory;

/**
 * @author andrea.boggia
 *
 */
public abstract class AProductTaxCalculator implements IProductTaxCalculator {
	
	protected double roundDecimalValue;
	protected Properties configProperty;
	
	public AProductTaxCalculator() throws ConfigurationException {
		setupConfigs();
	}
	
	protected void setupConfigs() throws ConfigurationException {
		configProperty = PropertyHelper.gerPropertyReader(PropertyHelper.CONFIG_DEFAULT_FILE_NAME);
		roundDecimalValue = PropertyHelper.getRequiredPropertyAsDouble(configProperty, PropertyHelper.CONFIG_PRICE_ROUND);
	}
	
	protected List<ProductCategory> getExeptCategories(String exeptListProperty) {
		List<ProductCategory> exeptedCategories = new ArrayList<ProductCategory>();
		String exeptList = configProperty.getProperty(exeptListProperty); 
		if(exeptList != null && !exeptList.trim().isEmpty()) {
			for(String cat: exeptList.split(PropertyHelper.CONFIG_LIST_SEPARATOR)) {
				exeptedCategories.add(ProductCategory.valueOf(cat)); //TODO: add try catch
			}
			return exeptedCategories;
		}	
		return null;
	}
	
		
	protected final double roundAmount(double Amount) {
		return roundAmount(Amount, roundDecimalValue);
	}

	protected final double roundAmount(double Amount, double DecimalValue) {
		double fraction = 1 / DecimalValue;
		return Math.ceil(Amount * fraction) / fraction;
	}
	
	protected double getTaxAmount(double price, double TaxRate) {
		if(TaxRate < 0 || TaxRate > 100) {
			throw new IllegalArgumentException(String.format("Tax Rate = %d is not valid", TaxRate));
		}
		return roundAmount(price * TaxRate);
	}
}
