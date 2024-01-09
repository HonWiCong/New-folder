<template>
  <div>
    <h2 id="page-heading" data-cy="TOrganizationHeading">
      <span v-text="$t('sainsApp.tOrganization.home.title')" id="t-organization-heading">T Organizations</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('sainsApp.tOrganization.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'TOrganizationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-t-organization"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('sainsApp.tOrganization.home.createLabel')"> Create a new T Organization </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && tOrganizations && tOrganizations.length === 0">
      <span v-text="$t('sainsApp.tOrganization.home.notFound')">No tOrganizations found</span>
    </div>
    <div class="table-responsive" v-if="tOrganizations && tOrganizations.length > 0">
      <table class="table table-striped" aria-describedby="tOrganizations">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgHqid')">Org Hqid</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgHqBr')">Org Hq Br</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCode')">Org Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgBrn')">Org Brn</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgPtaxid')">Org Ptaxid</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDefaultTaxCode')">Org Default Tax Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCompanyGstNo')">Org Company Gst No</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCompanyGstRegDate')">Org Company Gst Reg Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCompanyGstDeregDate')">Org Company Gst Dereg Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgPoTaxInclusive')">Org Po Tax Inclusive</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgName')">Org Name</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgNameOther')">Org Name Other</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgShortname')">Org Shortname</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgAddress')">Org Address</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgShippingAddress')">Org Shipping Address</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgBillingAddress')">Org Billing Address</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgPostcode')">Org Postcode</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCity')">Org City</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgState')">Org State</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCountry')">Org Country</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOffPhone1')">Org Off Phone 1</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOffPhone2')">Org Off Phone 2</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOffPhone3')">Org Off Phone 3</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOffFax1')">Org Off Fax 1</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOffFax2')">Org Off Fax 2</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCredittermid')">Org Credittermid</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCreditLimit')">Org Credit Limit</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgAgencyid')">Org Agencyid</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDivision')">Org Division</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDistrict')">Org District</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgWebsite')">Org Website</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgEmail')">Org Email</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgSupplierCategory')">Org Supplier Category</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCurrencyCode')">Org Currency Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgType')">Org Type</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgSectorid')">Org Sectorid</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgSector')">Org Sector</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgIndustry')">Org Industry</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgSainsGroup')">Org Sains Group</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgBumiputra')">Org Bumiputra</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgUpkReg')">Org Upk Reg</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgMofReg')">Org Mof Reg</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDesignation')">Org Designation</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContpersonTitle')">Org Contperson Title</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContperson')">Org Contperson</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDirectline')">Org Directline</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContpEmail')">Org Contp Email</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgContpHp')">Org Contp Hp</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgRemarks')">Org Remarks</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgActiveStatus')">Org Active Status</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCcGc')">Org Cc Gc</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCustomerCateCode')">Org Customer Cate Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgVendorCateCode')">Org Vendor Cate Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgSalesCateCode')">Org Sales Cate Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOutstandingBalance')">Org Outstanding Balance</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgOutstandingBalanceEx')">Org Outstanding Balance Ex</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgCompanyCode')">Org Company Code</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.orgDcrownPostStatus')">Org Dcrown Post Status</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.confirmedBy')">Confirmed By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.confirmedDate')">Confirmed Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.enteredBy')">Entered By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.enteredDate')">Entered Date</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.modifiedBy')">Modified By</span></th>
            <th scope="row"><span v-text="$t('sainsApp.tOrganization.modifiedDate')">Modified Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tOrganization in tOrganizations" :key="tOrganization.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TOrganizationView', params: { tOrganizationId: tOrganization.id } }">{{
                tOrganization.id
              }}</router-link>
            </td>
            <td>{{ tOrganization.orgHqid }}</td>
            <td>{{ tOrganization.orgHqBr }}</td>
            <td>{{ tOrganization.orgCode }}</td>
            <td>{{ tOrganization.orgBrn }}</td>
            <td>{{ tOrganization.orgPtaxid }}</td>
            <td>{{ tOrganization.orgDefaultTaxCode }}</td>
            <td>{{ tOrganization.orgCompanyGstNo }}</td>
            <td>{{ tOrganization.orgCompanyGstRegDate }}</td>
            <td>{{ tOrganization.orgCompanyGstDeregDate }}</td>
            <td>{{ tOrganization.orgPoTaxInclusive }}</td>
            <td>{{ tOrganization.orgName }}</td>
            <td>{{ tOrganization.orgNameOther }}</td>
            <td>{{ tOrganization.orgShortname }}</td>
            <td>{{ tOrganization.orgAddress }}</td>
            <td>{{ tOrganization.orgShippingAddress }}</td>
            <td>{{ tOrganization.orgBillingAddress }}</td>
            <td>{{ tOrganization.orgPostcode }}</td>
            <td>{{ tOrganization.orgCity }}</td>
            <td>{{ tOrganization.orgState }}</td>
            <td>{{ tOrganization.orgCountry }}</td>
            <td>{{ tOrganization.orgOffPhone1 }}</td>
            <td>{{ tOrganization.orgOffPhone2 }}</td>
            <td>{{ tOrganization.orgOffPhone3 }}</td>
            <td>{{ tOrganization.orgOffFax1 }}</td>
            <td>{{ tOrganization.orgOffFax2 }}</td>
            <td>{{ tOrganization.orgCredittermid }}</td>
            <td>{{ tOrganization.orgCreditLimit }}</td>
            <td>{{ tOrganization.orgAgencyid }}</td>
            <td>{{ tOrganization.orgDivision }}</td>
            <td>{{ tOrganization.orgDistrict }}</td>
            <td>{{ tOrganization.orgWebsite }}</td>
            <td>{{ tOrganization.orgEmail }}</td>
            <td>{{ tOrganization.orgSupplierCategory }}</td>
            <td>{{ tOrganization.orgCurrencyCode }}</td>
            <td>{{ tOrganization.orgType }}</td>
            <td>{{ tOrganization.orgSectorid }}</td>
            <td>{{ tOrganization.orgSector }}</td>
            <td>{{ tOrganization.orgIndustry }}</td>
            <td>{{ tOrganization.orgSainsGroup }}</td>
            <td>{{ tOrganization.orgBumiputra }}</td>
            <td>{{ tOrganization.orgUpkReg }}</td>
            <td>{{ tOrganization.orgMofReg }}</td>
            <td>{{ tOrganization.orgDesignation }}</td>
            <td>{{ tOrganization.orgContpersonTitle }}</td>
            <td>{{ tOrganization.orgContperson }}</td>
            <td>{{ tOrganization.orgDirectline }}</td>
            <td>{{ tOrganization.orgContpEmail }}</td>
            <td>{{ tOrganization.orgContpHp }}</td>
            <td>{{ tOrganization.orgRemarks }}</td>
            <td>{{ tOrganization.orgActiveStatus }}</td>
            <td>{{ tOrganization.orgCcGc }}</td>
            <td>{{ tOrganization.orgCustomerCateCode }}</td>
            <td>{{ tOrganization.orgVendorCateCode }}</td>
            <td>{{ tOrganization.orgSalesCateCode }}</td>
            <td>{{ tOrganization.orgOutstandingBalance }}</td>
            <td>{{ tOrganization.orgOutstandingBalanceEx }}</td>
            <td>{{ tOrganization.orgCompanyCode }}</td>
            <td>{{ tOrganization.orgDcrownPostStatus }}</td>
            <td>{{ tOrganization.confirmedBy }}</td>
            <td>{{ tOrganization.confirmedDate ? $d(Date.parse(tOrganization.confirmedDate), 'short') : '' }}</td>
            <td>{{ tOrganization.enteredBy }}</td>
            <td>{{ tOrganization.enteredDate ? $d(Date.parse(tOrganization.enteredDate), 'short') : '' }}</td>
            <td>{{ tOrganization.modifiedBy }}</td>
            <td>{{ tOrganization.modifiedDate ? $d(Date.parse(tOrganization.modifiedDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TOrganizationView', params: { tOrganizationId: tOrganization.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TOrganizationEdit', params: { tOrganizationId: tOrganization.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(tOrganization)"
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
        ><span id="sainsApp.tOrganization.delete.question" data-cy="tOrganizationDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-tOrganization-heading" v-text="$t('sainsApp.tOrganization.delete.question', { id: removeId })">
          Are you sure you want to delete this T Organization?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-tOrganization"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeTOrganization()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./t-organization.component.ts"></script>
