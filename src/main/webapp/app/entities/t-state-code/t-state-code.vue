<template>
  <div>
    <h2 id="page-heading" data-cy="TStateCodeHeading">
      <span v-text="$t('sainsApp.tStateCode.home.title')" id="t-state-code-heading">T State Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tStateCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TStateCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-state-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tStateCode.home.createLabel')"> Create a new T State Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tStateCodes && tStateCodes.length === 0">
      <span v-text="$t('sainsApp.tStateCode.home.notFound')">No tStateCodes found</span>
    </div>
    <div class="table-responsive" v-if="tStateCodes && tStateCodes.length > 0">
      <table class="table table-striped" aria-describedby="tStateCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.stateName')">State Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.stateCode')">State Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tStateCode.tCountryCode')">T Country Code</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tStateCode in tStateCodes" :key="tStateCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TStateCodeView', params: { tStateCodeId: tStateCode.id } }">{{ tStateCode.id }}</router-link>
            </td>
            <td>{{ tStateCode.stateName }}</td>
            <td>{{ tStateCode.stateCode }}</td>
            <td>{{ tStateCode.enteredBy }}</td>
            <td>{{ tStateCode.enteredDate ? $d(Date.parse(tStateCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tStateCode.modifiedBy }}</td>
            <td>{{ tStateCode.modifiedDate ? $d(Date.parse(tStateCode.modifiedDate), 'short') : '' }}</td>
            <td>
              <div v-if="tStateCode.tCountryCode">
                <router-link :to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tStateCode.tCountryCode.id } }">{{
                  tStateCode.tCountryCode.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TStateCodeView', params: { tStateCodeId: tStateCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TStateCodeEdit', params: { tStateCodeId: tStateCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tStateCode)"
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
        ><span id="sainsApp.tStateCode.delete.question" data-cy="tStateCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tStateCode-heading" v-text="$t('sainsApp.tStateCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T State Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tStateCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTStateCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-state-code.component.ts"></script>
