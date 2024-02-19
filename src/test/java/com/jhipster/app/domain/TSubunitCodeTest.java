package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TSubunitCodeTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TSubunitCode.class);
		TSubunitCode tSubunitCode1 = new TSubunitCode();
		tSubunitCode1.setId(1L);
		TSubunitCode tSubunitCode2 = new TSubunitCode();
		tSubunitCode2.setId(tSubunitCode1.getId());
		assertThat(tSubunitCode1).isEqualTo(tSubunitCode2);
		tSubunitCode2.setId(2L);
		assertThat(tSubunitCode1).isNotEqualTo(tSubunitCode2);
		tSubunitCode1.setId(null);
		assertThat(tSubunitCode1).isNotEqualTo(tSubunitCode2);
	}
}
