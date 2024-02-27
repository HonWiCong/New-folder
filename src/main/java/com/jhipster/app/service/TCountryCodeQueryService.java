package com.jhipster.app.service;

import com.jhipster.app.domain.*; // for static metamodels
import com.jhipster.app.domain.TCountryCode;
import com.jhipster.app.repository.TCountryCodeRepository;
import com.jhipster.app.service.criteria.TCountryCodeCriteria;
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
 * Service for executing complex queries for {@link TCountryCode} entities in the database.
 * The main input is a {@link TCountryCodeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TCountryCode} or a {@link Page} of {@link TCountryCode} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TCountryCodeQueryService extends QueryService<TCountryCode> {

	private final Logger log = LoggerFactory.getLogger(TCountryCodeQueryService.class);

	private final TCountryCodeRepository tCountryCodeRepository;

	public TCountryCodeQueryService(TCountryCodeRepository tCountryCodeRepository) {
		this.tCountryCodeRepository = tCountryCodeRepository;
	}

	/**
	 * Return a {@link List} of {@link TCountryCode} which matches the criteria from the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public List<TCountryCode> findByCriteria(TCountryCodeCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specification<TCountryCode> specification = createSpecification(criteria);
		return tCountryCodeRepository.findAll(specification);
	}

	/**
	 * Return a {@link Page} of {@link TCountryCode} which matches the criteria from the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @param page The page, which should be returned.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public Page<TCountryCode> findByCriteria(TCountryCodeCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria, page);
		final Specification<TCountryCode> specification = createSpecification(criteria);
		return tCountryCodeRepository.findAll(specification, page);
	}

	/**
	 * Return the number of matching entities in the database.
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the number of matching entities.
	 */
	@Transactional(readOnly = true)
	public long countByCriteria(TCountryCodeCriteria criteria) {
		log.debug("count by criteria : {}", criteria);
		final Specification<TCountryCode> specification = createSpecification(criteria);
		return tCountryCodeRepository.count(specification);
	}

	/**
	 * Function to convert {@link TCountryCodeCriteria} to a {@link Specification}
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching {@link Specification} of the entity.
	 */
	protected Specification<TCountryCode> createSpecification(TCountryCodeCriteria criteria) {
		Specification<TCountryCode> specification = Specification.where(null);
		if (criteria != null) {
			// This has to be called first, because the distinct method returns null
			if (criteria.getDistinct() != null) {
				specification = specification.and(distinct(criteria.getDistinct()));
			}
			if (criteria.getId() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getId(), TCountryCode_.id));
			}
			if (criteria.getCountryCode() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCountryCode(), TCountryCode_.countryCode));
			}
			if (criteria.getCountryName() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCountryName(), TCountryCode_.countryName));
			}
			if (criteria.getCountryNationality() != null) {
				specification =
					specification.and(buildStringSpecification(criteria.getCountryNationality(), TCountryCode_.countryNationality));
			}
			if (criteria.getEnteredBy() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getEnteredBy(), TCountryCode_.enteredBy));
			}
			if (criteria.getEnteredDate() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getEnteredDate(), TCountryCode_.enteredDate));
			}
			if (criteria.getModifiedBy() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getModifiedBy(), TCountryCode_.modifiedBy));
			}
			if (criteria.getModifiedDate() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), TCountryCode_.modifiedDate));
			}
			if (criteria.getOrgCustomerType() != null) {
				specification = specification.and(buildStringSpecification(criteria.getOrgCustomerType(), TCountryCode_.orgCustomerType));
			}
			if (criteria.getTStateCodeId() != null) {
				specification =
					specification.and(
						buildSpecification(
							criteria.getTStateCodeId(),
							root -> root.join(TCountryCode_.tStateCodes, JoinType.LEFT).get(TStateCode_.id)
						)
					);
			}
		}
		return specification;
	}
}
