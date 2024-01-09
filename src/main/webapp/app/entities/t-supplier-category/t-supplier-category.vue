<template>
  <div>
    <h2 id="page-heading" data-cy="TSupplierCategoryHeading">
      <span v-text="$t('sainsApp.tSupplierCategory.home.title')" id="t-supplier-category-heading">T Supplier Categories</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tSupplierCategory.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TSupplierCategoryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-supplier-category"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tSupplierCategory.home.createLabel')"> Create a new T Supplier Category </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tSupplierCategories && tSupplierCategories.length === 0">
      <span v-text="$t('sainsApp.tSupplierCategory.home.notFound')">No tSupplierCategories found</span>
    </div>
    <div class="table-responsive" v-if="tSupplierCategories && tSupplierCategories.length > 0">
      <table class="table table-striped" aria-describedby="tSupplierCategories">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSupplierCategory.spcCategory')">Spc Category</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSupplierCategory.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSupplierCategory.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSupplierCategory.modifyBy')">Modify By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSupplierCategory.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tSupplierCategory in tSupplierCategories" :key="tSupplierCategory.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TSupplierCategoryView', params: { tSupplierCategoryId: tSupplierCategory.id } }">{{
                tSupplierCategory.id
              }}</router-link>
            </td>
            <td>{{ tSupplierCategory.spcCategory }}</td>
            <td>{{ tSupplierCategory.enteredBy }}</td>
            <td>{{ tSupplierCategory.enteredDate ? $d(Date.parse(tSupplierCategory.enteredDate), 'short') : '' }}</td>
            <td>{{ tSupplierCategory.modifyBy }}</td>
            <td>{{ tSupplierCategory.modifiedDate ? $d(Date.parse(tSupplierCategory.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TSupplierCategoryView', params: { tSupplierCategoryId: tSupplierCategory.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TSupplierCategoryEdit', params: { tSupplierCategoryId: tSupplierCategory.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tSupplierCategory)"
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
        ><span
          id="sainsApp.tSupplierCategory.delete.question"
          data-cy="tSupplierCategoryDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tSupplierCategory-heading" v-text="$t('sainsApp.tSupplierCategory.delete.question', { id: removeId })">
          Are you sure you want to delete this T Supplier Category?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tSupplierCategory"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTSupplierCategory()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-supplier-category.component.ts"></script>
