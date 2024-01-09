<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tOrganization.home.createOrEditLabel"
          data-cy="TOrganizationCreateUpdateHeading"
          v-text="$t('sainsApp.tOrganization.home.createOrEditLabel')"
        >
          Create or edit a TOrganization
        </h2>
        <div>
          <div class="form-group" v-if="tOrganization.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tOrganization.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgHqid')" for="t-organization-orgHqid">Org Hqid</label>
            <input
              type="number"
              class="form-control"
              name="orgHqid"
              id="t-organization-orgHqid"
              data-cy="orgHqid"
              :class="{ valid: !$v.tOrganization.orgHqid.$invalid, invalid: $v.tOrganization.orgHqid.$invalid }"
              v-model.number="$v.tOrganization.orgHqid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgHqBr')" for="t-organization-orgHqBr">Org Hq Br</label>
            <input
              type="text"
              class="form-control"
              name="orgHqBr"
              id="t-organization-orgHqBr"
              data-cy="orgHqBr"
              :class="{ valid: !$v.tOrganization.orgHqBr.$invalid, invalid: $v.tOrganization.orgHqBr.$invalid }"
              v-model="$v.tOrganization.orgHqBr.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCode')" for="t-organization-orgCode">Org Code</label>
            <input
              type="text"
              class="form-control"
              name="orgCode"
              id="t-organization-orgCode"
              data-cy="orgCode"
              :class="{ valid: !$v.tOrganization.orgCode.$invalid, invalid: $v.tOrganization.orgCode.$invalid }"
              v-model="$v.tOrganization.orgCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgBrn')" for="t-organization-orgBrn">Org Brn</label>
            <input
              type="text"
              class="form-control"
              name="orgBrn"
              id="t-organization-orgBrn"
              data-cy="orgBrn"
              :class="{ valid: !$v.tOrganization.orgBrn.$invalid, invalid: $v.tOrganization.orgBrn.$invalid }"
              v-model="$v.tOrganization.orgBrn.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgPtaxid')" for="t-organization-orgPtaxid"
              >Org Ptaxid</label
            >
            <input
              type="number"
              class="form-control"
              name="orgPtaxid"
              id="t-organization-orgPtaxid"
              data-cy="orgPtaxid"
              :class="{ valid: !$v.tOrganization.orgPtaxid.$invalid, invalid: $v.tOrganization.orgPtaxid.$invalid }"
              v-model.number="$v.tOrganization.orgPtaxid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgDefaultTaxCode')" for="t-organization-orgDefaultTaxCode"
              >Org Default Tax Code</label
            >
            <input
              type="text"
              class="form-control"
              name="orgDefaultTaxCode"
              id="t-organization-orgDefaultTaxCode"
              data-cy="orgDefaultTaxCode"
              :class="{ valid: !$v.tOrganization.orgDefaultTaxCode.$invalid, invalid: $v.tOrganization.orgDefaultTaxCode.$invalid }"
              v-model="$v.tOrganization.orgDefaultTaxCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCompanyGstNo')" for="t-organization-orgCompanyGstNo"
              >Org Company Gst No</label
            >
            <input
              type="text"
              class="form-control"
              name="orgCompanyGstNo"
              id="t-organization-orgCompanyGstNo"
              data-cy="orgCompanyGstNo"
              :class="{ valid: !$v.tOrganization.orgCompanyGstNo.$invalid, invalid: $v.tOrganization.orgCompanyGstNo.$invalid }"
              v-model="$v.tOrganization.orgCompanyGstNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgCompanyGstRegDate')"
              for="t-organization-orgCompanyGstRegDate"
              >Org Company Gst Reg Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="t-organization-orgCompanyGstRegDate"
                  v-model="$v.tOrganization.orgCompanyGstRegDate.$model"
                  name="orgCompanyGstRegDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="t-organization-orgCompanyGstRegDate"
                data-cy="orgCompanyGstRegDate"
                type="text"
                class="form-control"
                name="orgCompanyGstRegDate"
                :class="{ valid: !$v.tOrganization.orgCompanyGstRegDate.$invalid, invalid: $v.tOrganization.orgCompanyGstRegDate.$invalid }"
                v-model="$v.tOrganization.orgCompanyGstRegDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgCompanyGstDeregDate')"
              for="t-organization-orgCompanyGstDeregDate"
              >Org Company Gst Dereg Date</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="t-organization-orgCompanyGstDeregDate"
                  v-model="$v.tOrganization.orgCompanyGstDeregDate.$model"
                  name="orgCompanyGstDeregDate"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="t-organization-orgCompanyGstDeregDate"
                data-cy="orgCompanyGstDeregDate"
                type="text"
                class="form-control"
                name="orgCompanyGstDeregDate"
                :class="{
                  valid: !$v.tOrganization.orgCompanyGstDeregDate.$invalid,
                  invalid: $v.tOrganization.orgCompanyGstDeregDate.$invalid,
                }"
                v-model="$v.tOrganization.orgCompanyGstDeregDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgPoTaxInclusive')" for="t-organization-orgPoTaxInclusive"
              >Org Po Tax Inclusive</label
            >
            <input
              type="text"
              class="form-control"
              name="orgPoTaxInclusive"
              id="t-organization-orgPoTaxInclusive"
              data-cy="orgPoTaxInclusive"
              :class="{ valid: !$v.tOrganization.orgPoTaxInclusive.$invalid, invalid: $v.tOrganization.orgPoTaxInclusive.$invalid }"
              v-model="$v.tOrganization.orgPoTaxInclusive.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgName')" for="t-organization-orgName">Org Name</label>
            <input
              type="text"
              class="form-control"
              name="orgName"
              id="t-organization-orgName"
              data-cy="orgName"
              :class="{ valid: !$v.tOrganization.orgName.$invalid, invalid: $v.tOrganization.orgName.$invalid }"
              v-model="$v.tOrganization.orgName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgNameOther')" for="t-organization-orgNameOther"
              >Org Name Other</label
            >
            <input
              type="text"
              class="form-control"
              name="orgNameOther"
              id="t-organization-orgNameOther"
              data-cy="orgNameOther"
              :class="{ valid: !$v.tOrganization.orgNameOther.$invalid, invalid: $v.tOrganization.orgNameOther.$invalid }"
              v-model="$v.tOrganization.orgNameOther.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgShortname')" for="t-organization-orgShortname"
              >Org Shortname</label
            >
            <input
              type="text"
              class="form-control"
              name="orgShortname"
              id="t-organization-orgShortname"
              data-cy="orgShortname"
              :class="{ valid: !$v.tOrganization.orgShortname.$invalid, invalid: $v.tOrganization.orgShortname.$invalid }"
              v-model="$v.tOrganization.orgShortname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgAddress')" for="t-organization-orgAddress"
              >Org Address</label
            >
            <input
              type="text"
              class="form-control"
              name="orgAddress"
              id="t-organization-orgAddress"
              data-cy="orgAddress"
              :class="{ valid: !$v.tOrganization.orgAddress.$invalid, invalid: $v.tOrganization.orgAddress.$invalid }"
              v-model="$v.tOrganization.orgAddress.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgShippingAddress')"
              for="t-organization-orgShippingAddress"
              >Org Shipping Address</label
            >
            <input
              type="text"
              class="form-control"
              name="orgShippingAddress"
              id="t-organization-orgShippingAddress"
              data-cy="orgShippingAddress"
              :class="{ valid: !$v.tOrganization.orgShippingAddress.$invalid, invalid: $v.tOrganization.orgShippingAddress.$invalid }"
              v-model="$v.tOrganization.orgShippingAddress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgBillingAddress')" for="t-organization-orgBillingAddress"
              >Org Billing Address</label
            >
            <input
              type="text"
              class="form-control"
              name="orgBillingAddress"
              id="t-organization-orgBillingAddress"
              data-cy="orgBillingAddress"
              :class="{ valid: !$v.tOrganization.orgBillingAddress.$invalid, invalid: $v.tOrganization.orgBillingAddress.$invalid }"
              v-model="$v.tOrganization.orgBillingAddress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgPostcode')" for="t-organization-orgPostcode"
              >Org Postcode</label
            >
            <input
              type="text"
              class="form-control"
              name="orgPostcode"
              id="t-organization-orgPostcode"
              data-cy="orgPostcode"
              :class="{ valid: !$v.tOrganization.orgPostcode.$invalid, invalid: $v.tOrganization.orgPostcode.$invalid }"
              v-model="$v.tOrganization.orgPostcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCity')" for="t-organization-orgCity">Org City</label>
            <input
              type="text"
              class="form-control"
              name="orgCity"
              id="t-organization-orgCity"
              data-cy="orgCity"
              :class="{ valid: !$v.tOrganization.orgCity.$invalid, invalid: $v.tOrganization.orgCity.$invalid }"
              v-model="$v.tOrganization.orgCity.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgState')" for="t-organization-orgState">Org State</label>
            <input
              type="text"
              class="form-control"
              name="orgState"
              id="t-organization-orgState"
              data-cy="orgState"
              :class="{ valid: !$v.tOrganization.orgState.$invalid, invalid: $v.tOrganization.orgState.$invalid }"
              v-model="$v.tOrganization.orgState.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCountry')" for="t-organization-orgCountry"
              >Org Country</label
            >
            <input
              type="text"
              class="form-control"
              name="orgCountry"
              id="t-organization-orgCountry"
              data-cy="orgCountry"
              :class="{ valid: !$v.tOrganization.orgCountry.$invalid, invalid: $v.tOrganization.orgCountry.$invalid }"
              v-model="$v.tOrganization.orgCountry.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgOffPhone1')" for="t-organization-orgOffPhone1"
              >Org Off Phone 1</label
            >
            <input
              type="text"
              class="form-control"
              name="orgOffPhone1"
              id="t-organization-orgOffPhone1"
              data-cy="orgOffPhone1"
              :class="{ valid: !$v.tOrganization.orgOffPhone1.$invalid, invalid: $v.tOrganization.orgOffPhone1.$invalid }"
              v-model="$v.tOrganization.orgOffPhone1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgOffPhone2')" for="t-organization-orgOffPhone2"
              >Org Off Phone 2</label
            >
            <input
              type="text"
              class="form-control"
              name="orgOffPhone2"
              id="t-organization-orgOffPhone2"
              data-cy="orgOffPhone2"
              :class="{ valid: !$v.tOrganization.orgOffPhone2.$invalid, invalid: $v.tOrganization.orgOffPhone2.$invalid }"
              v-model="$v.tOrganization.orgOffPhone2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgOffPhone3')" for="t-organization-orgOffPhone3"
              >Org Off Phone 3</label
            >
            <input
              type="text"
              class="form-control"
              name="orgOffPhone3"
              id="t-organization-orgOffPhone3"
              data-cy="orgOffPhone3"
              :class="{ valid: !$v.tOrganization.orgOffPhone3.$invalid, invalid: $v.tOrganization.orgOffPhone3.$invalid }"
              v-model="$v.tOrganization.orgOffPhone3.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgOffFax1')" for="t-organization-orgOffFax1"
              >Org Off Fax 1</label
            >
            <input
              type="text"
              class="form-control"
              name="orgOffFax1"
              id="t-organization-orgOffFax1"
              data-cy="orgOffFax1"
              :class="{ valid: !$v.tOrganization.orgOffFax1.$invalid, invalid: $v.tOrganization.orgOffFax1.$invalid }"
              v-model="$v.tOrganization.orgOffFax1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgOffFax2')" for="t-organization-orgOffFax2"
              >Org Off Fax 2</label
            >
            <input
              type="text"
              class="form-control"
              name="orgOffFax2"
              id="t-organization-orgOffFax2"
              data-cy="orgOffFax2"
              :class="{ valid: !$v.tOrganization.orgOffFax2.$invalid, invalid: $v.tOrganization.orgOffFax2.$invalid }"
              v-model="$v.tOrganization.orgOffFax2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCredittermid')" for="t-organization-orgCredittermid"
              >Org Credittermid</label
            >
            <input
              type="number"
              class="form-control"
              name="orgCredittermid"
              id="t-organization-orgCredittermid"
              data-cy="orgCredittermid"
              :class="{ valid: !$v.tOrganization.orgCredittermid.$invalid, invalid: $v.tOrganization.orgCredittermid.$invalid }"
              v-model.number="$v.tOrganization.orgCredittermid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCreditLimit')" for="t-organization-orgCreditLimit"
              >Org Credit Limit</label
            >
            <input
              type="number"
              class="form-control"
              name="orgCreditLimit"
              id="t-organization-orgCreditLimit"
              data-cy="orgCreditLimit"
              :class="{ valid: !$v.tOrganization.orgCreditLimit.$invalid, invalid: $v.tOrganization.orgCreditLimit.$invalid }"
              v-model.number="$v.tOrganization.orgCreditLimit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgAgencyid')" for="t-organization-orgAgencyid"
              >Org Agencyid</label
            >
            <input
              type="number"
              class="form-control"
              name="orgAgencyid"
              id="t-organization-orgAgencyid"
              data-cy="orgAgencyid"
              :class="{ valid: !$v.tOrganization.orgAgencyid.$invalid, invalid: $v.tOrganization.orgAgencyid.$invalid }"
              v-model.number="$v.tOrganization.orgAgencyid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgDivision')" for="t-organization-orgDivision"
              >Org Division</label
            >
            <input
              type="text"
              class="form-control"
              name="orgDivision"
              id="t-organization-orgDivision"
              data-cy="orgDivision"
              :class="{ valid: !$v.tOrganization.orgDivision.$invalid, invalid: $v.tOrganization.orgDivision.$invalid }"
              v-model="$v.tOrganization.orgDivision.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgDistrict')" for="t-organization-orgDistrict"
              >Org District</label
            >
            <input
              type="text"
              class="form-control"
              name="orgDistrict"
              id="t-organization-orgDistrict"
              data-cy="orgDistrict"
              :class="{ valid: !$v.tOrganization.orgDistrict.$invalid, invalid: $v.tOrganization.orgDistrict.$invalid }"
              v-model="$v.tOrganization.orgDistrict.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgWebsite')" for="t-organization-orgWebsite"
              >Org Website</label
            >
            <input
              type="text"
              class="form-control"
              name="orgWebsite"
              id="t-organization-orgWebsite"
              data-cy="orgWebsite"
              :class="{ valid: !$v.tOrganization.orgWebsite.$invalid, invalid: $v.tOrganization.orgWebsite.$invalid }"
              v-model="$v.tOrganization.orgWebsite.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgEmail')" for="t-organization-orgEmail">Org Email</label>
            <input
              type="text"
              class="form-control"
              name="orgEmail"
              id="t-organization-orgEmail"
              data-cy="orgEmail"
              :class="{ valid: !$v.tOrganization.orgEmail.$invalid, invalid: $v.tOrganization.orgEmail.$invalid }"
              v-model="$v.tOrganization.orgEmail.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgSupplierCategory')"
              for="t-organization-orgSupplierCategory"
              >Org Supplier Category</label
            >
            <input
              type="text"
              class="form-control"
              name="orgSupplierCategory"
              id="t-organization-orgSupplierCategory"
              data-cy="orgSupplierCategory"
              :class="{ valid: !$v.tOrganization.orgSupplierCategory.$invalid, invalid: $v.tOrganization.orgSupplierCategory.$invalid }"
              v-model="$v.tOrganization.orgSupplierCategory.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCurrencyCode')" for="t-organization-orgCurrencyCode"
              >Org Currency Code</label
            >
            <input
              type="text"
              class="form-control"
              name="orgCurrencyCode"
              id="t-organization-orgCurrencyCode"
              data-cy="orgCurrencyCode"
              :class="{ valid: !$v.tOrganization.orgCurrencyCode.$invalid, invalid: $v.tOrganization.orgCurrencyCode.$invalid }"
              v-model="$v.tOrganization.orgCurrencyCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgType')" for="t-organization-orgType">Org Type</label>
            <input
              type="text"
              class="form-control"
              name="orgType"
              id="t-organization-orgType"
              data-cy="orgType"
              :class="{ valid: !$v.tOrganization.orgType.$invalid, invalid: $v.tOrganization.orgType.$invalid }"
              v-model="$v.tOrganization.orgType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgSectorid')" for="t-organization-orgSectorid"
              >Org Sectorid</label
            >
            <input
              type="number"
              class="form-control"
              name="orgSectorid"
              id="t-organization-orgSectorid"
              data-cy="orgSectorid"
              :class="{ valid: !$v.tOrganization.orgSectorid.$invalid, invalid: $v.tOrganization.orgSectorid.$invalid }"
              v-model.number="$v.tOrganization.orgSectorid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgSector')" for="t-organization-orgSector"
              >Org Sector</label
            >
            <input
              type="text"
              class="form-control"
              name="orgSector"
              id="t-organization-orgSector"
              data-cy="orgSector"
              :class="{ valid: !$v.tOrganization.orgSector.$invalid, invalid: $v.tOrganization.orgSector.$invalid }"
              v-model="$v.tOrganization.orgSector.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgIndustry')" for="t-organization-orgIndustry"
              >Org Industry</label
            >
            <input
              type="text"
              class="form-control"
              name="orgIndustry"
              id="t-organization-orgIndustry"
              data-cy="orgIndustry"
              :class="{ valid: !$v.tOrganization.orgIndustry.$invalid, invalid: $v.tOrganization.orgIndustry.$invalid }"
              v-model="$v.tOrganization.orgIndustry.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgSainsGroup')" for="t-organization-orgSainsGroup"
              >Org Sains Group</label
            >
            <input
              type="text"
              class="form-control"
              name="orgSainsGroup"
              id="t-organization-orgSainsGroup"
              data-cy="orgSainsGroup"
              :class="{ valid: !$v.tOrganization.orgSainsGroup.$invalid, invalid: $v.tOrganization.orgSainsGroup.$invalid }"
              v-model="$v.tOrganization.orgSainsGroup.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgBumiputra')" for="t-organization-orgBumiputra"
              >Org Bumiputra</label
            >
            <input
              type="text"
              class="form-control"
              name="orgBumiputra"
              id="t-organization-orgBumiputra"
              data-cy="orgBumiputra"
              :class="{ valid: !$v.tOrganization.orgBumiputra.$invalid, invalid: $v.tOrganization.orgBumiputra.$invalid }"
              v-model="$v.tOrganization.orgBumiputra.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgUpkReg')" for="t-organization-orgUpkReg"
              >Org Upk Reg</label
            >
            <input
              type="text"
              class="form-control"
              name="orgUpkReg"
              id="t-organization-orgUpkReg"
              data-cy="orgUpkReg"
              :class="{ valid: !$v.tOrganization.orgUpkReg.$invalid, invalid: $v.tOrganization.orgUpkReg.$invalid }"
              v-model="$v.tOrganization.orgUpkReg.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgMofReg')" for="t-organization-orgMofReg"
              >Org Mof Reg</label
            >
            <input
              type="text"
              class="form-control"
              name="orgMofReg"
              id="t-organization-orgMofReg"
              data-cy="orgMofReg"
              :class="{ valid: !$v.tOrganization.orgMofReg.$invalid, invalid: $v.tOrganization.orgMofReg.$invalid }"
              v-model="$v.tOrganization.orgMofReg.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgDesignation')" for="t-organization-orgDesignation"
              >Org Designation</label
            >
            <input
              type="text"
              class="form-control"
              name="orgDesignation"
              id="t-organization-orgDesignation"
              data-cy="orgDesignation"
              :class="{ valid: !$v.tOrganization.orgDesignation.$invalid, invalid: $v.tOrganization.orgDesignation.$invalid }"
              v-model="$v.tOrganization.orgDesignation.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgContpersonTitle')"
              for="t-organization-orgContpersonTitle"
              >Org Contperson Title</label
            >
            <input
              type="text"
              class="form-control"
              name="orgContpersonTitle"
              id="t-organization-orgContpersonTitle"
              data-cy="orgContpersonTitle"
              :class="{ valid: !$v.tOrganization.orgContpersonTitle.$invalid, invalid: $v.tOrganization.orgContpersonTitle.$invalid }"
              v-model="$v.tOrganization.orgContpersonTitle.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgContperson')" for="t-organization-orgContperson"
              >Org Contperson</label
            >
            <input
              type="text"
              class="form-control"
              name="orgContperson"
              id="t-organization-orgContperson"
              data-cy="orgContperson"
              :class="{ valid: !$v.tOrganization.orgContperson.$invalid, invalid: $v.tOrganization.orgContperson.$invalid }"
              v-model="$v.tOrganization.orgContperson.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgDirectline')" for="t-organization-orgDirectline"
              >Org Directline</label
            >
            <input
              type="text"
              class="form-control"
              name="orgDirectline"
              id="t-organization-orgDirectline"
              data-cy="orgDirectline"
              :class="{ valid: !$v.tOrganization.orgDirectline.$invalid, invalid: $v.tOrganization.orgDirectline.$invalid }"
              v-model="$v.tOrganization.orgDirectline.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgContpEmail')" for="t-organization-orgContpEmail"
              >Org Contp Email</label
            >
            <input
              type="text"
              class="form-control"
              name="orgContpEmail"
              id="t-organization-orgContpEmail"
              data-cy="orgContpEmail"
              :class="{ valid: !$v.tOrganization.orgContpEmail.$invalid, invalid: $v.tOrganization.orgContpEmail.$invalid }"
              v-model="$v.tOrganization.orgContpEmail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgContpHp')" for="t-organization-orgContpHp"
              >Org Contp Hp</label
            >
            <input
              type="text"
              class="form-control"
              name="orgContpHp"
              id="t-organization-orgContpHp"
              data-cy="orgContpHp"
              :class="{ valid: !$v.tOrganization.orgContpHp.$invalid, invalid: $v.tOrganization.orgContpHp.$invalid }"
              v-model="$v.tOrganization.orgContpHp.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgRemarks')" for="t-organization-orgRemarks"
              >Org Remarks</label
            >
            <input
              type="text"
              class="form-control"
              name="orgRemarks"
              id="t-organization-orgRemarks"
              data-cy="orgRemarks"
              :class="{ valid: !$v.tOrganization.orgRemarks.$invalid, invalid: $v.tOrganization.orgRemarks.$invalid }"
              v-model="$v.tOrganization.orgRemarks.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgActiveStatus')" for="t-organization-orgActiveStatus"
              >Org Active Status</label
            >
            <input
              type="text"
              class="form-control"
              name="orgActiveStatus"
              id="t-organization-orgActiveStatus"
              data-cy="orgActiveStatus"
              :class="{ valid: !$v.tOrganization.orgActiveStatus.$invalid, invalid: $v.tOrganization.orgActiveStatus.$invalid }"
              v-model="$v.tOrganization.orgActiveStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCcGc')" for="t-organization-orgCcGc">Org Cc Gc</label>
            <input
              type="text"
              class="form-control"
              name="orgCcGc"
              id="t-organization-orgCcGc"
              data-cy="orgCcGc"
              :class="{ valid: !$v.tOrganization.orgCcGc.$invalid, invalid: $v.tOrganization.orgCcGc.$invalid }"
              v-model="$v.tOrganization.orgCcGc.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgCustomerCateCode')"
              for="t-organization-orgCustomerCateCode"
              >Org Customer Cate Code</label
            >
            <input
              type="text"
              class="form-control"
              name="orgCustomerCateCode"
              id="t-organization-orgCustomerCateCode"
              data-cy="orgCustomerCateCode"
              :class="{ valid: !$v.tOrganization.orgCustomerCateCode.$invalid, invalid: $v.tOrganization.orgCustomerCateCode.$invalid }"
              v-model="$v.tOrganization.orgCustomerCateCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgVendorCateCode')" for="t-organization-orgVendorCateCode"
              >Org Vendor Cate Code</label
            >
            <input
              type="text"
              class="form-control"
              name="orgVendorCateCode"
              id="t-organization-orgVendorCateCode"
              data-cy="orgVendorCateCode"
              :class="{ valid: !$v.tOrganization.orgVendorCateCode.$invalid, invalid: $v.tOrganization.orgVendorCateCode.$invalid }"
              v-model="$v.tOrganization.orgVendorCateCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgSalesCateCode')" for="t-organization-orgSalesCateCode"
              >Org Sales Cate Code</label
            >
            <input
              type="text"
              class="form-control"
              name="orgSalesCateCode"
              id="t-organization-orgSalesCateCode"
              data-cy="orgSalesCateCode"
              :class="{ valid: !$v.tOrganization.orgSalesCateCode.$invalid, invalid: $v.tOrganization.orgSalesCateCode.$invalid }"
              v-model="$v.tOrganization.orgSalesCateCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgOutstandingBalance')"
              for="t-organization-orgOutstandingBalance"
              >Org Outstanding Balance</label
            >
            <input
              type="number"
              class="form-control"
              name="orgOutstandingBalance"
              id="t-organization-orgOutstandingBalance"
              data-cy="orgOutstandingBalance"
              :class="{ valid: !$v.tOrganization.orgOutstandingBalance.$invalid, invalid: $v.tOrganization.orgOutstandingBalance.$invalid }"
              v-model.number="$v.tOrganization.orgOutstandingBalance.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgOutstandingBalanceEx')"
              for="t-organization-orgOutstandingBalanceEx"
              >Org Outstanding Balance Ex</label
            >
            <input
              type="number"
              class="form-control"
              name="orgOutstandingBalanceEx"
              id="t-organization-orgOutstandingBalanceEx"
              data-cy="orgOutstandingBalanceEx"
              :class="{
                valid: !$v.tOrganization.orgOutstandingBalanceEx.$invalid,
                invalid: $v.tOrganization.orgOutstandingBalanceEx.$invalid,
              }"
              v-model.number="$v.tOrganization.orgOutstandingBalanceEx.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgCompanyCode')" for="t-organization-orgCompanyCode"
              >Org Company Code</label
            >
            <input
              type="text"
              class="form-control"
              name="orgCompanyCode"
              id="t-organization-orgCompanyCode"
              data-cy="orgCompanyCode"
              :class="{ valid: !$v.tOrganization.orgCompanyCode.$invalid, invalid: $v.tOrganization.orgCompanyCode.$invalid }"
              v-model="$v.tOrganization.orgCompanyCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrganization.orgDcrownPostStatus')"
              for="t-organization-orgDcrownPostStatus"
              >Org Dcrown Post Status</label
            >
            <input
              type="text"
              class="form-control"
              name="orgDcrownPostStatus"
              id="t-organization-orgDcrownPostStatus"
              data-cy="orgDcrownPostStatus"
              :class="{ valid: !$v.tOrganization.orgDcrownPostStatus.$invalid, invalid: $v.tOrganization.orgDcrownPostStatus.$invalid }"
              v-model="$v.tOrganization.orgDcrownPostStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.confirmedBy')" for="t-organization-confirmedBy"
              >Confirmed By</label
            >
            <input
              type="number"
              class="form-control"
              name="confirmedBy"
              id="t-organization-confirmedBy"
              data-cy="confirmedBy"
              :class="{ valid: !$v.tOrganization.confirmedBy.$invalid, invalid: $v.tOrganization.confirmedBy.$invalid }"
              v-model.number="$v.tOrganization.confirmedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.confirmedDate')" for="t-organization-confirmedDate"
              >Confirmed Date</label
            >
            <div class="d-flex">
              <input
                id="t-organization-confirmedDate"
                data-cy="confirmedDate"
                type="datetime-local"
                class="form-control"
                name="confirmedDate"
                :class="{ valid: !$v.tOrganization.confirmedDate.$invalid, invalid: $v.tOrganization.confirmedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tOrganization.confirmedDate.$model)"
                @change="updateZonedDateTimeField('confirmedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.enteredBy')" for="t-organization-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-organization-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tOrganization.enteredBy.$invalid, invalid: $v.tOrganization.enteredBy.$invalid }"
              v-model.number="$v.tOrganization.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.enteredDate')" for="t-organization-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-organization-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tOrganization.enteredDate.$invalid, invalid: $v.tOrganization.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tOrganization.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.modifiedBy')" for="t-organization-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-organization-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tOrganization.modifiedBy.$invalid, invalid: $v.tOrganization.modifiedBy.$invalid }"
              v-model.number="$v.tOrganization.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrganization.modifiedDate')" for="t-organization-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-organization-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tOrganization.modifiedDate.$invalid, invalid: $v.tOrganization.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tOrganization.modifiedDate.$model)"
                @change="updateZonedDateTimeField('modifiedDate', $event)"
              />
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.tOrganization.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-organization-update.component.ts"></script>
