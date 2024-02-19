package com.jhipster.app.repository;

import com.jhipster.app.domain.ApplicationUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ApplicationUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>, JpaSpecificationExecutor<ApplicationUser> {}
