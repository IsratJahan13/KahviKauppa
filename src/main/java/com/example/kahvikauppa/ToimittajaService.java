package com.example.kahvikauppa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public Toimittaja createToimittaja(String nimi, String yhteyshenkilo,
            String yhteyshenkilonEmail) {
        Toimittaja existingToimittaja = this.toimittajaRepo.findByNimi(nimi);
        if (existingToimittaja != null) {
            return existingToimittaja;
        } else {
            Toimittaja toimittaja = new Toimittaja();
            toimittaja.setNimi(nimi);
            toimittaja.setYhteyshenkilo(yhteyshenkilo);
            toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
            return this.toimittajaRepo.save(toimittaja);
        }
    }

    public void getMuokkaaToimittaja(Long id, Model model) {
        Toimittaja toimittaja = toimittajaRepo.findById(id).orElse(null);
        model.addAttribute("toimittaja", toimittaja);
    }

    public Toimittaja muokkaaToimittaja(Long id, String nimi,
            String yhteyshenkilo, String yhteyshenkilonEmail) {
        Toimittaja toimittaja = toimittajaRepo.findById(id).orElse(null);
        if (toimittaja != null) {
            toimittaja.setNimi(nimi);
            toimittaja.setYhteyshenkilo(yhteyshenkilo);
            toimittaja.setYhteyshenkilonEmail(yhteyshenkilonEmail);
            return this.toimittajaRepo.save(toimittaja);
        }
        return null;
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
