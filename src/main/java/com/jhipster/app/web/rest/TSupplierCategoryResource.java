package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TSupplierCategory;
import com.jhipster.app.repository.TSupplierCategoryRepository;
import com.jhipster.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.app.domain.TSupplierCategory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TSupplierCategoryResource {

    private final Logger log = LoggerFactory.getLogger(TSupplierCategoryResource.class);

    private static final String ENTITY_NAME = "tSupplierCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TSupplierCategoryRepository tSupplierCategoryRepository;

    public TSupplierCategoryResource(TSupplierCategoryRepository tSupplierCategoryRepository) {
        this.tSupplierCategoryRepository = tSupplierCategoryRepository;
    }

    /**
     * {@code POST  /t-supplier-categories} : Create a new tSupplierCategory.
     *
     * @param tSupplierCategory the tSupplierCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tSupplierCategory, or with status {@code 400 (Bad Request)} if the tSupplierCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-supplier-categories")
    public ResponseEntity<TSupplierCategory> createTSupplierCategory(@RequestBody TSupplierCategory tSupplierCategory)
        throws URISyntaxException {
        log.debug("REST request to save TSupplierCategory : {}", tSupplierCategory);
        if (tSupplierCategory.getId() != null) {
            throw new BadRequestAlertException("A new tSupplierCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TSupplierCategory result = tSupplierCategoryRepository.save(tSupplierCategory);
        return ResponseEntity
            .created(new URI("/api/t-supplier-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-supplier-categories/:id} : Updates an existing tSupplierCategory.
     *
     * @param id the id of the tSupplierCategory to save.
     * @param tSupplierCategory the tSupplierCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSupplierCategory,
     * or with status {@code 400 (Bad Request)} if the tSupplierCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tSupplierCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-supplier-categories/{id}")
    public ResponseEntity<TSupplierCategory> updateTSupplierCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TSupplierCategory tSupplierCategory
    ) throws URISyntaxException {
        log.debug("REST request to update TSupplierCategory : {}, {}", id, tSupplierCategory);
        if (tSupplierCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tSupplierCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tSupplierCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TSupplierCategory result = tSupplierCategoryRepository.save(tSupplierCategory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSupplierCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-supplier-categories/:id} : Partial updates given fields of an existing tSupplierCategory, field will ignore if it is null
     *
     * @param id the id of the tSupplierCategory to save.
     * @param tSupplierCategory the tSupplierCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSupplierCategory,
     * or with status {@code 400 (Bad Request)} if the tSupplierCategory is not valid,
     * or with status {@code 404 (Not Found)} if the tSupplierCategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the tSupplierCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-supplier-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TSupplierCategory> partialUpdateTSupplierCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TSupplierCategory tSupplierCategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update TSupplierCategory partially : {}, {}", id, tSupplierCategory);
        if (tSupplierCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tSupplierCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tSupplierCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TSupplierCategory> result = tSupplierCategoryRepository
            .findById(tSupplierCategory.getId())
            .map(existingTSupplierCategory -> {
                if (tSupplierCategory.getSpcCategory() != null) {
                    existingTSupplierCategory.setSpcCategory(tSupplierCategory.getSpcCategory());
                }
                if (tSupplierCategory.getEnteredBy() != null) {
                    existingTSupplierCategory.setEnteredBy(tSupplierCategory.getEnteredBy());
                }
                if (tSupplierCategory.getEnteredDate() != null) {
                    existingTSupplierCategory.setEnteredDate(tSupplierCategory.getEnteredDate());
                }
                if (tSupplierCategory.getModifiedBy() != null) {
                    existingTSupplierCategory.setModifiedBy(tSupplierCategory.getModifiedBy());
                }
                if (tSupplierCategory.getModifiedDate() != null) {
                    existingTSupplierCategory.setModifiedDate(tSupplierCategory.getModifiedDate());
                }

                return existingTSupplierCategory;
            })
            .map(tSupplierCategoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSupplierCategory.getId().toString())
        );
    }

    /**
     * {@code GET  /t-supplier-categories} : get all the tSupplierCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tSupplierCategories in body.
     */
    @GetMapping("/t-supplier-categories")
    public List<TSupplierCategory> getAllTSupplierCategories() {
        log.debug("REST request to get all TSupplierCategories");
        return tSupplierCategoryRepository.findAll();
    }

    /**
     * {@code GET  /t-supplier-categories/:id} : get the "id" tSupplierCategory.
     *
     * @param id the id of the tSupplierCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tSupplierCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-supplier-categories/{id}")
    public ResponseEntity<TSupplierCategory> getTSupplierCategory(@PathVariable Long id) {
        log.debug("REST request to get TSupplierCategory : {}", id);
        Optional<TSupplierCategory> tSupplierCategory = tSupplierCategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tSupplierCategory);
    }

    /**
     * {@code DELETE  /t-supplier-categories/:id} : delete the "id" tSupplierCategory.
     *
     * @param id the id of the tSupplierCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-supplier-categories/{id}")
    public ResponseEntity<Void> deleteTSupplierCategory(@PathVariable Long id) {
        log.debug("REST request to delete TSupplierCategory : {}", id);
        tSupplierCategoryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
