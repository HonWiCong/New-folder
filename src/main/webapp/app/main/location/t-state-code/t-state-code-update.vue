<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tStateCode.home.createOrEditLabel"
					data-cy="TStateCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tStateCode.home.createOrEditLabel')"
				>
					Create or edit a TStateCode
				</h2>
				<div class="form-layout">
					<div class="form-group" v-if="tStateCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tStateCode.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.stateName')" for="t-state-code-stateName"
							>State Name</label
						>
						<input
							type="text"
							class="form-control"
							name="stateName"
							id="t-state-code-stateName"
							data-cy="stateName"
							:class="{ valid: !$v.tStateCode.stateName.$invalid, invalid: $v.tStateCode.stateName.$invalid }"
							v-model="$v.tStateCode.stateName.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.stateCode')" for="t-state-code-stateCode"
							>State Code</label
						>
						<input
							type="text"
							class="form-control"
							name="stateCode"
							id="t-state-code-stateCode"
							data-cy="stateCode"
							:class="{ valid: !$v.tStateCode.stateCode.$invalid, invalid: $v.tStateCode.stateCode.$invalid }"
							v-model="$v.tStateCode.stateCode.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.enteredBy')" for="t-state-code-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-state-code-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tStateCode.enteredBy.$invalid, invalid: $v.tStateCode.enteredBy.$invalid }"
							v-model.number="$v.tStateCode.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.enteredDate')" for="t-state-code-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-state-code-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tStateCode.enteredDate.$invalid, invalid: $v.tStateCode.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tStateCode.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.modifiedBy')" for="t-state-code-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-state-code-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tStateCode.modifiedBy.$invalid, invalid: $v.tStateCode.modifiedBy.$invalid }"
							v-model.number="$v.tStateCode.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.modifiedDate')" for="t-state-code-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-state-code-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tStateCode.modifiedDate.$invalid, invalid: $v.tStateCode.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tStateCode.modifiedDate.$model)"
								@change="updateZonedDateTimeField('modifiedDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tStateCode.tCountryCode')" for="t-state-code-tCountryCode"
							>T Country Code</label
						>
						<select class="form-control" id="t-state-code-tCountryCode" v-model="tStateCode.tcountryCode">
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									tStateCode.tcountryCode && tCountryCodeOption.id === tStateCode.tcountryCode.id
										? tStateCode.tcountryCode
										: tCountryCodeOption
								"
								v-for="tCountryCodeOption in tCountryCodes"
								:key="tCountryCodeOption.id"
							>
								{{ tCountryCodeOption.countryName }}
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
						:disabled="$v.tStateCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-state-code-update.component.ts"></script>
