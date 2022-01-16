package com.ivan4usa.utilityBills.services;

import com.ivan4usa.utilityBills.entities.Account;
import com.ivan4usa.utilityBills.entities.Bill;
import com.ivan4usa.utilityBills.entities.House;
import com.ivan4usa.utilityBills.payloads.StatisticsData;
import com.ivan4usa.utilityBills.payloads.StatisticsRequest;
import com.ivan4usa.utilityBills.payloads.StatisticsResponse;
import com.ivan4usa.utilityBills.repositories.BillRepository;
import com.ivan4usa.utilityBills.repositories.HouseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HouseService {

    private final HouseRepository repository;
    private final BillRepository billRepository;

    public HouseService(HouseRepository repository, BillRepository billRepository) {
        this.repository = repository;
        this.billRepository = billRepository;
    }

    public List<House> findAll(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public Optional<House> findById(Long id) {
        return repository.findById(id);
    }

    public House add(House house) {
        return repository.save(house);
    }

    public House update(House house) {
        return repository.save(house);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<StatisticsResponse> searchStatistics(StatisticsRequest statisticsRequest) {
        Long userId = statisticsRequest.getUserId();
        String year = statisticsRequest.getYear();
        List<House> houses = findAll(userId);

        List<StatisticsResponse> responseList = new ArrayList<>();


        for (House house: houses) {
            StatisticsResponse response = new StatisticsResponse();
            response.setHouse(house);
            List<StatisticsData> statisticsDataList = new ArrayList<>();
            for (Account account: house.getAccounts()) {
                StatisticsData statisticsData = new StatisticsData();
                statisticsData.setAccount(account);
                BigDecimal[] amountList = new BigDecimal[12];

                for (int i = 0; i < 12; i++) {
                    String month = String.valueOf(i + 1);
                    if (month.length() < 2) {
                        month = "0" + month;
                    }

                    LocalDate startDate = LocalDate.parse(year + "-" + month + "-01");
                    LocalDate endDate = LocalDate.parse(year + "-" + month + "-01");

                    endDate = endDate.withDayOfMonth(endDate.getMonth().length(endDate.isLeapYear()));

                    List<Bill> bills = billRepository.searchByEndDate(account.getId(), startDate, endDate);

                    BigDecimal amount = BigDecimal.ZERO;

                    for (Bill bill: bills) {
                        amount = amount.add(bill.getAmount());
                    }
                    amountList[i] = amount;
                }
                statisticsData.setBillsAmountsByMonth(amountList);
                statisticsDataList.add(statisticsData);
            }
            response.setDataList(statisticsDataList);
            responseList.add(response);
        }

        return responseList;
    }
}
