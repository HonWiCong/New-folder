package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TAuditTrailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TAuditTrail.class);
        TAuditTrail tAuditTrail1 = new TAuditTrail();
        tAuditTrail1.setId(1L);
        TAuditTrail tAuditTrail2 = new TAuditTrail();
        tAuditTrail2.setId(tAuditTrail1.getId());
        assertThat(tAuditTrail1).isEqualTo(tAuditTrail2);
        tAuditTrail2.setId(2L);
        assertThat(tAuditTrail1).isNotEqualTo(tAuditTrail2);
        tAuditTrail1.setId(null);
        assertThat(tAuditTrail1).isNotEqualTo(tAuditTrail2);
    }
}
