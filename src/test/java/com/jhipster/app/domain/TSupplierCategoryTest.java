package com.jhipster.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.jhipster.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TSupplierCategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TSupplierCategory.class);
        TSupplierCategory tSupplierCategory1 = new TSupplierCategory();
        tSupplierCategory1.setId(1L);
        TSupplierCategory tSupplierCategory2 = new TSupplierCategory();
        tSupplierCategory2.setId(tSupplierCategory1.getId());
        assertThat(tSupplierCategory1).isEqualTo(tSupplierCategory2);
        tSupplierCategory2.setId(2L);
        assertThat(tSupplierCategory1).isNotEqualTo(tSupplierCategory2);
        tSupplierCategory1.setId(null);
        assertThat(tSupplierCategory1).isNotEqualTo(tSupplierCategory2);
    }
}
