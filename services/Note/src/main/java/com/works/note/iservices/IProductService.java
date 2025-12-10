package com.works.note.iservices;

import com.works.note.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("Product")
public interface IProductService {

    @GetMapping("product/all")
    Product allProduct();

}
