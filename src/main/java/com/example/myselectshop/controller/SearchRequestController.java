package com.example.myselectshop.controller;


import com.example.myselectshop.models.ItemDto;
import com.example.myselectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> getItems(@RequestParam String query){
        String result= naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(result);
    }
}
