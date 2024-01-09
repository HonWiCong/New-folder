<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tSupplierCategory.home.createOrEditLabel"
          data-cy="TSupplierCategoryCreateUpdateHeading"
          v-text="$t('sainsApp.tSupplierCategory.home.createOrEditLabel')"
        >
          Create or edit a TSupplierCategory
        </h2>
        <div>
          <div class="form-group" v-if="tSupplierCategory.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tSupplierCategory.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSupplierCategory.spcCategory')" for="t-supplier-category-spcCategory"
              >Spc Category</label
            >
            <input
              type="text"
              class="form-control"
              name="spcCategory"
              id="t-supplier-category-spcCategory"
              data-cy="spcCategory"
              :class="{ valid: !$v.tSupplierCategory.spcCategory.$invalid, invalid: $v.tSupplierCategory.spcCategory.$invalid }"
              v-model="$v.tSupplierCategory.spcCategory.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSupplierCategory.enteredBy')" for="t-supplier-category-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-supplier-category-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tSupplierCategory.enteredBy.$invalid, invalid: $v.tSupplierCategory.enteredBy.$invalid }"
              v-model.number="$v.tSupplierCategory.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSupplierCategory.enteredDate')" for="t-supplier-category-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-supplier-category-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tSupplierCategory.enteredDate.$invalid, invalid: $v.tSupplierCategory.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSupplierCategory.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSupplierCategory.modifiedBy')" for="t-supplier-category-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-supplier-category-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tSupplierCategory.modifiedBy.$invalid, invalid: $v.tSupplierCategory.modifiedBy.$invalid }"
              v-model.number="$v.tSupplierCategory.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tSupplierCategory.modifiedDate')" for="t-supplier-category-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-supplier-category-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tSupplierCategory.modifiedDate.$invalid, invalid: $v.tSupplierCategory.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tSupplierCategory.modifiedDate.$model)"
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
            :disabled="$v.tSupplierCategory.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-supplier-category-update.component.ts"></script>
