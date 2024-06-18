package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VipAsiakasController {
    @Autowired
    VipAsiakasRepository vipAsiakasRepo;

    @Autowired
    private VipAsiakasService vipAsiakasService;

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
            @RequestParam String sahkopostiOsoite, RedirectAttributes redirectAttributes) {
        this.vipAsiakasService.createVipAsiakas(etunimi, sukunimi, sahkopostiOsoite);

        // Add success message to redirect attributes
        redirectAttributes.addFlashAttribute("message", "Successfully added");

        return "redirect:/vipAsiakas";
    }

    @PostMapping("/poistaVipAsiakas")
    public String deleteVipAsiakas(@RequestParam Long vipAsiakasId, RedirectAttributes redirectAttributes) {
        String message = "";
        this.vipAsiakasService.deleteVipAsiakas(vipAsiakasId);
        message = "Asiakas poistettiin onnistuneesti tietokannasta.";
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/vipAsiakasAll";
    }

}
