package com.works.note;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("note")
public class NoteRestController {

    @GetMapping("all")
    public Map all(){
        Map<String, Object> mp = new LinkedHashMap<>();
        mp.put("status", false);
        mp.put("message", "Note List");
        mp.put("count", 50);
        return mp;
    }

}
