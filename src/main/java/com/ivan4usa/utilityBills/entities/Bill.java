package com.ivan4usa.utilityBills.entities;

import com.vladmihalcea.hibernate.type.range.Range;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "period", columnDefinition = "daterange")
    private Range<LocalDate> period;

    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;

    @Column(name = "counter_start")
    private Integer counterStart;

    @Column(name = "counter_end")
    private Integer counterEnd;

    @Column(name = "comment")
    private String comment;

    @Column(name = "tariff")
    private String tariff;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

}
