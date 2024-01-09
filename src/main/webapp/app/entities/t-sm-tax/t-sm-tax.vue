<template>
  <div>
    <h2 id="page-heading" data-cy="TSmTaxHeading">
      <span v-text="$t('sainsApp.tSmTax.home.title')" id="t-sm-tax-heading">T Sm Taxes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tSmTax.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TSmTaxCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-sm-tax"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tSmTax.home.createLabel')"> Create a new T Sm Tax </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tSmTaxes && tSmTaxes.length === 0">
      <span v-text="$t('sainsApp.tSmTax.home.notFound')">No tSmTaxes found</span>
    </div>
    <div class="table-responsive" v-if="tSmTaxes && tSmTaxes.length > 0">
      <table class="table table-striped" aria-describedby="tSmTaxes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxCode')">Sm Tax Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxDescription')">Sm Tax Description</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxPercentage')">Sm Tax Percentage</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxType')">Sm Tax Type</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxGstCode')">Sm Tax Gst Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxGstType')">Sm Tax Gst Type</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxCollectedGlCode')">Sm Tax Collected Gl Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxCollectedGlDesc')">Sm Tax Collected Gl Desc</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxCollectedCostCenter')">Sm Tax Collected Cost Center</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxPaidGlCode')">Sm Tax Paid Gl Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxPaidGlDesc')">Sm Tax Paid Gl Desc</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxPaidCostCenter')">Sm Tax Paid Cost Center</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxTaxAuthority')">Sm Tax Tax Authority</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxStatus')">Sm Tax Status</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxEnteredBy')">Sm Tax Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxEnteredDate')">Sm Tax Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxModifiedBy')">Sm Tax Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxModifiedDate')">Sm Tax Modified Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxConfirmedBy')">Sm Tax Confirmed By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxConfirmedDate')">Sm Tax Confirmed Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxNarration')">Sm Tax Narration</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxDisplay')">Sm Tax Display</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxRcmFlag')">Sm Tax Rcm Flag</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tSmTax.smTaxCga')">Sm Tax Cga</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tSmTax in tSmTaxes" :key="tSmTax.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TSmTaxView', params: { tSmTaxId: tSmTax.id } }">{{ tSmTax.id }}</router-link>
            </td>
            <td>{{ tSmTax.smTaxCode }}</td>
            <td>{{ tSmTax.smTaxDescription }}</td>
            <td>{{ tSmTax.smTaxPercentage }}</td>
            <td>{{ tSmTax.smTaxType }}</td>
            <td>{{ tSmTax.smTaxGstCode }}</td>
            <td>{{ tSmTax.smTaxGstType }}</td>
            <td>{{ tSmTax.smTaxCollectedGlCode }}</td>
            <td>{{ tSmTax.smTaxCollectedGlDesc }}</td>
            <td>{{ tSmTax.smTaxCollectedCostCenter }}</td>
            <td>{{ tSmTax.smTaxPaidGlCode }}</td>
            <td>{{ tSmTax.smTaxPaidGlDesc }}</td>
            <td>{{ tSmTax.smTaxPaidCostCenter }}</td>
            <td>{{ tSmTax.smTaxTaxAuthority }}</td>
            <td v-text="$t('sainsApp.TaxStatus.' + tSmTax.smTaxStatus)">{{ tSmTax.smTaxStatus }}</td>
            <td>{{ tSmTax.smTaxEnteredBy }}</td>
            <td>{{ tSmTax.smTaxEnteredDate ? $d(Date.parse(tSmTax.smTaxEnteredDate), 'short') : '' }}</td>
            <td>{{ tSmTax.smTaxModifiedBy }}</td>
            <td>{{ tSmTax.smTaxModifiedDate ? $d(Date.parse(tSmTax.smTaxModifiedDate), 'short') : '' }}</td>
            <td>{{ tSmTax.smTaxConfirmedBy }}</td>
            <td>{{ tSmTax.smTaxConfirmedDate ? $d(Date.parse(tSmTax.smTaxConfirmedDate), 'short') : '' }}</td>
            <td>{{ tSmTax.smTaxNarration }}</td>
            <td>{{ tSmTax.smTaxDisplay }}</td>
            <td>{{ tSmTax.smTaxRcmFlag }}</td>
            <td>{{ tSmTax.smTaxCga }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'TSmTaxView', params: { tSmTaxId: tSmTax.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'TSmTaxEdit', params: { tSmTaxId: tSmTax.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tSmTax)"
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
        ><span id="sainsApp.tSmTax.delete.question" data-cy="tSmTaxDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tSmTax-heading" v-text="$t('sainsApp.tSmTax.delete.question', { id: removeId })">
          Are you sure you want to delete this T Sm Tax?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tSmTax"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTSmTax()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-sm-tax.component.ts"></script>
