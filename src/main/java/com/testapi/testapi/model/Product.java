package com.testapi.testapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToMany
    @JoinTable(name = "tbl_product_supplier",
    joinColumns = @JoinColumn(name = "productId"),
    inverseJoinColumns = @JoinColumn(name = "supplierId"))
    private Set<Supplier>suppliers;

}
