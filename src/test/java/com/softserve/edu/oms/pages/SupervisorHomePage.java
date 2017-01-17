    package com.softserve.edu.oms.pages;

    import com.softserve.edu.oms.locators.UserHomePageLocators;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;

    /**
     * this class represents Home Page for Supervisor user role
     */
    public class SupervisorHomePage extends HomePage{

        public SupervisorHomePage (WebDriver driver){
            super(driver);
        }

        // get Data
        public WebElement getItemManagementTab() {
            return driver.findElement(UserHomePageLocators.ITEM_MANAGEMENT_TAB.by);
        }

        // set Data
        public void clickItemManagementTab() {
            getItemManagementTab().click();
        }

    }
