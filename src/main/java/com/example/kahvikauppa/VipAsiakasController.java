package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VipAsiakasController {
    @Autowired
    VipAsiakasRepository vipAsiakasRepo;

    @GetMapping("/vipAsiakas")
    public String getVipAsiakas() {
        return "vipAsiakas"; // This should return the form view
    }

    @GetMapping("/vipAsiakasAll")
    public String getVipAsiakasAll(Model model) {
        model.addAttribute("vipAsiakkaat", vipAsiakasRepo.findAll());
        return "vipAsiakasAll";
    }

    @PostMapping("/vipAsiakas")
    public String createVipAsiakas(@RequestParam String etunimi, @RequestParam String sukunimi,
            @RequestParam String sahkopostiOsoite) {
        VipAsiakas vipAsiakas = new VipAsiakas();
        vipAsiakas.setEtunimi(etunimi);
        vipAsiakas.setSukunimi(sukunimi);
        vipAsiakas.setSahkopostiOsoite(sahkopostiOsoite);
        vipAsiakasRepo.save(vipAsiakas);
        return "redirect:/vipAsiakas";
    }

}
