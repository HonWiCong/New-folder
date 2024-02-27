package com.jhipster.app.service;

import com.jhipster.app.domain.TCountryCode;
import com.jhipster.app.repository.TCountryCodeRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TCountryCode}.
 */
@Service
@Transactional
public class TCountryCodeService {

	private final Logger log = LoggerFactory.getLogger(TCountryCodeService.class);

	private final TCountryCodeRepository tCountryCodeRepository;

	public TCountryCodeService(TCountryCodeRepository tCountryCodeRepository) {
		this.tCountryCodeRepository = tCountryCodeRepository;
	}

	/**
	 * Save a tCountryCode.
	 *
	 * @param tCountryCode the entity to save.
	 * @return the persisted entity.
	 */
	public TCountryCode save(TCountryCode tCountryCode) {
		log.debug("Request to save TCountryCode : {}", tCountryCode);
		return tCountryCodeRepository.save(tCountryCode);
	}

	/**
	 * Update a tCountryCode.
	 *
	 * @param tCountryCode the entity to save.
	 * @return the persisted entity.
	 */
	public TCountryCode update(TCountryCode tCountryCode) {
		log.debug("Request to update TCountryCode : {}", tCountryCode);
		return tCountryCodeRepository.save(tCountryCode);
	}

	/**
	 * Partially update a tCountryCode.
	 *
	 * @param tCountryCode the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<TCountryCode> partialUpdate(TCountryCode tCountryCode) {
		log.debug("Request to partially update TCountryCode : {}", tCountryCode);

		return tCountryCodeRepository
			.findById(tCountryCode.getId())
			.map(existingTCountryCode -> {
				if (tCountryCode.getCountryCode() != null) {
					existingTCountryCode.setCountryCode(tCountryCode.getCountryCode());
				}
				if (tCountryCode.getCountryName() != null) {
					existingTCountryCode.setCountryName(tCountryCode.getCountryName());
				}
				if (tCountryCode.getCountryNationality() != null) {
					existingTCountryCode.setCountryNationality(tCountryCode.getCountryNationality());
				}
				if (tCountryCode.getEnteredBy() != null) {
					existingTCountryCode.setEnteredBy(tCountryCode.getEnteredBy());
				}
				if (tCountryCode.getEnteredDate() != null) {
					existingTCountryCode.setEnteredDate(tCountryCode.getEnteredDate());
				}
				if (tCountryCode.getModifiedBy() != null) {
					existingTCountryCode.setModifiedBy(tCountryCode.getModifiedBy());
				}
				if (tCountryCode.getModifiedDate() != null) {
					existingTCountryCode.setModifiedDate(tCountryCode.getModifiedDate());
				}
				if (tCountryCode.getOrgCustomerType() != null) {
					existingTCountryCode.setOrgCustomerType(tCountryCode.getOrgCustomerType());
				}

				return existingTCountryCode;
			})
			.map(tCountryCodeRepository::save);
	}

	/**
	 * Get all the tCountryCodes.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	@Transactional(readOnly = true)
	public Page<TCountryCode> findAll(Pageable pageable) {
		log.debug("Request to get all TCountryCodes");
		return tCountryCodeRepository.findAll(pageable);
	}

	/**
	 * Get one tCountryCode by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public Optional<TCountryCode> findOne(Long id) {
		log.debug("Request to get TCountryCode : {}", id);
		return tCountryCodeRepository.findById(id);
	}

	/**
	 * Delete the tCountryCode by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(Long id) {
		log.debug("Request to delete TCountryCode : {}", id);
		tCountryCodeRepository.deleteById(id);
	}
}
