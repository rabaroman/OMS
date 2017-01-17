package com.softserve.edu.oms.locators;

import com.softserve.edu.oms.pages.AbstractAdminReportPage;
import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link AbstractAdminReportPage}.
 */
public enum AbstractAdminReportPageLocators {

    SUBHEADER(By.cssSelector("#list h2")),
    USERS_FOUND_SPAN(By.id("usersFound")),
    SEARCH_BY_LABEL(By.tagName("legend")),
    FIELD_FILTER_LABEL(By.cssSelector("#searchForm label")),
    SELECT_FIELD(By.id("field")),
    SELECT_CONDITION(By.id("condition")),
    SEARCH_FIELD(By.id("searchField")),
    SEARCH_BUTTON(By.name("search")),
    SHOW_ITEMS_LINK(By.cssSelector("#list p a")),
    FIRST_NAME_LINK(By.cssSelector("th:nth-child(1) a")),
    LAST_NAME_LINK(By.cssSelector("th:nth-child(2) a")),
    LOGIN_LINK(By.cssSelector("th:nth-child(3) a")),
    ROLE_LINK(By.cssSelector("th:nth-child(4) a")),
    REGION_LINK(By.cssSelector("th:nth-child(5) a")),
    FIRST_BUTTON(By.id("first")),
    BACKWARD_BUTTON(By.id("previous")),
    FORWARD_BUTTON(By.id("next")),
    LAST_BUTTON(By.id("last")),
    PAGE_NUMBER_SPAN(By.id("pageNumber")),
    PAGE_COUNT_SPAN(By.id("pageCount")),
    LOGINS(By.xpath(".//td[3]")),
    FIRST_NAMES(By.xpath(".//td[1]")),
    LAST_NAMES(By.xpath(".//td[2]")),
    ROLES(By.xpath(".//td[4]")),
    REGION(By.xpath(".//td[5]")),
    TABLE_BODY(By.tagName("tbody")),
    TR(By.tagName("tr")),
    TD(By.tagName("td")),
    GET_USER_BY_LOGIN(By.xpath("#table tr:first-child td:first-child"));

    public final By by;
    AbstractAdminReportPageLocators(final By by){
        this.by = by;
    }
}
