<!-- eslint-disable prettier/prettier -->
<template>
	<form name="editForm" role="form" novalidate v-on:submit.prevent="save()" v-if="applicationUserAccount">
		<section class="mt-3 user-detail-form-section">
			<h4 class="title mb-3">User Details</h4>
			<div class="user-detail-form-layout">
				<div class="form-group cell" :hidden="!applicationUserAccount.internalUser.id">
					<label v-text="$t('global.field.id')">ID</label>
					<b-form-input class="form-control" name="id" v-model="applicationUserAccount.internalUser.id" readonly />
				</div>
				<div class="form-group cell">
					<label class="form-control-label" v-text="$t('userManagement.login')">Login</label>
					<b-form-input
						class="form-control"
						:class="{ valid: !$v.userAccount.login.$invalid, invalid: $v.userAccount.login.$invalid }"
						v-model="applicationUserAccount.internalUser.login"
					/>

					<div v-if="$v.userAccount.login.$anyDirty && $v.userAccount.login.$invalid">
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.login.required"
							v-text="$t('entity.validation.required')"
						>
							This field is required.
						</small>

						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.login.maxLength"
							v-text="$t('entity.validation.maxlength', { max: 50 })"
						>
							This field cannot be longer than 50 characters.
						</small>

						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.login.pattern"
							v-text="$t('entity.validation.patternLogin')"
						>
							This field can only contain letters, digits and e-mail addresses.
						</small>
					</div>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" for="firstName" v-text="$t('userManagement.firstName')">First Name</label>
					<b-form-input
						class="form-control"
						id="firstName"
						v-bind:placeholder="$t('settings.form[\'firstname.placeholder\']')"
						:class="{
							valid: !$v.userAccount.firstName.$invalid,
							invalid: $v.userAccount.firstName.$invalid,
						}"
						v-model="applicationUserAccount.internalUser.firstName"
					/>
					<div v-if="$v.userAccount.firstName.$anyDirty && $v.userAccount.firstName.$invalid">
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.firstName.maxLength"
							v-text="$t('entity.validation.maxlength', { max: 50 })"
						>
							This field cannot be longer than 50 characters.
						</small>
					</div>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" for="lastName" v-text="$t('userManagement.lastName')">Last Name</label>
					<b-form-input
						class="form-control"
						id="lastName"
						name="lastName"
						v-bind:placeholder="$t('settings.form[\'lastname.placeholder\']')"
						:class="{ valid: !$v.userAccount.lastName.$invalid, invalid: $v.userAccount.lastName.$invalid }"
						v-model="applicationUserAccount.internalUser.lastName"
					/>
					<div v-if="$v.userAccount.lastName.$anyDirty && $v.userAccount.lastName.$invalid">
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.lastName.maxLength"
							v-text="$t('entity.validation.maxlength', { max: 50 })"
						>
							This field cannot be longer than 50 characters.
						</small>
					</div>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" for="email" v-text="$t('userManagement.email')">Email</label>
					<b-form-input
						type="email"
						class="form-control"
						id="email"
						:placeholder="$t('global.form[\'email.placeholder\']')"
						:class="{ valid: !$v.userAccount.email.$invalid, invalid: $v.userAccount.email.$invalid }"
						v-model="applicationUserAccount.internalUser.email"
						email
						required
					/>
					<div v-if="$v.userAccount.email.$anyDirty && $v.userAccount.email.$invalid">
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.email.required"
							v-text="$t('global.messages.validate.email.required')"
						>
							Your email is required.
						</small>
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.email.email"
							v-text="$t('global.messages.validate.email.invalid')"
						>
							Your email is invalid.
						</small>
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.email.minLength"
							v-text="$t('global.messages.validate.email.minlength')"
						>
							Your email is required to be at least 5 characters.
						</small>
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.email.maxLength"
							v-text="$t('global.messages.validate.email.maxlength')"
						>
							Your email cannot be longer than 50 characters.
						</small>
					</div>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" v-text="$t('sainsApp.applicationUser.ic')" for="application-user-ic">Ic</label>
					<b-form-input
						class="form-control"
						id="application-user-ic"
						:class="{ valid: !$v.userAccount.ic.$invalid, invalid: $v.userAccount.ic.$invalid }"
						v-model="applicationUserAccount.ic"
					/>
					<div v-if="$v.userAccount.ic.$anyDirty && $v.userAccount.ic.$invalid">
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.ic.maxLength"
							v-text="$t('entity.validation.maxlength', { max: 15 })"
						>
							This field cannot be longer than 15 characters.
						</small>
					</div>
				</div>
				<div class="form-group cell" :option="tSectionCodes">
					<label class="form-control-label" v-text="$t('sainsApp.applicationUser.division')" for="application-user-division"
						>Division</label
					>
					<select class="form-control" id="application-user-division" v-model="applicationUserAccount.division">
						<option :value="null"></option>
						<option
							:value="
								applicationUserAccount.division && tSectionCodeOption.id === applicationUserAccount.division.id
									? applicationUserAccount.division
									: tSectionCodeOption
							"
							v-for="tSectionCodeOption in tSectionCodes"
							:key="tSectionCodeOption.id"
						>
							{{ tSectionCodeOption.sectName }}
						</option>
					</select>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" v-text="$t('sainsApp.applicationUser.section')" for="application-user-section"
						>Section</label
					>
					<select
						class="form-control"
						id="application-user-section"
						data-cy="section"
						name="section"
						v-model="applicationUserAccount.section"
					>
						<option v-bind:value="null"></option>
						<option
							v-bind:value="
								applicationUserAccount.section && tUnitCodeOption.id === applicationUserAccount.section.id
									? applicationUserAccount.section
									: tUnitCodeOption
							"
							v-for="tUnitCodeOption in tUnitCodes"
							:key="tUnitCodeOption.id"
						>
							{{ tUnitCodeOption.untUnit }}
						</option>
					</select>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" v-text="$t('sainsApp.applicationUser.unit')" for="application-user-unit">Unit</label>
					<select
						class="form-control"
						id="application-user-unit"
						data-cy="unit"
						name="unit"
						v-model="applicationUserAccount.unit"
					>
						<option v-bind:value="null"></option>
						<option
							v-bind:value="
								applicationUserAccount.unit && tSubunitCodeOption.id === applicationUserAccount.unit.id
									? applicationUserAccount.unit
									: tSubunitCodeOption
							"
							v-for="tSubunitCodeOption in tSubunitCodes"
							:key="tSubunitCodeOption.id"
						>
							{{ tSubunitCodeOption.subuntSubunit }}
						</option>
					</select>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" v-text="$t('sainsApp.applicationUser.office')" for="application-user-office"
						>Office</label
					>
					<select
						class="form-control"
						id="application-user-office"
						data-cy="office"
						name="office"
						v-model="applicationUserAccount.office"
					>
						<option v-bind:value="null"></option>
						<option
							v-bind:value="
								applicationUserAccount.office && tOfficeCodeOption.id === applicationUserAccount.office.id
									? applicationUserAccount.office
									: tOfficeCodeOption
							"
							v-for="tOfficeCodeOption in tOfficeCodes"
							:key="tOfficeCodeOption.id"
						>
							{{ tOfficeCodeOption.offName }}
						</option>
					</select>
				</div>
				<div class="form-group cell">
					<label class="form-control-label" v-text="$t('sainsApp.applicationUser.status')" for="application-user-status"
						>Status</label
					>
					<b-form-input
						class="form-control"
						name="status"
						id="application-user-status"
						data-cy="status"
						:class="{ valid: !$v.userAccount.status.$invalid, invalid: $v.userAccount.status.$invalid }"
						v-model="applicationUserAccount.status"
					/>
					<div v-if="$v.userAccount.status.$anyDirty && $v.userAccount.status.$invalid">
						<small
							class="form-text text-danger"
							v-if="!$v.userAccount.status.maxLength"
							v-text="$t('entity.validation.maxlength', { max: 1 })"
						>
							This field cannot be longer than 1 characters.
						</small>
					</div>
				</div>
				<div class="form-group cell">
					<label class="form-check-label" for="activated">
						<span v-text="$t('userManagement.activated')">Activated</span>
					</label>
					<b-form-checkbox
						:disabled="userAccount.id === null"
						id="activated"
						v-model="applicationUserAccount.internalUser.activated"
					/>
				</div>
				<div class="form-group cell" v-if="languages && Object.keys(languages).length > 0">
					<label for="langKey" v-text="$t('userManagement.langKey')">Language</label>
					<select class="form-control" id="langKey" name="langKey" v-model="applicationUserAccount.internalUser.langKey">
						<option v-for="(language, key) in languages" :value="key" :key="key">{{ language.name }}</option>
					</select>
				</div>
				<div class="form-group cell">
					<label v-text="$t('userManagement.profiles')">Profiles</label>
					<select class="form-control" multiple name="authority" v-model="applicationUserAccount.internalUser.authorities">
						<option v-for="authority of authorities" :value="authority" :key="authority">{{ authority }}</option>
					</select>
				</div>
			</div>
		</section>
		<div>
			<h6>Delivery Order (DO)/Delivery Note (DN) And Receiving Store Access Right</h6>
		</div>
		<div>
			<h6>Ad-Hoc Transfer Store Access Right</h6>
		</div>
		<section class="mt-3 miscellanous-access-right-section">
			<h4 class="title mb-3">Miscellanous Access Right</h4>
			<div class="d-flex">
				<b-table hover outlined :items="miscellanous_access_right_items1" :fields="columns" thead-tr-class="d-none">
					<template #cell(checkbox-section)="data">
						<div class="radio-group">
							<b-form-radio :name="data.item.label" value="Y">Yes</b-form-radio>
							<b-form-radio :name="data.item.label" value="N">No</b-form-radio>
						</div>
					</template>
				</b-table>
				<b-table hover outlined :items="miscellanous_access_right_items2" :fields="columns" thead-tr-class="d-none">
					<template #cell(checkbox-section)="data">
						<div class="radio-group">
							<b-form-radio :name="data.item.label" value="Y">Yes</b-form-radio>
							<b-form-radio :name="data.item.label" value="N">No</b-form-radio>
						</div>
					</template>
				</b-table>
			</div>
		</section>
		<section class="mt-3 mb-3 user-roles-section">
			<h3 class="title mb-3">User Roles</h3>
			<div class="user-role-selection-section table">
				<div class="user-role-selection-section-row" v-for="(userRole, index) in tUserRoleCodes" :key="userRole.id">
					<div class="cell text-center">{{ index + 1 }}</div>
					<div class="cell center">
						<b-form-checkbox
							:checked="preSelectedUserRolesCodes.includes(userRole.id)"
							@change="onUserRoleChange(userRole, $event.target)"
							:id="userRole.id.toString()"
						/>
					</div>
					<label class="cell" :for="userRole.id.toString()">{{ userRole.roleName }}</label>
				</div>
			</div>
		</section>
		<div>
			<button type="button" class="btn btn-secondary" v-on:click="previousState()">
				<font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
			</button>
			<button type="submit" :disabled="isSaving" class="btn btn-primary">
				<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
			</button>
			<button type="button" @click="checkData">Check</button>
		</div>
	</form>
