package com.jhipster.app.repository;

import com.jhipster.app.domain.TCountryCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TCountryCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCountryCodeRepository extends JpaRepository<TCountryCode, Long>, JpaSpecificationExecutor<TCountryCode> {}
