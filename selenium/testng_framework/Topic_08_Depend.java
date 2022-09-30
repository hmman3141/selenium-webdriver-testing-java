package testng_framework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_08_Depend {
	@Test()
	public void TC_01_Create_New_Account() {
		Assert.assertTrue(true);
	}

	@Test(dependsOnMethods = "TC_01_Create_New_Account")
	public void TC_02_Update_Account() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "TC_02_Update_Account")
	public void TC_03_View_Account() {

	}

	@Test(dependsOnMethods = "TC_03_View_Account")
	public void TC_04_Delete_Account() {

	}
}
