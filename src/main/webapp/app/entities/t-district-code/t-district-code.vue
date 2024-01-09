<template>
  <div>
    <h2 id="page-heading" data-cy="TDistrictCodeHeading">
      <span v-text="$t('sainsApp.tDistrictCode.home.title')" id="t-district-code-heading">T District Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tDistrictCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TDistrictCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-district-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tDistrictCode.home.createLabel')"> Create a new T District Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tDistrictCodes && tDistrictCodes.length === 0">
      <span v-text="$t('sainsApp.tDistrictCode.home.notFound')">No tDistrictCodes found</span>
    </div>
    <div class="table-responsive" v-if="tDistrictCodes && tDistrictCodes.length > 0">
      <table class="table table-striped" aria-describedby="tDistrictCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDistrictCode.disName')">Dis Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDistrictCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDistrictCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDistrictCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDistrictCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tDistrictCode.tDivisionCode')">T Division Code</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tDistrictCode in tDistrictCodes" :key="tDistrictCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TDistrictCodeView', params: { tDistrictCodeId: tDistrictCode.id } }">{{
                tDistrictCode.id
              }}</router-link>
            </td>
            <td>{{ tDistrictCode.disName }}</td>
            <td>{{ tDistrictCode.enteredBy }}</td>
            <td>{{ tDistrictCode.enteredDate ? $d(Date.parse(tDistrictCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tDistrictCode.modifiedBy }}</td>
            <td>{{ tDistrictCode.modifiedDate ? $d(Date.parse(tDistrictCode.modifiedDate), 'short') : '' }}</td>
            <td>
              <div v-if="tDistrictCode.tDivisionCode">
                <router-link :to="{ name: 'TDivisionCodeView', params: { tDivisionCodeId: tDistrictCode.tDivisionCode.id } }">{{
                  tDistrictCode.tDivisionCode.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TDistrictCodeView', params: { tDistrictCodeId: tDistrictCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TDistrictCodeEdit', params: { tDistrictCodeId: tDistrictCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tDistrictCode)"
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
        ><span id="sainsApp.tDistrictCode.delete.question" data-cy="tDistrictCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tDistrictCode-heading" v-text="$t('sainsApp.tDistrictCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T District Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tDistrictCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTDistrictCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-district-code.component.ts"></script>
