package com.ivan4usa.utilityBills.payloads;

import com.ivan4usa.utilityBills.entities.House;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {
    private House house;
    private List<StatisticsData> dataList;
}
