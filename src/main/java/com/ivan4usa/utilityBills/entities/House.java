package com.ivan4usa.utilityBills.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "house", schema = "utility_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "zip", length = 5)
    private String zip;

    @Column(name = "user_id", nullable = false )
    private Long userId;
}
