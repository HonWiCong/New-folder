package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TSmTaxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TSmTax.class);
        TSmTax tSmTax1 = new TSmTax();
        tSmTax1.setId(1L);
        TSmTax tSmTax2 = new TSmTax();
        tSmTax2.setId(tSmTax1.getId());
        assertThat(tSmTax1).isEqualTo(tSmTax2);
        tSmTax2.setId(2L);
        assertThat(tSmTax1).isNotEqualTo(tSmTax2);
        tSmTax1.setId(null);
        assertThat(tSmTax1).isNotEqualTo(tSmTax2);
    }
}
