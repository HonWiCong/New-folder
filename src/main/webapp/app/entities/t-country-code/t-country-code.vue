<template>
  <div>
    <h2 id="page-heading" data-cy="TCountryCodeHeading">
      <span v-text="$t('sainsApp.tCountryCode.home.title')" id="t-country-code-heading">T Country Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tCountryCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TCountryCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-country-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tCountryCode.home.createLabel')"> Create a new T Country Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tCountryCodes && tCountryCodes.length === 0">
      <span v-text="$t('sainsApp.tCountryCode.home.notFound')">No tCountryCodes found</span>
    </div>
    <div class="table-responsive" v-if="tCountryCodes && tCountryCodes.length > 0">
      <table class="table table-striped" aria-describedby="tCountryCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.countryCode')">Country Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.countryName')">Country Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.countryNationality')">Country Nationality</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tCountryCode.orgCustomerType')">Org Customer Type</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tCountryCode in tCountryCodes" :key="tCountryCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tCountryCode.id } }">{{
                tCountryCode.id
              }}</router-link>
            </td>
            <td>{{ tCountryCode.countryCode }}</td>
            <td>{{ tCountryCode.countryName }}</td>
            <td>{{ tCountryCode.countryNationality }}</td>
            <td>{{ tCountryCode.enteredBy }}</td>
            <td>{{ tCountryCode.enteredDate ? $d(Date.parse(tCountryCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tCountryCode.modifiedBy }}</td>
            <td>{{ tCountryCode.modifiedDate ? $d(Date.parse(tCountryCode.modifiedDate), 'short') : '' }}</td>
            <td>{{ tCountryCode.orgCustomerType }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tCountryCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TCountryCodeEdit', params: { tCountryCodeId: tCountryCode.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tCountryCode)"
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
        ><span id="sainsApp.tCountryCode.delete.question" data-cy="tCountryCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tCountryCode-heading" v-text="$t('sainsApp.tCountryCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T Country Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tCountryCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTCountryCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-country-code.component.ts"></script>
