package com.jhipster.app.repository;

import com.jhipster.app.domain.TBrandCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TBrandCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TBrandCodeRepository extends JpaRepository<TBrandCode, Long> {}
