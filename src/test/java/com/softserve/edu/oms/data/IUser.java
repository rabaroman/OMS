package com.softserve.edu.oms.data;

/**
 * IUser interface is used to represent User class
 * and insure that fields of User object could not be changed after initialization
 *
 * @since 01.12.16
 */
public interface IUser {

    String getLoginname();

    String getFirstname();

    String getLastname();

    String getPassword();

    String getEmail();

    String getRegion();

    String getRole();

    boolean CompareTo(IUser user);
}
