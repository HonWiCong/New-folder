package com.jhipster.app.repository;

import com.jhipster.app.domain.TIndustryCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TIndustryCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TIndustryCodeRepository extends JpaRepository<TIndustryCode, Long> {}
