package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TSectorCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TSectorCode.class);
        TSectorCode tSectorCode1 = new TSectorCode();
        tSectorCode1.setId(1L);
        TSectorCode tSectorCode2 = new TSectorCode();
        tSectorCode2.setId(tSectorCode1.getId());
        assertThat(tSectorCode1).isEqualTo(tSectorCode2);
        tSectorCode2.setId(2L);
        assertThat(tSectorCode1).isNotEqualTo(tSectorCode2);
        tSectorCode1.setId(null);
        assertThat(tSectorCode1).isNotEqualTo(tSectorCode2);
    }
}
