package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KahviKauppaController {

    @Autowired
    private TuoteService tuoteService;

    @Autowired
    private OsastoService osastoService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/tuote/{id}")
    public String tuoteDetails(@PathVariable Long id, @Param(value = "osastoId") long osastoId, Model model) {
        Tuote tuote = tuoteService.findById(id);
        model.addAttribute("tuote", tuote);
        model.addAttribute("osastoId", osastoId);
        return "tuoteSivu";
    }

    @GetMapping("/kahviLaitteet")
    public String getKahvilaitteet(Model model, @RequestParam("id") long osastoId) {
        List<Tuote> products = new ArrayList<>();
        Osasto department = osastoService.getOne(osastoId);
        model.addAttribute("depName", department.getNimi());
        model.addAttribute("osastoId", osastoId);

        List<Osasto> departmentTree = getDepartmentsTree(department);
        for (Osasto os : departmentTree) {
            products.addAll(tuoteService.findAllByOsasto(os));
        }
        model.addAttribute("tuotteet", products);

        // Long kahvilaitteetOsastoIDP = 1L;
        // model.addAttribute("tuotteet",
        // tuoteService.findByOsastoOsastoIDP(kahvilaitteetOsastoIDP));
        return "kahviLaitteet";
    }

    // @GetMapping("/kulutusTuotteet")
    // public String getKulutusTuotteet(Model model) {
    // Long kulutustuotteetOsastoIDP = 2L;
    // model.addAttribute("tuotteet",
    // tuoteService.findByOsastoOsastoIDP(kulutustuotteetOsastoIDP));
    // return "kulutusTuotteet";
    // }

    private List<Osasto> getDepartmentsTree(Osasto department) {
        List<Osasto> departments = new ArrayList<>();
        departments.add(department);
        for (Osasto child : department.getChildren()) {
            departments.addAll(getDepartmentsTree(child));
        }
        return departments;
    }

}
