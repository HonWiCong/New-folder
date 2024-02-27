<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tStateCode">
				<h2 class="jh-entity-heading" data-cy="tStateCodeDetailsHeading">
					<span v-text="$t('sainsApp.tStateCode.detail.title')">TStateCode</span> {{ tStateCode.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tStateCode.stateName')">State Name</span>
					</dt>
					<dd>
						<span>{{ tStateCode.stateName }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tStateCode.stateCode')">State Code</span>
					</dt>
					<dd>
						<span>{{ tStateCode.stateCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tStateCode.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tStateCode.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tStateCode.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tStateCode.enteredDate">{{ $d(Date.parse(tStateCode.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tStateCode.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tStateCode.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tStateCode.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tStateCode.modifiedDate">{{ $d(Date.parse(tStateCode.modifiedDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tStateCode.tCountryCode')">T Country Code</span>
					</dt>
					<dd>
						<div v-if="tStateCode.tCountryCode">
							<router-link :to="{ name: 'TCountryCodeView', params: { tCountryCodeId: tStateCode.tCountryCode.id } }">{{
								tStateCode.tCountryCode.id
							}}</router-link>
						</div>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tStateCode.id"
					:to="{ name: 'TStateCodeEdit', params: { tStateCodeId: tStateCode.id } }"
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

<script lang="ts" src="./t-state-code-details.component.ts"></script>
