package com.Framework.signIn.Index;

import org.testng.annotations.Test;

import com.Framework.Init.Common;
import com.Framework.Init.SeleniumInit;

public class SignInIndex extends SeleniumInit{
	
	public static int step;
	
	@Test
	public void signinGmail()
	{
		Common.logstep("Open Gmail URL.");
		if(signInVerification.isGmailOpened())
		{
			Common.logverification("Gmail opened successfully.");
		}
		
	}

}
