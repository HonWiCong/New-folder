package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TUserRoleTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TUserRole.class);
		TUserRole tUserRole1 = new TUserRole();
		tUserRole1.setId(1L);
		TUserRole tUserRole2 = new TUserRole();
		tUserRole2.setId(tUserRole1.getId());
		assertThat(tUserRole1).isEqualTo(tUserRole2);
		tUserRole2.setId(2L);
		assertThat(tUserRole1).isNotEqualTo(tUserRole2);
		tUserRole1.setId(null);
		assertThat(tUserRole1).isNotEqualTo(tUserRole2);
	}
}
