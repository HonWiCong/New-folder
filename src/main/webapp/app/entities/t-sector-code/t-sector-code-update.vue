<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tSectorCode.home.createOrEditLabel"
          data-cy="TSectorCodeCreateUpdateHeading"
          v-text="$t('sainsApp.tSectorCode.home.createOrEditLabel')"
        >
          Create or edit a TSectorCode
        </h2>
        <div>
          <div class="form-group" v-if="tSectorCode.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tSectorCode.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSectorCode.sectorName')" for="t-sector-code-sectorName"
              >Sector Name</label
            >
            <input
              type="text"
              class="form-control"
              name="sectorName"
              id="t-sector-code-sectorName"
              data-cy="sectorName"
              :class="{ valid: !$v.tSectorCode.sectorName.$invalid, invalid: $v.tSectorCode.sectorName.$invalid }"
              v-model="$v.tSectorCode.sectorName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSectorCode.sectorDescription')" for="t-sector-code-sectorDescription"
              >Sector Description</label
            >
            <input
              type="text"
              class="form-control"
              name="sectorDescription"
              id="t-sector-code-sectorDescription"
              data-cy="sectorDescription"
              :class="{ valid: !$v.tSectorCode.sectorDescription.$invalid, invalid: $v.tSectorCode.sectorDescription.$invalid }"
              v-model="$v.tSectorCode.sectorDescription.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSectorCode.enteredBy')" for="t-sector-code-enteredBy">Entered By</label>
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-sector-code-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tSectorCode.enteredBy.$invalid, invalid: $v.tSectorCode.enteredBy.$invalid }"
              v-model.number="$v.tSectorCode.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSectorCode.enteredDate')" for="t-sector-code-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-sector-code-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tSectorCode.enteredDate.$invalid, invalid: $v.tSectorCode.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSectorCode.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSectorCode.modifiedBy')" for="t-sector-code-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-sector-code-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tSectorCode.modifiedBy.$invalid, invalid: $v.tSectorCode.modifiedBy.$invalid }"
              v-model.number="$v.tSectorCode.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSectorCode.modifiedDate')" for="t-sector-code-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-sector-code-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tSectorCode.modifiedDate.$invalid, invalid: $v.tSectorCode.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSectorCode.modifiedDate.$model)"
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
            :disabled="$v.tSectorCode.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-sector-code-update.component.ts"></script>
