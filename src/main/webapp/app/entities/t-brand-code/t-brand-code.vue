<template>
  <div>
    <h2 id="page-heading" data-cy="TBrandCodeHeading">
      <span v-text="$t('sainsApp.tBrandCode.home.title')" id="t-brand-code-heading">T Brand Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tBrandCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TBrandCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-brand-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tBrandCode.home.createLabel')"> Create a new T Brand Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tBrandCodes && tBrandCodes.length === 0">
      <span v-text="$t('sainsApp.tBrandCode.home.notFound')">No tBrandCodes found</span>
    </div>
    <div class="table-responsive" v-if="tBrandCodes && tBrandCodes.length > 0">
      <table class="table table-striped" aria-describedby="tBrandCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tBrandCode.brandName')">Brand Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tBrandCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tBrandCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tBrandCode.modifyBy')">Modify By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tBrandCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tBrandCode in tBrandCodes" :key="tBrandCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TBrandCodeView', params: { tBrandCodeId: tBrandCode.id } }">{{ tBrandCode.id }}</router-link>
            </td>
            <td>{{ tBrandCode.brandName }}</td>
            <td>{{ tBrandCode.enteredBy }}</td>
            <td>{{ tBrandCode.enteredDate ? $d(Date.parse(tBrandCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tBrandCode.modifyBy }}</td>
            <td>{{ tBrandCode.modifiedDate ? $d(Date.parse(tBrandCode.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TBrandCodeView', params: { tBrandCodeId: tBrandCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TBrandCodeEdit', params: { tBrandCodeId: tBrandCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tBrandCode)"
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
        ><span id="sainsApp.tBrandCode.delete.question" data-cy="tBrandCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tBrandCode-heading" v-text="$t('sainsApp.tBrandCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T Brand Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tBrandCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTBrandCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-brand-code.component.ts"></script>
