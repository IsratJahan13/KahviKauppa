package com.example.kahvikauppa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KahviKauppaController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/kahviLaitteet")
    public String getKahvilaitteet() {
        return "kahviLaitteet";
    }

    @GetMapping("/kulutusTuotteet")
    public String getKulutusTuotteet() {
        return "kulutusTuotteet";
    }

    @GetMapping("/tuoteSivu")
    public String getTuoteSivu() {
        return "tuoteSivu";
    }

}
