package com.ak.rstore.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;

    private int cat_id;
    private String name;
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "CAT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    @Column(name = "CAT_NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "category", cascade = CascadeType.ALL)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> productSet) {
        this.products = productSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;
        if (!name.equals(category.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cat_id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
