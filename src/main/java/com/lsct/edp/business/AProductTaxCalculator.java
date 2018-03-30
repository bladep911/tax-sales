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
 * Abstract class that setup the default settings for a tax calculator.
 * The class provide also a list of generic methods to calculate tax cost and 
 * round its value
 * @author Andrea Boggia
 * @author andrea.boggia@gmail.com
 * @version 0.0.1
 */
public abstract class AProductTaxCalculator implements IProductTaxCalculator {
	/**
	 * represent the nearest decimal value used to round tax value 
	 */
	protected double roundDecimalValue;
	/**
	 * Property used to read tax calculator settings such as tax rate and excepted products.
	 * This is an instance field because could be possible that a subclass can use a different 
	 * setting file than the default one.
	 */
	protected Properties configProperty;
	
	/**
	 * Create a new instance and setup configuration for calculator
	 * @throws ConfigurationException in case config file is missing or required settings are missing
	 * into the config file.
	 */ 
	public AProductTaxCalculator() throws ConfigurationException {
		setupConfigs();
	}
	
	/**
	 * Setup the config and the round decimal properties 
	 * @throws ConfigurationException in case config file is missing or required settings are missing
	 * into the config file.
	 **/
	protected void setupConfigs() throws ConfigurationException {
		configProperty = PropertyHelper.gerPropertyReader(PropertyHelper.CONFIG_DEFAULT_FILE_NAME);
		roundDecimalValue = PropertyHelper.getRequiredPropertyAsDouble(configProperty, PropertyHelper.CONFIG_PRICE_ROUND);
	}
	
	/**
	 * Gets the list of excepted product category related to the given property key
	 * @param exeptListProperty string representing the file property key to get the excepted category list
	 * @return a list of ProductCategory enum
	 */
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
	
	/**
	 * Round the the given amount to the nearest decimal specified by roundDecimalValue property as precision
	 * @param Amount amount to round
	 * @return a double rounded to the nearest decimal using roundDecimalValue as precision
	 */
	protected final double roundAmount(double Amount) {
		return roundAmount(Amount, roundDecimalValue);
	}

	/**
	 * Round given amount to the nearest given decimal value
	 * @param Amount double value to round
	 * @param DecimalValue double representing the decimal precision used to round
	 * @return a double representing rounded value
	 */
	protected final double roundAmount(double Amount, double DecimalValue) {
		double fraction = 1 / DecimalValue;
		return Math.ceil(Amount * fraction) / fraction;
	}
	
	/**
	 * Gets tax costs calculated on price using the given tax rate
	 * @param price a double representing the price from which get the tax costs
	 * @param TaxRate a double representing the tax rate to use
	 * @return a double representing calculated tax amount
	 */
	protected double getTaxAmount(double price, double TaxRate) {
		if(TaxRate < 0 || TaxRate > 100) {
			throw new IllegalArgumentException(String.format("Tax Rate = %d is not valid", TaxRate));
		}
		return roundAmount(price * TaxRate);
	}
}
