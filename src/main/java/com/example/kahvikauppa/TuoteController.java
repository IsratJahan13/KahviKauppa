package com.example.kahvikauppa;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TuoteController {
    @Autowired
    private TuoteService tuoteService;

    @Autowired
    private OsastoService osastoService;

    @Autowired
    private ToimittajaService toimittajaService;

    @Autowired
    private ValmistajaService valmistajaService;

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("tuotteet", this.tuoteService.tuoteList());
        model.addAttribute("osastot", this.osastoService.osastoList()); // this is used for showing the option
                                                                        // lists(valitse
        // osasto) from the database
        model.addAttribute("toimittajat", this.toimittajaService.toimittajaList());
        model.addAttribute("valmistajat", this.valmistajaService.valmistajaList());
        return "admin";
    }

    @PostMapping("/admin")
    public String createTuotteet(@RequestParam String nimi, @RequestParam BigDecimal hinta,
            @RequestParam("kuva") MultipartFile kuvaFile, @RequestParam String kuvaus,
            @RequestParam Long osastoID, @RequestParam Long toimittajaID, @RequestParam Long valmistajaID)
            throws IOException {

        tuoteService.createTuotteet(nimi, hinta, kuvaFile, kuvaus, osastoID, toimittajaID, valmistajaID);
        return "redirect:/admin";
    }

    @GetMapping("/muokkaaTuote/{id}")
    public String getMuokkaaTuote(@PathVariable Long id, Model model) {
        Tuote tuote = tuoteService.findById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("kuvaus", tuote.getKuvaus());
        model.addAttribute("osastot", osastoService.osastoList());
        model.addAttribute("toimittajat", toimittajaService.toimittajaList());
        model.addAttribute("valmistajat", valmistajaService.valmistajaList());
        return "muokkaaTuote";
    }

    @PostMapping("/muokkaaTuote/{id}")
    public String muokkaaTuote(@PathVariable Long id, @RequestParam("kuva") MultipartFile kuva,
            @RequestParam String nimi, @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID, @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID) throws IOException {
        this.tuoteService.muokkaaTuote(id, kuva, nimi, hinta, kuvaus, osastoID, toimittajaID, valmistajaID);
        return "redirect:/admin";
    }

    @PostMapping("/poistaTuote")
    public String deleteTuote(@RequestParam Long tuoteId) {
        this.tuoteService.deleteTuote(tuoteId);
        return "redirect:/admin";
    }
}
