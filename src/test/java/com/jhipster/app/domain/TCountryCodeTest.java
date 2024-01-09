package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TCountryCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TCountryCode.class);
        TCountryCode tCountryCode1 = new TCountryCode();
        tCountryCode1.setId(1L);
        TCountryCode tCountryCode2 = new TCountryCode();
        tCountryCode2.setId(tCountryCode1.getId());
        assertThat(tCountryCode1).isEqualTo(tCountryCode2);
        tCountryCode2.setId(2L);
        assertThat(tCountryCode1).isNotEqualTo(tCountryCode2);
        tCountryCode1.setId(null);
        assertThat(tCountryCode1).isNotEqualTo(tCountryCode2);
    }
}
