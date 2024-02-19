<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.applicationUser.home.createOrEditLabel"
					data-cy="ApplicationUserCreateUpdateHeading"
					v-text="$t('sainsApp.applicationUser.home.createOrEditLabel')"
				>
					Create or edit a ApplicationUser
				</h2>
				<div>
					<div class="form-group" v-if="applicationUser.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="applicationUser.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.applicationUser.ic')" for="application-user-ic">Ic</label>
						<input
							type="text"
							class="form-control"
							name="ic"
							id="application-user-ic"
							data-cy="ic"
							:class="{ valid: !$v.applicationUser.ic.$invalid, invalid: $v.applicationUser.ic.$invalid }"
							v-model="$v.applicationUser.ic.$model"
						/>
						<div v-if="$v.applicationUser.ic.$anyDirty && $v.applicationUser.ic.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.applicationUser.ic.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 15 })"
							>
								This field cannot be longer than 15 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.applicationUser.status')" for="application-user-status"
							>Status</label
						>
						<input
							type="text"
							class="form-control"
							name="status"
							id="application-user-status"
							data-cy="status"
							:class="{ valid: !$v.applicationUser.status.$invalid, invalid: $v.applicationUser.status.$invalid }"
							v-model="$v.applicationUser.status.$model"
						/>
						<div v-if="$v.applicationUser.status.$anyDirty && $v.applicationUser.status.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.applicationUser.status.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 1 })"
							>
								This field cannot be longer than 1 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.applicationUser.internalUser')"
							for="application-user-internalUser"
							>Internal User</label
						>
						<select
							class="form-control"
							id="application-user-internalUser"
							data-cy="internalUser"
							name="internalUser"
							v-model="applicationUser.internalUser"
						>
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									applicationUser.internalUser && userOption.id === applicationUser.internalUser.id
										? applicationUser.internalUser
										: userOption
								"
								v-for="userOption in users"
								:key="userOption.id"
							>
								{{ userOption.id }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.applicationUser.division')" for="application-user-division"
							>Division</label
						>
						<select
							class="form-control"
							id="application-user-division"
							data-cy="division"
							name="division"
							v-model="applicationUser.division"
						>
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									applicationUser.division && tSectionCodeOption.id === applicationUser.division.id
										? applicationUser.division
										: tSectionCodeOption
								"
								v-for="tSectionCodeOption in tSectionCodes"
								:key="tSectionCodeOption.id"
							>
								{{ tSectionCodeOption.id }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.applicationUser.section')" for="application-user-section"
							>Section</label
						>
						<select
							class="form-control"
							id="application-user-section"
							data-cy="section"
							name="section"
							v-model="applicationUser.section"
						>
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									applicationUser.section && tUnitCodeOption.id === applicationUser.section.id
										? applicationUser.section
										: tUnitCodeOption
								"
								v-for="tUnitCodeOption in tUnitCodes"
								:key="tUnitCodeOption.id"
							>
								{{ tUnitCodeOption.id }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.applicationUser.unit')" for="application-user-unit"
							>Unit</label
						>
						<select class="form-control" id="application-user-unit" data-cy="unit" name="unit" v-model="applicationUser.unit">
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									applicationUser.unit && tSubunitCodeOption.id === applicationUser.unit.id
										? applicationUser.unit
										: tSubunitCodeOption
								"
								v-for="tSubunitCodeOption in tSubunitCodes"
								:key="tSubunitCodeOption.id"
							>
								{{ tSubunitCodeOption.id }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.applicationUser.office')" for="application-user-office"
							>Office</label
						>
						<select
							class="form-control"
							id="application-user-office"
							data-cy="office"
							name="office"
							v-model="applicationUser.office"
						>
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									applicationUser.office && tOfficeCodeOption.id === applicationUser.office.id
										? applicationUser.office
										: tOfficeCodeOption
								"
								v-for="tOfficeCodeOption in tOfficeCodes"
								:key="tOfficeCodeOption.id"
							>
								{{ tOfficeCodeOption.id }}
							</option>
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
						<font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
					</button>
					<button
						type="submit"
						id="save-entity"
						data-cy="entityCreateSaveButton"
						:disabled="$v.applicationUser.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./application-user-update.component.ts"></script>
