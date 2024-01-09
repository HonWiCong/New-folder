package com.jhipster.app.repository;

import com.jhipster.app.domain.TStateCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TStateCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TStateCodeRepository extends JpaRepository<TStateCode, Long> {}
