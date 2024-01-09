package com.jhipster.app.repository;

import com.jhipster.app.domain.TSupplierCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TSupplierCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TSupplierCategoryRepository extends JpaRepository<TSupplierCategory, Long> {}
