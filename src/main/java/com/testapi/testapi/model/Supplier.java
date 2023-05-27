package com.testapi.testapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String name;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products;
}
