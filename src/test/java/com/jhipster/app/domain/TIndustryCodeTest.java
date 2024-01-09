package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TIndustryCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TIndustryCode.class);
        TIndustryCode tIndustryCode1 = new TIndustryCode();
        tIndustryCode1.setId(1L);
        TIndustryCode tIndustryCode2 = new TIndustryCode();
        tIndustryCode2.setId(tIndustryCode1.getId());
        assertThat(tIndustryCode1).isEqualTo(tIndustryCode2);
        tIndustryCode2.setId(2L);
        assertThat(tIndustryCode1).isNotEqualTo(tIndustryCode2);
        tIndustryCode1.setId(null);
        assertThat(tIndustryCode1).isNotEqualTo(tIndustryCode2);
    }
}
