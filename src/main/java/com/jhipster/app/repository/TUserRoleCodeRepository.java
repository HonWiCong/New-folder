package com.jhipster.app.repository;

import com.jhipster.app.domain.TUserRoleCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TUserRoleCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TUserRoleCodeRepository extends JpaRepository<TUserRoleCode, Long> {}
