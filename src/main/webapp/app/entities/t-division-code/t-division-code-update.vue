<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tDivisionCode.home.createOrEditLabel"
          data-cy="TDivisionCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tDivisionCode.home.createOrEditLabel')"
        >
          Create or edit a TDivisionCode
        </h2>
        <div>
          <div class="form-group" v-if="tDivisionCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tDivisionCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDivisionCode.divName')" for="t-division-code-divName">Div Name</label>
            <input
              type="text"
              class="form-control"
              name="divName"
              id="t-division-code-divName"
              data-cy="divName"
              :class="{ valid: !$v.tDivisionCode.divName.$invalid, invalid: $v.tDivisionCode.divName.$invalid }"
              v-model="$v.tDivisionCode.divName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDivisionCode.enteredBy')" for="t-division-code-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-division-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tDivisionCode.enteredBy.$invalid, invalid: $v.tDivisionCode.enteredBy.$invalid }"
              v-model.number="$v.tDivisionCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDivisionCode.enteredDate')" for="t-division-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-division-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tDivisionCode.enteredDate.$invalid, invalid: $v.tDivisionCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tDivisionCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDivisionCode.modifiedBy')" for="t-division-code-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-division-code-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tDivisionCode.modifiedBy.$invalid, invalid: $v.tDivisionCode.modifiedBy.$invalid }"
              v-model.number="$v.tDivisionCode.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tDivisionCode.modifiedDate')" for="t-division-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-division-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tDivisionCode.modifiedDate.$invalid, invalid: $v.tDivisionCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tDivisionCode.modifiedDate.$model)"
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
            :disabled="$v.tDivisionCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-division-code-update.component.ts"></script>
