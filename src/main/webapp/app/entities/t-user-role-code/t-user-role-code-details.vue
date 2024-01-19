<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tUserRoleCode">
				<h2 class="jh-entity-heading" data-cy="tUserRoleCodeDetailsHeading">
					<span v-text="$t('sainsApp.tUserRoleCode.detail.title')">TUserRoleCode</span> {{ tUserRoleCode.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.roleName')">Role Name</span>
					</dt>
					<dd>
						<span>{{ tUserRoleCode.roleName }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.roleHead')">Role Head</span>
					</dt>
					<dd>
						<span>{{ tUserRoleCode.roleHead }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.actApprover1')">Act Approver 1</span>
					</dt>
					<dd>
						<span>{{ tUserRoleCode.actApprover1 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.actApprover2')">Act Approver 2</span>
					</dt>
					<dd>
						<span>{{ tUserRoleCode.actApprover2 }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tUserRoleCode.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tUserRoleCode.enteredDate">{{ $d(Date.parse(tUserRoleCode.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tUserRoleCode.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRoleCode.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tUserRoleCode.modifiedDate">{{ $d(Date.parse(tUserRoleCode.modifiedDate), 'long') }}</span>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tUserRoleCode.id"
					:to="{ name: 'TUserRoleCodeEdit', params: { tUserRoleCodeId: tUserRoleCode.id } }"
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

<script lang="ts" src="./t-user-role-code-details.component.ts"></script>
