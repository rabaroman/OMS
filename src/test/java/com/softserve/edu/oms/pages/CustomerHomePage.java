    package com.softserve.edu.oms.pages;

    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;

    import static com.softserve.edu.oms.locators.UserHomePageLocators.ORDERING_TAB;

    /**
     * This class represents Home Page for Customer user role
     */
    public class CustomerHomePage extends HomePage{

        public CustomerHomePage(WebDriver driver){
            super(driver);
        }

        // get Data
        public WebElement getOrderingTab(){
            return driver.findElement(ORDERING_TAB.by);
        }

        // Functional
        public String getOrderingTabText() {
            return getOrderingTab().getText();
        }

        // set Data
        public void clickOrderingTab() {
            getOrderingTab().click();
        }

        // Business Logic
        public OrderingPage gotoOrderingPage(){
            clickOrderingTab();
            return new OrderingPage();
        }
    }
