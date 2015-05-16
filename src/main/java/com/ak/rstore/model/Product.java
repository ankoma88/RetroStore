package com.ak.rstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int amount;
    private int year;
    private String photo;
    private Date receiptDate;

    private ShopOrder order;
    private Category category;

    public Product() {
        this.receiptDate = new Date();
        this.price = new BigDecimal(0);
        this.amount = 1;
    }

    public Product(String name, String description) {
        this.receiptDate = new Date();
        this.name = name;
        this.description = description;
        this.price = new BigDecimal(0);
        this.amount = 1;
        this.photo = "";
        this.order = new ShopOrder();
    }

    @Id
    @Column (name = "PRODUCT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int id) {
        this.productId = id;
    }

    @Column (name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name = "DESCRIPTION", unique=true, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column (name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column (name = "AMOUNT")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column (name = "YEAR")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column (name = "PHOTO")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column (name = "RECEIPTDATE", nullable = false)
    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn
    public ShopOrder getOrder() {
        return order;
    }

    public void setOrder(ShopOrder order) {
        this.order = order;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (description != null ? !description.equals(product.description) : product.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + year;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product "+name+" "+ productId;
    }



}
