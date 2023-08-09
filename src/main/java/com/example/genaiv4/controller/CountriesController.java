package com.example.genaiv4.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/countries")
public class CountriesController {

    @GetMapping("/get")
    @ResponseBody
    public String getFoos(@RequestParam String id) {
        return "ID: " + id;
    }


}
