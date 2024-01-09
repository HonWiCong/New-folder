package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TOrganizationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TOrganization.class);
        TOrganization tOrganization1 = new TOrganization();
        tOrganization1.setId(1L);
        TOrganization tOrganization2 = new TOrganization();
        tOrganization2.setId(tOrganization1.getId());
        assertThat(tOrganization1).isEqualTo(tOrganization2);
        tOrganization2.setId(2L);
        assertThat(tOrganization1).isNotEqualTo(tOrganization2);
        tOrganization1.setId(null);
        assertThat(tOrganization1).isNotEqualTo(tOrganization2);
    }
}
