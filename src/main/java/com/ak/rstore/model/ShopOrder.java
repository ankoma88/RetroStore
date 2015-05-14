package com.ak.rstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "shop_order")
public class ShopOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private int orderId;
    private Customer customer;
    private Collection<Product> orderedProducts;


    public ShopOrder() {
    }

    public ShopOrder(Customer customer) {
        this.customer = customer;
    }

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        this.orderId = id;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @OneToMany (mappedBy = "order")
    public Collection<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Collection<Product> products) {
        this.orderedProducts = products;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopOrder)) return false;

        ShopOrder that = (ShopOrder) o;

        if (orderId != that.orderId) return false;
        if (!customer.equals(that.customer)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + customer.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order "+ orderId;
    }
}
