<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tUserRoleCode.home.createOrEditLabel"
					data-cy="TUserRoleCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tUserRoleCode.home.createOrEditLabel')"
				>
					Create or edit a TUserRoleCode
				</h2>
				<div>
					<div class="form-group" v-if="tUserRoleCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tUserRoleCode.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRoleCode.roleName')" for="t-user-role-code-roleName"
							>Role Name</label
						>
						<input
							type="text"
							class="form-control"
							name="roleName"
							id="t-user-role-code-roleName"
							data-cy="roleName"
							:class="{ valid: !$v.tUserRoleCode.roleName.$invalid, invalid: $v.tUserRoleCode.roleName.$invalid }"
							v-model="$v.tUserRoleCode.roleName.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRoleCode.roleHead')" for="t-user-role-code-roleHead"
							>Role Head</label
						>
						<input
							type="text"
							class="form-control"
							name="roleHead"
							id="t-user-role-code-roleHead"
							data-cy="roleHead"
							:class="{ valid: !$v.tUserRoleCode.roleHead.$invalid, invalid: $v.tUserRoleCode.roleHead.$invalid }"
							v-model="$v.tUserRoleCode.roleHead.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tUserRoleCode.actApprover1')"
							for="t-user-role-code-actApprover1"
							>Act Approver 1</label
						>
						<input
							type="number"
							class="form-control"
							name="actApprover1"
							id="t-user-role-code-actApprover1"
							data-cy="actApprover1"
							:class="{ valid: !$v.tUserRoleCode.actApprover1.$invalid, invalid: $v.tUserRoleCode.actApprover1.$invalid }"
							v-model.number="$v.tUserRoleCode.actApprover1.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tUserRoleCode.actApprover2')"
							for="t-user-role-code-actApprover2"
							>Act Approver 2</label
						>
						<input
							type="number"
							class="form-control"
							name="actApprover2"
							id="t-user-role-code-actApprover2"
							data-cy="actApprover2"
							:class="{ valid: !$v.tUserRoleCode.actApprover2.$invalid, invalid: $v.tUserRoleCode.actApprover2.$invalid }"
							v-model.number="$v.tUserRoleCode.actApprover2.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRoleCode.enteredBy')" for="t-user-role-code-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-user-role-code-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tUserRoleCode.enteredBy.$invalid, invalid: $v.tUserRoleCode.enteredBy.$invalid }"
							v-model.number="$v.tUserRoleCode.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tUserRoleCode.enteredDate')"
							for="t-user-role-code-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-user-role-code-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tUserRoleCode.enteredDate.$invalid, invalid: $v.tUserRoleCode.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tUserRoleCode.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUserRoleCode.modifiedBy')" for="t-user-role-code-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-user-role-code-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tUserRoleCode.modifiedBy.$invalid, invalid: $v.tUserRoleCode.modifiedBy.$invalid }"
							v-model.number="$v.tUserRoleCode.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tUserRoleCode.modifiedDate')"
							for="t-user-role-code-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-user-role-code-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tUserRoleCode.modifiedDate.$invalid, invalid: $v.tUserRoleCode.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tUserRoleCode.modifiedDate.$model)"
								@change="updateZonedDateTimeField('modifiedDate', $event)"
							/>
						</div>
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
						:disabled="$v.tUserRoleCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-user-role-code-update.component.ts"></script>
