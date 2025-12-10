package com.works.note;

import com.works.note.iservices.IProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("note")
public class NoteRestController {


    // Discover Client
    // private final DiscoveryClient discoveryClient;
    private final CircuitBreakerFactory globalCustomConfiguration;
    private final IProductService productService;
    public NoteRestController(CircuitBreakerFactory globalCustomConfiguration, IProductService productService) {
        this.globalCustomConfiguration = globalCustomConfiguration;
        //this.discoveryClient = discoveryClient;
        this.productService = productService;
    }

    @Value("${definex.data}")
    private String definexData;

    @Value("${spring.zipkin.baseUrl}")
    private String zinkinUrl;

    @GetMapping("all")
    public Map all(){
        /*
        List<ServiceInstance> list = discoveryClient.getInstances("Product");
        ServiceInstance instance = list.get(0);
        System.out.println(instance.getServiceId() + " : " + instance.getHost() + " : " + instance.getPort());
        RestTemplate restTemplate = new RestTemplate();
        String url = instance.getUri().toString() + "/product/all";
        Product proObj = restTemplate.getForObject(url, Product.class);
         */
        CircuitBreaker globalCircuitBreaker = globalCustomConfiguration.create("globalCircuitBreaker");
        return globalCircuitBreaker.run(
                () -> success(),
                throwable -> fallBack()
        );

    }

    public Map success() {
        try {
            Thread.sleep(6000);
        }catch (Exception e){}
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("status", false);
        mp.put("message", "Note List");
        mp.put("count", 50);
        mp.put("data", definexData);
        mp.put("zipkinUrl", zinkinUrl);
        mp.put("result", productService.allProduct());
        return mp;
    }

    private Map fallBack(){
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("status", false);
        mp.put("message", "fallback call");
        return mp;
    }

}
