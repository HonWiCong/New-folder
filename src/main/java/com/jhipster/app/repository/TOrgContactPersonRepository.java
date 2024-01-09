package com.jhipster.app.repository;

import com.jhipster.app.domain.TOrgContactPerson;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TOrgContactPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TOrgContactPersonRepository extends JpaRepository<TOrgContactPerson, Long> {}
