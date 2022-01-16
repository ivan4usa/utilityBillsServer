package com.ivan4usa.utilityBills.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "account", schema = "utility_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "company", length = 255)
    private String company;

    @Column(name = "edrpou", length = 8)
    private String edrpou;

    @Column(name = "account_number", length = 30)
    private String accountNumber;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "house_id", referencedColumnName = "id", insertable=false, updatable=false)
    private House house;

    @Column(name = "house_id", nullable = false)
    private Long houseId;


}
