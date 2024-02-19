package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TUnitCodeTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TUnitCode.class);
		TUnitCode tUnitCode1 = new TUnitCode();
		tUnitCode1.setId(1L);
		TUnitCode tUnitCode2 = new TUnitCode();
		tUnitCode2.setId(tUnitCode1.getId());
		assertThat(tUnitCode1).isEqualTo(tUnitCode2);
		tUnitCode2.setId(2L);
		assertThat(tUnitCode1).isNotEqualTo(tUnitCode2);
		tUnitCode1.setId(null);
		assertThat(tUnitCode1).isNotEqualTo(tUnitCode2);
	}
}
