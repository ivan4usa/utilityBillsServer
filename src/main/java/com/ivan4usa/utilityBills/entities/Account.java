package com.ivan4usa.utilityBills.entities;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "house_id", referencedColumnName = "id", nullable = false)
    private House house;
}
