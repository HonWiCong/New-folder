package com.jhipster.app.repository;

import com.jhipster.app.domain.TAuditTrail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TAuditTrail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TAuditTrailRepository extends JpaRepository<TAuditTrail, Long> {}
