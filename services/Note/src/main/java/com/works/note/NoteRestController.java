package com.works.note;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("note")
public class NoteRestController {

    @Value("${definex.data}")
    private String definexData;

    @GetMapping("all")
    public Map all(){
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("status", false);
        mp.put("message", "Note List");
        mp.put("count", 50);
        mp.put("data", definexData);
        return mp;
    }

}
