package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TSubunitCode;
import com.jhipster.app.repository.TSubunitCodeRepository;
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
 * REST controller for managing {@link com.jhipster.app.domain.TSubunitCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TSubunitCodeResource {

	private final Logger log = LoggerFactory.getLogger(TSubunitCodeResource.class);

	private static final String ENTITY_NAME = "tSubunitCode";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TSubunitCodeRepository tSubunitCodeRepository;

	public TSubunitCodeResource(TSubunitCodeRepository tSubunitCodeRepository) {
		this.tSubunitCodeRepository = tSubunitCodeRepository;
	}

	/**
	 * {@code POST  /t-subunit-codes} : Create a new tSubunitCode.
	 *
	 * @param tSubunitCode the tSubunitCode to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tSubunitCode, or with status {@code 400 (Bad Request)} if the tSubunitCode has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-subunit-codes")
	public ResponseEntity<TSubunitCode> createTSubunitCode(@Valid @RequestBody TSubunitCode tSubunitCode) throws URISyntaxException {
		log.debug("REST request to save TSubunitCode : {}", tSubunitCode);
		if (tSubunitCode.getId() != null) {
			throw new BadRequestAlertException("A new tSubunitCode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TSubunitCode result = tSubunitCodeRepository.save(tSubunitCode);
		return ResponseEntity
			.created(new URI("/api/t-subunit-codes/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-subunit-codes/:id} : Updates an existing tSubunitCode.
	 *
	 * @param id the id of the tSubunitCode to save.
	 * @param tSubunitCode the tSubunitCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSubunitCode,
	 * or with status {@code 400 (Bad Request)} if the tSubunitCode is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tSubunitCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-subunit-codes/{id}")
	public ResponseEntity<TSubunitCode> updateTSubunitCode(
		@PathVariable(value = "id", required = false) final Long id,
		@Valid @RequestBody TSubunitCode tSubunitCode
	) throws URISyntaxException {
		log.debug("REST request to update TSubunitCode : {}, {}", id, tSubunitCode);
		if (tSubunitCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tSubunitCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tSubunitCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TSubunitCode result = tSubunitCodeRepository.save(tSubunitCode);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSubunitCode.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-subunit-codes/:id} : Partial updates given fields of an existing tSubunitCode, field will ignore if it is null
	 *
	 * @param id the id of the tSubunitCode to save.
	 * @param tSubunitCode the tSubunitCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tSubunitCode,
	 * or with status {@code 400 (Bad Request)} if the tSubunitCode is not valid,
	 * or with status {@code 404 (Not Found)} if the tSubunitCode is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tSubunitCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-subunit-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TSubunitCode> partialUpdateTSubunitCode(
		@PathVariable(value = "id", required = false) final Long id,
		@NotNull @RequestBody TSubunitCode tSubunitCode
	) throws URISyntaxException {
		log.debug("REST request to partial update TSubunitCode partially : {}, {}", id, tSubunitCode);
		if (tSubunitCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tSubunitCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tSubunitCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TSubunitCode> result = tSubunitCodeRepository
			.findById(tSubunitCode.getId())
			.map(existingTSubunitCode -> {
				if (tSubunitCode.getSubuntSubunit() != null) {
					existingTSubunitCode.setSubuntSubunit(tSubunitCode.getSubuntSubunit());
				}
				if (tSubunitCode.getSubuntHeadid() != null) {
					existingTSubunitCode.setSubuntHeadid(tSubunitCode.getSubuntHeadid());
				}
				if (tSubunitCode.getSubuntActingHeadid() != null) {
					existingTSubunitCode.setSubuntActingHeadid(tSubunitCode.getSubuntActingHeadid());
				}
				if (tSubunitCode.getSubuntHrpayId() != null) {
					existingTSubunitCode.setSubuntHrpayId(tSubunitCode.getSubuntHrpayId());
				}
				if (tSubunitCode.getSubuntPapId() != null) {
					existingTSubunitCode.setSubuntPapId(tSubunitCode.getSubuntPapId());
				}
				if (tSubunitCode.getSubuntPapCode() != null) {
					existingTSubunitCode.setSubuntPapCode(tSubunitCode.getSubuntPapCode());
				}
				if (tSubunitCode.getSubuntPapActive() != null) {
					existingTSubunitCode.setSubuntPapActive(tSubunitCode.getSubuntPapActive());
				}
				if (tSubunitCode.getSubunyUnitid() != null) {
					existingTSubunitCode.setSubunyUnitid(tSubunitCode.getSubunyUnitid());
				}

				return existingTSubunitCode;
			})
			.map(tSubunitCodeRepository::save);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tSubunitCode.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-subunit-codes} : get all the tSubunitCodes.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tSubunitCodes in body.
	 */
	@GetMapping("/t-subunit-codes")
	public List<TSubunitCode> getAllTSubunitCodes() {
		log.debug("REST request to get all TSubunitCodes");
		return tSubunitCodeRepository.findAll();
	}

	/**
	 * {@code GET  /t-subunit-codes/:id} : get the "id" tSubunitCode.
	 *
	 * @param id the id of the tSubunitCode to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tSubunitCode, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-subunit-codes/{id}")
	public ResponseEntity<TSubunitCode> getTSubunitCode(@PathVariable Long id) {
		log.debug("REST request to get TSubunitCode : {}", id);
		Optional<TSubunitCode> tSubunitCode = tSubunitCodeRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tSubunitCode);
	}

	/**
	 * {@code DELETE  /t-subunit-codes/:id} : delete the "id" tSubunitCode.
	 *
	 * @param id the id of the tSubunitCode to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-subunit-codes/{id}")
	public ResponseEntity<Void> deleteTSubunitCode(@PathVariable Long id) {
		log.debug("REST request to delete TSubunitCode : {}", id);
		tSubunitCodeRepository.deleteById(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
