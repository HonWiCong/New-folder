<template>
  <div>
    <b-dropdown-item to="/t-country-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tCountryCode')">T Country Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-state-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tStateCode')">T State Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-division-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tDivisionCode')">T Division Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-district-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tDistrictCode')">T District Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-city-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tCityCode')">T City Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-industry-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tIndustryCode')">T Industry Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-sector-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tSectorCode')">T Sector Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-brand-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tBrandCode')">T Brand Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-audit-trail">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tAuditTrail')">T Audit Trail</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-supplier-category">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tSupplierCategory')">T Supplier Category</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-sm-tax">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tSmTax')">T Sm Tax</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-org-contact-person">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tOrgContactPerson')">T Org Contact Person</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-title-code">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tTitleCode')">T Title Code</span>
    </b-dropdown-item>
    <b-dropdown-item to="/t-organization">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.tOrganization')">T Organization</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
