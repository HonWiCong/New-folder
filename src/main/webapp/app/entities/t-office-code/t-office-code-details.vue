<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tOfficeCode">
				<h2 class="jh-entity-heading" data-cy="tOfficeCodeDetailsHeading">
					<span v-text="$t('sainsApp.tOfficeCode.detail.title')">TOfficeCode</span> {{ tOfficeCode.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offName')">Off Name</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offName }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offAddress')">Off Address</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offAddress }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offPostcode')">Off Postcode</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offPostcode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offCity')">Off City</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offCity }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offState')">Off State</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offState }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offOffphone')">Off Offphone</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offOffphone }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.offOfffax')">Off Offfax</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.offOfffax }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tOfficeCode.enteredDate">{{ $d(Date.parse(tOfficeCode.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tOfficeCode.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tOfficeCode.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tOfficeCode.modifiedDate">{{ $d(Date.parse(tOfficeCode.modifiedDate), 'long') }}</span>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tOfficeCode.id"
					:to="{ name: 'TOfficeCodeEdit', params: { tOfficeCodeId: tOfficeCode.id } }"
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

<script lang="ts" src="./t-office-code-details.component.ts"></script>
