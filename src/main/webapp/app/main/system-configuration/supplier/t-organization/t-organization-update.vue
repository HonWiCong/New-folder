<template>
	<div class="row justify-content-center">
		<div class="col-12">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<section>
					<h5>Basic Information</h5>
					<div class="form-group" v-if="tOrganization.id">
						<label for="id" v-text="$t('global.field.id')"></label>
						<b-form-input type="text" id="id" name="id" v-model="tOrganization.id" readonly />
					</div>
					<div class="column-2">
						<div class="form-group">
							<label
								class="form-control-label"
								v-text="$t('sainsApp.tOrganization.orgBrn')"
								for="t-organization-orgBrn"
							></label>
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
						<div class="form-group">
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
					</div>
					<div class="column-2">
						<div class="form-group">
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
						<div class="form-group">
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
						<div class="form-group">
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
						<div class="form-group">
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
					</div>
				</section>

				<section>
					<h5>Address & Contact</h5>
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
					</div>
				</section>

				<section>
					<h5>Company Information</h5>
					<div class="form-group">
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
					<div class="column-2">
						<div class="form-group">
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
						<div class="form-group">
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
								:class="{
									valid: !$v.tOrganization.orgBumiputra.$invalid,
									invalid: $v.tOrganization.orgBumiputra.$invalid,
								}"
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
				</section>
				<hr />

				<section>
					<div class="d-flex justify-content-between align-items-center">
						<h5 class="mb-3">Contact Person</h5>
						<button type="button" class="btn btn-primary" @click="addContactPerson">Add</button>
					</div>
					<table class="contact-person-table">
						<thead class="">
							<tr>
								<td class="header-column id-col">No.</td>
								<td class="header-column" v-text="$t('sainsApp.tOrgContactPerson.ocpTitle')"></td>
								<td class="header-column" v-text="$t('sainsApp.tOrgContactPerson.ocpName')"></td>
								<td class="header-column" v-text="$t('sainsApp.tOrgContactPerson.ocpDesignation')"></td>
								<td class="header-column" v-text="$t('sainsApp.tOrganization.orgDirectline')"></td>
								<td class="header-column" v-text="$t('sainsApp.tOrgContactPerson.ocpHandphone')"></td>
								<td class="header-column" v-text="$t('sainsApp.tOrgContactPerson.ocpMail')"></td>
								<td class="header-column"></td>
							</tr>
						</thead>
						<tbody>
							<tr class="" v-for="(contactPerson, index) in $v.tOrganization.contactPersons.$each.$iter" :key="index">
								<td class="text-center">{{ parseInt(index.toString()) + 1 }}</td>
								<td class="">
									<select name="" id="" v-model="contactPerson.ocpTitle.$model" class="form-control">
										<option value="" disabled>Select one</option>
										<option v-for="title in tTitleCodes" :value="title.id" v-bind:key="title.id">
											{{ title.ttTitle }}
										</option>
									</select>
								</td>
								<td class="">
									<b-form-input
										placeholder="Jason"
										class="form-control"
										name="ocpName"
										:class="{
											valid: contactPerson.ocpName.$invalid,
											invalid: contactPerson.ocpName.$invalid,
										}"
										v-model="contactPerson.ocpName.$model"
									/>
								</td>
								<td class="">
									<input
										placeholder="Manager"
										class="form-control"
										name="ocpDesignation"
										:class="{
											valid: !contactPerson.ocpDesignation.$invalid,
											invalid: contactPerson.ocpDesignation.$invalid,
										}"
										v-model="contactPerson.ocpDesignation.$model"
									/>
								</td>
								<td class="">
									<input
										placeholder="0102485728"
										class="form-control"
										name="orgDirectline"
										:class="{
											valid: !$v.tOrganization.orgDirectline.$invalid,
											invalid: $v.tOrganization.orgDirectline.$invalid,
										}"
										v-model="$v.tOrganization.orgDirectline.$model"
									/>
								</td>
								<td class="">
									<input
										placeholder="0123456789"
										class="form-control"
										name="ocpHandphone"
										:class="{
											valid: !contactPerson.ocpHandphone.$invalid,
											invalid: contactPerson.ocpHandphone.$invalid,
										}"
										v-model="contactPerson.ocpHandphone.$model"
									/>
								</td>
								<td class="">
									<input
										placeholder="jason@gmail.com"
										class="form-control"
										name="ocpMail"
										:class="{ valid: !contactPerson.ocpMail.$invalid, invalid: contactPerson.ocpMail.$invalid }"
										v-model="contactPerson.ocpMail.$model"
									/>
								</td>
								<td class="">
									<X class="icon" @click="removeContactPerson(parseInt(index.toString()))" />
								</td>
							</tr>
						</tbody>
					</table>
				</section>

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
						<select name="" id="" class="form-control" v-model="$v.tOrganization.orgActiveStatus.$model">
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
	color: red;
}
section {
	border-radius: 10px;
	background-color: white;
	padding: 2rem;
	margin-bottom: 2rem;
}
.form-group {
	/* display: flex; */
}
.column-2 {
	display: grid;
	grid-template-columns: 1fr 1fr;
	column-gap: 2rem;
}
.contact-person-table input {
	border: none;
	outline: none;
	color: black;
}
.contact-person-table input:focus {
	border: none;
	outline: none;
	box-shadow: none;
}
.contact-person-table input::placeholder {
	color: hsla(0, 0%, 50%, 0.8) !important;
}
.contact-person-table select {
	border: none;
}
.contact-person-table select:focus {
	border: none;
	outline: none;
	box-shadow: none;
}
.header-column {
	color: #6c757d;
	font-weight: bold;
	padding: 0.5rem 0.75rem;
	padding-bottom: 1rem;
	border-bottom: 1px solid #e0e0e0;
}
.id-col {
	user-select: none;
}
</style>
