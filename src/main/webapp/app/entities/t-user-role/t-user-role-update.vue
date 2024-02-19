<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tUserRole.home.createOrEditLabel"
					data-cy="TUserRoleCreateUpdateHeading"
					v-text="$t('sainsApp.tUserRole.home.createOrEditLabel')"
				>
					Create or edit a TUserRole
				</h2>
				<div>
					<div class="form-group" v-if="tUserRole.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tUserRole.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.sysuserId')" for="t-user-role-sysuserId"
							>Sysuser Id</label
						>
						<input
							type="number"
							class="form-control"
							name="sysuserId"
							id="t-user-role-sysuserId"
							data-cy="sysuserId"
							:class="{ valid: !$v.tUserRole.sysuserId.$invalid, invalid: $v.tUserRole.sysuserId.$invalid }"
							v-model.number="$v.tUserRole.sysuserId.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.usrRoleid')" for="t-user-role-usrRoleid"
							>Usr Roleid</label
						>
						<input
							type="number"
							class="form-control"
							name="usrRoleid"
							id="t-user-role-usrRoleid"
							data-cy="usrRoleid"
							:class="{ valid: !$v.tUserRole.usrRoleid.$invalid, invalid: $v.tUserRole.usrRoleid.$invalid }"
							v-model.number="$v.tUserRole.usrRoleid.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.enteredBy')" for="t-user-role-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-user-role-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tUserRole.enteredBy.$invalid, invalid: $v.tUserRole.enteredBy.$invalid }"
							v-model.number="$v.tUserRole.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.enteredDate')" for="t-user-role-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-user-role-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tUserRole.enteredDate.$invalid, invalid: $v.tUserRole.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tUserRole.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.modifiedBy')" for="t-user-role-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-user-role-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tUserRole.modifiedBy.$invalid, invalid: $v.tUserRole.modifiedBy.$invalid }"
							v-model.number="$v.tUserRole.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.modifiedDate')" for="t-user-role-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-user-role-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tUserRole.modifiedDate.$invalid, invalid: $v.tUserRole.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tUserRole.modifiedDate.$model)"
								@change="updateZonedDateTimeField('modifiedDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.role')" for="t-user-role-role">Role</label>
						<select class="form-control" id="t-user-role-role" data-cy="role" name="role" v-model="tUserRole.role">
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									tUserRole.role && tUserRoleCodeOption.id === tUserRole.role.id ? tUserRole.role : tUserRoleCodeOption
								"
								v-for="tUserRoleCodeOption in tUserRoleCodes"
								:key="tUserRoleCodeOption.id"
							>
								{{ tUserRoleCodeOption.id }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRole.user')" for="t-user-role-user">User</label>
						<select class="form-control" id="t-user-role-user" data-cy="user" name="user" v-model="tUserRole.user">
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									tUserRole.user && applicationUserOption.id === tUserRole.user.id
										? tUserRole.user
										: applicationUserOption
								"
								v-for="applicationUserOption in applicationUsers"
								:key="applicationUserOption.id"
							>
								{{ applicationUserOption.id }}
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
						:disabled="$v.tUserRole.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-user-role-update.component.ts"></script>
