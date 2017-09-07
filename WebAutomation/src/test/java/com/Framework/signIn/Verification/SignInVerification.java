package com.Framework.signIn.Verification;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.Framework.Init.AbstractPage;

public class SignInVerification extends AbstractPage{

	public SignInVerification(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath=".//*[@id='identifierId']") 
	public WebElement emailText;
	
	public boolean isGmailOpened() {
		// TODO Auto-generated method stub
		if(emailText.isDisplayed())
			return true;
		else
			return false;
	}

}
