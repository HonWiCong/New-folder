package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TBrandCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TBrandCode.class);
        TBrandCode tBrandCode1 = new TBrandCode();
        tBrandCode1.setId(1L);
        TBrandCode tBrandCode2 = new TBrandCode();
        tBrandCode2.setId(tBrandCode1.getId());
        assertThat(tBrandCode1).isEqualTo(tBrandCode2);
        tBrandCode2.setId(2L);
        assertThat(tBrandCode1).isNotEqualTo(tBrandCode2);
        tBrandCode1.setId(null);
        assertThat(tBrandCode1).isNotEqualTo(tBrandCode2);
    }
}
