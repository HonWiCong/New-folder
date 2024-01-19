<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tSectionCode">
				<h2 class="jh-entity-heading" data-cy="tSectionCodeDetailsHeading">
					<span v-text="$t('sainsApp.tSectionCode.detail.title')">TSectionCode</span> {{ tSectionCode.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectName')">Sect Name</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectName }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectHeadid')">Sect Headid</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectHeadid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectActingHeadid')">Sect Acting Headid</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectActingHeadid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectSainsSubsidiary')">Sect Sains Subsidiary</span>
					</dt>
					<dd>
						<span v-text="$t('sainsApp.SectSainsSubsidiary.' + tSectionCode.sectSainsSubsidiary)">{{
							tSectionCode.sectSainsSubsidiary
						}}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectHrpayId')">Sect Hrpay Id</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectHrpayId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectPapId')">Sect Pap Id</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectPapId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectPapCode')">Sect Pap Code</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectPapCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectPapCompany')">Sect Pap Company</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectPapCompany }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectPapActive')">Sect Pap Active</span>
					</dt>
					<dd>
						<span v-text="$t('sainsApp.SectPapActive.' + tSectionCode.sectPapActive)">{{ tSectionCode.sectPapActive }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.sectCcCode')">Sect Cc Code</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.sectCcCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tSectionCode.enteredDate">{{ $d(Date.parse(tSectionCode.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tSectionCode.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSectionCode.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tSectionCode.modifiedDate">{{ $d(Date.parse(tSectionCode.modifiedDate), 'long') }}</span>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tSectionCode.id"
					:to="{ name: 'TSectionCodeEdit', params: { tSectionCodeId: tSectionCode.id } }"
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

<script lang="ts" src="./t-section-code-details.component.ts"></script>
