<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tUnitCode.home.createOrEditLabel"
					data-cy="TUnitCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tUnitCode.home.createOrEditLabel')"
				>
					Create or edit a TUnitCode
				</h2>
				<div>
					<div class="form-group" v-if="tUnitCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tUnitCode.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.untUnit')" for="t-unit-code-untUnit"
							>Unt Unit</label
						>
						<input
							type="text"
							class="form-control"
							name="untUnit"
							id="t-unit-code-untUnit"
							data-cy="untUnit"
							:class="{ valid: !$v.tUnitCode.untUnit.$invalid, invalid: $v.tUnitCode.untUnit.$invalid }"
							v-model="$v.tUnitCode.untUnit.$model"
						/>
						<div v-if="$v.tUnitCode.untUnit.$anyDirty && $v.tUnitCode.untUnit.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tUnitCode.untUnit.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 100 })"
							>
								This field cannot be longer than 100 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.untHeadid')" for="t-unit-code-untHeadid"
							>Unt Headid</label
						>
						<input
							type="number"
							class="form-control"
							name="untHeadid"
							id="t-unit-code-untHeadid"
							data-cy="untHeadid"
							:class="{ valid: !$v.tUnitCode.untHeadid.$invalid, invalid: $v.tUnitCode.untHeadid.$invalid }"
							v-model.number="$v.tUnitCode.untHeadid.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tUnitCode.untActingHeadid')"
							for="t-unit-code-untActingHeadid"
							>Unt Acting Headid</label
						>
						<input
							type="number"
							class="form-control"
							name="untActingHeadid"
							id="t-unit-code-untActingHeadid"
							data-cy="untActingHeadid"
							:class="{ valid: !$v.tUnitCode.untActingHeadid.$invalid, invalid: $v.tUnitCode.untActingHeadid.$invalid }"
							v-model.number="$v.tUnitCode.untActingHeadid.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.untHrpayId')" for="t-unit-code-untHrpayId"
							>Unt Hrpay Id</label
						>
						<input
							type="text"
							class="form-control"
							name="untHrpayId"
							id="t-unit-code-untHrpayId"
							data-cy="untHrpayId"
							:class="{ valid: !$v.tUnitCode.untHrpayId.$invalid, invalid: $v.tUnitCode.untHrpayId.$invalid }"
							v-model="$v.tUnitCode.untHrpayId.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.untPapId')" for="t-unit-code-untPapId"
							>Unt Pap Id</label
						>
						<input
							type="text"
							class="form-control"
							name="untPapId"
							id="t-unit-code-untPapId"
							data-cy="untPapId"
							:class="{ valid: !$v.tUnitCode.untPapId.$invalid, invalid: $v.tUnitCode.untPapId.$invalid }"
							v-model="$v.tUnitCode.untPapId.$model"
						/>
						<div v-if="$v.tUnitCode.untPapId.$anyDirty && $v.tUnitCode.untPapId.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tUnitCode.untPapId.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 10 })"
							>
								This field cannot be longer than 10 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.untPapCode')" for="t-unit-code-untPapCode"
							>Unt Pap Code</label
						>
						<input
							type="text"
							class="form-control"
							name="untPapCode"
							id="t-unit-code-untPapCode"
							data-cy="untPapCode"
							:class="{ valid: !$v.tUnitCode.untPapCode.$invalid, invalid: $v.tUnitCode.untPapCode.$invalid }"
							v-model="$v.tUnitCode.untPapCode.$model"
						/>
						<div v-if="$v.tUnitCode.untPapCode.$anyDirty && $v.tUnitCode.untPapCode.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tUnitCode.untPapCode.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 10 })"
							>
								This field cannot be longer than 10 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.untPapActive')" for="t-unit-code-untPapActive"
							>Unt Pap Active</label
						>
						<input
							type="text"
							class="form-control"
							name="untPapActive"
							id="t-unit-code-untPapActive"
							data-cy="untPapActive"
							:class="{ valid: !$v.tUnitCode.untPapActive.$invalid, invalid: $v.tUnitCode.untPapActive.$invalid }"
							v-model="$v.tUnitCode.untPapActive.$model"
						/>
						<div v-if="$v.tUnitCode.untPapActive.$anyDirty && $v.tUnitCode.untPapActive.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tUnitCode.untPapActive.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 1 })"
							>
								This field cannot be longer than 1 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tUnitCode.untPapDepartmentid')"
							for="t-unit-code-untPapDepartmentid"
							>Unt Pap Departmentid</label
						>
						<input
							type="text"
							class="form-control"
							name="untPapDepartmentid"
							id="t-unit-code-untPapDepartmentid"
							data-cy="untPapDepartmentid"
							:class="{ valid: !$v.tUnitCode.untPapDepartmentid.$invalid, invalid: $v.tUnitCode.untPapDepartmentid.$invalid }"
							v-model="$v.tUnitCode.untPapDepartmentid.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.enteredBy')" for="t-unit-code-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-unit-code-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tUnitCode.enteredBy.$invalid, invalid: $v.tUnitCode.enteredBy.$invalid }"
							v-model.number="$v.tUnitCode.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.enteredDate')" for="t-unit-code-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-unit-code-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tUnitCode.enteredDate.$invalid, invalid: $v.tUnitCode.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tUnitCode.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.modifiedBy')" for="t-unit-code-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-unit-code-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tUnitCode.modifiedBy.$invalid, invalid: $v.tUnitCode.modifiedBy.$invalid }"
							v-model.number="$v.tUnitCode.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tUnitCode.modifiedDate')" for="t-unit-code-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-unit-code-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tUnitCode.modifiedDate.$invalid, invalid: $v.tUnitCode.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tUnitCode.modifiedDate.$model)"
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
						:disabled="$v.tUnitCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-unit-code-update.component.ts"></script>
