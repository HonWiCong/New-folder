package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TDistrictCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TDistrictCode.class);
        TDistrictCode tDistrictCode1 = new TDistrictCode();
        tDistrictCode1.setId(1L);
        TDistrictCode tDistrictCode2 = new TDistrictCode();
        tDistrictCode2.setId(tDistrictCode1.getId());
        assertThat(tDistrictCode1).isEqualTo(tDistrictCode2);
        tDistrictCode2.setId(2L);
        assertThat(tDistrictCode1).isNotEqualTo(tDistrictCode2);
        tDistrictCode1.setId(null);
        assertThat(tDistrictCode1).isNotEqualTo(tDistrictCode2);
    }
}
