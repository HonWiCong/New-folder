<template>
  <div>
    <h2 id="page-heading" data-cy="TTitleCodeHeading">
      <span v-text="$t('sainsApp.tTitleCode.home.title')" id="t-title-code-heading">T Title Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tTitleCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TTitleCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-title-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tTitleCode.home.createLabel')"> Create a new T Title Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tTitleCodes && tTitleCodes.length === 0">
      <span v-text="$t('sainsApp.tTitleCode.home.notFound')">No tTitleCodes found</span>
    </div>
    <div class="table-responsive" v-if="tTitleCodes && tTitleCodes.length > 0">
      <table class="table table-striped" aria-describedby="tTitleCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tTitleCode.ttTitle')">Tt Title</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tTitleCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tTitleCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tTitleCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tTitleCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tTitleCode in tTitleCodes" :key="tTitleCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TTitleCodeView', params: { tTitleCodeId: tTitleCode.id } }">{{ tTitleCode.id }}</router-link>
            </td>
            <td>{{ tTitleCode.ttTitle }}</td>
            <td>{{ tTitleCode.enteredBy }}</td>
            <td>{{ tTitleCode.enteredDate ? $d(Date.parse(tTitleCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tTitleCode.modifiedBy }}</td>
            <td>{{ tTitleCode.modifiedDate ? $d(Date.parse(tTitleCode.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TTitleCodeView', params: { tTitleCodeId: tTitleCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TTitleCodeEdit', params: { tTitleCodeId: tTitleCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tTitleCode)"
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
        ><span id="sainsApp.tTitleCode.delete.question" data-cy="tTitleCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tTitleCode-heading" v-text="$t('sainsApp.tTitleCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T Title Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tTitleCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTTitleCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-title-code.component.ts"></script>
