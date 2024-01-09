<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tDistrictCode.home.createOrEditLabel"
          data-cy="TDistrictCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tDistrictCode.home.createOrEditLabel')"
        >
          Create or edit a TDistrictCode
        </h2>
        <div>
          <div class="form-group" v-if="tDistrictCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tDistrictCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDistrictCode.disName')" for="t-district-code-disName">Dis Name</label>
            <input
              type="text"
              class="form-control"
              name="disName"
              id="t-district-code-disName"
              data-cy="disName"
              :class="{ valid: !$v.tDistrictCode.disName.$invalid, invalid: $v.tDistrictCode.disName.$invalid }"
              v-model="$v.tDistrictCode.disName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDistrictCode.enteredBy')" for="t-district-code-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-district-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tDistrictCode.enteredBy.$invalid, invalid: $v.tDistrictCode.enteredBy.$invalid }"
              v-model.number="$v.tDistrictCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDistrictCode.enteredDate')" for="t-district-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-district-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tDistrictCode.enteredDate.$invalid, invalid: $v.tDistrictCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tDistrictCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDistrictCode.modifyBy')" for="t-district-code-modifyBy"
              >Modify By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifyBy"
              id="t-district-code-modifyBy"
              data-cy="modifyBy"
              :class="{ valid: !$v.tDistrictCode.modifyBy.$invalid, invalid: $v.tDistrictCode.modifyBy.$invalid }"
              v-model.number="$v.tDistrictCode.modifyBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDistrictCode.modifiedDate')" for="t-district-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-district-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tDistrictCode.modifiedDate.$invalid, invalid: $v.tDistrictCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tDistrictCode.modifiedDate.$model)"
                @change="updateZonedDateTimeField('modifiedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDistrictCode.tDivisionCode')" for="t-district-code-tDivisionCode"
              >T Division Code</label
            >
            <select
              class="form-control"
              id="t-district-code-tDivisionCode"
              data-cy="tDivisionCode"
              name="tDivisionCode"
              v-model="tDistrictCode.tDivisionCode"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  tDistrictCode.tDivisionCode && tDivisionCodeOption.id === tDistrictCode.tDivisionCode.id
                    ? tDistrictCode.tDivisionCode
                    : tDivisionCodeOption
                "
                v-for="tDivisionCodeOption in tDivisionCodes"
                :key="tDivisionCodeOption.id"
              >
                {{ tDivisionCodeOption.id }}
              </option>
            </select>
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
            :disabled="$v.tDistrictCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-district-code-update.component.ts"></script>
