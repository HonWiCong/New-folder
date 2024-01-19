package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TSectionCodeTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TSectionCode.class);
		TSectionCode tSectionCode1 = new TSectionCode();
		tSectionCode1.setId(1L);
		TSectionCode tSectionCode2 = new TSectionCode();
		tSectionCode2.setId(tSectionCode1.getId());
		assertThat(tSectionCode1).isEqualTo(tSectionCode2);
		tSectionCode2.setId(2L);
		assertThat(tSectionCode1).isNotEqualTo(tSectionCode2);
		tSectionCode1.setId(null);
		assertThat(tSectionCode1).isNotEqualTo(tSectionCode2);
	}
}
