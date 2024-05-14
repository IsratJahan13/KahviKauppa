package com.example.kahvikauppa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ValmistajaController {
    @Autowired
    private ValmistajaService valmistajaService;

    @GetMapping("/valmistajat")
    public String getValmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaService.valmistajaList());
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String createValmistaja(@RequestParam String nimi, @RequestParam String url) {
        this.valmistajaService.createValmistaja(nimi, url);
        return "redirect:/valmistajat";
    }

    @GetMapping("/muokkaaValmistaja/{id}")
    public String getMuokkaaValmistaja(@PathVariable Long id, Model model) {
        Valmistaja valmistaja = valmistajaService.findById(id);
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaaValmistaja";
    }

    @PostMapping("/muokkaaValmistaja/{id}")
    public String muokkaaValmistaja(@PathVariable Long id, @RequestParam String nimi, @RequestParam String url) {
        this.valmistajaService.muokkaaValmistaja(id, nimi, url);
        return "redirect:/valmistajat";
    }

    @PostMapping("/poistaValmistaja")
    public String deleteValmistaja(@RequestParam Long valmistajaId) {
        this.valmistajaService.deleteValmistaja(valmistajaId);
        return "redirect:/valmistajat";
    }
}
