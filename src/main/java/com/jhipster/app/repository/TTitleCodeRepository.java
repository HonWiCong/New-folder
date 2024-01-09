package com.jhipster.app.repository;

import com.jhipster.app.domain.TTitleCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TTitleCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TTitleCodeRepository extends JpaRepository<TTitleCode, Long> {}
