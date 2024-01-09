<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tTitleCode.home.createOrEditLabel"
          data-cy="TTitleCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tTitleCode.home.createOrEditLabel')"
        >
          Create or edit a TTitleCode
        </h2>
        <div>
          <div class="form-group" v-if="tTitleCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tTitleCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tTitleCode.ttTitle')" for="t-title-code-ttTitle">Tt Title</label>
            <input
              type="text"
              class="form-control"
              name="ttTitle"
              id="t-title-code-ttTitle"
              data-cy="ttTitle"
              :class="{ valid: !$v.tTitleCode.ttTitle.$invalid, invalid: $v.tTitleCode.ttTitle.$invalid }"
              v-model="$v.tTitleCode.ttTitle.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tTitleCode.enteredBy')" for="t-title-code-enteredBy">Entered By</label>
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-title-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tTitleCode.enteredBy.$invalid, invalid: $v.tTitleCode.enteredBy.$invalid }"
              v-model.number="$v.tTitleCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tTitleCode.enteredDate')" for="t-title-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-title-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tTitleCode.enteredDate.$invalid, invalid: $v.tTitleCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tTitleCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tTitleCode.modifiedBy')" for="t-title-code-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-title-code-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tTitleCode.modifiedBy.$invalid, invalid: $v.tTitleCode.modifiedBy.$invalid }"
              v-model.number="$v.tTitleCode.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tTitleCode.modifiedDate')" for="t-title-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-title-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tTitleCode.modifiedDate.$invalid, invalid: $v.tTitleCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tTitleCode.modifiedDate.$model)"
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
            :disabled="$v.tTitleCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-title-code-update.component.ts"></script>
