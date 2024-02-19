<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tSubunitCode">
				<h2 class="jh-entity-heading" data-cy="tSubunitCodeDetailsHeading">
					<span v-text="$t('sainsApp.tSubunitCode.detail.title')">TSubunitCode</span> {{ tSubunitCode.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntSubunit')">Subunt Subunit</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntSubunit }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntHeadid')">Subunt Headid</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntHeadid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntActingHeadid')">Subunt Acting Headid</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntActingHeadid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntHrpayId')">Subunt Hrpay Id</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntHrpayId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntPapId')">Subunt Pap Id</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntPapId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntPapCode')">Subunt Pap Code</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntPapCode }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subuntPapActive')">Subunt Pap Active</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subuntPapActive }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.subunyUnitid')">Subuny Unitid</span>
					</dt>
					<dd>
						<span>{{ tSubunitCode.subunyUnitid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tSubunitCode.unit')">Unit</span>
					</dt>
					<dd>
						<div v-if="tSubunitCode.unit">
							<router-link :to="{ name: 'TUnitCodeView', params: { tUnitCodeId: tSubunitCode.unit.id } }">{{
								tSubunitCode.unit.id
							}}</router-link>
						</div>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tSubunitCode.id"
					:to="{ name: 'TSubunitCodeEdit', params: { tSubunitCodeId: tSubunitCode.id } }"
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

<script lang="ts" src="./t-subunit-code-details.component.ts"></script>
