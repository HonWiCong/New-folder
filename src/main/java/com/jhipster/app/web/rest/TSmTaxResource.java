package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TSmTax;
import com.jhipster.app.repository.TSmTaxRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TSmTax}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TSmTaxResource {

    private final Logger log = LoggerFactory.getLogger(TSmTaxResource.class);

    private static final String ENTITY_NAME = "tSmTax";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TSmTaxRepository tSmTaxRepository;

    public TSmTaxResource(TSmTaxRepository tSmTaxRepository) {
        this.tSmTaxRepository = tSmTaxRepository;
    }

    /**
     * {@code POST  /t-sm-taxes} : Create a new tSmTax.
     *
     * @param tSmTax the tSmTax to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tSmTax, or with status {@code 400 (Bad Request)} if the tSmTax has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-sm-taxes")
    public ResponseEntity<TSmTax> createTSmTax(@RequestBody TSmTax tSmTax) throws URISyntaxException {
        log.debug("REST request to save TSmTax : {}", tSmTax);
        if (tSmTax.getId() != null) {
            throw new BadRequestAlertException("A new tSmTax cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TSmTax result = tSmTaxRepository.save(tSmTax);
        return ResponseEntity
            .created(new URI("/api/t-sm-taxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-sm-taxes/:id} : Updates an existing tSmTax.
     *
     * @param id the id of the tSmTax to save.
     * @param tSmTax the tSmTax to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSmTax,
     * or with status {@code 400 (Bad Request)} if the tSmTax is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tSmTax couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-sm-taxes/{id}")
    public ResponseEntity<TSmTax> updateTSmTax(@PathVariable(value = "id", required = false) final Long id, @RequestBody TSmTax tSmTax)
        throws URISyntaxException {
        log.debug("REST request to update TSmTax : {}, {}", id, tSmTax);
        if (tSmTax.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tSmTax.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tSmTaxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TSmTax result = tSmTaxRepository.save(tSmTax);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSmTax.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-sm-taxes/:id} : Partial updates given fields of an existing tSmTax, field will ignore if it is null
     *
     * @param id the id of the tSmTax to save.
     * @param tSmTax the tSmTax to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSmTax,
     * or with status {@code 400 (Bad Request)} if the tSmTax is not valid,
     * or with status {@code 404 (Not Found)} if the tSmTax is not found,
     * or with status {@code 500 (Internal Server Error)} if the tSmTax couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-sm-taxes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TSmTax> partialUpdateTSmTax(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TSmTax tSmTax
    ) throws URISyntaxException {
        log.debug("REST request to partial update TSmTax partially : {}, {}", id, tSmTax);
        if (tSmTax.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tSmTax.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tSmTaxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TSmTax> result = tSmTaxRepository
            .findById(tSmTax.getId())
            .map(existingTSmTax -> {
                if (tSmTax.getSmTaxCode() != null) {
                    existingTSmTax.setSmTaxCode(tSmTax.getSmTaxCode());
                }
                if (tSmTax.getSmTaxDescription() != null) {
                    existingTSmTax.setSmTaxDescription(tSmTax.getSmTaxDescription());
                }
                if (tSmTax.getSmTaxPercentage() != null) {
                    existingTSmTax.setSmTaxPercentage(tSmTax.getSmTaxPercentage());
                }
                if (tSmTax.getSmTaxType() != null) {
                    existingTSmTax.setSmTaxType(tSmTax.getSmTaxType());
                }
                if (tSmTax.getSmTaxGstCode() != null) {
                    existingTSmTax.setSmTaxGstCode(tSmTax.getSmTaxGstCode());
                }
                if (tSmTax.getSmTaxGstType() != null) {
                    existingTSmTax.setSmTaxGstType(tSmTax.getSmTaxGstType());
                }
                if (tSmTax.getSmTaxCollectedGlCode() != null) {
                    existingTSmTax.setSmTaxCollectedGlCode(tSmTax.getSmTaxCollectedGlCode());
                }
                if (tSmTax.getSmTaxCollectedGlDesc() != null) {
                    existingTSmTax.setSmTaxCollectedGlDesc(tSmTax.getSmTaxCollectedGlDesc());
                }
                if (tSmTax.getSmTaxCollectedCostCenter() != null) {
                    existingTSmTax.setSmTaxCollectedCostCenter(tSmTax.getSmTaxCollectedCostCenter());
                }
                if (tSmTax.getSmTaxPaidGlCode() != null) {
                    existingTSmTax.setSmTaxPaidGlCode(tSmTax.getSmTaxPaidGlCode());
                }
                if (tSmTax.getSmTaxPaidGlDesc() != null) {
                    existingTSmTax.setSmTaxPaidGlDesc(tSmTax.getSmTaxPaidGlDesc());
                }
                if (tSmTax.getSmTaxPaidCostCenter() != null) {
                    existingTSmTax.setSmTaxPaidCostCenter(tSmTax.getSmTaxPaidCostCenter());
                }
                if (tSmTax.getSmTaxTaxAuthority() != null) {
                    existingTSmTax.setSmTaxTaxAuthority(tSmTax.getSmTaxTaxAuthority());
                }
                if (tSmTax.getSmTaxStatus() != null) {
                    existingTSmTax.setSmTaxStatus(tSmTax.getSmTaxStatus());
                }
                if (tSmTax.getSmTaxEnteredBy() != null) {
                    existingTSmTax.setSmTaxEnteredBy(tSmTax.getSmTaxEnteredBy());
                }
                if (tSmTax.getSmTaxEnteredDate() != null) {
                    existingTSmTax.setSmTaxEnteredDate(tSmTax.getSmTaxEnteredDate());
                }
                if (tSmTax.getSmTaxModifiedBy() != null) {
                    existingTSmTax.setSmTaxModifiedBy(tSmTax.getSmTaxModifiedBy());
                }
                if (tSmTax.getSmTaxModifiedDate() != null) {
                    existingTSmTax.setSmTaxModifiedDate(tSmTax.getSmTaxModifiedDate());
                }
                if (tSmTax.getSmTaxConfirmedBy() != null) {
                    existingTSmTax.setSmTaxConfirmedBy(tSmTax.getSmTaxConfirmedBy());
                }
                if (tSmTax.getSmTaxConfirmedDate() != null) {
                    existingTSmTax.setSmTaxConfirmedDate(tSmTax.getSmTaxConfirmedDate());
                }
                if (tSmTax.getSmTaxNarration() != null) {
                    existingTSmTax.setSmTaxNarration(tSmTax.getSmTaxNarration());
                }
                if (tSmTax.getSmTaxDisplay() != null) {
                    existingTSmTax.setSmTaxDisplay(tSmTax.getSmTaxDisplay());
                }
                if (tSmTax.getSmTaxRcmFlag() != null) {
                    existingTSmTax.setSmTaxRcmFlag(tSmTax.getSmTaxRcmFlag());
                }
                if (tSmTax.getSmTaxCga() != null) {
                    existingTSmTax.setSmTaxCga(tSmTax.getSmTaxCga());
                }

                return existingTSmTax;
            })
            .map(tSmTaxRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSmTax.getId().toString())
        );
    }

    /**
     * {@code GET  /t-sm-taxes} : get all the tSmTaxes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tSmTaxes in body.
     */
    @GetMapping("/t-sm-taxes")
    public List<TSmTax> getAllTSmTaxes() {
        log.debug("REST request to get all TSmTaxes");
        return tSmTaxRepository.findAll();
    }

    /**
     * {@code GET  /t-sm-taxes/:id} : get the "id" tSmTax.
     *
     * @param id the id of the tSmTax to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tSmTax, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-sm-taxes/{id}")
    public ResponseEntity<TSmTax> getTSmTax(@PathVariable Long id) {
        log.debug("REST request to get TSmTax : {}", id);
        Optional<TSmTax> tSmTax = tSmTaxRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tSmTax);
    }

    /**
     * {@code DELETE  /t-sm-taxes/:id} : delete the "id" tSmTax.
     *
     * @param id the id of the tSmTax to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-sm-taxes/{id}")
    public ResponseEntity<Void> deleteTSmTax(@PathVariable Long id) {
        log.debug("REST request to delete TSmTax : {}", id);
        tSmTaxRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
