package com.ivan4usa.utilityBills.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payment", schema = "utility_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false, precision = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @Column(name="pay_date", nullable = false)
    private LocalDate payDate;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="payment_file")
    private byte[] paymentFile;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bill_id", referencedColumnName = "id", nullable = false)
    private Bill bill;
}
