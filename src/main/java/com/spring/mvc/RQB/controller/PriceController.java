package com.spring.mvc.RQB.controller;

import com.spring.mvc.RQB.entities.Item;
import com.spring.mvc.RQB.service.RQBService;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@RestController
@RequestMapping(value = {"/RQB/price"})
public class PriceController {
    
    @Autowired
    private RQBService service;
    
    //各股報價資訊（Watch List用）
    @GetMapping(value = {"/refresh"})
    @Transactional
    public List<Item> refresh() {
        List<Item> list = StreamSupport
                .stream(service.getItemRepository().findAll().spliterator(), false)
                .collect(Collectors.toList());;
        for (Item item : list) {
            // 取得報價資訊
            try {
                service.getItemRepository().updatePrice(item.getId(), 
                        item.getPrePrice(), 
                        item.getPrice(), 
                        item.getTransactionDate(), 
                        item.getQuantity());
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        return list;
    }
    
}
