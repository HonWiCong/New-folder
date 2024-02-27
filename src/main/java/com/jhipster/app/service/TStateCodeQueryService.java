package com.jhipster.app.service;

import com.jhipster.app.domain.*; // for static metamodels
import com.jhipster.app.domain.TStateCode;
import com.jhipster.app.repository.TStateCodeRepository;
import com.jhipster.app.service.criteria.TStateCodeCriteria;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link TStateCode} entities in the database.
 * The main input is a {@link TStateCodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TStateCode} or a {@link Page} of {@link TStateCode} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TStateCodeQueryService extends QueryService<TStateCode> {

	private final Logger log = LoggerFactory.getLogger(TStateCodeQueryService.class);

	private final TStateCodeRepository tStateCodeRepository;

	public TStateCodeQueryService(TStateCodeRepository tStateCodeRepository) {
		this.tStateCodeRepository = tStateCodeRepository;
	}

	/**
	 * Return a {@link List} of {@link TStateCode} which matches the criteria from the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public List<TStateCode> findByCriteria(TStateCodeCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specification<TStateCode> specification = createSpecification(criteria);
		return tStateCodeRepository.findAll(specification);
	}

	/**
	 * Return a {@link Page} of {@link TStateCode} which matches the criteria from the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @param page The page, which should be returned.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public Page<TStateCode> findByCriteria(TStateCodeCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria, page);
		final Specification<TStateCode> specification = createSpecification(criteria);
		return tStateCodeRepository.findAll(specification, page);
	}

	/**
	 * Return the number of matching entities in the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the number of matching entities.
	 */
	@Transactional(readOnly = true)
	public long countByCriteria(TStateCodeCriteria criteria) {
		log.debug("count by criteria : {}", criteria);
		final Specification<TStateCode> specification = createSpecification(criteria);
		return tStateCodeRepository.count(specification);
	}

	/**
	 * Function to convert {@link TStateCodeCriteria} to a {@link Specification}
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching {@link Specification} of the entity.
	 */
	protected Specification<TStateCode> createSpecification(TStateCodeCriteria criteria) {
		Specification<TStateCode> specification = Specification.where(null);
		if (criteria != null) {
			// This has to be called first, because the distinct method returns null
			if (criteria.getDistinct() != null) {
				specification = specification.and(distinct(criteria.getDistinct()));
			}
			if (criteria.getId() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getId(), TStateCode_.id));
			}
			if (criteria.getStateName() != null) {
				specification = specification.and(buildStringSpecification(criteria.getStateName(), TStateCode_.stateName));
			}
			if (criteria.getStateCode() != null) {
				specification = specification.and(buildStringSpecification(criteria.getStateCode(), TStateCode_.stateCode));
			}
			if (criteria.getEnteredBy() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getEnteredBy(), TStateCode_.enteredBy));
			}
			if (criteria.getEnteredDate() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getEnteredDate(), TStateCode_.enteredDate));
			}
			if (criteria.getModifiedBy() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getModifiedBy(), TStateCode_.modifiedBy));
			}
			if (criteria.getModifiedDate() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), TStateCode_.modifiedDate));
			}
			if (criteria.getTCountryCodeId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getTCountryCodeId(),
							root -> root.join(TStateCode_.tCountryCode, JoinType.LEFT).get(TCountryCode_.id)
						)
					);
			}
			if (criteria.getTCityCodeId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getTCityCodeId(),
							root -> root.join(TStateCode_.tCityCodes, JoinType.LEFT).get(TCityCode_.id)
						)
					);
			}
			if (criteria.getCountryName() != null) {
				specification =
					specification.or(
						buildSpecification(
							criteria.getCountryName(),
							root -> root.join(TStateCode_.tCountryCode, JoinType.LEFT).get(TCountryCode_.countryName)
						)
					);
			}
		}
		return specification;
	}
}
