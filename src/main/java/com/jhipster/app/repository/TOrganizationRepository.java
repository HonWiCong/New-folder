package com.jhipster.app.repository;

import com.jhipster.app.domain.TOrganization;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TOrganization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TOrganizationRepository extends JpaRepository<TOrganization, Long> {}
