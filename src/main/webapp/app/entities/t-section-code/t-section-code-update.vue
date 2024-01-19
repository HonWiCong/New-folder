<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
				<h2
					id="sainsApp.tSectionCode.home.createOrEditLabel"
					data-cy="TSectionCodeCreateUpdateHeading"
					v-text="$t('sainsApp.tSectionCode.home.createOrEditLabel')"
				>
					Create or edit a TSectionCode
				</h2>
				<div>
					<div class="form-group" v-if="tSectionCode.id">
						<label for="id" v-text="$t('global.field.id')">ID</label>
						<input type="text" class="form-control" id="id" name="id" v-model="tSectionCode.id" readonly />
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.sectName')" for="t-section-code-sectName"
							>Sect Name</label
						>
						<input
							type="text"
							class="form-control"
							name="sectName"
							id="t-section-code-sectName"
							data-cy="sectName"
							:class="{ valid: !$v.tSectionCode.sectName.$invalid, invalid: $v.tSectionCode.sectName.$invalid }"
							v-model="$v.tSectionCode.sectName.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.sectHeadid')" for="t-section-code-sectHeadid"
							>Sect Headid</label
						>
						<input
							type="number"
							class="form-control"
							name="sectHeadid"
							id="t-section-code-sectHeadid"
							data-cy="sectHeadid"
							:class="{ valid: !$v.tSectionCode.sectHeadid.$invalid, invalid: $v.tSectionCode.sectHeadid.$invalid }"
							v-model.number="$v.tSectionCode.sectHeadid.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSectionCode.sectActingHeadid')"
							for="t-section-code-sectActingHeadid"
							>Sect Acting Headid</label
						>
						<input
							type="number"
							class="form-control"
							name="sectActingHeadid"
							id="t-section-code-sectActingHeadid"
							data-cy="sectActingHeadid"
							:class="{
								valid: !$v.tSectionCode.sectActingHeadid.$invalid,
								invalid: $v.tSectionCode.sectActingHeadid.$invalid,
							}"
							v-model.number="$v.tSectionCode.sectActingHeadid.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSectionCode.sectSainsSubsidiary')"
							for="t-section-code-sectSainsSubsidiary"
							>Sect Sains Subsidiary</label
						>
						<select
							class="form-control"
							name="sectSainsSubsidiary"
							:class="{
								valid: !$v.tSectionCode.sectSainsSubsidiary.$invalid,
								invalid: $v.tSectionCode.sectSainsSubsidiary.$invalid,
							}"
							v-model="$v.tSectionCode.sectSainsSubsidiary.$model"
							id="t-section-code-sectSainsSubsidiary"
							data-cy="sectSainsSubsidiary"
						>
							<option
								v-for="sectSainsSubsidiary in sectSainsSubsidiaryValues"
								:key="sectSainsSubsidiary"
								v-bind:value="sectSainsSubsidiary"
								v-bind:label="$t('sainsApp.SectSainsSubsidiary.' + sectSainsSubsidiary)"
							>
								{{ sectSainsSubsidiary }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.sectHrpayId')" for="t-section-code-sectHrpayId"
							>Sect Hrpay Id</label
						>
						<input
							type="text"
							class="form-control"
							name="sectHrpayId"
							id="t-section-code-sectHrpayId"
							data-cy="sectHrpayId"
							:class="{ valid: !$v.tSectionCode.sectHrpayId.$invalid, invalid: $v.tSectionCode.sectHrpayId.$invalid }"
							v-model="$v.tSectionCode.sectHrpayId.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.sectPapId')" for="t-section-code-sectPapId"
							>Sect Pap Id</label
						>
						<input
							type="text"
							class="form-control"
							name="sectPapId"
							id="t-section-code-sectPapId"
							data-cy="sectPapId"
							:class="{ valid: !$v.tSectionCode.sectPapId.$invalid, invalid: $v.tSectionCode.sectPapId.$invalid }"
							v-model="$v.tSectionCode.sectPapId.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.sectPapCode')" for="t-section-code-sectPapCode"
							>Sect Pap Code</label
						>
						<input
							type="text"
							class="form-control"
							name="sectPapCode"
							id="t-section-code-sectPapCode"
							data-cy="sectPapCode"
							:class="{ valid: !$v.tSectionCode.sectPapCode.$invalid, invalid: $v.tSectionCode.sectPapCode.$invalid }"
							v-model="$v.tSectionCode.sectPapCode.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSectionCode.sectPapCompany')"
							for="t-section-code-sectPapCompany"
							>Sect Pap Company</label
						>
						<input
							type="text"
							class="form-control"
							name="sectPapCompany"
							id="t-section-code-sectPapCompany"
							data-cy="sectPapCompany"
							:class="{ valid: !$v.tSectionCode.sectPapCompany.$invalid, invalid: $v.tSectionCode.sectPapCompany.$invalid }"
							v-model="$v.tSectionCode.sectPapCompany.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSectionCode.sectPapActive')"
							for="t-section-code-sectPapActive"
							>Sect Pap Active</label
						>
						<select
							class="form-control"
							name="sectPapActive"
							:class="{ valid: !$v.tSectionCode.sectPapActive.$invalid, invalid: $v.tSectionCode.sectPapActive.$invalid }"
							v-model="$v.tSectionCode.sectPapActive.$model"
							id="t-section-code-sectPapActive"
							data-cy="sectPapActive"
						>
							<option
								v-for="sectPapActive in sectPapActiveValues"
								:key="sectPapActive"
								v-bind:value="sectPapActive"
								v-bind:label="$t('sainsApp.SectPapActive.' + sectPapActive)"
							>
								{{ sectPapActive }}
							</option>
						</select>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.sectCcCode')" for="t-section-code-sectCcCode"
							>Sect Cc Code</label
						>
						<input
							type="text"
							class="form-control"
							name="sectCcCode"
							id="t-section-code-sectCcCode"
							data-cy="sectCcCode"
							:class="{ valid: !$v.tSectionCode.sectCcCode.$invalid, invalid: $v.tSectionCode.sectCcCode.$invalid }"
							v-model="$v.tSectionCode.sectCcCode.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.enteredBy')" for="t-section-code-enteredBy"
							>Entered By</label
						>
						<input
							type="number"
							class="form-control"
							name="enteredBy"
							id="t-section-code-enteredBy"
							data-cy="enteredBy"
							:class="{ valid: !$v.tSectionCode.enteredBy.$invalid, invalid: $v.tSectionCode.enteredBy.$invalid }"
							v-model.number="$v.tSectionCode.enteredBy.$model"
						/>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.enteredDate')" for="t-section-code-enteredDate"
							>Entered Date</label
						>
						<div class="d-flex">
							<input
								id="t-section-code-enteredDate"
								data-cy="enteredDate"
								type="datetime-local"
								class="form-control"
								name="enteredDate"
								:class="{ valid: !$v.tSectionCode.enteredDate.$invalid, invalid: $v.tSectionCode.enteredDate.$invalid }"
								:value="convertDateTimeFromServer($v.tSectionCode.enteredDate.$model)"
								@change="updateZonedDateTimeField('enteredDate', $event)"
							/>
						</div>
					</div>
					<div class="form-group">
						<label class="form-control-label" v-text="$t('sainsApp.tSectionCode.modifiedBy')" for="t-section-code-modifiedBy"
							>Modified By</label
						>
						<input
							type="number"
							class="form-control"
							name="modifiedBy"
							id="t-section-code-modifiedBy"
							data-cy="modifiedBy"
							:class="{ valid: !$v.tSectionCode.modifiedBy.$invalid, invalid: $v.tSectionCode.modifiedBy.$invalid }"
							v-model.number="$v.tSectionCode.modifiedBy.$model"
						/>
					</div>
					<div class="form-group">
						<label
							class="form-control-label"
							v-text="$t('sainsApp.tSectionCode.modifiedDate')"
							for="t-section-code-modifiedDate"
							>Modified Date</label
						>
						<div class="d-flex">
							<input
								id="t-section-code-modifiedDate"
								data-cy="modifiedDate"
								type="datetime-local"
								class="form-control"
								name="modifiedDate"
								:class="{ valid: !$v.tSectionCode.modifiedDate.$invalid, invalid: $v.tSectionCode.modifiedDate.$invalid }"
								:value="convertDateTimeFromServer($v.tSectionCode.modifiedDate.$model)"
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
						:disabled="$v.tSectionCode.$invalid || isSaving"
						class="btn btn-primary"
					>
						<font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</template>
<script lang="ts" src="./t-section-code-update.component.ts"></script>
