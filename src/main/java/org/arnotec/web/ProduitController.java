package org.arnotec.web;

import org.arnotec.dao.ProduitRepository;
import org.arnotec.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ProduitController
 */
@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/user/index")
    public String chercher(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "motCle", defaultValue = "") String mc) {
        Page<Produit> pageProduits = produitRepository.findByDesignationContains(mc, PageRequest.of(page, 5));
        model.addAttribute("listProduits", pageProduits.getContent());
        model.addAttribute("pages", new int[pageProduits.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", mc);
        return "produits";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, int page, String motCle) {
        produitRepository.deleteById(id);
        return "redirect:/user/index?page=" + page + "&motCle=" + motCle;
    }

    @GetMapping("/admin/formProduit")
    public String form(Model model) {
        model.addAttribute("produit", new Produit());
        return "FormProduit";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, Long id) {
        Produit produit = produitRepository.findById(id).get();
        model.addAttribute("produit", produit);
        return "EditProduit";
    }

    @PostMapping("/admin/save")
    public String save(@Valid Produit produit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "FormProduit";
        }
        produitRepository.save(produit);
        return "redirect:/user/index";
    }

    @GetMapping("/")
    public String defaut() {
        return "redirect:/user/index";
    }

}
