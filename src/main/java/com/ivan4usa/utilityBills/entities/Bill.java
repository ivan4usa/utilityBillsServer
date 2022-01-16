package com.ivan4usa.utilityBills.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill", schema = "utility_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;

    @Column(name = "date_end", nullable = false)
    private LocalDate dateEnd;

    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;

    @Column(name = "counter_start")
    private Integer counterStart;

    @Column(name = "counter_end")
    private Integer counterEnd;

    @Column(name = "comment", length = 20)
    private String comment;

    @Column(name = "tariff")
    private String tariff;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy="bill", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Payment> payments = new ArrayList<>();

}
