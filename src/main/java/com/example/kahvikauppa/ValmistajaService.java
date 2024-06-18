package com.example.kahvikauppa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ValmistajaService {
    @Autowired
    ValmistajaRepository valmistajaRepo;

    public List<Valmistaja> valmistajaList() {
        return valmistajaRepo.findAll();
    }

    public Valmistaja findById(Long id) {
        return valmistajaRepo.findById(id).orElse(null);
    }

    public Valmistaja createValmistaja(String nimi, String url) {
        Valmistaja valmistaja = this.valmistajaRepo.findByNimi(nimi);
        if (valmistaja != null) {
            return valmistaja;
        } else {
            Valmistaja newValmistaja = new Valmistaja();
            newValmistaja.setNimi(nimi);
            newValmistaja.setUrl(url);
            return this.valmistajaRepo.save(newValmistaja);
        }
    }

    public void getMuokkaaValmistaja(Long id, Model model) {
        Valmistaja valmistaja = valmistajaRepo.findById(id).orElse(null);
        model.addAttribute("valmistaja", valmistaja);
    }

    public void muokkaaValmistaja(Long id, String nimi, String url) {
        Valmistaja valmistaja = valmistajaRepo.findById(id).orElse(null);
        if (valmistaja != null) {
            valmistaja.setNimi(nimi);
            valmistaja.setUrl(url);
            valmistajaRepo.save(valmistaja);
        }
    }

    // public void deleteValmistaja(Long valmistajaId) {

    // Optional<Valmistaja> optionalValmistaja =
    // valmistajaRepo.findById(valmistajaId);

    // if (optionalValmistaja.isPresent()) {
    // Valmistaja valmistaja = optionalValmistaja.get();

    // valmistajaRepo.delete(valmistaja);
    // }
    // }

    public void deleteValmistaja(Long valmistajaId) {
        valmistajaRepo.deleteById(valmistajaId);
    }
}
