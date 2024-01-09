<template>
  <div>
    <h2 id="page-heading" data-cy="TSectorCodeHeading">
      <span v-text="$t('sainsApp.tSectorCode.home.title')" id="t-sector-code-heading">T Sector Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tSectorCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TSectorCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-sector-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tSectorCode.home.createLabel')"> Create a new T Sector Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tSectorCodes && tSectorCodes.length === 0">
      <span v-text="$t('sainsApp.tSectorCode.home.notFound')">No tSectorCodes found</span>
    </div>
    <div class="table-responsive" v-if="tSectorCodes && tSectorCodes.length > 0">
      <table class="table table-striped" aria-describedby="tSectorCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSectorCode.sectorName')">Sector Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSectorCode.sectorDescription')">Sector Description</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSectorCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSectorCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSectorCode.modifyBy')">Modify By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSectorCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tSectorCode in tSectorCodes" :key="tSectorCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TSectorCodeView', params: { tSectorCodeId: tSectorCode.id } }">{{ tSectorCode.id }}</router-link>
            </td>
            <td>{{ tSectorCode.sectorName }}</td>
            <td>{{ tSectorCode.sectorDescription }}</td>
            <td>{{ tSectorCode.enteredBy }}</td>
            <td>{{ tSectorCode.enteredDate ? $d(Date.parse(tSectorCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tSectorCode.modifyBy }}</td>
            <td>{{ tSectorCode.modifiedDate ? $d(Date.parse(tSectorCode.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TSectorCodeView', params: { tSectorCodeId: tSectorCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TSectorCodeEdit', params: { tSectorCodeId: tSectorCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tSectorCode)"
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
        ><span id="sainsApp.tSectorCode.delete.question" data-cy="tSectorCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tSectorCode-heading" v-text="$t('sainsApp.tSectorCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T Sector Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tSectorCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTSectorCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-sector-code.component.ts"></script>
