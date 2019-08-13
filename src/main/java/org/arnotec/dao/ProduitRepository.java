package org.arnotec.dao;

import org.arnotec.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    public Page<Produit> findByDesignationContains(String mc, Pageable pageable);
}
