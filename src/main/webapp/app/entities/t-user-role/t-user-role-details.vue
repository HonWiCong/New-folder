<template>
	<div class="row justify-content-center">
		<div class="col-8">
			<div v-if="tUserRole">
				<h2 class="jh-entity-heading" data-cy="tUserRoleDetailsHeading">
					<span v-text="$t('sainsApp.tUserRole.detail.title')">TUserRole</span> {{ tUserRole.id }}
				</h2>
				<dl class="row jh-entity-details">
					<dt>
						<span v-text="$t('sainsApp.tUserRole.sysuserId')">Sysuser Id</span>
					</dt>
					<dd>
						<span>{{ tUserRole.sysuserId }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.usrRoleid')">Usr Roleid</span>
					</dt>
					<dd>
						<span>{{ tUserRole.usrRoleid }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.enteredBy')">Entered By</span>
					</dt>
					<dd>
						<span>{{ tUserRole.enteredBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.enteredDate')">Entered Date</span>
					</dt>
					<dd>
						<span v-if="tUserRole.enteredDate">{{ $d(Date.parse(tUserRole.enteredDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.modifiedBy')">Modified By</span>
					</dt>
					<dd>
						<span>{{ tUserRole.modifiedBy }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.modifiedDate')">Modified Date</span>
					</dt>
					<dd>
						<span v-if="tUserRole.modifiedDate">{{ $d(Date.parse(tUserRole.modifiedDate), 'long') }}</span>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.role')">Role</span>
					</dt>
					<dd>
						<div v-if="tUserRole.role">
							<router-link :to="{ name: 'TUserRoleCodeView', params: { tUserRoleCodeId: tUserRole.role.id } }">{{
								tUserRole.role.id
							}}</router-link>
						</div>
					</dd>
					<dt>
						<span v-text="$t('sainsApp.tUserRole.user')">User</span>
					</dt>
					<dd>
						<div v-if="tUserRole.user">
							<router-link :to="{ name: 'ApplicationUserView', params: { applicationUserId: tUserRole.user.id } }">{{
								tUserRole.user.id
							}}</router-link>
						</div>
					</dd>
				</dl>
				<button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
					<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
				</button>
				<router-link
					v-if="tUserRole.id"
					:to="{ name: 'TUserRoleEdit', params: { tUserRoleId: tUserRole.id } }"
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

<script lang="ts" src="./t-user-role-details.component.ts"></script>
