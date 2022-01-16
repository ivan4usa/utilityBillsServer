package com.ivan4usa.utilityBills.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchValues {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
}
