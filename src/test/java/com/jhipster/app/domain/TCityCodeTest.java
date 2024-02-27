package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCityCodeTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TCityCode.class);
		TCityCode tCityCode1 = new TCityCode();
		tCityCode1.setId(1L);
		TCityCode tCityCode2 = new TCityCode();
		tCityCode2.setId(tCityCode1.getId());
		assertThat(tCityCode1).isEqualTo(tCityCode2);
		tCityCode2.setId(2L);
		assertThat(tCityCode1).isNotEqualTo(tCityCode2);
		tCityCode1.setId(null);
		assertThat(tCityCode1).isNotEqualTo(tCityCode2);
	}
}
