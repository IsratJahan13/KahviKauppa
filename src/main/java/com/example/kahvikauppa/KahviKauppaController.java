package com.example.kahvikauppa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
    public String getKahviLaitteet(Model model, @RequestParam("id") long osastoId,
            @RequestParam(defaultValue = "0") int page) {
        int pageSize = 6;

        // Get the current department
        Osasto department = osastoService.getOne(osastoId);
        List<Osasto> departmentTree = getDepartmentsTree(department);

        // Use custom query to fetch paginated Tuote objects for the department tree
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Tuote> tuotePage = tuoteService.findByOsastoIn(departmentTree, pageable);

        model.addAttribute("depName", department.getNimi());
        model.addAttribute("osastoId", osastoId);
        model.addAttribute("totalPages", tuotePage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageNumber", page + 1);
        model.addAttribute("tuotteet", tuotePage.getContent());

        // Add count of products to the model
        long totalKahviLaitteet = tuoteService.countTotalProducts(departmentTree);
        model.addAttribute("totalKahviLaitteet", totalKahviLaitteet);

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
