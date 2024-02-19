package com.jhipster.app.repository;

import com.jhipster.app.domain.TOfficeCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TOfficeCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TOfficeCodeRepository extends JpaRepository<TOfficeCode, Long> {}
