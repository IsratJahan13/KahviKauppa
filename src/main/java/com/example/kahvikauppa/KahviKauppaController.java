package com.example.kahvikauppa;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.tools.FileObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class KahviKauppaController {
    @Autowired
    TuoteRepository tuotteetRepo;

    @Autowired
    ToimittajaRepository toimittajaRepo;

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
        return "redirect:/valmistajat";
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

    @GetMapping("/muokkaaToimittaja/{id}")
    public String getMuokkaaToimittaja(@PathVariable Long id, Model model) {
        Toimittaja toimittaja = toimittajaRepo.findById(id).orElse(null);
        model.addAttribute("toimittaja", toimittaja);
        return "muokkaaToimittaja";
    }

    @PostMapping("/muokkaaToimittaja/{id}")
    public String muokkaaToimittaja(@PathVariable Long id, @RequestParam String nimi,
            @RequestParam String yhteyshenkilo, @RequestParam String yhteyshenkilonEmail) {
        Toimittaja toimittaja = toimittajaRepo.findById(id).orElse(null);
        if (toimittaja != null) {
            toimittaja.setNimi(nimi);
            toimittaja.setYhteyshenkilo(yhteyshenkilo);
            toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
            toimittajaRepo.save(toimittaja);
        }
        return "redirect:/toimittajat";
    }

    @PostMapping("/poistaToimittaja")
    public String deleteToimittaja(@RequestParam Long toimittajaId) {
        // Find the toimittaja by ID
        Optional<Toimittaja> optionalToimittaja = toimittajaRepo.findById(toimittajaId);

        // Check if the toimittaja exists
        if (optionalToimittaja.isPresent()) {
            Toimittaja toimittaja = optionalToimittaja.get();

            // Delete the toimittaja
            toimittajaRepo.delete(toimittaja);
        } else {
            // Handle case where toimittaja is not found
            // You might want to show an error message or redirect to an error page
        }

        return "redirect:/toimittajat";
    }

    @GetMapping("/osastot")
    public String getOsastot(Model model) {
        model.addAttribute("osastot", osastoRepo.findAll());
        return "osastot";
    }

    @PostMapping("/osastot")
    public String createOsasto(@RequestParam String nimi, @RequestParam Long osastoIDP) {
        Osasto osasto = new Osasto();
        osasto.setNimi(nimi);
        osasto.setOsastoIDP(osastoIDP);
        osastoRepo.save(osasto);
        return "redirect:/osastot";
    }

    @GetMapping("/muokkaaOsasto/{id}")
    public String getMuokkaaOsasto(@PathVariable Long id, Model model) {
        Osasto osasto = osastoRepo.findById(id).orElse(null);
        model.addAttribute("osasto", osasto);
        return "muokkaaOsasto";
    }

    @PostMapping("/muokkaaOsasto/{id}")
    public String muokkaaOsasto(@PathVariable Long id, @RequestParam String nimi, @RequestParam Long osastoIDP) {
        Osasto osasto = osastoRepo.findById(id).orElse(null);
        if (osasto != null) {
            osasto.setNimi(nimi);
            osasto.setOsastoIDP(osastoIDP);
            osastoRepo.save(osasto);
        }
        return "redirect:/osastot";
    }

    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("tuotteet", tuotteetRepo.findAll());
        model.addAttribute("osastot", this.osastoRepo.findAll()); // this is used for showing the option lists(valitse
                                                                  // osasto) from the database
        model.addAttribute("toimittajat", this.toimittajaRepo.findAll());
        model.addAttribute("valmistajat", this.valmistajaRepo.findAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String createTuotteet(@RequestParam String nimi, @RequestParam BigDecimal hinta,
            @RequestParam("kuva") MultipartFile kuvaFile, @RequestParam String kuvaus,

            @RequestParam Long osastoID, @RequestParam Long toimittajaID, @RequestParam Long valmistajaID)
            throws IOException {
        Tuote tuote = new Tuote();
        tuote.setNimi(nimi);
        tuote.setHinta(hinta);

        byte[] bytes = kuvaFile.getBytes();

        tuote.setKuva(bytes);
        tuote.setKuvaus(kuvaus);

        Osasto osasto = osastoRepo.findById(osastoID).orElse(null);
        tuote.setOsasto(osasto);
        Toimittaja toimittaja = toimittajaRepo.findById(toimittajaID).orElse(null);
        tuote.setToimittaja(toimittaja);
        Valmistaja valmistaja = valmistajaRepo.findById(valmistajaID).orElse(null);
        tuote.setValmistaja(valmistaja);

        tuotteetRepo.save(tuote);
        return "redirect:/admin";
    }

    @GetMapping("/muokkaaTuote/{id}")
    public String getMuokkaaTuote(@PathVariable Long id, Model model) {
        Tuote tuote = tuotteetRepo.findById(id).orElse(null);
        model.addAttribute("tuote", tuote);
        model.addAttribute("kuvaus", tuote.getKuvaus());
        model.addAttribute("osastot", osastoRepo.findAll());
        model.addAttribute("toimittajat", toimittajaRepo.findAll());
        model.addAttribute("valmistajat", valmistajaRepo.findAll());
        return "muokkaaTuote";
    }

    @PostMapping("/muokkaaTuote/{id}")
    public String muokkaaTuote(@PathVariable Long id, @RequestParam String nimi, @RequestParam BigDecimal hinta,
            @RequestParam String kuvaus, @RequestParam Long osastoID, @RequestParam Long toimittajaID,
            @RequestParam Long valmistajaID) {
        Tuote tuote = tuotteetRepo.findById(id).orElse(null);
        if (tuote != null) {
            tuote.setNimi(nimi);
            tuote.setHinta(hinta);
            tuote.setKuvaus(kuvaus);
            Osasto osasto = osastoRepo.findById(osastoID).orElse(null);
            tuote.setOsasto(osasto);
            Toimittaja toimittaja = toimittajaRepo.findById(toimittajaID).orElse(null);
            tuote.setToimittaja(toimittaja);
            Valmistaja valmistaja = valmistajaRepo.findById(valmistajaID).orElse(null);
            tuote.setValmistaja(valmistaja);
            tuotteetRepo.save(tuote);
        }
        return "redirect:/admin";
    }

}
