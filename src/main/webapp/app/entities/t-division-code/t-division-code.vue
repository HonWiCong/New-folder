<template>
  <div>
    <h2 id="page-heading" data-cy="TDivisionCodeHeading">
      <span v-text="$t('sainsApp.tDivisionCode.home.title')" id="t-division-code-heading">T Division Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tDivisionCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TDivisionCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-division-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tDivisionCode.home.createLabel')"> Create a new T Division Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tDivisionCodes && tDivisionCodes.length === 0">
      <span v-text="$t('sainsApp.tDivisionCode.home.notFound')">No tDivisionCodes found</span>
    </div>
    <div class="table-responsive" v-if="tDivisionCodes && tDivisionCodes.length > 0">
      <table class="table table-striped" aria-describedby="tDivisionCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDivisionCode.divName')">Div Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDivisionCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDivisionCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDivisionCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDivisionCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tDivisionCode in tDivisionCodes" :key="tDivisionCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TDivisionCodeView', params: { tDivisionCodeId: tDivisionCode.id } }">{{
                tDivisionCode.id
              }}</router-link>
            </td>
            <td>{{ tDivisionCode.divName }}</td>
            <td>{{ tDivisionCode.enteredBy }}</td>
            <td>{{ tDivisionCode.enteredDate ? $d(Date.parse(tDivisionCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tDivisionCode.modifiedBy }}</td>
            <td>{{ tDivisionCode.modifiedDate ? $d(Date.parse(tDivisionCode.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TDivisionCodeView', params: { tDivisionCodeId: tDivisionCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TDivisionCodeEdit', params: { tDivisionCodeId: tDivisionCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tDivisionCode)"
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
        ><span id="sainsApp.tDivisionCode.delete.question" data-cy="tDivisionCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tDivisionCode-heading" v-text="$t('sainsApp.tDivisionCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T Division Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tDivisionCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTDivisionCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-division-code.component.ts"></script>
