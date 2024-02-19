package com.jhipster.app.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

	private GitProperties gitProperties;
	private BuildProperties buildProperties;
	private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

	public CacheConfiguration(JHipsterProperties jHipsterProperties) {
		JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

		jcacheConfiguration =
			Eh107Configuration.fromEhcacheCacheConfiguration(
				CacheConfigurationBuilder
					.newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
					.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
					.build()
			);
	}

	@Bean
	public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
		return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
	}

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cm -> {
			createCache(cm, com.jhipster.app.repository.UserRepository.USERS_BY_LOGIN_CACHE);
			createCache(cm, com.jhipster.app.repository.UserRepository.USERS_BY_EMAIL_CACHE);
			createCache(cm, com.jhipster.app.domain.User.class.getName());
			createCache(cm, com.jhipster.app.domain.Authority.class.getName());
			createCache(cm, com.jhipster.app.domain.User.class.getName() + ".authorities");
			createCache(cm, com.jhipster.app.domain.TCountryCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TCountryCode.class.getName() + ".tStateCodes");
			createCache(cm, com.jhipster.app.domain.TStateCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TStateCode.class.getName() + ".tCityCodes");
			createCache(cm, com.jhipster.app.domain.TDivisionCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TDivisionCode.class.getName() + ".tDistrictCodes");
			createCache(cm, com.jhipster.app.domain.TDistrictCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TCityCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TIndustryCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TSectorCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TBrandCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TAuditTrail.class.getName());
			createCache(cm, com.jhipster.app.domain.TSupplierCategory.class.getName());
			createCache(cm, com.jhipster.app.domain.TSmTax.class.getName());
			createCache(cm, com.jhipster.app.domain.TOrgContactPerson.class.getName());
			createCache(cm, com.jhipster.app.domain.TTitleCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TOrganization.class.getName());
			createCache(cm, com.jhipster.app.domain.TOrganization.class.getName() + ".tOrgContactPeople");
			createCache(cm, com.jhipster.app.domain.TOrganization.class.getName() + ".contactPersons");
			createCache(cm, com.jhipster.app.domain.TSectionCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TUserRoleCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TUnitCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TUnitCode.class.getName() + ".subunits");
			createCache(cm, com.jhipster.app.domain.TSubunitCode.class.getName());
			createCache(cm, com.jhipster.app.domain.TOfficeCode.class.getName());
			createCache(cm, com.jhipster.app.domain.ApplicationUser.class.getName());
			createCache(cm, com.jhipster.app.domain.TSectionCode.class.getName() + ".users");
			createCache(cm, com.jhipster.app.domain.TUnitCode.class.getName() + ".users");
			createCache(cm, com.jhipster.app.domain.TSubunitCode.class.getName() + ".users");
			createCache(cm, com.jhipster.app.domain.TOfficeCode.class.getName() + ".users");
			createCache(cm, com.jhipster.app.domain.TUserRole.class.getName());
			createCache(cm, com.jhipster.app.domain.TUserRole.class.getName() + ".roles");
			createCache(cm, com.jhipster.app.domain.TUserRole.class.getName() + ".users");
			createCache(cm, com.jhipster.app.domain.TUserRoleCode.class.getName() + ".tUserRoles");
			createCache(cm, com.jhipster.app.domain.ApplicationUser.class.getName() + ".tUserRoles");
			createCache(cm, com.jhipster.app.domain.TSectionCode.class.getName() + ".applicationUsers");
			createCache(cm, com.jhipster.app.domain.TUserRoleCode.class.getName() + ".userRoles");
			createCache(cm, com.jhipster.app.domain.ApplicationUser.class.getName() + ".userRoles");
			// jhipster-needle-ehcache-add-entry
		};
	}

	private void createCache(javax.cache.CacheManager cm, String cacheName) {
		javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
		if (cache != null) {
			cache.clear();
		} else {
			cm.createCache(cacheName, jcacheConfiguration);
		}
	}

	@Autowired(required = false)
	public void setGitProperties(GitProperties gitProperties) {
		this.gitProperties = gitProperties;
	}

	@Autowired(required = false)
	public void setBuildProperties(BuildProperties buildProperties) {
		this.buildProperties = buildProperties;
	}

	@Bean
	public KeyGenerator keyGenerator() {
		return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
	}
}
