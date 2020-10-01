package app.pages;


import org.testng.Assert;

import core.BasePage;
import utils.Locator;
import utils.TestNGUtils;
import utils.Locator.Type;

public class CheckoutPage extends BasePage{
	public static Locator CHECKOUT_ERROR=new Locator("Checkout Error", "com.ebay.mobile:id/home", Type.ID);
	public static Locator CONTINUE_SHOPPING=new Locator("Continue Shopping button", "Continue Shopping", Type.ID);
	public static Locator CHECKOUT_PRODUCT_NAME=new Locator("Checkout product name", "com.ebay.mobile:id/item_title", Type.ID);
	public static Locator CHECKOUT_PRODUCT_PRICE=new Locator("Checkout product price", "com.ebay.mobile:id/textview_item_price", Type.ID);
	public static Locator CHECKOUT_PRODUCT_COMMIT=new Locator("Checkout product commit button", "com.ebay.mobile:id/take_action", Type.ID);
	public static Locator CHECKOUT_PRODUCT_ERROR_OK=new Locator("Checkout product error ok button", "com.ebay.mobile:id/error_okay_btn", Type.ID);

	public CheckoutPage() {

	}
	/**
	 * @author Vikas_Tripathi
	 * Description: Click on a button to exit the error page. This method either clicks on Continue Shopping button or close button
	 * @return
	 * @throws Exception
	 */
	public CheckoutPage clickOnAbutton() throws Exception {
		TestNGUtils.reportLog("Click on any button to exit the error page");
		try {
		getAction().findElement(CONTINUE_SHOPPING);
		getAction().click(CONTINUE_SHOPPING);
		getAction().waitFor(3000);
		}catch(Exception e) {
			getAction().findElement(CHECKOUT_ERROR);
			getAction().click(CHECKOUT_ERROR);
			getAction().waitFor(3000);
			
		}
		return this;
	}
	/**
	 * @author Vikas_Tripathi
	 * Description: Click on 'Commit to order' button in Checkout page 
	 * @return
	 * @throws Exception
	 */
	public CheckoutPage clickOnCommitButton() throws Exception {
		TestNGUtils.reportLog("Click on commit to order button");
		getAction().findElement(CHECKOUT_PRODUCT_COMMIT);
		getAction().click(CHECKOUT_PRODUCT_COMMIT);
		getAction().waitFor(3000);
		getAction().findElement(CHECKOUT_PRODUCT_ERROR_OK);
		getAction().click(CHECKOUT_PRODUCT_ERROR_OK);
		getAction().waitFor(5000);
		return this;
	}
	
	/**
	 * @author Vikas_Tripathi
	 * Description: This methods check whether the page after Review is an error page or not
	 * @return
	 * @throws Exception
	 */
	public  boolean ifErrorPage() throws Exception {
		TestNGUtils.reportLog("Check if error page is loaded");
		boolean error=false;
		getAction().waitFor(2000);;
		if(!getAction().isElementVisible(CHECKOUT_PRODUCT_COMMIT)) {
			error=true;
		}
		return error;
	}
	
	/**
	 * @author Vikas_Tripathi
	 * Description: Verify Product Details in checkout page with that of SRP
	 * @return
	 * @throws Exception
	 */
	public CheckoutPage verifyProductDetailsCheckoutPage() throws Exception {

		TestNGUtils.reportLog("Verifying whether product name and price in checkout matches with that of search results page");
		getAction().findElement(CHECKOUT_PRODUCT_NAME);
		String nameInCheckout=getAction().getText(CHECKOUT_PRODUCT_NAME);
		String nameInSrp=(String) getAction().retrieveKeyValue("productName");
		Assert.assertEquals(nameInCheckout, nameInSrp, "Product Name mismatch between checkout and SRP.Product name in checkout:"+nameInCheckout+" Product name in SRP:"+nameInSrp);
		String priceInCheckout=getAction().getText(CHECKOUT_PRODUCT_PRICE).replaceAll(",", "").replaceAll("Rs.", "");
		String priceInSrp=(String) getAction().retrieveKeyValue("productPrice");
		Assert.assertEquals( Long.parseLong(priceInCheckout),Long.parseLong(priceInSrp), "Product Price mismatch between Checkout and SRP.Product price in Checkout:"+priceInCheckout+" Product price in SRP:"+priceInSrp);


		return this;
	}
}
