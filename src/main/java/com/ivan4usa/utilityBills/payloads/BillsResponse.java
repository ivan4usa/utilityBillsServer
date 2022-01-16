package com.ivan4usa.utilityBills.payloads;

import com.ivan4usa.utilityBills.entities.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillsResponse {
    private List<Bill> bills;
    private String startPage;
    private Bill lastBill;
}
