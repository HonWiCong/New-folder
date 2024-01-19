package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TUserRoleCodeTest {

	@Test
	void equalsVerifier() throws Exception {
		TestUtil.equalsVerifier(TUserRoleCode.class);
		TUserRoleCode tUserRoleCode1 = new TUserRoleCode();
		tUserRoleCode1.setId(1L);
		TUserRoleCode tUserRoleCode2 = new TUserRoleCode();
		tUserRoleCode2.setId(tUserRoleCode1.getId());
		assertThat(tUserRoleCode1).isEqualTo(tUserRoleCode2);
		tUserRoleCode2.setId(2L);
		assertThat(tUserRoleCode1).isNotEqualTo(tUserRoleCode2);
		tUserRoleCode1.setId(null);
		assertThat(tUserRoleCode1).isNotEqualTo(tUserRoleCode2);
	}
}
