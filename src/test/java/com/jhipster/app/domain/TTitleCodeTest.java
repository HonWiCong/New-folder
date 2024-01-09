package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TTitleCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TTitleCode.class);
        TTitleCode tTitleCode1 = new TTitleCode();
        tTitleCode1.setId(1L);
        TTitleCode tTitleCode2 = new TTitleCode();
        tTitleCode2.setId(tTitleCode1.getId());
        assertThat(tTitleCode1).isEqualTo(tTitleCode2);
        tTitleCode2.setId(2L);
        assertThat(tTitleCode1).isNotEqualTo(tTitleCode2);
        tTitleCode1.setId(null);
        assertThat(tTitleCode1).isNotEqualTo(tTitleCode2);
    }
}
