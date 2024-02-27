package com.jhipster.app.service;

import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TStateCodeRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TStateCode}.
 */
@Service
@Transactional
public class TStateCodeService {

	private final Logger log = LoggerFactory.getLogger(TStateCodeService.class);

	private final TStateCodeRepository tStateCodeRepository;

	public TStateCodeService(TStateCodeRepository tStateCodeRepository) {
		this.tStateCodeRepository = tStateCodeRepository;
	}

	/**
	 * Save a tStateCode.
	 *
	 * @param tStateCode the entity to save.
	 * @return the persisted entity.
	 */
	public TStateCode save(TStateCode tStateCode) {
		log.debug("Request to save TStateCode : {}", tStateCode);
		return tStateCodeRepository.save(tStateCode);
	}

	/**
	 * Update a tStateCode.
	 *
	 * @param tStateCode the entity to save.
	 * @return the persisted entity.
	 */
	public TStateCode update(TStateCode tStateCode) {
		log.debug("Request to update TStateCode : {}", tStateCode);
		return tStateCodeRepository.save(tStateCode);
	}

	/**
	 * Partially update a tStateCode.
	 *
	 * @param tStateCode the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<TStateCode> partialUpdate(TStateCode tStateCode) {
		log.debug("Request to partially update TStateCode : {}", tStateCode);

		return tStateCodeRepository
			.findById(tStateCode.getId())
			.map(existingTStateCode -> {
				if (tStateCode.getStateName() != null) {
					existingTStateCode.setStateName(tStateCode.getStateName());
				}
				if (tStateCode.getStateCode() != null) {
					existingTStateCode.setStateCode(tStateCode.getStateCode());
				}
				if (tStateCode.getEnteredBy() != null) {
					existingTStateCode.setEnteredBy(tStateCode.getEnteredBy());
				}
				if (tStateCode.getEnteredDate() != null) {
					existingTStateCode.setEnteredDate(tStateCode.getEnteredDate());
				}
				if (tStateCode.getModifiedBy() != null) {
					existingTStateCode.setModifiedBy(tStateCode.getModifiedBy());
				}
				if (tStateCode.getModifiedDate() != null) {
					existingTStateCode.setModifiedDate(tStateCode.getModifiedDate());
				}

				return existingTStateCode;
			})
			.map(tStateCodeRepository::save);
	}

	/**
	 * Get all the tStateCodes.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Transactional(readOnly = true)
	public Page<TStateCode> findAll(Pageable pageable) {
		log.debug("Request to get all TStateCodes");
		return tStateCodeRepository.findAll(pageable);
	}

	/**
	 * Get one tStateCode by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public Optional<TStateCode> findOne(Long id) {
		log.debug("Request to get TStateCode : {}", id);
		return tStateCodeRepository.findById(id);
	}

	/**
	 * Delete the tStateCode by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(Long id) {
		log.debug("Request to delete TStateCode : {}", id);
		tStateCodeRepository.deleteById(id);
	}
}
