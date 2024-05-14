package com.example.kahvikauppa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class OsastoService {

    @Autowired
    OsastoRepository osastoRepo;

    public List<Osasto> osastoList() {
        return osastoRepo.findAll();
    }

    public Osasto findById(Long id) {
        return osastoRepo.findById(id).orElse(null);
    }

    public void createOsasto(String nimi, Long osastoIDP) {
        Osasto osasto = new Osasto();
        osasto.setNimi(nimi);
        osasto.setOsastoIDP(osastoIDP);
        osastoRepo.save(osasto);
    }

    public void getMuokkaaOsasto(Long id, Model model) {
        Osasto osasto = osastoRepo.findById(id).orElse(null);
        model.addAttribute("osasto", osasto);
    }

    public void muokkaaOsasto(Long id, String nimi, Long osastoIDP) {
        Osasto osasto = osastoRepo.findById(id).orElse(null);
        if (osasto != null) {
            osasto.setNimi(nimi);
            osasto.setOsastoIDP(osastoIDP);
            osastoRepo.save(osasto);
        }
    }

    public void deleteOsasto(Long osastoId) {

        Optional<Osasto> optionalOsasto = osastoRepo.findById(osastoId);

        if (optionalOsasto.isPresent()) {
            Osasto osasto = optionalOsasto.get();

            osastoRepo.delete(osasto);
        } else {

        }

    }
}
