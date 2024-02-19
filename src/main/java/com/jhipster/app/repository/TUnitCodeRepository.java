package com.jhipster.app.repository;

import com.jhipster.app.domain.TUnitCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TUnitCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TUnitCodeRepository extends JpaRepository<TUnitCode, Long> {}
