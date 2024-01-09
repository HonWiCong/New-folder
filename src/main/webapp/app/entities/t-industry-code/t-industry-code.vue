<template>
  <div>
    <h2 id="page-heading" data-cy="TIndustryCodeHeading">
      <span v-text="$t('sainsApp.tIndustryCode.home.title')" id="t-industry-code-heading">T Industry Codes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tIndustryCode.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TIndustryCodeCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-industry-code"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tIndustryCode.home.createLabel')"> Create a new T Industry Code </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tIndustryCodes && tIndustryCodes.length === 0">
      <span v-text="$t('sainsApp.tIndustryCode.home.notFound')">No tIndustryCodes found</span>
    </div>
    <div class="table-responsive" v-if="tIndustryCodes && tIndustryCodes.length > 0">
      <table class="table table-striped" aria-describedby="tIndustryCodes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tIndustryCode.industryName')">Industry Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tIndustryCode.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tIndustryCode.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tIndustryCode.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tIndustryCode.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tIndustryCode in tIndustryCodes" :key="tIndustryCode.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TIndustryCodeView', params: { tIndustryCodeId: tIndustryCode.id } }">{{
                tIndustryCode.id
              }}</router-link>
            </td>
            <td>{{ tIndustryCode.industryName }}</td>
            <td>{{ tIndustryCode.enteredBy }}</td>
            <td>{{ tIndustryCode.enteredDate ? $d(Date.parse(tIndustryCode.enteredDate), 'short') : '' }}</td>
            <td>{{ tIndustryCode.modifiedBy }}</td>
            <td>{{ tIndustryCode.modifiedDate ? $d(Date.parse(tIndustryCode.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TIndustryCodeView', params: { tIndustryCodeId: tIndustryCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TIndustryCodeEdit', params: { tIndustryCodeId: tIndustryCode.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tIndustryCode)"
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
        ><span id="sainsApp.tIndustryCode.delete.question" data-cy="tIndustryCodeDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tIndustryCode-heading" v-text="$t('sainsApp.tIndustryCode.delete.question', { id: removeId })">
          Are you sure you want to delete this T Industry Code?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tIndustryCode"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTIndustryCode()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-industry-code.component.ts"></script>
