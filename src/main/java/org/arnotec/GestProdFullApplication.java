package org.arnotec;

import org.arnotec.dao.ProduitRepository;
import org.arnotec.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class
 */
@SpringBootApplication
public class GestProdFullApplication implements CommandLineRunner {
    @Autowired
    private ProduitRepository produitRepository;

    public static void main(String[] args) {

        SpringApplication.run(GestProdFullApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        produitRepository.save(new Produit(null, "PC 5146", 12000, 10));
        produitRepository.save(new Produit(null, "IMPRIMANTE LZ-5000", 6000, 5));
        produitRepository.save(new Produit(null, "PC HP-6150", 25000, 6));
        produitRepository.save(new Produit(null, "IMPRIMANTE DELL-8500", 35000, 17));

        produitRepository.findAll().forEach(produit -> {
            System.out.println(produit.getDesignation()+"\n"+" -----");
        });

    }
}
