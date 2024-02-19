package com.jhipster.app.repository;

import com.jhipster.app.domain.TUserRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TUserRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TUserRoleRepository extends JpaRepository<TUserRole, Long> {}
