<template>
  <div>
    <h2 id="page-heading" data-cy="TCityCodeHeading">
      <span v-text="$t('sainsApp.tCityCode.home.title')" id="t-city-code-heading">T City Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tCityCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TCityCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-city-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tCityCode.home.createLabel')"> Create a new T City Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tCityCodes && tCityCodes.length === 0">
      <span v-text="$t('sainsApp.tCityCode.home.notFound')">No tCityCodes found</span>
    </div>
    <div class="table-responsive" v-if="tCityCodes && tCityCodes.length > 0">
      <table class="table table-striped" aria-describedby="tCityCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.cityCode')">City Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.cityName')">City Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCityCode.tStateCode')">T State Code</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tCityCode in tCityCodes" :key="tCityCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TCityCodeView', params: { tCityCodeId: tCityCode.id } }">{{ tCityCode.id }}</router-link>
            </td>
            <td>{{ tCityCode.cityCode }}</td>
            <td>{{ tCityCode.cityName }}</td>
            <td>{{ tCityCode.enteredBy }}</td>
            <td>{{ tCityCode.enteredDate ? $d(Date.parse(tCityCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tCityCode.modifiedBy }}</td>
            <td>{{ tCityCode.modifiedDate ? $d(Date.parse(tCityCode.modifiedDate), 'short') : '' }}</td>
            <td>
              <div v-if="tCityCode.tStateCode">
                <router-link :to="{ name: 'TStateCodeView', params: { tStateCodeId: tCityCode.tStateCode.id } }">{{
                  tCityCode.tStateCode.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TCityCodeView', params: { tCityCodeId: tCityCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TCityCodeEdit', params: { tCityCodeId: tCityCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tCityCode)"
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
        ><span id="sainsApp.tCityCode.delete.question" data-cy="tCityCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tCityCode-heading" v-text="$t('sainsApp.tCityCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T City Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tCityCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTCityCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-city-code.component.ts"></script>
