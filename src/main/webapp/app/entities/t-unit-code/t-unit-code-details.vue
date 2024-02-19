<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tUnitCode">
				<h2 class="jh-entity-heading" data-cy="tUnitCodeDetailsHeading">
					<span v-text="$t('sainsApp.tUnitCode.detail.title')">TUnitCode</span> {{ tUnitCode.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untUnit')">Unt Unit</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untUnit }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untHeadid')">Unt Headid</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untHeadid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untActingHeadid')">Unt Acting Headid</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untActingHeadid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untHrpayId')">Unt Hrpay Id</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untHrpayId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untPapId')">Unt Pap Id</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untPapId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untPapCode')">Unt Pap Code</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untPapCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untPapActive')">Unt Pap Active</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untPapActive }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.untPapDepartmentid')">Unt Pap Departmentid</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.untPapDepartmentid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tUnitCode.enteredDate">{{ $d(Date.parse(tUnitCode.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tUnitCode.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUnitCode.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tUnitCode.modifiedDate">{{ $d(Date.parse(tUnitCode.modifiedDate), 'long') }}</span>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tUnitCode.id"
					:to="{ name: 'TUnitCodeEdit', params: { tUnitCodeId: tUnitCode.id } }"
					custom
					v-slot="{ navigate }"
				>
					<button @click="navigate" class="btn btn-primary">
						<font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
					</button>
				</router-link>
			</div>
		</div>
	</div>
</template>

<script lang="ts" src="./t-unit-code-details.component.ts"></script>
