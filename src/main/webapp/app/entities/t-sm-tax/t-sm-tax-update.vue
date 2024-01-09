<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tSmTax.home.createOrEditLabel"
          data-cy="TSmTaxCreateUpdateHeading"
          v-text="$t('sainsApp.tSmTax.home.createOrEditLabel')"
        >
          Create or edit a TSmTax
        </h2>
        <div>
          <div class="form-group" v-if="tSmTax.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tSmTax.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxCode')" for="t-sm-tax-smTaxCode">Sm Tax Code</label>
            <input
              type="text"
              class="form-control"
              name="smTaxCode"
              id="t-sm-tax-smTaxCode"
              data-cy="smTaxCode"
              :class="{ valid: !$v.tSmTax.smTaxCode.$invalid, invalid: $v.tSmTax.smTaxCode.$invalid }"
              v-model="$v.tSmTax.smTaxCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxDescription')" for="t-sm-tax-smTaxDescription"
              >Sm Tax Description</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxDescription"
              id="t-sm-tax-smTaxDescription"
              data-cy="smTaxDescription"
              :class="{ valid: !$v.tSmTax.smTaxDescription.$invalid, invalid: $v.tSmTax.smTaxDescription.$invalid }"
              v-model="$v.tSmTax.smTaxDescription.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxPercentage')" for="t-sm-tax-smTaxPercentage"
              >Sm Tax Percentage</label
            >
            <input
              type="number"
              class="form-control"
              name="smTaxPercentage"
              id="t-sm-tax-smTaxPercentage"
              data-cy="smTaxPercentage"
              :class="{ valid: !$v.tSmTax.smTaxPercentage.$invalid, invalid: $v.tSmTax.smTaxPercentage.$invalid }"
              v-model.number="$v.tSmTax.smTaxPercentage.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxType')" for="t-sm-tax-smTaxType">Sm Tax Type</label>
            <input
              type="text"
              class="form-control"
              name="smTaxType"
              id="t-sm-tax-smTaxType"
              data-cy="smTaxType"
              :class="{ valid: !$v.tSmTax.smTaxType.$invalid, invalid: $v.tSmTax.smTaxType.$invalid }"
              v-model="$v.tSmTax.smTaxType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxGstCode')" for="t-sm-tax-smTaxGstCode"
              >Sm Tax Gst Code</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxGstCode"
              id="t-sm-tax-smTaxGstCode"
              data-cy="smTaxGstCode"
              :class="{ valid: !$v.tSmTax.smTaxGstCode.$invalid, invalid: $v.tSmTax.smTaxGstCode.$invalid }"
              v-model="$v.tSmTax.smTaxGstCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxGstType')" for="t-sm-tax-smTaxGstType"
              >Sm Tax Gst Type</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxGstType"
              id="t-sm-tax-smTaxGstType"
              data-cy="smTaxGstType"
              :class="{ valid: !$v.tSmTax.smTaxGstType.$invalid, invalid: $v.tSmTax.smTaxGstType.$invalid }"
              v-model="$v.tSmTax.smTaxGstType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxCollectedGlCode')" for="t-sm-tax-smTaxCollectedGlCode"
              >Sm Tax Collected Gl Code</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxCollectedGlCode"
              id="t-sm-tax-smTaxCollectedGlCode"
              data-cy="smTaxCollectedGlCode"
              :class="{ valid: !$v.tSmTax.smTaxCollectedGlCode.$invalid, invalid: $v.tSmTax.smTaxCollectedGlCode.$invalid }"
              v-model="$v.tSmTax.smTaxCollectedGlCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxCollectedGlDesc')" for="t-sm-tax-smTaxCollectedGlDesc"
              >Sm Tax Collected Gl Desc</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxCollectedGlDesc"
              id="t-sm-tax-smTaxCollectedGlDesc"
              data-cy="smTaxCollectedGlDesc"
              :class="{ valid: !$v.tSmTax.smTaxCollectedGlDesc.$invalid, invalid: $v.tSmTax.smTaxCollectedGlDesc.$invalid }"
              v-model="$v.tSmTax.smTaxCollectedGlDesc.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tSmTax.smTaxCollectedCostCenter')"
              for="t-sm-tax-smTaxCollectedCostCenter"
              >Sm Tax Collected Cost Center</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxCollectedCostCenter"
              id="t-sm-tax-smTaxCollectedCostCenter"
              data-cy="smTaxCollectedCostCenter"
              :class="{ valid: !$v.tSmTax.smTaxCollectedCostCenter.$invalid, invalid: $v.tSmTax.smTaxCollectedCostCenter.$invalid }"
              v-model="$v.tSmTax.smTaxCollectedCostCenter.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxPaidGlCode')" for="t-sm-tax-smTaxPaidGlCode"
              >Sm Tax Paid Gl Code</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxPaidGlCode"
              id="t-sm-tax-smTaxPaidGlCode"
              data-cy="smTaxPaidGlCode"
              :class="{ valid: !$v.tSmTax.smTaxPaidGlCode.$invalid, invalid: $v.tSmTax.smTaxPaidGlCode.$invalid }"
              v-model="$v.tSmTax.smTaxPaidGlCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxPaidGlDesc')" for="t-sm-tax-smTaxPaidGlDesc"
              >Sm Tax Paid Gl Desc</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxPaidGlDesc"
              id="t-sm-tax-smTaxPaidGlDesc"
              data-cy="smTaxPaidGlDesc"
              :class="{ valid: !$v.tSmTax.smTaxPaidGlDesc.$invalid, invalid: $v.tSmTax.smTaxPaidGlDesc.$invalid }"
              v-model="$v.tSmTax.smTaxPaidGlDesc.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxPaidCostCenter')" for="t-sm-tax-smTaxPaidCostCenter"
              >Sm Tax Paid Cost Center</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxPaidCostCenter"
              id="t-sm-tax-smTaxPaidCostCenter"
              data-cy="smTaxPaidCostCenter"
              :class="{ valid: !$v.tSmTax.smTaxPaidCostCenter.$invalid, invalid: $v.tSmTax.smTaxPaidCostCenter.$invalid }"
              v-model="$v.tSmTax.smTaxPaidCostCenter.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxTaxAuthority')" for="t-sm-tax-smTaxTaxAuthority"
              >Sm Tax Tax Authority</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxTaxAuthority"
              id="t-sm-tax-smTaxTaxAuthority"
              data-cy="smTaxTaxAuthority"
              :class="{ valid: !$v.tSmTax.smTaxTaxAuthority.$invalid, invalid: $v.tSmTax.smTaxTaxAuthority.$invalid }"
              v-model="$v.tSmTax.smTaxTaxAuthority.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxStatus')" for="t-sm-tax-smTaxStatus">Sm Tax Status</label>
            <select
              class="form-control"
              name="smTaxStatus"
              :class="{ valid: !$v.tSmTax.smTaxStatus.$invalid, invalid: $v.tSmTax.smTaxStatus.$invalid }"
              v-model="$v.tSmTax.smTaxStatus.$model"
              id="t-sm-tax-smTaxStatus"
              data-cy="smTaxStatus"
            >
              <option
                v-for="taxStatus in taxStatusValues"
                :key="taxStatus"
                v-bind:value="taxStatus"
                v-bind:label="$t('sainsApp.TaxStatus.' + taxStatus)"
              >
                {{ taxStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxEnteredBy')" for="t-sm-tax-smTaxEnteredBy"
              >Sm Tax Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="smTaxEnteredBy"
              id="t-sm-tax-smTaxEnteredBy"
              data-cy="smTaxEnteredBy"
              :class="{ valid: !$v.tSmTax.smTaxEnteredBy.$invalid, invalid: $v.tSmTax.smTaxEnteredBy.$invalid }"
              v-model.number="$v.tSmTax.smTaxEnteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxEnteredDate')" for="t-sm-tax-smTaxEnteredDate"
              >Sm Tax Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-sm-tax-smTaxEnteredDate"
                data-cy="smTaxEnteredDate"
                type="datetime-local"
                class="form-control"
                name="smTaxEnteredDate"
                :class="{ valid: !$v.tSmTax.smTaxEnteredDate.$invalid, invalid: $v.tSmTax.smTaxEnteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSmTax.smTaxEnteredDate.$model)"
                @change="updateZonedDateTimeField('smTaxEnteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxModifiedBy')" for="t-sm-tax-smTaxModifiedBy"
              >Sm Tax Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="smTaxModifiedBy"
              id="t-sm-tax-smTaxModifiedBy"
              data-cy="smTaxModifiedBy"
              :class="{ valid: !$v.tSmTax.smTaxModifiedBy.$invalid, invalid: $v.tSmTax.smTaxModifiedBy.$invalid }"
              v-model.number="$v.tSmTax.smTaxModifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxModifiedDate')" for="t-sm-tax-smTaxModifiedDate"
              >Sm Tax Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-sm-tax-smTaxModifiedDate"
                data-cy="smTaxModifiedDate"
                type="datetime-local"
                class="form-control"
                name="smTaxModifiedDate"
                :class="{ valid: !$v.tSmTax.smTaxModifiedDate.$invalid, invalid: $v.tSmTax.smTaxModifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSmTax.smTaxModifiedDate.$model)"
                @change="updateZonedDateTimeField('smTaxModifiedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxConfirmedBy')" for="t-sm-tax-smTaxConfirmedBy"
              >Sm Tax Confirmed By</label
            >
            <input
              type="number"
              class="form-control"
              name="smTaxConfirmedBy"
              id="t-sm-tax-smTaxConfirmedBy"
              data-cy="smTaxConfirmedBy"
              :class="{ valid: !$v.tSmTax.smTaxConfirmedBy.$invalid, invalid: $v.tSmTax.smTaxConfirmedBy.$invalid }"
              v-model.number="$v.tSmTax.smTaxConfirmedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxConfirmedDate')" for="t-sm-tax-smTaxConfirmedDate"
              >Sm Tax Confirmed Date</label
            >
            <div class="d-flex">
              <input
                id="t-sm-tax-smTaxConfirmedDate"
                data-cy="smTaxConfirmedDate"
                type="datetime-local"
                class="form-control"
                name="smTaxConfirmedDate"
                :class="{ valid: !$v.tSmTax.smTaxConfirmedDate.$invalid, invalid: $v.tSmTax.smTaxConfirmedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSmTax.smTaxConfirmedDate.$model)"
                @change="updateZonedDateTimeField('smTaxConfirmedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxNarration')" for="t-sm-tax-smTaxNarration"
              >Sm Tax Narration</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxNarration"
              id="t-sm-tax-smTaxNarration"
              data-cy="smTaxNarration"
              :class="{ valid: !$v.tSmTax.smTaxNarration.$invalid, invalid: $v.tSmTax.smTaxNarration.$invalid }"
              v-model="$v.tSmTax.smTaxNarration.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxDisplay')" for="t-sm-tax-smTaxDisplay">Sm Tax Display</label>
            <input
              type="text"
              class="form-control"
              name="smTaxDisplay"
              id="t-sm-tax-smTaxDisplay"
              data-cy="smTaxDisplay"
              :class="{ valid: !$v.tSmTax.smTaxDisplay.$invalid, invalid: $v.tSmTax.smTaxDisplay.$invalid }"
              v-model="$v.tSmTax.smTaxDisplay.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxRcmFlag')" for="t-sm-tax-smTaxRcmFlag"
              >Sm Tax Rcm Flag</label
            >
            <input
              type="text"
              class="form-control"
              name="smTaxRcmFlag"
              id="t-sm-tax-smTaxRcmFlag"
              data-cy="smTaxRcmFlag"
              :class="{ valid: !$v.tSmTax.smTaxRcmFlag.$invalid, invalid: $v.tSmTax.smTaxRcmFlag.$invalid }"
              v-model="$v.tSmTax.smTaxRcmFlag.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSmTax.smTaxCga')" for="t-sm-tax-smTaxCga">Sm Tax Cga</label>
            <input
              type="text"
              class="form-control"
              name="smTaxCga"
              id="t-sm-tax-smTaxCga"
              data-cy="smTaxCga"
              :class="{ valid: !$v.tSmTax.smTaxCga.$invalid, invalid: $v.tSmTax.smTaxCga.$invalid }"
              v-model="$v.tSmTax.smTaxCga.$model"
            />
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
            :disabled="$v.tSmTax.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-sm-tax-update.component.ts"></script>
