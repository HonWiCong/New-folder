<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tOrganization">
				<h2 class="jh-entity-heading" data-cy="tOrganizationDetailsHeading">
					<span v-text="$t('sainsApp.tOrganization.detail.title')">TOrganization</span> {{ tOrganization.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgHqid')">Org Hqid</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgHqid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgHqBr')">Org Hq Br</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgHqBr }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCode')">Org Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgBrn')">Org Brn</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgBrn }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgPtaxid')">Org Ptaxid</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgPtaxid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgDefaultTaxCode')">Org Default Tax Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgDefaultTaxCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCompanyGstNo')">Org Company Gst No</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCompanyGstNo }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCompanyGstRegDate')">Org Company Gst Reg Date</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCompanyGstRegDate }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCompanyGstDeregDate')">Org Company Gst Dereg Date</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCompanyGstDeregDate }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgPoTaxInclusive')">Org Po Tax Inclusive</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgPoTaxInclusive }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgName')">Org Name</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgName }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgNameOther')">Org Name Other</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgNameOther }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgShortname')">Org Shortname</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgShortname }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgAddress')">Org Address</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgAddress }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgShippingAddress')">Org Shipping Address</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgShippingAddress }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgBillingAddress')">Org Billing Address</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgBillingAddress }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgPostcode')">Org Postcode</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgPostcode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCity')">Org City</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCity }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgState')">Org State</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgState }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCountry')">Org Country</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCountry }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOffPhone1')">Org Off Phone 1</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOffPhone1 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOffPhone2')">Org Off Phone 2</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOffPhone2 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOffPhone3')">Org Off Phone 3</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOffPhone3 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOffFax1')">Org Off Fax 1</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOffFax1 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOffFax2')">Org Off Fax 2</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOffFax2 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCredittermid')">Org Credittermid</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCredittermid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCreditLimit')">Org Credit Limit</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCreditLimit }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgAgencyid')">Org Agencyid</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgAgencyid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgDivision')">Org Division</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgDivision }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgDistrict')">Org District</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgDistrict }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgWebsite')">Org Website</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgWebsite }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgEmail')">Org Email</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgEmail }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgSupplierCategory')">Org Supplier Category</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgSupplierCategory }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCurrencyCode')">Org Currency Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCurrencyCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgType')">Org Type</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgType }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgSectorid')">Org Sectorid</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgSectorid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgSector')">Org Sector</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgSector }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgIndustry')">Org Industry</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgIndustry }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgSainsGroup')">Org Sains Group</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgSainsGroup }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgBumiputra')">Org Bumiputra</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgBumiputra }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgUpkReg')">Org Upk Reg</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgUpkReg }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgMofReg')">Org Mof Reg</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgMofReg }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgDesignation')">Org Designation</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgDesignation }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgContpersonTitle')">Org Contperson Title</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgContpersonTitle }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgContperson')">Org Contperson</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgContperson }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgDirectline')">Org Directline</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgDirectline }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgContpEmail')">Org Contp Email</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgContpEmail }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgContpHp')">Org Contp Hp</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgContpHp }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgRemarks')">Org Remarks</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgRemarks }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgActiveStatus')">Org Active Status</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgActiveStatus }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCcGc')">Org Cc Gc</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCcGc }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCustomerCateCode')">Org Customer Cate Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCustomerCateCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgVendorCateCode')">Org Vendor Cate Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgVendorCateCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgSalesCateCode')">Org Sales Cate Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgSalesCateCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOutstandingBalance')">Org Outstanding Balance</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOutstandingBalance }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgOutstandingBalanceEx')">Org Outstanding Balance Ex</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgOutstandingBalanceEx }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgCompanyCode')">Org Company Code</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgCompanyCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.orgDcrownPostStatus')">Org Dcrown Post Status</span>
					</dt>
					<dd>
						<span>{{ tOrganization.orgDcrownPostStatus }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.confirmedBy')">Confirmed By</span>
					</dt>
					<dd>
						<span>{{ tOrganization.confirmedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.confirmedDate')">Confirmed Date</span>
					</dt>
					<dd>
						<span v-if="tOrganization.confirmedDate">{{ $d(Date.parse(tOrganization.confirmedDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tOrganization.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tOrganization.enteredDate">{{ $d(Date.parse(tOrganization.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tOrganization.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOrganization.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tOrganization.modifiedDate">{{ $d(Date.parse(tOrganization.modifiedDate), 'long') }}</span>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tOrganization.id"
					:to="{ name: 'TOrganizationEdit', params: { tOrganizationId: tOrganization.id } }"
					custom
					v-slot="{ navigate }"
				>
					<button @click="navigate" class="btn btn-primary">
						<font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
					</button>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script lang="ts" src="./t-organization-details.component.ts"></script>
