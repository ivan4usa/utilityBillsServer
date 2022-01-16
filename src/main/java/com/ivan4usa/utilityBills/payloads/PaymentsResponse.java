package com.ivan4usa.utilityBills.payloads;

import com.ivan4usa.utilityBills.entities.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsResponse {
    private List<Payment> payments;
    private String startPage;
    private Payment lastPayment;
}
