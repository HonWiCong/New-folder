package com.jhipster.app.repository;

import com.jhipster.app.domain.TSmTax;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TSmTax entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TSmTaxRepository extends JpaRepository<TSmTax, Long> {}
