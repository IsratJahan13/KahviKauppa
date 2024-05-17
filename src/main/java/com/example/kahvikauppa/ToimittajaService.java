package com.example.kahvikauppa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ToimittajaService {
    @Autowired
    ToimittajaRepository toimittajaRepo;

    public List<Toimittaja> toimittajaList() {
        return toimittajaRepo.findAll();
    }

    public Toimittaja findById(Long id) {
        return toimittajaRepo.findById(id).orElse(null);
    }

    public void createToimittaja(String nimi, String yhteyshenkilo,
            String yhteyshenkilonEmail) {
        Toimittaja toimittaja = new Toimittaja();
        toimittaja.setNimi(nimi);
        toimittaja.setYhteyshenkilo(yhteyshenkilo);
        toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
        toimittajaRepo.save(toimittaja);
    }

    public void getMuokkaaToimittaja(Long id, Model model) {
        Toimittaja toimittaja = toimittajaRepo.findById(id).orElse(null);
        model.addAttribute("toimittaja", toimittaja);
    }

    public void muokkaaToimittaja(Long id, String nimi,
            String yhteyshenkilo, String yhteyshenkilonEmail) {
        Toimittaja toimittaja = toimittajaRepo.findById(id).orElse(null);
        if (toimittaja != null) {
            toimittaja.setNimi(nimi);
            toimittaja.setYhteyshenkilo(yhteyshenkilo);
            toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
            toimittajaRepo.save(toimittaja);
        }
    }

    // public void deleteToimittaja(Long toimittajaId) {

    // Optional<Toimittaja> optionalToimittaja =
    // toimittajaRepo.findById(toimittajaId);

    // if (optionalToimittaja.isPresent()) {
    // Toimittaja toimittaja = optionalToimittaja.get();

    // toimittajaRepo.delete(toimittaja);
    // } else {

    // }

    // }

    public void deleteToimittaja(Long toimittajaId) {
        toimittajaRepo.deleteById(toimittajaId);
    }
}
