<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="applicationUser">
				<h2 class="jh-entity-heading" data-cy="applicationUserDetailsHeading">
					<span v-text="$t('sainsApp.applicationUser.detail.title')">ApplicationUser</span> {{ applicationUser.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.applicationUser.ic')">Ic</span>
					</dt>
					<dd>
						<span>{{ applicationUser.ic }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.applicationUser.status')">Status</span>
					</dt>
					<dd>
						<span>{{ applicationUser.status }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.applicationUser.internalUser')">Internal User</span>
					</dt>
					<dd>
						{{ applicationUser.internalUser ? applicationUser.internalUser.id : '' }}
					</dd>
					<dt>
						<span v-text="$t('sainsApp.applicationUser.division')">Division</span>
					</dt>
					<dd>
						<div v-if="applicationUser.division">
							<router-link :to="{ name: 'TSectionCodeView', params: { tSectionCodeId: applicationUser.division.id } }">{{
								applicationUser.division.id
							}}</router-link>
						</div>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.applicationUser.section')">Section</span>
					</dt>
					<dd>
						<div v-if="applicationUser.section">
							<router-link :to="{ name: 'TUnitCodeView', params: { tUnitCodeId: applicationUser.section.id } }">{{
								applicationUser.section.id
							}}</router-link>
						</div>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.applicationUser.unit')">Unit</span>
					</dt>
					<dd>
						<div v-if="applicationUser.unit">
							<router-link :to="{ name: 'TSubunitCodeView', params: { tSubunitCodeId: applicationUser.unit.id } }">{{
								applicationUser.unit.id
							}}</router-link>
						</div>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.applicationUser.office')">Office</span>
					</dt>
					<dd>
						<div v-if="applicationUser.office">
							<router-link :to="{ name: 'TOfficeCodeView', params: { tOfficeCodeId: applicationUser.office.id } }">{{
								applicationUser.office.id
							}}</router-link>
						</div>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="applicationUser.id"
					:to="{ name: 'ApplicationUserEdit', params: { applicationUserId: applicationUser.id } }"
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

<script lang="ts" src="./application-user-details.component.ts"></script>
