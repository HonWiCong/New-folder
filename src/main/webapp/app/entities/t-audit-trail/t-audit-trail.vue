<template>
  <div>
    <h2 id="page-heading" data-cy="TAuditTrailHeading">
      <span v-text="$t('sainsApp.tAuditTrail.home.title')" id="t-audit-trail-heading">T Audit Trails</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tAuditTrail.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TAuditTrailCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-audit-trail"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tAuditTrail.home.createLabel')"> Create a new T Audit Trail </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tAuditTrails && tAuditTrails.length === 0">
      <span v-text="$t('sainsApp.tAuditTrail.home.notFound')">No tAuditTrails found</span>
    </div>
    <div class="table-responsive" v-if="tAuditTrails && tAuditTrails.length > 0">
      <table class="table table-striped" aria-describedby="tAuditTrails">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.userId')">User Id</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.dateTime')">Date Time</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.tableName')">Table Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.auditAction')">Audit Action</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.recordId')">Record Id</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.fieldDesc')">Field Desc</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.atStatus')">At Status</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tAuditTrail.stFullDesc')">St Full Desc</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tAuditTrail in tAuditTrails" :key="tAuditTrail.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TAuditTrailView', params: { tAuditTrailId: tAuditTrail.id } }">{{ tAuditTrail.id }}</router-link>
            </td>
            <td>{{ tAuditTrail.userId }}</td>
            <td>{{ tAuditTrail.dateTime ? $d(Date.parse(tAuditTrail.dateTime), 'short') : '' }}</td>
            <td>{{ tAuditTrail.tableName }}</td>
            <td>{{ tAuditTrail.auditAction }}</td>
            <td>{{ tAuditTrail.recordId }}</td>
            <td>{{ tAuditTrail.fieldDesc }}</td>
            <td>{{ tAuditTrail.atStatus }}</td>
            <td>{{ tAuditTrail.stFullDesc }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TAuditTrailView', params: { tAuditTrailId: tAuditTrail.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TAuditTrailEdit', params: { tAuditTrailId: tAuditTrail.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tAuditTrail)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="sainsApp.tAuditTrail.delete.question" data-cy="tAuditTrailDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tAuditTrail-heading" v-text="$t('sainsApp.tAuditTrail.delete.question', { id: removeId })">
          Are you sure you want to delete this T Audit Trail?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tAuditTrail"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTAuditTrail()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-audit-trail.component.ts"></script>
