package com.jhipster.app.repository;

import com.jhipster.app.domain.TCityCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TCityCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TCityCodeRepository extends JpaRepository<TCityCode, Long>, JpaSpecificationExecutor<TCityCode> {}
