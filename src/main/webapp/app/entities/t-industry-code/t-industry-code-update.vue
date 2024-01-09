<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tIndustryCode.home.createOrEditLabel"
          data-cy="TIndustryCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tIndustryCode.home.createOrEditLabel')"
        >
          Create or edit a TIndustryCode
        </h2>
        <div>
          <div class="form-group" v-if="tIndustryCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tIndustryCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tIndustryCode.industryName')" for="t-industry-code-industryName"
              >Industry Name</label
            >
            <input
              type="text"
              class="form-control"
              name="industryName"
              id="t-industry-code-industryName"
              data-cy="industryName"
              :class="{ valid: !$v.tIndustryCode.industryName.$invalid, invalid: $v.tIndustryCode.industryName.$invalid }"
              v-model="$v.tIndustryCode.industryName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tIndustryCode.enteredBy')" for="t-industry-code-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-industry-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tIndustryCode.enteredBy.$invalid, invalid: $v.tIndustryCode.enteredBy.$invalid }"
              v-model.number="$v.tIndustryCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tIndustryCode.enteredDate')" for="t-industry-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-industry-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tIndustryCode.enteredDate.$invalid, invalid: $v.tIndustryCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tIndustryCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tIndustryCode.modifyBy')" for="t-industry-code-modifyBy"
              >Modify By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifyBy"
              id="t-industry-code-modifyBy"
              data-cy="modifyBy"
              :class="{ valid: !$v.tIndustryCode.modifyBy.$invalid, invalid: $v.tIndustryCode.modifyBy.$invalid }"
              v-model.number="$v.tIndustryCode.modifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tIndustryCode.modifiedDate')" for="t-industry-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-industry-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tIndustryCode.modifiedDate.$invalid, invalid: $v.tIndustryCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tIndustryCode.modifiedDate.$model)"
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
            :disabled="$v.tIndustryCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-industry-code-update.component.ts"></script>
