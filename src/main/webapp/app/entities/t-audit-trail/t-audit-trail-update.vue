<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="sainsApp.tAuditTrail.home.createOrEditLabel"
          data-cy="TAuditTrailCreateUpdateHeading"
          v-text="$t('sainsApp.tAuditTrail.home.createOrEditLabel')"
        >
          Create or edit a TAuditTrail
        </h2>
        <div>
          <div class="form-group" v-if="tAuditTrail.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="tAuditTrail.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.userId')" for="t-audit-trail-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="t-audit-trail-userId"
              data-cy="userId"
              :class="{ valid: !$v.tAuditTrail.userId.$invalid, invalid: $v.tAuditTrail.userId.$invalid }"
              v-model.number="$v.tAuditTrail.userId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.dateTime')" for="t-audit-trail-dateTime">Date Time</label>
            <div class="d-flex">
              <input
                id="t-audit-trail-dateTime"
                data-cy="dateTime"
                type="datetime-local"
                class="form-control"
                name="dateTime"
                :class="{ valid: !$v.tAuditTrail.dateTime.$invalid, invalid: $v.tAuditTrail.dateTime.$invalid }"
                :value="convertDateTimeFromServer($v.tAuditTrail.dateTime.$model)"
                @change="updateZonedDateTimeField('dateTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.tableName')" for="t-audit-trail-tableName">Table Name</label>
            <input
              type="text"
              class="form-control"
              name="tableName"
              id="t-audit-trail-tableName"
              data-cy="tableName"
              :class="{ valid: !$v.tAuditTrail.tableName.$invalid, invalid: $v.tAuditTrail.tableName.$invalid }"
              v-model="$v.tAuditTrail.tableName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.auditAction')" for="t-audit-trail-auditAction"
              >Audit Action</label
            >
            <input
              type="text"
              class="form-control"
              name="auditAction"
              id="t-audit-trail-auditAction"
              data-cy="auditAction"
              :class="{ valid: !$v.tAuditTrail.auditAction.$invalid, invalid: $v.tAuditTrail.auditAction.$invalid }"
              v-model="$v.tAuditTrail.auditAction.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.recordId')" for="t-audit-trail-recordId">Record Id</label>
            <input
              type="number"
              class="form-control"
              name="recordId"
              id="t-audit-trail-recordId"
              data-cy="recordId"
              :class="{ valid: !$v.tAuditTrail.recordId.$invalid, invalid: $v.tAuditTrail.recordId.$invalid }"
              v-model.number="$v.tAuditTrail.recordId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.fieldDesc')" for="t-audit-trail-fieldDesc">Field Desc</label>
            <input
              type="text"
              class="form-control"
              name="fieldDesc"
              id="t-audit-trail-fieldDesc"
              data-cy="fieldDesc"
              :class="{ valid: !$v.tAuditTrail.fieldDesc.$invalid, invalid: $v.tAuditTrail.fieldDesc.$invalid }"
              v-model="$v.tAuditTrail.fieldDesc.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.atStatus')" for="t-audit-trail-atStatus">At Status</label>
            <input
              type="text"
              class="form-control"
              name="atStatus"
              id="t-audit-trail-atStatus"
              data-cy="atStatus"
              :class="{ valid: !$v.tAuditTrail.atStatus.$invalid, invalid: $v.tAuditTrail.atStatus.$invalid }"
              v-model="$v.tAuditTrail.atStatus.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('sainsApp.tAuditTrail.stFullDesc')" for="t-audit-trail-stFullDesc"
              >St Full Desc</label
            >
            <input
              type="text"
              class="form-control"
              name="stFullDesc"
              id="t-audit-trail-stFullDesc"
              data-cy="stFullDesc"
              :class="{ valid: !$v.tAuditTrail.stFullDesc.$invalid, invalid: $v.tAuditTrail.stFullDesc.$invalid }"
              v-model="$v.tAuditTrail.stFullDesc.$model"
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
            :disabled="$v.tAuditTrail.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./t-audit-trail-update.component.ts"></script>
