package com.jhipster.app.repository;

import com.jhipster.app.domain.TSectorCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TSectorCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TSectorCodeRepository extends JpaRepository<TSectorCode, Long> {}
