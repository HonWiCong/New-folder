package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TOfficeCodeTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TOfficeCode.class);
		TOfficeCode tOfficeCode1 = new TOfficeCode();
		tOfficeCode1.setId(1L);
		TOfficeCode tOfficeCode2 = new TOfficeCode();
		tOfficeCode2.setId(tOfficeCode1.getId());
		assertThat(tOfficeCode1).isEqualTo(tOfficeCode2);
		tOfficeCode2.setId(2L);
		assertThat(tOfficeCode1).isNotEqualTo(tOfficeCode2);
		tOfficeCode1.setId(null);
		assertThat(tOfficeCode1).isNotEqualTo(tOfficeCode2);
	}
}
