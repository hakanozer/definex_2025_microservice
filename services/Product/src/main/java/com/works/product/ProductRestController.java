package com.works.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
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

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @GetMapping("all")
    public Map all(){
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("status", true);
        mp.put("message", "Product List");
        mp.put("count", 10);
        mp.put("groupId", groupId);
        return mp;
    }

    @KafkaListener(topics = "news-topic", groupId = "#{T(java.util.UUID).randomUUID().toString()}")
    public void listen(String event) {
        System.out.println("Gelen Mesaj:" + event + groupId);
        //System.out.println("Alındı: " + event.getCount()+ " - status:" + event.isStatus());
        // İş mantığı: DB kaydet, başka servise çağrı vb.
    }

}
