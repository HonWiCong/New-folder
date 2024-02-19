<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tOfficeCode.home.createOrEditLabel"
					data-cy="TOfficeCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tOfficeCode.home.createOrEditLabel')"
				>
					Create or edit a TOfficeCode
				</h2>
				<div>
					<div class="form-group" v-if="tOfficeCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tOfficeCode.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offName')" for="t-office-code-offName"
							>Off Name</label
						>
						<input
							type="text"
							class="form-control"
							name="offName"
							id="t-office-code-offName"
							data-cy="offName"
							:class="{ valid: !$v.tOfficeCode.offName.$invalid, invalid: $v.tOfficeCode.offName.$invalid }"
							v-model="$v.tOfficeCode.offName.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offAddress')" for="t-office-code-offAddress"
							>Off Address</label
						>
						<input
							type="text"
							class="form-control"
							name="offAddress"
							id="t-office-code-offAddress"
							data-cy="offAddress"
							:class="{ valid: !$v.tOfficeCode.offAddress.$invalid, invalid: $v.tOfficeCode.offAddress.$invalid }"
							v-model="$v.tOfficeCode.offAddress.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offPostcode')" for="t-office-code-offPostcode"
							>Off Postcode</label
						>
						<input
							type="text"
							class="form-control"
							name="offPostcode"
							id="t-office-code-offPostcode"
							data-cy="offPostcode"
							:class="{ valid: !$v.tOfficeCode.offPostcode.$invalid, invalid: $v.tOfficeCode.offPostcode.$invalid }"
							v-model="$v.tOfficeCode.offPostcode.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offCity')" for="t-office-code-offCity"
							>Off City</label
						>
						<input
							type="text"
							class="form-control"
							name="offCity"
							id="t-office-code-offCity"
							data-cy="offCity"
							:class="{ valid: !$v.tOfficeCode.offCity.$invalid, invalid: $v.tOfficeCode.offCity.$invalid }"
							v-model="$v.tOfficeCode.offCity.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offState')" for="t-office-code-offState"
							>Off State</label
						>
						<input
							type="text"
							class="form-control"
							name="offState"
							id="t-office-code-offState"
							data-cy="offState"
							:class="{ valid: !$v.tOfficeCode.offState.$invalid, invalid: $v.tOfficeCode.offState.$invalid }"
							v-model="$v.tOfficeCode.offState.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offOffphone')" for="t-office-code-offOffphone"
							>Off Offphone</label
						>
						<input
							type="text"
							class="form-control"
							name="offOffphone"
							id="t-office-code-offOffphone"
							data-cy="offOffphone"
							:class="{ valid: !$v.tOfficeCode.offOffphone.$invalid, invalid: $v.tOfficeCode.offOffphone.$invalid }"
							v-model="$v.tOfficeCode.offOffphone.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.offOfffax')" for="t-office-code-offOfffax"
							>Off Offfax</label
						>
						<input
							type="text"
							class="form-control"
							name="offOfffax"
							id="t-office-code-offOfffax"
							data-cy="offOfffax"
							:class="{ valid: !$v.tOfficeCode.offOfffax.$invalid, invalid: $v.tOfficeCode.offOfffax.$invalid }"
							v-model="$v.tOfficeCode.offOfffax.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.enteredBy')" for="t-office-code-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-office-code-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tOfficeCode.enteredBy.$invalid, invalid: $v.tOfficeCode.enteredBy.$invalid }"
							v-model.number="$v.tOfficeCode.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.enteredDate')" for="t-office-code-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-office-code-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tOfficeCode.enteredDate.$invalid, invalid: $v.tOfficeCode.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tOfficeCode.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.modifiedBy')" for="t-office-code-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-office-code-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tOfficeCode.modifiedBy.$invalid, invalid: $v.tOfficeCode.modifiedBy.$invalid }"
							v-model.number="$v.tOfficeCode.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tOfficeCode.modifiedDate')" for="t-office-code-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-office-code-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tOfficeCode.modifiedDate.$invalid, invalid: $v.tOfficeCode.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tOfficeCode.modifiedDate.$model)"
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
						:disabled="$v.tOfficeCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-office-code-update.component.ts"></script>
