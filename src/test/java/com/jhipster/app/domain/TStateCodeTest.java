package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TStateCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TStateCode.class);
        TStateCode tStateCode1 = new TStateCode();
        tStateCode1.setId(1L);
        TStateCode tStateCode2 = new TStateCode();
        tStateCode2.setId(tStateCode1.getId());
        assertThat(tStateCode1).isEqualTo(tStateCode2);
        tStateCode2.setId(2L);
        assertThat(tStateCode1).isNotEqualTo(tStateCode2);
        tStateCode1.setId(null);
        assertThat(tStateCode1).isNotEqualTo(tStateCode2);
    }
}
