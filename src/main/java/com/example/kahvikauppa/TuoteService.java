package com.example.kahvikauppa;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TuoteService {

    @Autowired
    TuoteRepository tuotteetRepo;

    @Autowired
    OsastoRepository osastoRepo;

    @Autowired
    ToimittajaRepository toimittajaRepo;

    @Autowired
    ValmistajaRepository valmistajaRepo;

    public List<Tuote> tuoteList() {
        return tuotteetRepo.findAll();
    }

    public List<Osasto> osastoList() {
        return osastoRepo.findAll();
    }

    public List<Toimittaja> toimittajaList() {
        return toimittajaRepo.findAll();
    }

    public List<Valmistaja> valmistajaList() {
        return valmistajaRepo.findAll();
    }

    public Tuote findById(Long id) {
        return this.tuotteetRepo.findById(id).orElse(null);
    }

    public void createTuotteet(String nimi, BigDecimal hinta,
            MultipartFile kuvaFile, String kuvaus,
            Long osastoID, Long toimittajaID, Long valmistajaID)
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
    }

    public Tuote getProductImage(Long id) {
        return tuotteetRepo.findById(id).orElse(null);
    }

    public void getMuokkaaTuote(Long id, Model model) {
        Tuote tuote = tuotteetRepo.findById(id).orElse(null);
        model.addAttribute("tuote", tuote);
        model.addAttribute("kuva", tuote.getKuva());
        model.addAttribute("kuvaus", tuote.getKuvaus());
        model.addAttribute("osastot", osastoRepo.findAll());
        model.addAttribute("toimittajat", toimittajaRepo.findAll());
        model.addAttribute("valmistajat", valmistajaRepo.findAll());
    }

    public void muokkaaTuote(Long id, MultipartFile kuva, String nimi, BigDecimal hinta,
            String kuvaus, Long osastoID, Long toimittajaID,
            Long valmistajaID) throws IOException {
        Tuote tuote = tuotteetRepo.findById(id).orElse(null);
        if (tuote != null) {
            if (tuote != null) {
                if (!kuva.isEmpty()) {
                    byte[] kuvaBytes = kuva.getBytes();
                    tuote.setKuva(kuvaBytes);
                }
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
        }
    }

    public void deleteTuote(Long tuoteId) {
        // Find the Tuote by ID
        Optional<Tuote> optionalTuote = tuotteetRepo.findById(tuoteId);

        // Check if the Tuote exists
        if (optionalTuote.isPresent()) {
            Tuote tuote = optionalTuote.get();

            // Delete the Tuote
            tuotteetRepo.delete(tuote);
        } else {
            // Handle case where Tuote is not found
            // You might want to show an error message or redirect to an error page
        }
    }
}
