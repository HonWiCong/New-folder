<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tCountryCode.home.createOrEditLabel"
          data-cy="TCountryCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tCountryCode.home.createOrEditLabel')"
        >
          Create or edit a TCountryCode
        </h2>
        <div>
          <div class="form-group" v-if="tCountryCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tCountryCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.countryCode')" for="t-country-code-countryCode"
              >Country Code</label
            >
            <input
              type="text"
              class="form-control"
              name="countryCode"
              id="t-country-code-countryCode"
              data-cy="countryCode"
              :class="{ valid: !$v.tCountryCode.countryCode.$invalid, invalid: $v.tCountryCode.countryCode.$invalid }"
              v-model="$v.tCountryCode.countryCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.countryName')" for="t-country-code-countryName"
              >Country Name</label
            >
            <input
              type="text"
              class="form-control"
              name="countryName"
              id="t-country-code-countryName"
              data-cy="countryName"
              :class="{ valid: !$v.tCountryCode.countryName.$invalid, invalid: $v.tCountryCode.countryName.$invalid }"
              v-model="$v.tCountryCode.countryName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tCountryCode.countryNationality')"
              for="t-country-code-countryNationality"
              >Country Nationality</label
            >
            <input
              type="text"
              class="form-control"
              name="countryNationality"
              id="t-country-code-countryNationality"
              data-cy="countryNationality"
              :class="{ valid: !$v.tCountryCode.countryNationality.$invalid, invalid: $v.tCountryCode.countryNationality.$invalid }"
              v-model="$v.tCountryCode.countryNationality.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.enteredBy')" for="t-country-code-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-country-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tCountryCode.enteredBy.$invalid, invalid: $v.tCountryCode.enteredBy.$invalid }"
              v-model.number="$v.tCountryCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.enteredDate')" for="t-country-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-country-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tCountryCode.enteredDate.$invalid, invalid: $v.tCountryCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tCountryCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.modifiedBy')" for="t-country-code-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-country-code-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tCountryCode.modifiedBy.$invalid, invalid: $v.tCountryCode.modifiedBy.$invalid }"
              v-model.number="$v.tCountryCode.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.modifiedDate')" for="t-country-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-country-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tCountryCode.modifiedDate.$invalid, invalid: $v.tCountryCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tCountryCode.modifiedDate.$model)"
                @change="updateZonedDateTimeField('modifiedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tCountryCode.orgCustomerType')" for="t-country-code-orgCustomerType"
              >Org Customer Type</label
            >
            <input
              type="text"
              class="form-control"
              name="orgCustomerType"
              id="t-country-code-orgCustomerType"
              data-cy="orgCustomerType"
              :class="{ valid: !$v.tCountryCode.orgCustomerType.$invalid, invalid: $v.tCountryCode.orgCustomerType.$invalid }"
              v-model="$v.tCountryCode.orgCustomerType.$model"
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
            :disabled="$v.tCountryCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-country-code-update.component.ts"></script>
