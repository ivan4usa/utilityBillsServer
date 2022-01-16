package com.ivan4usa.utilityBills.payloads;

import com.ivan4usa.utilityBills.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsData {
    Account account;
    BigDecimal[] billsAmountsByMonth;
}