package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ValmistajaController {
    @Autowired
    private ValmistajaService valmistajaService;

    @GetMapping("/valmistajat")
    public String getValmistajat(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("valmistajat", valmistajaService.valmistajaList());
        if (!message.isEmpty()) {
            model.addAttribute("message", message);
        } else {
            model.addAttribute("message", false);
        }
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

    // @PostMapping("/poistaValmistaja")
    // public String deleteValmistaja(@RequestParam Long valmistajaId) {
    // this.valmistajaService.deleteValmistaja(valmistajaId);
    // return "redirect:/valmistajat";
    // }

    @PostMapping("/poistaValmistaja")
    public String deleteValmistaja(@RequestParam Long valmistajaId, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            this.valmistajaService.deleteValmistaja(valmistajaId);
            message = "Valmistaja poistettiin onnistuneesti tietokannasta.";

        } catch (Exception e) {
            // Set error message to be displayed in the browser
            message = "Valmistajaa ei voitu poistaa, koska siihen liittyy tuotteita tietokannassa.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/valmistajat";
    }
}
