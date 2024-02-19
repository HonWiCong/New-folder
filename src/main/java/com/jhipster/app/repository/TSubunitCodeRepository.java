package com.jhipster.app.repository;

import com.jhipster.app.domain.TSubunitCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TSubunitCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TSubunitCodeRepository extends JpaRepository<TSubunitCode, Long> {}
