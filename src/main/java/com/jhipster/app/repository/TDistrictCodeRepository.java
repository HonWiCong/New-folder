package com.jhipster.app.repository;

import com.jhipster.app.domain.TDistrictCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TDistrictCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TDistrictCodeRepository extends JpaRepository<TDistrictCode, Long> {}
