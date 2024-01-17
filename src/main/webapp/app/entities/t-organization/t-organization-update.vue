<template>
	<div class="row justify-content-center">
		<div class="col-10">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tOrganization.home.createOrEditLabel"
					data-cy="TSupplierCreateUpdateHeading"
					v-text="$t('sainsApp.tOrganization.home.createOrEditLabel')"
				></h2>
				<div>
					<div class="form-group" v-if="tOrganization.id">
						<label for="id" v-text="$t('global.field.id')"></label>
						<input type="text" class="form-control" id="id" name="id" v-model="tOrganization.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOrganization.orgBrn')" for="t-organization-orgBrn"></label>
						<input
							type="text"
							class="form-control"
							name="orgBrn"
							id="t-organization-orgBrn"
							data-cy="orgBrn"
							:class="{ valid: !$v.tOrganization.orgBrn.$invalid, invalid: $v.tOrganization.orgBrn.$invalid }"
							v-model="$v.tOrganization.orgBrn.$model"
						/>
					</div>
					<div class="row">
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgCode')"
								for="t-organization-orgCode"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgCode"
								id="t-organization-orgCode"
								data-cy="orgCode"
								:class="{ valid: !$v.tOrganization.orgCode.$invalid, invalid: $v.tOrganization.orgCode.$invalid }"
								v-model="$v.tOrganization.orgCode.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgName')"
								for="t-organization-orgName"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgName"
								id="t-organization-orgName"
								data-cy="orgName"
								:class="{ valid: !$v.tOrganization.orgName.$invalid, invalid: $v.tOrganization.orgName.$invalid }"
								v-model="$v.tOrganization.orgName.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgNameOther')"
								for="t-organization-orgNameOther"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgNameOther"
								id="t-organization-orgNameOther"
								data-cy="orgNameOther"
								:class="{
									valid: !$v.tOrganization.orgNameOther.$invalid,
									invalid: $v.tOrganization.orgNameOther.$invalid,
								}"
								v-model="$v.tOrganization.orgNameOther.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgShortname')"
								for="t-organization-orgShortname"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgShortname"
								id="t-organization-orgShortname"
								data-cy="orgShortname"
								:class="{
									valid: !$v.tOrganization.orgShortname.$invalid,
									invalid: $v.tOrganization.orgShortname.$invalid,
								}"
								v-model="$v.tOrganization.orgShortname.$model"
							/>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrganization.orgAddress')"
							for="t-organization-orgAddress"
						></label>
						<input
							type="text"
							class="form-control"
							name="orgAddress"
							id="t-organization-orgAddress"
							data-cy="orgAddress"
							:class="{ valid: !$v.tOrganization.orgAddress.$invalid, invalid: $v.tOrganization.orgAddress.$invalid }"
							v-model="$v.tOrganization.orgAddress.$model"
						/>
					</div>
					<div class="row">
						<div class="col-3 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgCountry')"
								for="t-organization-orgCountry"
							></label>
							<select name="" id="" v-model="$v.tOrganization.orgCountry.$model" class="form-control">
								<option v-for="country in tCountryCodes" :value="country.id" v-bind:key="country.id">
									{{ country.countryName }}
								</option>
							</select>
						</div>
						<div class="col-3 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgState')"
								for="t-organization-orgState"
							></label>
							<select name="" id="" v-model="$v.tOrganization.orgState.$model" class="form-control">
								<option v-for="state in tStateCodes" :value="state.id" v-bind:key="state.id">
									{{ state.stateName }}
								</option>
							</select>
						</div>
						<div class="col-3 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgCity')"
								for="t-organization-orgCity"
							></label>
							<select name="" id="" v-model="$v.tOrganization.orgCity.$model" class="form-control">
								<option v-for="city in tCityCodes" :value="city.id" v-bind:key="city.id">
									{{ city.cityName }}
								</option>
							</select>
						</div>
						<div class="col-3 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgPostcode')"
								for="t-organization-orgPostcode"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgPostcode"
								id="t-organization-orgPostcode"
								data-cy="orgPostcode"
								:class="{
									valid: !$v.tOrganization.orgPostcode.$invalid,
									invalid: $v.tOrganization.orgPostcode.$invalid,
								}"
								v-model="$v.tOrganization.orgPostcode.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgOffPhone1')"
								for="t-organization-orgOffPhone1"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgOffPhone1"
								id="t-organization-orgOffPhone1"
								data-cy="orgOffPhone1"
								:class="{
									valid: !$v.tOrganization.orgOffPhone1.$invalid,
									invalid: $v.tOrganization.orgOffPhone1.$invalid,
								}"
								v-model="$v.tOrganization.orgOffPhone1.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgOffPhone2')"
								for="t-organization-orgOffPhone2"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgOffPhone2"
								id="t-organization-orgOffPhone2"
								data-cy="orgOffPhone2"
								:class="{
									valid: !$v.tOrganization.orgOffPhone2.$invalid,
									invalid: $v.tOrganization.orgOffPhone2.$invalid,
								}"
								v-model="$v.tOrganization.orgOffPhone2.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgOffFax1')"
								for="t-organization-orgOffFax1"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgOffFax1"
								id="t-organization-orgOffFax1"
								data-cy="orgOffFax1"
								:class="{ valid: !$v.tOrganization.orgOffFax1.$invalid, invalid: $v.tOrganization.orgOffFax1.$invalid }"
								v-model="$v.tOrganization.orgOffFax1.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgOffFax2')"
								for="t-organization-orgOffFax2"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgOffFax2"
								id="t-organization-orgOffFax2"
								data-cy="orgOffFax2"
								:class="{ valid: !$v.tOrganization.orgOffFax2.$invalid, invalid: $v.tOrganization.orgOffFax2.$invalid }"
								v-model="$v.tOrganization.orgOffFax2.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgDivision')"
								for="t-organization-orgDivision"
							></label>
							<select name="" id="" v-model="$v.tOrganization.orgDivision.$model" class="form-control">
								<option v-for="division in tDivisionCodes" :value="division.id" v-bind:key="division.id">
									{{ division.divName }}
								</option>
							</select>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgDistrict')"
								for="t-organization-orgDistrict"
							></label>
							<select name="" id="" v-model="$v.tOrganization.orgDistrict.$model" class="form-control">
								<option v-for="district in districtList" :value="district.id" v-bind:key="district.id">
									{{ district.disName }}
								</option>
							</select>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgWebsite')"
								for="t-organization-orgWebsite"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgWebsite"
								id="t-organization-orgWebsite"
								data-cy="orgWebsite"
								:class="{ valid: !$v.tOrganization.orgWebsite.$invalid, invalid: $v.tOrganization.orgWebsite.$invalid }"
								v-model="$v.tOrganization.orgWebsite.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgEmail')"
								for="t-organization-orgEmail"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgEmail"
								id="t-organization-orgEmail"
								data-cy="orgEmail"
								:class="{ valid: !$v.tOrganization.orgEmail.$invalid, invalid: $v.tOrganization.orgEmail.$invalid }"
								v-model="$v.tOrganization.orgEmail.$model"
							/>
						</div>
						<div class="col-12 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgCompanyGstNo')"
								for="t-organization-orgCompanyGstNo"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgCompanyGstNo"
								id="t-organization-orgCompanyGstNo"
								data-cy="orgCompanyGstNo"
								:class="{
									valid: !$v.tOrganization.orgCompanyGstNo.$invalid,
									invalid: $v.tOrganization.orgCompanyGstNo.$invalid,
								}"
								v-model="$v.tOrganization.orgCompanyGstNo.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgCompanyGstRegDate')"
								for="t-organization-orgCompanyGstRegDate"
							></label>
							<b-input-group class="mb-3">
								<b-input-group-prepend>
									<b-form-datepicker
										aria-controls="t-organization-orgCompanyGstRegDate"
										v-model="$v.tOrganization.orgCompanyGstRegDate.$model"
										name="orgCompanyGstRegDate"
										class="form-control"
										:locale="currentLanguage"
										button-only
										today-button
										reset-button
										close-button
									>
									</b-form-datepicker>
								</b-input-group-prepend>
								<b-form-input
									id="t-organization-orgCompanyGstRegDate"
									data-cy="orgCompanyGstRegDate"
									type="text"
									class="form-control"
									name="orgCompanyGstRegDate"
									:class="{
										valid: !$v.tOrganization.orgCompanyGstRegDate.$invalid,
										invalid: $v.tOrganization.orgCompanyGstRegDate.$invalid,
									}"
									v-model="$v.tOrganization.orgCompanyGstRegDate.$model"
								/>
							</b-input-group>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgCompanyGstDeregDate')"
								for="t-organization-orgCompanyGstDeregDate"
							></label>
							<b-input-group class="mb-3">
								<b-input-group-prepend>
									<b-form-datepicker
										aria-controls="t-organization-orgCompanyGstDeregDate"
										v-model="$v.tOrganization.orgCompanyGstDeregDate.$model"
										name="orgCompanyGstDeregDate"
										class="form-control"
										:locale="currentLanguage"
										button-only
										today-button
										reset-button
										close-button
									>
									</b-form-datepicker>
								</b-input-group-prepend>
								<b-form-input
									id="t-organization-orgCompanyGstDeregDate"
									data-cy="orgCompanyGstDeregDate"
									type="text"
									class="form-control"
									name="orgCompanyGstDeregDate"
									:class="{
										valid: !$v.tOrganization.orgCompanyGstDeregDate.$invalid,
										invalid: $v.tOrganization.orgCompanyGstDeregDate.$invalid,
									}"
									v-model="$v.tOrganization.orgCompanyGstDeregDate.$model"
								/>
							</b-input-group>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrganization.orgPoTaxInclusive')"
							for="t-organization-orgPoTaxInclusive"
						></label>
						<input
							type="text"
							class="form-control"
							name="orgPoTaxInclusive"
							id="t-organization-orgPoTaxInclusive"
							data-cy="orgPoTaxInclusive"
							:class="{
								valid: !$v.tOrganization.orgPoTaxInclusive.$invalid,
								invalid: $v.tOrganization.orgPoTaxInclusive.$invalid,
							}"
							v-model="$v.tOrganization.orgPoTaxInclusive.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrganization.orgDefaultTaxCode')"
							for="t-organization-orgDefaultTaxCode"
						></label>
						<select name="" id="" v-model="$v.tOrganization.orgDefaultTaxCode.$model" class="form-control">
							<option v-for="tax in tSmTaxCodes" :value="tax.id" v-bind:key="tax.id">
								{{ tax.smTaxCode }} ~ {{ tax.smTaxDescription }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrganization.orgSupplierCategory')"
							for="t-organization-orgSupplierCategory"
						></label>
						<input
							type="text"
							class="form-control"
							name="orgSupplierCategory"
							id="t-organization-orgSupplierCategory"
							data-cy="orgSupplierCategory"
							:class="{
								valid: !$v.tOrganization.orgSupplierCategory.$invalid,
								invalid: $v.tOrganization.orgSupplierCategory.$invalid,
							}"
							v-model="$v.tOrganization.orgSupplierCategory.$model"
						/>
					</div>
					<div class="row">
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgSector')"
								for="t-organization-orgSector"
							></label>
							<select name="" id="" v-model="$v.tOrganization.orgSector.$model" class="form-control">
								<option v-for="sector in tSectorCodes" :value="sector.id" v-bind:key="sector.id">
									{{ sector.sectorName }}
								</option>
							</select>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgIndustry')"
								for="t-organization-orgIndustry"
							></label>
							<select name="orgIndustry" id="" v-model="$v.tOrganization.orgIndustry.$model" class="form-control">
								<option v-for="industry in tIndustryCodes" :value="industry.id" v-bind:key="industry.id">
									{{ industry.industryName }}
								</option>
							</select>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgSainsGroup')"
								for="t-organization-orgSainsGroup"
							></label>
							<div>
								<input
									type="radio"
									value="S"
									name="orgSainsGroup"
									id="group"
									data-cy="orgSainsGroup"
									:class="{
										valid: !$v.tOrganization.orgSainsGroup.$invalid,
										invalid: $v.tOrganization.orgSainsGroup.$invalid,
									}"
									v-model="$v.tOrganization.orgSainsGroup.$model"
								/>
								<label for="group">SAINS GROUP</label>
								<input
									type="radio"
									value="N"
									name="orgSainsGroup"
									id="none"
									data-cy="orgSainsGroup"
									:class="{
										valid: !$v.tOrganization.orgSainsGroup.$invalid,
										invalid: $v.tOrganization.orgSainsGroup.$invalid,
									}"
									v-model="$v.tOrganization.orgSainsGroup.$model"
								/>
								<label for="none">None</label>
							</div>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgBumiputra')"
								for="t-organization-orgBumiputra"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgBumiputra"
								id="t-organization-orgBumiputra"
								data-cy="orgBumiputra"
								:class="{ valid: !$v.tOrganization.orgBumiputra.$invalid, invalid: $v.tOrganization.orgBumiputra.$invalid }"
								v-model="$v.tOrganization.orgBumiputra.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgUpkReg')"
								for="t-organization-orgUpkReg"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgUpkReg"
								id="t-organization-orgUpkReg"
								data-cy="orgUpkReg"
								:class="{ valid: !$v.tOrganization.orgUpkReg.$invalid, invalid: $v.tOrganization.orgUpkReg.$invalid }"
								v-model="$v.tOrganization.orgUpkReg.$model"
							/>
						</div>
						<div class="col-6 form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgMofReg')"
								for="t-organization-orgMofReg"
							></label>
							<input
								type="text"
								class="form-control"
								name="orgMofReg"
								id="t-organization-orgMofReg"
								data-cy="orgMofReg"
								:class="{ valid: !$v.tOrganization.orgMofReg.$invalid, invalid: $v.tOrganization.orgMofReg.$invalid }"
								v-model="$v.tOrganization.orgMofReg.$model"
							/>
						</div>
					</div>
					<hr />
				</div>
				<div class="d-flex justify-content-between align-items-center">
					<h2>Contact Person</h2>
					<button type="button" class="btn btn-primary" @click="addContactPerson">Add</button>
				</div>
				<div class="row label-row">
					<div class="col-1 form-group">
						<div class="form-control-label">No.</div>
					</div>
					<div class="col-1 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrgContactPerson.ocpTitle')"
							for="t-org-contact-person-ocpTitle"
						></label>
					</div>
					<div class="col-2 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrgContactPerson.ocpName')"
							for="t-org-contact-person-ocpName"
						></label>
					</div>
					<div class="col-2 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrgContactPerson.ocpDesignation')"
							for="t-org-contact-person-ocpDesignation"
						></label>
					</div>
					<div class="col-2 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrganization.orgDirectline')"
							for="t-organization-orgDirectline"
						></label>
					</div>
					<div class="col-2 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrgContactPerson.ocpHandphone')"
							for="t-org-contact-person-ocpHandphone"
						></label>
					</div>
					<div class="col-1 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrgContactPerson.ocpMail')"
							for="t-org-contact-person-ocpMail"
						></label>
					</div>
				</div>
				<div class="row" v-for="(contactPerson, i) in tOrganization.contactPersons" :key="i">
					<div class="col-1 text-center">{{ i + 1 }}</div>
					<div class="col-1 form-group">
						<select name="" id="" v-model="contactPerson.ocpTitle" class="form-control">
							<option v-for="title in tTitleCodes" :value="title.id" v-bind:key="title.id">
								{{ title.ttTitle }}
							</option>
						</select>
					</div>
					<div class="col-2 form-group">
						<b-form-input
							type="text"
							class="form-control"
							name="ocpName"
							id="t-org-contact-person-ocpName"
							:class="{
								valid: contactPerson.ocpName !== undefined,
								invalid: contactPerson.ocpName === undefined && contactPerson.ocpName === '',
							}"
							v-model="contactPerson.ocpName"
						/>
					</div>
					<div class="col-2 form-group">
						<input
							type="text"
							class="form-control"
							name="ocpDesignation"
							id="t-org-contact-person-ocpDesignation"
							data-cy="ocpDesignation"
							:class="{
								valid: !$v.tOrgContactPerson.ocpDesignation.$invalid,
								invalid: $v.tOrgContactPerson.ocpDesignation.$invalid,
							}"
							v-model="contactPerson.ocpDesignation"
						/>
					</div>
					<div class="col-2 form-group">
						<input
							type="text"
							class="form-control"
							name="orgDirectline"
							id="t-organization-orgDirectline"
							data-cy="orgDirectline"
							:class="{
								valid: !$v.tOrganization.orgDirectline.$invalid,
								invalid: $v.tOrganization.orgDirectline.$invalid,
							}"
							v-model="$v.tOrganization.orgDirectline.$model"
						/>
					</div>
					<div class="col-2 form-group">
						<input
							type="text"
							class="form-control"
							name="ocpHandphone"
							id="t-org-contact-person-ocpHandphone"
							data-cy="ocpHandphone"
							:class="{
								valid: !$v.tOrgContactPerson.ocpHandphone.$invalid,
								invalid: $v.tOrgContactPerson.ocpHandphone.$invalid,
							}"
							v-model="contactPerson.ocpHandphone"
						/>
					</div>
					<div class="col-1 form-group">
						<input
							type="text"
							class="form-control"
							name="ocpMail"
							id="t-org-contact-person-ocpMail"
							data-cy="ocpMail"
							:class="{ valid: !$v.tOrgContactPerson.ocpMail.$invalid, invalid: $v.tOrgContactPerson.ocpMail.$invalid }"
							v-model="contactPerson.ocpMail"
						/>
					</div>
					<div class="col-1 form-group d-flex justify-content-center align-items-center icon">
						<svg
							@click="removeContactPerson(i)"
							xmlns="http://www.w3.org/2000/svg"
							width="24"
							height="24"
							viewBox="0 0 24 24"
							fill="none"
							stroke="currentColor"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
							class="lucide lucide-trash-2"
						>
							<path d="M3 6h18" />
							<path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6" />
							<path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2" />
							<line x1="10" x2="10" y1="11" y2="17" />
							<line x1="14" x2="14" y1="11" y2="17" />
						</svg>
					</div>
				</div>
				<div class="form-group">
					<label
						class="form-control-label"
						v-text="$t('sainsApp.tOrganization.orgRemarks')"
						for="t-organization-orgRemarks"
					></label>
					<input
						type="text"
						class="form-control"
						name="orgRemarks"
						id="t-organization-orgRemarks"
						data-cy="orgRemarks"
						:class="{ valid: !$v.tOrganization.orgRemarks.$invalid, invalid: $v.tOrganization.orgRemarks.$invalid }"
						v-model="$v.tOrganization.orgRemarks.$model"
					/>
				</div>
				<div class="row">
					<div class="col-2 form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tOrgContactPerson.ocpStatus')"
							for="t-org-contact-person-ocpStatus"
						></label>
						<select name="" id="" class="form-control" v-model="$v.tOrgContactPerson.ocpStatus.$model">
							<option value="Y">Active</option>
							<option value="N">Inactive</option>
						</select>
					</div>
				</div>
				<div>
					<button
						type="button"
						id="cancel-save"
						data-cy="entityCreateCancelButton"
						class="btn btn-secondary"
						v-on:click="previousState()"
					>
						<font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')"></span>
					</button>
					<button
						type="submit"
						id="save-entity"
						data-cy="entityCreateSaveButton"
						:disabled="$v.tOrganization.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')"></span>
					</button>
					<button type="button" @click="checkInvalid">Check</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-organization-update.component.ts"></script>
<style scoped>
.icon {
	cursor: pointer;
}
</style>
