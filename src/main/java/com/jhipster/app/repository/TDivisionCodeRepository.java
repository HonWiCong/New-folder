package com.jhipster.app.repository;

import com.jhipster.app.domain.TDivisionCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TDivisionCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TDivisionCodeRepository extends JpaRepository<TDivisionCode, Long> {}
