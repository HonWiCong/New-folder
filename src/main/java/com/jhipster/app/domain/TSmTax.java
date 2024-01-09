package com.jhipster.app.domain;

import com.jhipster.app.domain.enumeration.TaxStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TSmTax.
 */
@Entity
@Table(name = "t_sm_tax")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TSmTax implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sm_tax_code")
    private String smTaxCode;

    @Column(name = "sm_tax_description")
    private String smTaxDescription;

    @Column(name = "sm_tax_percentage", precision = 21, scale = 2)
    private BigDecimal smTaxPercentage;

    @Column(name = "sm_tax_type")
    private String smTaxType;

    @Column(name = "sm_tax_gst_code")
    private String smTaxGstCode;

    @Column(name = "sm_tax_gst_type")
    private String smTaxGstType;

    @Column(name = "sm_tax_collected_gl_code")
    private String smTaxCollectedGlCode;

    @Column(name = "sm_tax_collected_gl_desc")
    private String smTaxCollectedGlDesc;

    @Column(name = "sm_tax_collected_cost_center")
    private String smTaxCollectedCostCenter;

    @Column(name = "sm_tax_paid_gl_code")
    private String smTaxPaidGlCode;

    @Column(name = "sm_tax_paid_gl_desc")
    private String smTaxPaidGlDesc;

    @Column(name = "sm_tax_paid_cost_center")
    private String smTaxPaidCostCenter;

    @Column(name = "sm_tax_tax_authority")
    private String smTaxTaxAuthority;

    @Enumerated(EnumType.STRING)
    @Column(name = "sm_tax_status")
    private TaxStatus smTaxStatus;

    @Column(name = "sm_tax_entered_by")
    private Integer smTaxEnteredBy;

    @Column(name = "sm_tax_entered_date")
    private ZonedDateTime smTaxEnteredDate;

    @Column(name = "sm_tax_modified_by")
    private Integer smTaxModifiedBy;

    @Column(name = "sm_tax_modified_date")
    private ZonedDateTime smTaxModifiedDate;

    @Column(name = "sm_tax_confirmed_by")
    private Integer smTaxConfirmedBy;

    @Column(name = "sm_tax_confirmed_date")
    private ZonedDateTime smTaxConfirmedDate;

    @Column(name = "sm_tax_narration")
    private String smTaxNarration;

    @Column(name = "sm_tax_display")
    private String smTaxDisplay;

    @Column(name = "sm_tax_rcm_flag")
    private String smTaxRcmFlag;

    @Column(name = "sm_tax_cga")
    private String smTaxCga;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TSmTax id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmTaxCode() {
        return this.smTaxCode;
    }

    public TSmTax smTaxCode(String smTaxCode) {
        this.setSmTaxCode(smTaxCode);
        return this;
    }

    public void setSmTaxCode(String smTaxCode) {
        this.smTaxCode = smTaxCode;
    }

    public String getSmTaxDescription() {
        return this.smTaxDescription;
    }

    public TSmTax smTaxDescription(String smTaxDescription) {
        this.setSmTaxDescription(smTaxDescription);
        return this;
    }

    public void setSmTaxDescription(String smTaxDescription) {
        this.smTaxDescription = smTaxDescription;
    }

    public BigDecimal getSmTaxPercentage() {
        return this.smTaxPercentage;
    }

    public TSmTax smTaxPercentage(BigDecimal smTaxPercentage) {
        this.setSmTaxPercentage(smTaxPercentage);
        return this;
    }

    public void setSmTaxPercentage(BigDecimal smTaxPercentage) {
        this.smTaxPercentage = smTaxPercentage;
    }

    public String getSmTaxType() {
        return this.smTaxType;
    }

    public TSmTax smTaxType(String smTaxType) {
        this.setSmTaxType(smTaxType);
        return this;
    }

    public void setSmTaxType(String smTaxType) {
        this.smTaxType = smTaxType;
    }

    public String getSmTaxGstCode() {
        return this.smTaxGstCode;
    }

    public TSmTax smTaxGstCode(String smTaxGstCode) {
        this.setSmTaxGstCode(smTaxGstCode);
        return this;
    }

    public void setSmTaxGstCode(String smTaxGstCode) {
        this.smTaxGstCode = smTaxGstCode;
    }

    public String getSmTaxGstType() {
        return this.smTaxGstType;
    }

    public TSmTax smTaxGstType(String smTaxGstType) {
        this.setSmTaxGstType(smTaxGstType);
        return this;
    }

    public void setSmTaxGstType(String smTaxGstType) {
        this.smTaxGstType = smTaxGstType;
    }

    public String getSmTaxCollectedGlCode() {
        return this.smTaxCollectedGlCode;
    }

    public TSmTax smTaxCollectedGlCode(String smTaxCollectedGlCode) {
        this.setSmTaxCollectedGlCode(smTaxCollectedGlCode);
        return this;
    }

    public void setSmTaxCollectedGlCode(String smTaxCollectedGlCode) {
        this.smTaxCollectedGlCode = smTaxCollectedGlCode;
    }

    public String getSmTaxCollectedGlDesc() {
        return this.smTaxCollectedGlDesc;
    }

    public TSmTax smTaxCollectedGlDesc(String smTaxCollectedGlDesc) {
        this.setSmTaxCollectedGlDesc(smTaxCollectedGlDesc);
        return this;
    }

    public void setSmTaxCollectedGlDesc(String smTaxCollectedGlDesc) {
        this.smTaxCollectedGlDesc = smTaxCollectedGlDesc;
    }

    public String getSmTaxCollectedCostCenter() {
        return this.smTaxCollectedCostCenter;
    }

    public TSmTax smTaxCollectedCostCenter(String smTaxCollectedCostCenter) {
        this.setSmTaxCollectedCostCenter(smTaxCollectedCostCenter);
        return this;
    }

    public void setSmTaxCollectedCostCenter(String smTaxCollectedCostCenter) {
        this.smTaxCollectedCostCenter = smTaxCollectedCostCenter;
    }

    public String getSmTaxPaidGlCode() {
        return this.smTaxPaidGlCode;
    }

    public TSmTax smTaxPaidGlCode(String smTaxPaidGlCode) {
        this.setSmTaxPaidGlCode(smTaxPaidGlCode);
        return this;
    }

    public void setSmTaxPaidGlCode(String smTaxPaidGlCode) {
        this.smTaxPaidGlCode = smTaxPaidGlCode;
    }

    public String getSmTaxPaidGlDesc() {
        return this.smTaxPaidGlDesc;
    }

    public TSmTax smTaxPaidGlDesc(String smTaxPaidGlDesc) {
        this.setSmTaxPaidGlDesc(smTaxPaidGlDesc);
        return this;
    }

    public void setSmTaxPaidGlDesc(String smTaxPaidGlDesc) {
        this.smTaxPaidGlDesc = smTaxPaidGlDesc;
    }

    public String getSmTaxPaidCostCenter() {
        return this.smTaxPaidCostCenter;
    }

    public TSmTax smTaxPaidCostCenter(String smTaxPaidCostCenter) {
        this.setSmTaxPaidCostCenter(smTaxPaidCostCenter);
        return this;
    }

    public void setSmTaxPaidCostCenter(String smTaxPaidCostCenter) {
        this.smTaxPaidCostCenter = smTaxPaidCostCenter;
    }

    public String getSmTaxTaxAuthority() {
        return this.smTaxTaxAuthority;
    }

    public TSmTax smTaxTaxAuthority(String smTaxTaxAuthority) {
        this.setSmTaxTaxAuthority(smTaxTaxAuthority);
        return this;
    }

    public void setSmTaxTaxAuthority(String smTaxTaxAuthority) {
        this.smTaxTaxAuthority = smTaxTaxAuthority;
    }

    public TaxStatus getSmTaxStatus() {
        return this.smTaxStatus;
    }

    public TSmTax smTaxStatus(TaxStatus smTaxStatus) {
        this.setSmTaxStatus(smTaxStatus);
        return this;
    }

    public void setSmTaxStatus(TaxStatus smTaxStatus) {
        this.smTaxStatus = smTaxStatus;
    }

    public Integer getSmTaxEnteredBy() {
        return this.smTaxEnteredBy;
    }

    public TSmTax smTaxEnteredBy(Integer smTaxEnteredBy) {
        this.setSmTaxEnteredBy(smTaxEnteredBy);
        return this;
    }

    public void setSmTaxEnteredBy(Integer smTaxEnteredBy) {
        this.smTaxEnteredBy = smTaxEnteredBy;
    }

    public ZonedDateTime getSmTaxEnteredDate() {
        return this.smTaxEnteredDate;
    }

    public TSmTax smTaxEnteredDate(ZonedDateTime smTaxEnteredDate) {
        this.setSmTaxEnteredDate(smTaxEnteredDate);
        return this;
    }

    public void setSmTaxEnteredDate(ZonedDateTime smTaxEnteredDate) {
        this.smTaxEnteredDate = smTaxEnteredDate;
    }

    public Integer getSmTaxModifiedBy() {
        return this.smTaxModifiedBy;
    }

    public TSmTax smTaxModifiedBy(Integer smTaxModifiedBy) {
        this.setSmTaxModifiedBy(smTaxModifiedBy);
        return this;
    }

    public void setSmTaxModifiedBy(Integer smTaxModifiedBy) {
        this.smTaxModifiedBy = smTaxModifiedBy;
    }

    public ZonedDateTime getSmTaxModifiedDate() {
        return this.smTaxModifiedDate;
    }

    public TSmTax smTaxModifiedDate(ZonedDateTime smTaxModifiedDate) {
        this.setSmTaxModifiedDate(smTaxModifiedDate);
        return this;
    }

    public void setSmTaxModifiedDate(ZonedDateTime smTaxModifiedDate) {
        this.smTaxModifiedDate = smTaxModifiedDate;
    }

    public Integer getSmTaxConfirmedBy() {
        return this.smTaxConfirmedBy;
    }

    public TSmTax smTaxConfirmedBy(Integer smTaxConfirmedBy) {
        this.setSmTaxConfirmedBy(smTaxConfirmedBy);
        return this;
    }

    public void setSmTaxConfirmedBy(Integer smTaxConfirmedBy) {
        this.smTaxConfirmedBy = smTaxConfirmedBy;
    }

    public ZonedDateTime getSmTaxConfirmedDate() {
        return this.smTaxConfirmedDate;
    }

    public TSmTax smTaxConfirmedDate(ZonedDateTime smTaxConfirmedDate) {
        this.setSmTaxConfirmedDate(smTaxConfirmedDate);
        return this;
    }

    public void setSmTaxConfirmedDate(ZonedDateTime smTaxConfirmedDate) {
        this.smTaxConfirmedDate = smTaxConfirmedDate;
    }

    public String getSmTaxNarration() {
        return this.smTaxNarration;
    }

    public TSmTax smTaxNarration(String smTaxNarration) {
        this.setSmTaxNarration(smTaxNarration);
        return this;
    }

    public void setSmTaxNarration(String smTaxNarration) {
        this.smTaxNarration = smTaxNarration;
    }

    public String getSmTaxDisplay() {
        return this.smTaxDisplay;
    }

    public TSmTax smTaxDisplay(String smTaxDisplay) {
        this.setSmTaxDisplay(smTaxDisplay);
        return this;
    }

    public void setSmTaxDisplay(String smTaxDisplay) {
        this.smTaxDisplay = smTaxDisplay;
    }

    public String getSmTaxRcmFlag() {
        return this.smTaxRcmFlag;
    }

    public TSmTax smTaxRcmFlag(String smTaxRcmFlag) {
        this.setSmTaxRcmFlag(smTaxRcmFlag);
        return this;
    }

    public void setSmTaxRcmFlag(String smTaxRcmFlag) {
        this.smTaxRcmFlag = smTaxRcmFlag;
    }

    public String getSmTaxCga() {
        return this.smTaxCga;
    }

    public TSmTax smTaxCga(String smTaxCga) {
        this.setSmTaxCga(smTaxCga);
        return this;
    }

    public void setSmTaxCga(String smTaxCga) {
        this.smTaxCga = smTaxCga;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TSmTax)) {
            return false;
        }
        return id != null && id.equals(((TSmTax) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TSmTax{" +
            "id=" + getId() +
            ", smTaxCode='" + getSmTaxCode() + "'" +
            ", smTaxDescription='" + getSmTaxDescription() + "'" +
            ", smTaxPercentage=" + getSmTaxPercentage() +
            ", smTaxType='" + getSmTaxType() + "'" +
            ", smTaxGstCode='" + getSmTaxGstCode() + "'" +
            ", smTaxGstType='" + getSmTaxGstType() + "'" +
            ", smTaxCollectedGlCode='" + getSmTaxCollectedGlCode() + "'" +
            ", smTaxCollectedGlDesc='" + getSmTaxCollectedGlDesc() + "'" +
            ", smTaxCollectedCostCenter='" + getSmTaxCollectedCostCenter() + "'" +
            ", smTaxPaidGlCode='" + getSmTaxPaidGlCode() + "'" +
            ", smTaxPaidGlDesc='" + getSmTaxPaidGlDesc() + "'" +
            ", smTaxPaidCostCenter='" + getSmTaxPaidCostCenter() + "'" +
            ", smTaxTaxAuthority='" + getSmTaxTaxAuthority() + "'" +
            ", smTaxStatus='" + getSmTaxStatus() + "'" +
            ", smTaxEnteredBy=" + getSmTaxEnteredBy() +
            ", smTaxEnteredDate='" + getSmTaxEnteredDate() + "'" +
            ", smTaxModifiedBy=" + getSmTaxModifiedBy() +
            ", smTaxModifiedDate='" + getSmTaxModifiedDate() + "'" +
            ", smTaxConfirmedBy=" + getSmTaxConfirmedBy() +
            ", smTaxConfirmedDate='" + getSmTaxConfirmedDate() + "'" +
            ", smTaxNarration='" + getSmTaxNarration() + "'" +
            ", smTaxDisplay='" + getSmTaxDisplay() + "'" +
            ", smTaxRcmFlag='" + getSmTaxRcmFlag() + "'" +
            ", smTaxCga='" + getSmTaxCga() + "'" +
            "}";
    }
}
