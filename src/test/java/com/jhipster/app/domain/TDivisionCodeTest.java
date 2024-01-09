package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TDivisionCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TDivisionCode.class);
        TDivisionCode tDivisionCode1 = new TDivisionCode();
        tDivisionCode1.setId(1L);
        TDivisionCode tDivisionCode2 = new TDivisionCode();
        tDivisionCode2.setId(tDivisionCode1.getId());
        assertThat(tDivisionCode1).isEqualTo(tDivisionCode2);
        tDivisionCode2.setId(2L);
        assertThat(tDivisionCode1).isNotEqualTo(tDivisionCode2);
        tDivisionCode1.setId(null);
        assertThat(tDivisionCode1).isNotEqualTo(tDivisionCode2);
    }
}
