<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tCityCode.home.createOrEditLabel"
					data-cy="TCityCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tCityCode.home.createOrEditLabel')"
				>
					Create or edit a TCityCode
				</h2>
				<div>
					<div class="form-group" v-if="tCityCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tCityCode.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.cityCode')" for="t-city-code-cityCode"
							>City Code</label
						>
						<input
							type="text"
							class="form-control"
							name="cityCode"
							id="t-city-code-cityCode"
							data-cy="cityCode"
							:class="{ valid: !$v.tCityCode.cityCode.$invalid, invalid: $v.tCityCode.cityCode.$invalid }"
							v-model="$v.tCityCode.cityCode.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.cityName')" for="t-city-code-cityName"
							>City Name</label
						>
						<input
							type="text"
							class="form-control"
							name="cityName"
							id="t-city-code-cityName"
							data-cy="cityName"
							:class="{ valid: !$v.tCityCode.cityName.$invalid, invalid: $v.tCityCode.cityName.$invalid }"
							v-model="$v.tCityCode.cityName.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.enteredBy')" for="t-city-code-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-city-code-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tCityCode.enteredBy.$invalid, invalid: $v.tCityCode.enteredBy.$invalid }"
							v-model.number="$v.tCityCode.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.enteredDate')" for="t-city-code-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-city-code-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tCityCode.enteredDate.$invalid, invalid: $v.tCityCode.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tCityCode.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.modifiedBy')" for="t-city-code-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-city-code-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tCityCode.modifiedBy.$invalid, invalid: $v.tCityCode.modifiedBy.$invalid }"
							v-model.number="$v.tCityCode.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.modifiedDate')" for="t-city-code-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-city-code-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tCityCode.modifiedDate.$invalid, invalid: $v.tCityCode.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tCityCode.modifiedDate.$model)"
								@change="updateZonedDateTimeField('modifiedDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tCityCode.tStateCode')" for="t-city-code-tStateCode"
							>T State Code</label
						>
						<select class="form-control" id="t-city-code-tStateCode" name="tStateCode" v-model="tCityCode.tstateCode">
							<option v-bind:value="null"></option>
							<option
								v-bind:value="
									tCityCode.tstateCode && tStateCodeOption.id === tCityCode.tstateCode.id
										? tCityCode.tstateCode
										: tStateCodeOption
								"
								v-for="tStateCodeOption in tStateCodes"
								:key="tStateCodeOption.id"
							>
								{{ tStateCodeOption.id }}
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
						:disabled="$v.tCityCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-city-code-update.component.ts"></script>
