package com.jhipster.app.repository;

import com.jhipster.app.domain.TSectionCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TSectionCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TSectionCodeRepository extends JpaRepository<TSectionCode, Long> {}
