<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tBrandCode.home.createOrEditLabel"
          data-cy="TBrandCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tBrandCode.home.createOrEditLabel')"
        >
          Create or edit a TBrandCode
        </h2>
        <div>
          <div class="form-group" v-if="tBrandCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tBrandCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tBrandCode.brandName')" for="t-brand-code-brandName">Brand Name</label>
            <input
              type="text"
              class="form-control"
              name="brandName"
              id="t-brand-code-brandName"
              data-cy="brandName"
              :class="{ valid: !$v.tBrandCode.brandName.$invalid, invalid: $v.tBrandCode.brandName.$invalid }"
              v-model="$v.tBrandCode.brandName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tBrandCode.enteredBy')" for="t-brand-code-enteredBy">Entered By</label>
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-brand-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tBrandCode.enteredBy.$invalid, invalid: $v.tBrandCode.enteredBy.$invalid }"
              v-model.number="$v.tBrandCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tBrandCode.enteredDate')" for="t-brand-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-brand-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tBrandCode.enteredDate.$invalid, invalid: $v.tBrandCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tBrandCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tBrandCode.modifiedBy')" for="t-brand-code-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-brand-code-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tBrandCode.modifiedBy.$invalid, invalid: $v.tBrandCode.modifiedBy.$invalid }"
              v-model.number="$v.tBrandCode.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tBrandCode.modifiedDate')" for="t-brand-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-brand-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tBrandCode.modifiedDate.$invalid, invalid: $v.tBrandCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tBrandCode.modifiedDate.$model)"
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
            :disabled="$v.tBrandCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-brand-code-update.component.ts"></script>
