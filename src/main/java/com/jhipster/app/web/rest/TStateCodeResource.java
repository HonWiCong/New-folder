package com.jhipster.app.web.rest;

import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TStateCodeRepository;
import com.jhipster.app.service.TStateCodeQueryService;
import com.jhipster.app.service.TStateCodeService;
import com.jhipster.app.service.criteria.TStateCodeCriteria;
import com.jhipster.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.jhipster.app.domain.TStateCode}.
 */
@RestController
@RequestMapping("/api")
public class TStateCodeResource {

	private final Logger log = LoggerFactory.getLogger(TStateCodeResource.class);

	private static final String ENTITY_NAME = "tStateCode";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final TStateCodeService tStateCodeService;

	private final TStateCodeRepository tStateCodeRepository;

	private final TStateCodeQueryService tStateCodeQueryService;

	public TStateCodeResource(
		TStateCodeService tStateCodeService,
		TStateCodeRepository tStateCodeRepository,
		TStateCodeQueryService tStateCodeQueryService
	) {
		this.tStateCodeService = tStateCodeService;
		this.tStateCodeRepository = tStateCodeRepository;
		this.tStateCodeQueryService = tStateCodeQueryService;
	}

	/**
	 * {@code POST  /t-state-codes} : Create a new tStateCode.
	 *
	 * @param tStateCode the tStateCode to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tStateCode, or with status {@code 400 (Bad Request)} if the tStateCode has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/t-state-codes")
	public ResponseEntity<TStateCode> createTStateCode(@RequestBody TStateCode tStateCode) throws URISyntaxException {
		log.debug("REST request to save TStateCode : {}", tStateCode);
		if (tStateCode.getId() != null) {
			throw new BadRequestAlertException("A new tStateCode cannot already have an ID", ENTITY_NAME, "idexists");
		}
		TStateCode result = tStateCodeService.save(tStateCode);
		return ResponseEntity
			.created(new URI("/api/t-state-codes/" + result.getId()))
			.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PUT  /t-state-codes/:id} : Updates an existing tStateCode.
	 *
	 * @param id the id of the tStateCode to save.
	 * @param tStateCode the tStateCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tStateCode,
	 * or with status {@code 400 (Bad Request)} if the tStateCode is not valid,
	 * or with status {@code 500 (Internal Server Error)} if the tStateCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/t-state-codes/{id}")
	public ResponseEntity<TStateCode> updateTStateCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TStateCode tStateCode
	) throws URISyntaxException {
		log.debug("REST request to update TStateCode : {}, {}", id, tStateCode);
		if (tStateCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tStateCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tStateCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		TStateCode result = tStateCodeService.update(tStateCode);
		return ResponseEntity
			.ok()
			.headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tStateCode.getId().toString()))
			.body(result);
	}

	/**
	 * {@code PATCH  /t-state-codes/:id} : Partial updates given fields of an existing tStateCode, field will ignore if it is null
	 *
	 * @param id the id of the tStateCode to save.
	 * @param tStateCode the tStateCode to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tStateCode,
	 * or with status {@code 400 (Bad Request)} if the tStateCode is not valid,
	 * or with status {@code 404 (Not Found)} if the tStateCode is not found,
	 * or with status {@code 500 (Internal Server Error)} if the tStateCode couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PatchMapping(value = "/t-state-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
	public ResponseEntity<TStateCode> partialUpdateTStateCode(
		@PathVariable(value = "id", required = false) final Long id,
		@RequestBody TStateCode tStateCode
	) throws URISyntaxException {
		log.debug("REST request to partial update TStateCode partially : {}, {}", id, tStateCode);
		if (tStateCode.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		if (!Objects.equals(id, tStateCode.getId())) {
			throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
		}

		if (!tStateCodeRepository.existsById(id)) {
			throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
		}

		Optional<TStateCode> result = tStateCodeService.partialUpdate(tStateCode);

		return ResponseUtil.wrapOrNotFound(
			result,
			HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tStateCode.getId().toString())
		);
	}

	/**
	 * {@code GET  /t-state-codes} : get all the tStateCodes.
	 *
	 * @param pageable the pagination information.
	 * @param criteria the criteria which the requested entities should match.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tStateCodes in body.
	 */
	@GetMapping("/t-state-codes")
	public ResponseEntity<List<TStateCode>> getAllTStateCodes(
		TStateCodeCriteria criteria,
		@org.springdoc.api.annotations.ParameterObject Pageable pageable
	) {
		log.debug("REST request to get TStateCodes by criteria: {}", criteria);
		Page<TStateCode> page = tStateCodeQueryService.findByCriteria(criteria, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /t-state-codes/count} : count all the tStateCodes.
	 *
	 * @param criteria the criteria which the requested entities should match.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
	 */
	@GetMapping("/t-state-codes/count")
	public ResponseEntity<Long> countTStateCodes(TStateCodeCriteria criteria) {
		log.debug("REST request to count TStateCodes by criteria: {}", criteria);
		return ResponseEntity.ok().body(tStateCodeQueryService.countByCriteria(criteria));
	}

	/**
	 * {@code GET  /t-state-codes/:id} : get the "id" tStateCode.
	 *
	 * @param id the id of the tStateCode to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tStateCode, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/t-state-codes/{id}")
	public ResponseEntity<TStateCode> getTStateCode(@PathVariable Long id) {
		log.debug("REST request to get TStateCode : {}", id);
		Optional<TStateCode> tStateCode = tStateCodeService.findOne(id);
		return ResponseUtil.wrapOrNotFound(tStateCode);
	}

	/**
	 * {@code DELETE  /t-state-codes/:id} : delete the "id" tStateCode.
	 *
	 * @param id the id of the tStateCode to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/t-state-codes/{id}")
	public ResponseEntity<Void> deleteTStateCode(@PathVariable Long id) {
		log.debug("REST request to delete TStateCode : {}", id);
		tStateCodeService.delete(id);
		return ResponseEntity
			.noContent()
			.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
			.build();
	}
}
