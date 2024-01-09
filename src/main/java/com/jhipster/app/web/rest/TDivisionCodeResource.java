package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TDivisionCode;
import com.jhipster.app.repository.TDivisionCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TDivisionCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TDivisionCodeResource {

    private final Logger log = LoggerFactory.getLogger(TDivisionCodeResource.class);

    private static final String ENTITY_NAME = "tDivisionCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TDivisionCodeRepository tDivisionCodeRepository;

    public TDivisionCodeResource(TDivisionCodeRepository tDivisionCodeRepository) {
        this.tDivisionCodeRepository = tDivisionCodeRepository;
    }

    /**
     * {@code POST  /t-division-codes} : Create a new tDivisionCode.
     *
     * @param tDivisionCode the tDivisionCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tDivisionCode, or with status {@code 400 (Bad Request)} if the tDivisionCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-division-codes")
    public ResponseEntity<TDivisionCode> createTDivisionCode(@RequestBody TDivisionCode tDivisionCode) throws URISyntaxException {
        log.debug("REST request to save TDivisionCode : {}", tDivisionCode);
        if (tDivisionCode.getId() != null) {
            throw new BadRequestAlertException("A new tDivisionCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TDivisionCode result = tDivisionCodeRepository.save(tDivisionCode);
        return ResponseEntity
            .created(new URI("/api/t-division-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-division-codes/:id} : Updates an existing tDivisionCode.
     *
     * @param id the id of the tDivisionCode to save.
     * @param tDivisionCode the tDivisionCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tDivisionCode,
     * or with status {@code 400 (Bad Request)} if the tDivisionCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tDivisionCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-division-codes/{id}")
    public ResponseEntity<TDivisionCode> updateTDivisionCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TDivisionCode tDivisionCode
    ) throws URISyntaxException {
        log.debug("REST request to update TDivisionCode : {}, {}", id, tDivisionCode);
        if (tDivisionCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tDivisionCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tDivisionCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TDivisionCode result = tDivisionCodeRepository.save(tDivisionCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tDivisionCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-division-codes/:id} : Partial updates given fields of an existing tDivisionCode, field will ignore if it is null
     *
     * @param id the id of the tDivisionCode to save.
     * @param tDivisionCode the tDivisionCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tDivisionCode,
     * or with status {@code 400 (Bad Request)} if the tDivisionCode is not valid,
     * or with status {@code 404 (Not Found)} if the tDivisionCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tDivisionCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-division-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TDivisionCode> partialUpdateTDivisionCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TDivisionCode tDivisionCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TDivisionCode partially : {}, {}", id, tDivisionCode);
        if (tDivisionCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tDivisionCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tDivisionCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TDivisionCode> result = tDivisionCodeRepository
            .findById(tDivisionCode.getId())
            .map(existingTDivisionCode -> {
                if (tDivisionCode.getDivName() != null) {
                    existingTDivisionCode.setDivName(tDivisionCode.getDivName());
                }
                if (tDivisionCode.getEnteredBy() != null) {
                    existingTDivisionCode.setEnteredBy(tDivisionCode.getEnteredBy());
                }
                if (tDivisionCode.getEnteredDate() != null) {
                    existingTDivisionCode.setEnteredDate(tDivisionCode.getEnteredDate());
                }
                if (tDivisionCode.getModifiedBy() != null) {
                    existingTDivisionCode.setModifiedBy(tDivisionCode.getModifiedBy());
                }
                if (tDivisionCode.getModifiedDate() != null) {
                    existingTDivisionCode.setModifiedDate(tDivisionCode.getModifiedDate());
                }

                return existingTDivisionCode;
            })
            .map(tDivisionCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tDivisionCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-division-codes} : get all the tDivisionCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tDivisionCodes in body.
     */
    @GetMapping("/t-division-codes")
    public List<TDivisionCode> getAllTDivisionCodes() {
        log.debug("REST request to get all TDivisionCodes");
        return tDivisionCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-division-codes/:id} : get the "id" tDivisionCode.
     *
     * @param id the id of the tDivisionCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tDivisionCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-division-codes/{id}")
    public ResponseEntity<TDivisionCode> getTDivisionCode(@PathVariable Long id) {
        log.debug("REST request to get TDivisionCode : {}", id);
        Optional<TDivisionCode> tDivisionCode = tDivisionCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tDivisionCode);
    }

    /**
     * {@code DELETE  /t-division-codes/:id} : delete the "id" tDivisionCode.
     *
     * @param id the id of the tDivisionCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-division-codes/{id}")
    public ResponseEntity<Void> deleteTDivisionCode(@PathVariable Long id) {
        log.debug("REST request to delete TDivisionCode : {}", id);
        tDivisionCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
