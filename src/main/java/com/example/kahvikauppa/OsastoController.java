package com.example.kahvikauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OsastoController {
    @Autowired
    private OsastoService osastoService;

    @GetMapping("/osastot")
    public String getOsastot(Model model) {
        model.addAttribute("osastot", this.osastoService.osastoList());
        return "osastot";
    }

    @PostMapping("/osastot")
    public String createOsasto(@RequestParam String nimi, @RequestParam Long osastoIDP) {
        this.osastoService.createOsasto(nimi, osastoIDP);
        return "redirect:/osastot";
    }

    @GetMapping("/muokkaaOsasto/{id}")
    public String getMuokkaaOsasto(@PathVariable Long id, Model model) {
        Osasto osasto = osastoService.findById(id);
        if (osasto != null) {
            model.addAttribute("osasto", osasto);
            return "muokkaaOsasto";
        } else {
            // Handle case where Osasto is not found
            // You might want to show an error message or redirect to an error page
            return "redirect:/osastot";
        }
    }

    @PostMapping("/muokkaaOsasto/{id}")
    public String muokkaaOsasto(@PathVariable Long id, @RequestParam String nimi, @RequestParam Long osastoIDP) {
        this.osastoService.muokkaaOsasto(id, nimi, osastoIDP);
        return "redirect:/osastot";
    }

    @PostMapping("/poistaOsasto")
    public String deleteOsasto(@RequestParam Long osastoId, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            this.osastoService.deleteOsasto(osastoId);
            message = "Osasto poistettiin onnistuneesti tietokannasta.";

        } catch (Exception e) {
            // Set error message to be displayed in the browser
            message = "Osasto ei voitu poistaa, koska siihen liittyy tuotteita tietokannassa.";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/osastot";
    }
}
