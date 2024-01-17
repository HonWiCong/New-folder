package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TOrgContactPersonTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TOrgContactPerson.class);
		TOrgContactPerson tOrgContactPerson1 = new TOrgContactPerson();
		tOrgContactPerson1.setId(1L);
		TOrgContactPerson tOrgContactPerson2 = new TOrgContactPerson();
		tOrgContactPerson2.setId(tOrgContactPerson1.getId());
		assertThat(tOrgContactPerson1).isEqualTo(tOrgContactPerson2);
		tOrgContactPerson2.setId(2L);
		assertThat(tOrgContactPerson1).isNotEqualTo(tOrgContactPerson2);
		tOrgContactPerson1.setId(null);
		assertThat(tOrgContactPerson1).isNotEqualTo(tOrgContactPerson2);
	}
}