</template>
<!-- eslint-disable prettier/prettier -->
<script src="./user-management-user-detail.component.ts"></script>
<!-- eslint-disable prettier/prettier -->
<style scoped>
.user-detail-form-section {
	padding: 2rem;
	border-radius: 0.5rem;
	background: #fff;
}
.user-detail-form-layout {
	display: grid;
	grid-template-columns: repeat(2, 1fr);
}
.form-group {
	display: grid;
	grid-template-columns: 200px 1fr 0.25fr;
	align-items: center;
}
.user-role-selection-section {
	display: grid;
	grid-template-columns: 1fr 1fr;
}
.user-role-selection-section-row {
	display: grid;
	grid-template-columns: 0.1fr 100px 1fr;
	/* border: 1px solid #dee2e6;
	border-collapse: collapse; */
}
.center {
	display: flex;
	justify-content: center;
}
.cell {
	padding: 12px;
	margin: 0;
	/* border: 1px solid #dee2e6; */
	/* border-collapse: collapse; */
	/* background: #fff; */
}
.radio-group {
	display: flex;
	gap: 10px;
}
.table-title {
	color: white;
	padding: 0.5rem;
}
.miscellanous-access-right-section {
	padding: 2rem;
	border-radius: 0.5rem;
	background: #fff;
}
.user-roles-section {
	padding: 2rem;
	border-radius: 0.5rem;
	background: #fff;
}
</style>
