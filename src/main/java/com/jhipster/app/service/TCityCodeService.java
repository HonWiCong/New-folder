package com.jhipster.app.service;

import com.jhipster.app.domain.TCityCode;
import com.jhipster.app.repository.TCityCodeRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TCityCode}.
 */
@Service
@Transactional
public class TCityCodeService {

	private final Logger log = LoggerFactory.getLogger(TCityCodeService.class);

	private final TCityCodeRepository tCityCodeRepository;

	public TCityCodeService(TCityCodeRepository tCityCodeRepository) {
		this.tCityCodeRepository = tCityCodeRepository;
	}

	/**
	 * Save a tCityCode.
	 *
	 * @param tCityCode the entity to save.
	 * @return the persisted entity.
	 */
	public TCityCode save(TCityCode tCityCode) {
		log.debug("Request to save TCityCode : {}", tCityCode);
		return tCityCodeRepository.save(tCityCode);
	}

	/**
	 * Update a tCityCode.
	 *
	 * @param tCityCode the entity to save.
	 * @return the persisted entity.
	 */
	public TCityCode update(TCityCode tCityCode) {
		log.debug("Request to update TCityCode : {}", tCityCode);
		return tCityCodeRepository.save(tCityCode);
	}

	/**
	 * Partially update a tCityCode.
	 *
	 * @param tCityCode the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<TCityCode> partialUpdate(TCityCode tCityCode) {
		log.debug("Request to partially update TCityCode : {}", tCityCode);

		return tCityCodeRepository
			.findById(tCityCode.getId())
			.map(existingTCityCode -> {
				if (tCityCode.getCityCode() != null) {
					existingTCityCode.setCityCode(tCityCode.getCityCode());
				}
				if (tCityCode.getCityName() != null) {
					existingTCityCode.setCityName(tCityCode.getCityName());
				}
				if (tCityCode.getEnteredBy() != null) {
					existingTCityCode.setEnteredBy(tCityCode.getEnteredBy());
				}
				if (tCityCode.getEnteredDate() != null) {
					existingTCityCode.setEnteredDate(tCityCode.getEnteredDate());
				}
				if (tCityCode.getModifiedBy() != null) {
					existingTCityCode.setModifiedBy(tCityCode.getModifiedBy());
				}
				if (tCityCode.getModifiedDate() != null) {
					existingTCityCode.setModifiedDate(tCityCode.getModifiedDate());
				}

				return existingTCityCode;
			})
			.map(tCityCodeRepository::save);
	}

	/**
	 * Get all the tCityCodes.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Transactional(readOnly = true)
	public Page<TCityCode> findAll(Pageable pageable) {
		log.debug("Request to get all TCityCodes");
		return tCityCodeRepository.findAll(pageable);
	}

	/**
	 * Get one tCityCode by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public Optional<TCityCode> findOne(Long id) {
		log.debug("Request to get TCityCode : {}", id);
		return tCityCodeRepository.findById(id);
	}

	/**
	 * Delete the tCityCode by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(Long id) {
		log.debug("Request to delete TCityCode : {}", id);
		tCityCodeRepository.deleteById(id);
	}
}
