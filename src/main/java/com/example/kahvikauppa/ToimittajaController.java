package com.example.kahvikauppa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ToimittajaController {
    @Autowired
    private ToimittajaService toimittajaService;

    @GetMapping("/toimittajat")
    public String getToimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaService.toimittajaList());
        return "toimittajat";
    }

    @PostMapping("/toimittajat")
    public String createToimittaja(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkilonEmail) {
        this.toimittajaService.createToimittaja(nimi, yhteyshenkilo, yhteyshenkilonEmail);

        return "redirect:/toimittajat";
    }

    @GetMapping("/muokkaaToimittaja/{id}")
    public String getMuokkaaToimittaja(@PathVariable Long id, Model model) {
        Toimittaja toimittaja = toimittajaService.findById(id);
        if (toimittaja != null) {
            model.addAttribute("toimittaja", toimittaja);
            return "muokkaaToimittaja";
        } else {
            return "redirect:/toimittajat";
        }
    }

    @PostMapping("/muokkaaToimittaja/{id}")
    public String muokkaaToimittaja(@PathVariable Long id, @RequestParam String nimi,
            @RequestParam String yhteyshenkilo, @RequestParam String yhteyshenkilonEmail) {
        this.toimittajaService.muokkaaToimittaja(id, nimi, yhteyshenkilo, yhteyshenkilonEmail);
        return "redirect:/toimittajat";
    }

    @PostMapping("/poistaToimittaja")
    public String deleteToimittaja(@RequestParam Long toimittajaId, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            this.toimittajaService.deleteToimittaja(toimittajaId);
            message = "Toimittaja poistettiin onnistuneesti tietokannasta.";

        } catch (Exception e) {
            // Set error message to be displayed in the browser
            message = "Toimittaja ei voitu poistaa, koska siihen liittyy tuotteita tietokannassa.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/toimittajat";
    }
}