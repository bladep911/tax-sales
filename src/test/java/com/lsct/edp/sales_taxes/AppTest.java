package com.lsct.edp.sales_taxes;

import com.lsct.edp.business.*;
import com.lsct.edp.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.naming.ConfigurationException;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	// static properties
	private final String ASSERT_TOTAL = "TOTAL";
	private final String ASSERT_SALES_TAX = "SALES_TAX";

	private static Logger LOGGER;
	private static ProductTaxCalculatorFactory taxCalculatorFactory;

	static {
		// init tax calculator factory
		taxCalculatorFactory = new ProductTaxCalculatorFactory();

		// init the LOGGER using custom configs
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
		try {
			LogManager.getLogManager().readConfiguration(stream);
			LOGGER = Logger.getLogger(AppTest.class.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// instance properties
	private String inputJsonFile;
	private String outputAssertFile;
	private Purchase purchaseTest;
	private Properties assertProperty;

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new AppTest("Tax Calculator Test 1", "purchase1.json", "assert1.properties"));
		suite.addTest(new AppTest("Tax Calculator Test 2", "purchase2.json", "assert2.properties"));
		suite.addTest(new AppTest("Tax Calculator Test 3", "purchase3.json", "assert3.properties"));
		return suite;
	}

	protected static String getFileContent(String filename) throws IOException {
		String fileContent = null;
		InputStream resourceStream = null;
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			resourceStream = loader.getResourceAsStream(filename);
			fileContent = IOUtils.toString(resourceStream, "UTF-8");
		} catch (IOException e) {
			throw new IOException(String.format("Resource file %s not found: %s", filename, e.getMessage()));
		} finally {
			resourceStream.close();
		}
		return fileContent;
	}

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName, String inputFile, String assertFile) {
		this(testName);
		inputJsonFile = inputFile;
		outputAssertFile = assertFile;
	}

	protected void setUp() throws JsonSyntaxException, IOException, ConfigurationException {
		//setup purchase test 1
		Gson gson = new GsonBuilder().create();
		purchaseTest = gson.fromJson(getFileContent(inputJsonFile), Purchase.class);
		assertProperty = PropertyHelper.gerPropertyReader(outputAssertFile);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void runTest() {
		LOGGER.info(String.format("%s: Start", this.getName()));
		IProductTaxCalculator taxCalc;
		try {
			for (PurchaseItem purchaseItem : purchaseTest.getProducts()) {
				Product product = purchaseItem.getProduct();
				taxCalc = taxCalculatorFactory.getTaxCalculator(product.getTaxClass());
				product.setPriceTax(taxCalc.calculateTax(product));
			}
			System.out.println(purchaseTest.toString());

			// assert
			String expectedTotal = PropertyHelper.getRequiredProperty(assertProperty, ASSERT_TOTAL);
			String expectedSalesTax = PropertyHelper.getRequiredProperty(assertProperty, ASSERT_SALES_TAX);
			
			assertTrue(expectedTotal.equals(String.format("%.2f", purchaseTest.getTotalCost())) && 
					   expectedSalesTax.equals(String.format("%.2f", purchaseTest.getTotalTaxAmount())));
		
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
			assertTrue(false);
		} finally {
			LOGGER.info(String.format("%s: End", this.getName()));
		}
	}
}
