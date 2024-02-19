<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tSubunitCode.home.createOrEditLabel"
					data-cy="TSubunitCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tSubunitCode.home.createOrEditLabel')"
				>
					Create or edit a TSubunitCode
				</h2>
				<div>
					<div class="form-group" v-if="tSubunitCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tSubunitCode.id" readonly />
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subuntSubunit')"
							for="t-subunit-code-subuntSubunit"
							>Subunt Subunit</label
						>
						<input
							type="text"
							class="form-control"
							name="subuntSubunit"
							id="t-subunit-code-subuntSubunit"
							data-cy="subuntSubunit"
							:class="{ valid: !$v.tSubunitCode.subuntSubunit.$invalid, invalid: $v.tSubunitCode.subuntSubunit.$invalid }"
							v-model="$v.tSubunitCode.subuntSubunit.$model"
						/>
						<div v-if="$v.tSubunitCode.subuntSubunit.$anyDirty && $v.tSubunitCode.subuntSubunit.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tSubunitCode.subuntSubunit.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 100 })"
							>
								This field cannot be longer than 100 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subuntHeadid')"
							for="t-subunit-code-subuntHeadid"
							>Subunt Headid</label
						>
						<input
							type="number"
							class="form-control"
							name="subuntHeadid"
							id="t-subunit-code-subuntHeadid"
							data-cy="subuntHeadid"
							:class="{ valid: !$v.tSubunitCode.subuntHeadid.$invalid, invalid: $v.tSubunitCode.subuntHeadid.$invalid }"
							v-model.number="$v.tSubunitCode.subuntHeadid.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subuntActingHeadid')"
							for="t-subunit-code-subuntActingHeadid"
							>Subunt Acting Headid</label
						>
						<input
							type="number"
							class="form-control"
							name="subuntActingHeadid"
							id="t-subunit-code-subuntActingHeadid"
							data-cy="subuntActingHeadid"
							:class="{
								valid: !$v.tSubunitCode.subuntActingHeadid.$invalid,
								invalid: $v.tSubunitCode.subuntActingHeadid.$invalid,
							}"
							v-model.number="$v.tSubunitCode.subuntActingHeadid.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subuntHrpayId')"
							for="t-subunit-code-subuntHrpayId"
							>Subunt Hrpay Id</label
						>
						<input
							type="text"
							class="form-control"
							name="subuntHrpayId"
							id="t-subunit-code-subuntHrpayId"
							data-cy="subuntHrpayId"
							:class="{ valid: !$v.tSubunitCode.subuntHrpayId.$invalid, invalid: $v.tSubunitCode.subuntHrpayId.$invalid }"
							v-model="$v.tSubunitCode.subuntHrpayId.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSubunitCode.subuntPapId')" for="t-subunit-code-subuntPapId"
							>Subunt Pap Id</label
						>
						<input
							type="text"
							class="form-control"
							name="subuntPapId"
							id="t-subunit-code-subuntPapId"
							data-cy="subuntPapId"
							:class="{ valid: !$v.tSubunitCode.subuntPapId.$invalid, invalid: $v.tSubunitCode.subuntPapId.$invalid }"
							v-model="$v.tSubunitCode.subuntPapId.$model"
						/>
						<div v-if="$v.tSubunitCode.subuntPapId.$anyDirty && $v.tSubunitCode.subuntPapId.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tSubunitCode.subuntPapId.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 10 })"
							>
								This field cannot be longer than 10 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subuntPapCode')"
							for="t-subunit-code-subuntPapCode"
							>Subunt Pap Code</label
						>
						<input
							type="text"
							class="form-control"
							name="subuntPapCode"
							id="t-subunit-code-subuntPapCode"
							data-cy="subuntPapCode"
							:class="{ valid: !$v.tSubunitCode.subuntPapCode.$invalid, invalid: $v.tSubunitCode.subuntPapCode.$invalid }"
							v-model="$v.tSubunitCode.subuntPapCode.$model"
						/>
						<div v-if="$v.tSubunitCode.subuntPapCode.$anyDirty && $v.tSubunitCode.subuntPapCode.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tSubunitCode.subuntPapCode.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 10 })"
							>
								This field cannot be longer than 10 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subuntPapActive')"
							for="t-subunit-code-subuntPapActive"
							>Subunt Pap Active</label
						>
						<input
							type="text"
							class="form-control"
							name="subuntPapActive"
							id="t-subunit-code-subuntPapActive"
							data-cy="subuntPapActive"
							:class="{ valid: !$v.tSubunitCode.subuntPapActive.$invalid, invalid: $v.tSubunitCode.subuntPapActive.$invalid }"
							v-model="$v.tSubunitCode.subuntPapActive.$model"
						/>
						<div v-if="$v.tSubunitCode.subuntPapActive.$anyDirty && $v.tSubunitCode.subuntPapActive.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tSubunitCode.subuntPapActive.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 1 })"
							>
								This field cannot be longer than 1 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSubunitCode.subunyUnitid')"
							for="t-subunit-code-subunyUnitid"
							>Subuny Unitid</label
						>
						<input
							type="text"
							class="form-control"
							name="subunyUnitid"
							id="t-subunit-code-subunyUnitid"
							data-cy="subunyUnitid"
							:class="{ valid: !$v.tSubunitCode.subunyUnitid.$invalid, invalid: $v.tSubunitCode.subunyUnitid.$invalid }"
							v-model="$v.tSubunitCode.subunyUnitid.$model"
						/>
						<div v-if="$v.tSubunitCode.subunyUnitid.$anyDirty && $v.tSubunitCode.subunyUnitid.$invalid">
							<small
								class="form-text text-danger"
								v-if="!$v.tSubunitCode.subunyUnitid.maxLength"
								v-text="$t('entity.validation.maxlength', { max: 10 })"
							>
								This field cannot be longer than 10 characters.
							</small>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSubunitCode.unit')" for="t-subunit-code-unit">Unit</label>
						<select class="form-control" id="t-subunit-code-unit" data-cy="unit" name="unit" v-model="tSubunitCode.unit">
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									tSubunitCode.unit && tUnitCodeOption.id === tSubunitCode.unit.id ? tSubunitCode.unit : tUnitCodeOption
								"
								v-for="tUnitCodeOption in tUnitCodes"
								:key="tUnitCodeOption.id"
							>
								{{ tUnitCodeOption.id }}
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
						:disabled="$v.tSubunitCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-subunit-code-update.component.ts"></script>
