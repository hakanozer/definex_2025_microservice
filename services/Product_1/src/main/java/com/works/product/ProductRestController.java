package com.works.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("product")
public class ProductRestController {

    @GetMapping("all")
    public Map all(){
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("status", true);
        mp.put("message", "Product List - 1");
        mp.put("count", 10);
        return mp;
    }

}
