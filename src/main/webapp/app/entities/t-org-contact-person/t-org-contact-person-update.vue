<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tOrgContactPerson.home.createOrEditLabel"
          data-cy="TOrgContactPersonCreateUpdateHeading"
          v-text="$t('sainsApp.tOrgContactPerson.home.createOrEditLabel')"
        >
          Create or edit a TOrgContactPerson
        </h2>
        <div>
          <div class="form-group" v-if="tOrgContactPerson.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tOrgContactPerson.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpOrgcodeid')" for="t-org-contact-person-ocpOrgcodeid"
              >Ocp Orgcodeid</label
            >
            <input
              type="number"
              class="form-control"
              name="ocpOrgcodeid"
              id="t-org-contact-person-ocpOrgcodeid"
              data-cy="ocpOrgcodeid"
              :class="{ valid: !$v.tOrgContactPerson.ocpOrgcodeid.$invalid, invalid: $v.tOrgContactPerson.ocpOrgcodeid.$invalid }"
              v-model.number="$v.tOrgContactPerson.ocpOrgcodeid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpTitle')" for="t-org-contact-person-ocpTitle"
              >Ocp Title</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpTitle"
              id="t-org-contact-person-ocpTitle"
              data-cy="ocpTitle"
              :class="{ valid: !$v.tOrgContactPerson.ocpTitle.$invalid, invalid: $v.tOrgContactPerson.ocpTitle.$invalid }"
              v-model="$v.tOrgContactPerson.ocpTitle.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpName')" for="t-org-contact-person-ocpName"
              >Ocp Name</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpName"
              id="t-org-contact-person-ocpName"
              data-cy="ocpName"
              :class="{ valid: !$v.tOrgContactPerson.ocpName.$invalid, invalid: $v.tOrgContactPerson.ocpName.$invalid }"
              v-model="$v.tOrgContactPerson.ocpName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrgContactPerson.ocpDesignation')"
              for="t-org-contact-person-ocpDesignation"
              >Ocp Designation</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpDesignation"
              id="t-org-contact-person-ocpDesignation"
              data-cy="ocpDesignation"
              :class="{ valid: !$v.tOrgContactPerson.ocpDesignation.$invalid, invalid: $v.tOrgContactPerson.ocpDesignation.$invalid }"
              v-model="$v.tOrgContactPerson.ocpDesignation.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrgContactPerson.ocpTelephone1')"
              for="t-org-contact-person-ocpTelephone1"
              >Ocp Telephone 1</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpTelephone1"
              id="t-org-contact-person-ocpTelephone1"
              data-cy="ocpTelephone1"
              :class="{ valid: !$v.tOrgContactPerson.ocpTelephone1.$invalid, invalid: $v.tOrgContactPerson.ocpTelephone1.$invalid }"
              v-model="$v.tOrgContactPerson.ocpTelephone1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpHandphone')" for="t-org-contact-person-ocpHandphone"
              >Ocp Handphone</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpHandphone"
              id="t-org-contact-person-ocpHandphone"
              data-cy="ocpHandphone"
              :class="{ valid: !$v.tOrgContactPerson.ocpHandphone.$invalid, invalid: $v.tOrgContactPerson.ocpHandphone.$invalid }"
              v-model="$v.tOrgContactPerson.ocpHandphone.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpMail')" for="t-org-contact-person-ocpMail"
              >Ocp Mail</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpMail"
              id="t-org-contact-person-ocpMail"
              data-cy="ocpMail"
              :class="{ valid: !$v.tOrgContactPerson.ocpMail.$invalid, invalid: $v.tOrgContactPerson.ocpMail.$invalid }"
              v-model="$v.tOrgContactPerson.ocpMail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpSector')" for="t-org-contact-person-ocpSector"
              >Ocp Sector</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpSector"
              id="t-org-contact-person-ocpSector"
              data-cy="ocpSector"
              :class="{ valid: !$v.tOrgContactPerson.ocpSector.$invalid, invalid: $v.tOrgContactPerson.ocpSector.$invalid }"
              v-model="$v.tOrgContactPerson.ocpSector.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.ocpStatus')" for="t-org-contact-person-ocpStatus"
              >Ocp Status</label
            >
            <input
              type="text"
              class="form-control"
              name="ocpStatus"
              id="t-org-contact-person-ocpStatus"
              data-cy="ocpStatus"
              :class="{ valid: !$v.tOrgContactPerson.ocpStatus.$invalid, invalid: $v.tOrgContactPerson.ocpStatus.$invalid }"
              v-model="$v.tOrgContactPerson.ocpStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.enteredBy')" for="t-org-contact-person-enteredBy"
              >Entered By</label
            >
            <input
              type="number"
              class="form-control"
              name="enteredBy"
              id="t-org-contact-person-enteredBy"
              data-cy="enteredBy"
              :class="{ valid: !$v.tOrgContactPerson.enteredBy.$invalid, invalid: $v.tOrgContactPerson.enteredBy.$invalid }"
              v-model.number="$v.tOrgContactPerson.enteredBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.enteredDate')" for="t-org-contact-person-enteredDate"
              >Entered Date</label
            >
            <div class="d-flex">
              <input
                id="t-org-contact-person-enteredDate"
                data-cy="enteredDate"
                type="datetime-local"
                class="form-control"
                name="enteredDate"
                :class="{ valid: !$v.tOrgContactPerson.enteredDate.$invalid, invalid: $v.tOrgContactPerson.enteredDate.$invalid }"
                :value="convertDateTimeFromServer($v.tOrgContactPerson.enteredDate.$model)"
                @change="updateZonedDateTimeField('enteredDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.modifiedBy')" for="t-org-contact-person-modifiedBy"
              >Modified By</label
            >
            <input
              type="number"
              class="form-control"
              name="modifiedBy"
              id="t-org-contact-person-modifiedBy"
              data-cy="modifiedBy"
              :class="{ valid: !$v.tOrgContactPerson.modifiedBy.$invalid, invalid: $v.tOrgContactPerson.modifiedBy.$invalid }"
              v-model.number="$v.tOrgContactPerson.modifiedBy.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tOrgContactPerson.modifiedDate')" for="t-org-contact-person-modifiedDate"
              >Modified Date</label
            >
            <div class="d-flex">
              <input
                id="t-org-contact-person-modifiedDate"
                data-cy="modifiedDate"
                type="datetime-local"
                class="form-control"
                name="modifiedDate"
                :class="{ valid: !$v.tOrgContactPerson.modifiedDate.$invalid, invalid: $v.tOrgContactPerson.modifiedDate.$invalid }"
                :value="convertDateTimeFromServer($v.tOrgContactPerson.modifiedDate.$model)"
                @change="updateZonedDateTimeField('modifiedDate', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('sainsApp.tOrgContactPerson.tOrganization')"
              for="t-org-contact-person-tOrganization"
              >T Organization</label
            >
            <select
              class="form-control"
              id="t-org-contact-person-tOrganization"
              data-cy="tOrganization"
              name="tOrganization"
              v-model="tOrgContactPerson.tOrganization"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  tOrgContactPerson.tOrganization && tOrganizationOption.id === tOrgContactPerson.tOrganization.id
                    ? tOrgContactPerson.tOrganization
                    : tOrganizationOption
                "
                v-for="tOrganizationOption in tOrganizations"
                :key="tOrganizationOption.id"
              >
                {{ tOrganizationOption.id }}
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
            :disabled="$v.tOrgContactPerson.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-org-contact-person-update.component.ts"></script>
