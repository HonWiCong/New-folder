<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="tSmTax">
        <h2 class="jh-entity-heading" data-cy="tSmTaxDetailsHeading">
          <span v-text="$t('sainsApp.tSmTax.detail.title')">TSmTax</span> {{ tSmTax.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxCode')">Sm Tax Code</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxDescription')">Sm Tax Description</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxDescription }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxPercentage')">Sm Tax Percentage</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxPercentage }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxType')">Sm Tax Type</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxType }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxGstCode')">Sm Tax Gst Code</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxGstCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxGstType')">Sm Tax Gst Type</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxGstType }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxCollectedGlCode')">Sm Tax Collected Gl Code</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxCollectedGlCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxCollectedGlDesc')">Sm Tax Collected Gl Desc</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxCollectedGlDesc }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxCollectedCostCenter')">Sm Tax Collected Cost Center</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxCollectedCostCenter }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxPaidGlCode')">Sm Tax Paid Gl Code</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxPaidGlCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxPaidGlDesc')">Sm Tax Paid Gl Desc</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxPaidGlDesc }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxPaidCostCenter')">Sm Tax Paid Cost Center</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxPaidCostCenter }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxTaxAuthority')">Sm Tax Tax Authority</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxTaxAuthority }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxStatus')">Sm Tax Status</span>
          </dt>
          <dd>
            <span v-text="$t('sainsApp.TaxStatus.' + tSmTax.smTaxStatus)">{{ tSmTax.smTaxStatus }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxEnteredBy')">Sm Tax Entered By</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxEnteredBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxEnteredDate')">Sm Tax Entered Date</span>
          </dt>
          <dd>
            <span v-if="tSmTax.smTaxEnteredDate">{{ $d(Date.parse(tSmTax.smTaxEnteredDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxModifiedBy')">Sm Tax Modified By</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxModifiedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxModifiedDate')">Sm Tax Modified Date</span>
          </dt>
          <dd>
            <span v-if="tSmTax.smTaxModifiedDate">{{ $d(Date.parse(tSmTax.smTaxModifiedDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxConfirmedBy')">Sm Tax Confirmed By</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxConfirmedBy }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxConfirmedDate')">Sm Tax Confirmed Date</span>
          </dt>
          <dd>
            <span v-if="tSmTax.smTaxConfirmedDate">{{ $d(Date.parse(tSmTax.smTaxConfirmedDate), 'long') }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxNarration')">Sm Tax Narration</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxNarration }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxDisplay')">Sm Tax Display</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxDisplay }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxRcmFlag')">Sm Tax Rcm Flag</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxRcmFlag }}</span>
          </dd>
          <dt>
            <span v-text="$t('sainsApp.tSmTax.smTaxCga')">Sm Tax Cga</span>
          </dt>
          <dd>
            <span>{{ tSmTax.smTaxCga }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="tSmTax.id" :to="{ name: 'TSmTaxEdit', params: { tSmTaxId: tSmTax.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./t-sm-tax-details.component.ts"></script>
