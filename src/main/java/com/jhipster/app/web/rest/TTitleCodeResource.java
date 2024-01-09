package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TTitleCode;
import com.jhipster.app.repository.TTitleCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TTitleCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TTitleCodeResource {

    private final Logger log = LoggerFactory.getLogger(TTitleCodeResource.class);

    private static final String ENTITY_NAME = "tTitleCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TTitleCodeRepository tTitleCodeRepository;

    public TTitleCodeResource(TTitleCodeRepository tTitleCodeRepository) {
        this.tTitleCodeRepository = tTitleCodeRepository;
    }

    /**
     * {@code POST  /t-title-codes} : Create a new tTitleCode.
     *
     * @param tTitleCode the tTitleCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tTitleCode, or with status {@code 400 (Bad Request)} if the tTitleCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/t-title-codes")
    public ResponseEntity<TTitleCode> createTTitleCode(@RequestBody TTitleCode tTitleCode) throws URISyntaxException {
        log.debug("REST request to save TTitleCode : {}", tTitleCode);
        if (tTitleCode.getId() != null) {
            throw new BadRequestAlertException("A new tTitleCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TTitleCode result = tTitleCodeRepository.save(tTitleCode);
        return ResponseEntity
            .created(new URI("/api/t-title-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /t-title-codes/:id} : Updates an existing tTitleCode.
     *
     * @param id the id of the tTitleCode to save.
     * @param tTitleCode the tTitleCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tTitleCode,
     * or with status {@code 400 (Bad Request)} if the tTitleCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tTitleCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/t-title-codes/{id}")
    public ResponseEntity<TTitleCode> updateTTitleCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TTitleCode tTitleCode
    ) throws URISyntaxException {
        log.debug("REST request to update TTitleCode : {}, {}", id, tTitleCode);
        if (tTitleCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tTitleCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tTitleCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TTitleCode result = tTitleCodeRepository.save(tTitleCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tTitleCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /t-title-codes/:id} : Partial updates given fields of an existing tTitleCode, field will ignore if it is null
     *
     * @param id the id of the tTitleCode to save.
     * @param tTitleCode the tTitleCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tTitleCode,
     * or with status {@code 400 (Bad Request)} if the tTitleCode is not valid,
     * or with status {@code 404 (Not Found)} if the tTitleCode is not found,
     * or with status {@code 500 (Internal Server Error)} if the tTitleCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/t-title-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TTitleCode> partialUpdateTTitleCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TTitleCode tTitleCode
    ) throws URISyntaxException {
        log.debug("REST request to partial update TTitleCode partially : {}, {}", id, tTitleCode);
        if (tTitleCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tTitleCode.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tTitleCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TTitleCode> result = tTitleCodeRepository
            .findById(tTitleCode.getId())
            .map(existingTTitleCode -> {
                if (tTitleCode.getTtTitle() != null) {
                    existingTTitleCode.setTtTitle(tTitleCode.getTtTitle());
                }
                if (tTitleCode.getEnteredBy() != null) {
                    existingTTitleCode.setEnteredBy(tTitleCode.getEnteredBy());
                }
                if (tTitleCode.getEnteredDate() != null) {
                    existingTTitleCode.setEnteredDate(tTitleCode.getEnteredDate());
                }
                if (tTitleCode.getModifiedBy() != null) {
                    existingTTitleCode.setModifiedBy(tTitleCode.getModifiedBy());
                }
                if (tTitleCode.getModifiedDate() != null) {
                    existingTTitleCode.setModifiedDate(tTitleCode.getModifiedDate());
                }

                return existingTTitleCode;
            })
            .map(tTitleCodeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tTitleCode.getId().toString())
        );
    }

    /**
     * {@code GET  /t-title-codes} : get all the tTitleCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tTitleCodes in body.
     */
    @GetMapping("/t-title-codes")
    public List<TTitleCode> getAllTTitleCodes() {
        log.debug("REST request to get all TTitleCodes");
        return tTitleCodeRepository.findAll();
    }

    /**
     * {@code GET  /t-title-codes/:id} : get the "id" tTitleCode.
     *
     * @param id the id of the tTitleCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tTitleCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/t-title-codes/{id}")
    public ResponseEntity<TTitleCode> getTTitleCode(@PathVariable Long id) {
        log.debug("REST request to get TTitleCode : {}", id);
        Optional<TTitleCode> tTitleCode = tTitleCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tTitleCode);
    }

    /**
     * {@code DELETE  /t-title-codes/:id} : delete the "id" tTitleCode.
     *
     * @param id the id of the tTitleCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/t-title-codes/{id}")
    public ResponseEntity<Void> deleteTTitleCode(@PathVariable Long id) {
        log.debug("REST request to delete TTitleCode : {}", id);
        tTitleCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
