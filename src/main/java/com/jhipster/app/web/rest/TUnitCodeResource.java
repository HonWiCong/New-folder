package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TUnitCode;
import com.jhipster.app.repository.TUnitCodeRepository;
import com.jhipster.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.app.domain.TUnitCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TUnitCodeResource {

	private final Logger log = LoggerFactory.getLogger(TUnitCodeResource.class);

	private static final String ENTITY_NAME = "tUnitCode";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TUnitCodeRepository tUnitCodeRepository;

	public TUnitCodeResource(TUnitCodeRepository tUnitCodeRepository) {
		this.tUnitCodeRepository = tUnitCodeRepository;
	}

	/**
	 * {@code POST  /t-unit-codes} : Create a new tUnitCode.
	 *
	 * @param tUnitCode the tUnitCode to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tUnitCode, or with status {@code 400 (Bad Request)} if the tUnitCode has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-unit-codes")
	public ResponseEntity<TUnitCode> createTUnitCode(@Valid @RequestBody TUnitCode tUnitCode) throws URISyntaxException {
		log.debug("REST request to save TUnitCode : {}", tUnitCode);
		if (tUnitCode.getId() != null) {
			throw new BadRequestAlertException("A new tUnitCode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TUnitCode result = tUnitCodeRepository.save(tUnitCode);
		return ResponseEntity
			.created(new URI("/api/t-unit-codes/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-unit-codes/:id} : Updates an existing tUnitCode.
	 *
	 * @param id the id of the tUnitCode to save.
	 * @param tUnitCode the tUnitCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tUnitCode,
	 * or with status {@code 400 (Bad Request)} if the tUnitCode is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tUnitCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-unit-codes/{id}")
	public ResponseEntity<TUnitCode> updateTUnitCode(
		@PathVariable(value = "id", required = false) final Long id,
		@Valid @RequestBody TUnitCode tUnitCode
	) throws URISyntaxException {
		log.debug("REST request to update TUnitCode : {}, {}", id, tUnitCode);
		if (tUnitCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tUnitCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tUnitCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TUnitCode result = tUnitCodeRepository.save(tUnitCode);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tUnitCode.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-unit-codes/:id} : Partial updates given fields of an existing tUnitCode, field will ignore if it is null
	 *
	 * @param id the id of the tUnitCode to save.
	 * @param tUnitCode the tUnitCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tUnitCode,
	 * or with status {@code 400 (Bad Request)} if the tUnitCode is not valid,
	 * or with status {@code 404 (Not Found)} if the tUnitCode is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tUnitCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-unit-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TUnitCode> partialUpdateTUnitCode(
		@PathVariable(value = "id", required = false) final Long id,
		@NotNull @RequestBody TUnitCode tUnitCode
	) throws URISyntaxException {
		log.debug("REST request to partial update TUnitCode partially : {}, {}", id, tUnitCode);
		if (tUnitCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tUnitCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tUnitCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TUnitCode> result = tUnitCodeRepository
			.findById(tUnitCode.getId())
			.map(existingTUnitCode -> {
				if (tUnitCode.getUntUnit() != null) {
					existingTUnitCode.setUntUnit(tUnitCode.getUntUnit());
				}
				if (tUnitCode.getUntHeadid() != null) {
					existingTUnitCode.setUntHeadid(tUnitCode.getUntHeadid());
				}
				if (tUnitCode.getUntActingHeadid() != null) {
					existingTUnitCode.setUntActingHeadid(tUnitCode.getUntActingHeadid());
				}
				if (tUnitCode.getUntHrpayId() != null) {
					existingTUnitCode.setUntHrpayId(tUnitCode.getUntHrpayId());
				}
				if (tUnitCode.getUntPapId() != null) {
					existingTUnitCode.setUntPapId(tUnitCode.getUntPapId());
				}
				if (tUnitCode.getUntPapCode() != null) {
					existingTUnitCode.setUntPapCode(tUnitCode.getUntPapCode());
				}
				if (tUnitCode.getUntPapActive() != null) {
					existingTUnitCode.setUntPapActive(tUnitCode.getUntPapActive());
				}
				if (tUnitCode.getUntPapDepartmentid() != null) {
					existingTUnitCode.setUntPapDepartmentid(tUnitCode.getUntPapDepartmentid());
				}
				if (tUnitCode.getEnteredBy() != null) {
					existingTUnitCode.setEnteredBy(tUnitCode.getEnteredBy());
				}
				if (tUnitCode.getEnteredDate() != null) {
					existingTUnitCode.setEnteredDate(tUnitCode.getEnteredDate());
				}
				if (tUnitCode.getModifiedBy() != null) {
					existingTUnitCode.setModifiedBy(tUnitCode.getModifiedBy());
				}
				if (tUnitCode.getModifiedDate() != null) {
					existingTUnitCode.setModifiedDate(tUnitCode.getModifiedDate());
				}

				return existingTUnitCode;
			})
			.map(tUnitCodeRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tUnitCode.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-unit-codes} : get all the tUnitCodes.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tUnitCodes in body.
	 */
	@GetMapping("/t-unit-codes")
	public List<TUnitCode> getAllTUnitCodes() {
		log.debug("REST request to get all TUnitCodes");
		return tUnitCodeRepository.findAll();
	}

	/**
	 * {@code GET  /t-unit-codes/:id} : get the "id" tUnitCode.
	 *
	 * @param id the id of the tUnitCode to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tUnitCode, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-unit-codes/{id}")
	public ResponseEntity<TUnitCode> getTUnitCode(@PathVariable Long id) {
		log.debug("REST request to get TUnitCode : {}", id);
		Optional<TUnitCode> tUnitCode = tUnitCodeRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tUnitCode);
	}

	/**
	 * {@code DELETE  /t-unit-codes/:id} : delete the "id" tUnitCode.
	 *
	 * @param id the id of the tUnitCode to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-unit-codes/{id}")
	public ResponseEntity<Void> deleteTUnitCode(@PathVariable Long id) {
		log.debug("REST request to delete TUnitCode : {}", id);
		tUnitCodeRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
