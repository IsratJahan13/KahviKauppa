package com.example.kahvikauppa;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KahviKauppaController {
    @Autowired
    ToimittajaRepository toimittajaRepo;

    @Autowired
    TuoteRepository tuotteetRepo;

    @Autowired
    OsastoRepository osastoRepo;

    @Autowired
    ValmistajaRepository valmistajaRepo;

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

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("osastot", this.osastoRepo.findAll()); // this is used for showing the option lists(valitse
                                                                  // osasto) from the database
        model.addAttribute("toimittajat", this.toimittajaRepo.findAll());
        model.addAttribute("valmistajat", this.valmistajaRepo.findAll());
        return "admin";
    }

    @GetMapping("/valmistajat")
    public String getValmistajat(Model model) {
        model.addAttribute("valmistajat", valmistajaRepo.findAll());
        return "valmistajat";
    }

    @PostMapping("/valmistajat")
    public String createValmistaja(@RequestParam String nimi, @RequestParam String url) {
        Valmistaja valmistaja = new Valmistaja();
        valmistaja.setNimi(nimi);
        valmistaja.setUrl(url);
        valmistajaRepo.save(valmistaja);
        return "valmistajat";
    }

    @GetMapping("/muokkaaValmistaja/{id}")
    public String getMuokkaaValmistaja(@PathVariable Long id, Model model) {
        Valmistaja valmistaja = valmistajaRepo.findById(id).orElse(null);
        model.addAttribute("valmistaja", valmistaja);
        return "muokkaaValmistaja";
    }

    @PostMapping("/muokkaaValmistaja/{id}")
    public String muokkaaValmistaja(@PathVariable Long id, @RequestParam String nimi, @RequestParam String url) {
        Valmistaja valmistaja = valmistajaRepo.findById(id).orElse(null);
        if (valmistaja != null) {
            valmistaja.setNimi(nimi);
            valmistaja.setUrl(url);
            valmistajaRepo.save(valmistaja);
        }
        return "redirect:/valmistajat";
    }

    @GetMapping("/toimittajat")
    public String getToimittajat(Model model) {
        model.addAttribute("toimittajat", toimittajaRepo.findAll());
        return "toimittajat";
    }

    @GetMapping("/osastot")
    public String getOsastot(Model model) {
        model.addAttribute("osastot", osastoRepo.findAll());
        return "osastot";
    }

    @PostMapping("/admin")
    public String createTuotteet(@RequestParam String nimi, @RequestParam String kuvaus, @RequestParam BigDecimal hinta,
            @RequestParam Long osastoID,
            @RequestParam String tuotekuva) {
        Tuote tuote = new Tuote();
        tuote.setNimi(nimi);
        tuote.setKuvaus(kuvaus);
        tuote.setHinta(hinta);
        tuote.setTuotekuva(tuotekuva);
        Osasto osasto = osastoRepo.findById(osastoID).orElse(null);

        tuotteetRepo.save(tuote);
        return "admin";
    }

    @PostMapping("/toimittajat")
    public String createToimittaja(@RequestParam String nimi, @RequestParam String yhteyshenkilo,
            @RequestParam String yhteyshenkilonEmail) {
        Toimittaja toimittaja = new Toimittaja();
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        toimittajaRepo.save(toimittaja);
        return "redirect:/toimittajat";

    }

    @PostMapping("/osastot")
    public String createOsasto(@RequestParam String nimi, @RequestParam Long osastoIDP) {
        Osasto osasto = new Osasto();
        osasto.setNimi(nimi);
        osasto.setOsastoIDP(osastoIDP);
        osastoRepo.save(osasto);
        return "redirect:/osastot";
    }

}
