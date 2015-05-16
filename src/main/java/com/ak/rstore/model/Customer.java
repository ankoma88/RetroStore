package com.ak.rstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private int customerId;
    private String loginName;
    private String firstName;
    private String lastName;
    private String password;
    private int purchasesNumber;
    private double moneySpent;
    private Date registrationDate;

    private Collection<ShopOrder> shopOrders;

    public Customer() {
        this.registrationDate = new Date();
    }

    public Customer(String loginName) {
        this.registrationDate = new Date();
        this.loginName = loginName;
    }

    @Id
    @Column (name = "CUSTOMER_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int id) {
        this.customerId = id;
    }

    @Column (name = "LOGIN_NAME",unique=true, nullable = false)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column (name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column (name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column (name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column (name = "PURCHASES_NUMBER")
    public int getPurchasesNumber() {
        return purchasesNumber;
    }

    public void setPurchasesNumber(int purchasesNumber) {
        this.purchasesNumber = purchasesNumber;
    }

    @Column (name = "MONEY_SPENT")
    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE", unique = true, nullable = false, length = 10)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL)
    public Collection<ShopOrder> getShopOrders() {
        return shopOrders;
    }

    public void setShopOrders(Collection<ShopOrder> shopOrders) {
        this.shopOrders = shopOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        if (!loginName.equals(customer.loginName)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + loginName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer "+ loginName;
    }
}
