package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipAsiakasService {
    @Autowired
    VipAsiakasRepository vipAsiakasRepo;

    public VipAsiakas findById(Long id) {
        return vipAsiakasRepo.findById(id).orElse(null);
    }

    public VipAsiakas createVipAsiakas(String etunimi, String sukunimi,
            String sahkopostiOsoite) {
        VipAsiakas vipAsiakas = this.vipAsiakasRepo.findBySahkopostiOsoite(sahkopostiOsoite);
        if (vipAsiakas != null) {
            return vipAsiakas;
        } else {
            VipAsiakas newVipAsiakas = new VipAsiakas();
            newVipAsiakas.setEtunimi(etunimi);
            newVipAsiakas.setSukunimi(sukunimi);
            newVipAsiakas.setSahkopostiOsoite(sahkopostiOsoite);
            return vipAsiakasRepo.save(newVipAsiakas);
        }
    }

    public void deleteVipAsiakas(Long vipAsiakasId) {
        vipAsiakasRepo.deleteById(vipAsiakasId);
    }
}
