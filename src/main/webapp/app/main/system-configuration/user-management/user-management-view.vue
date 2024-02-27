<template>
	<div class="custom-container">
		<div class="row justify-content-center">
			<div class="col-8">
				<div v-if="user">
					<dl class="row jh-entity-details">
						<dt><span v-text="$t('userManagement.login')">Login</span></dt>
						<dd>
							<span>{{ user.internalUser.login }}</span>
							<b-badge variant="success" v-if="user.internalUser.activated" v-text="$t('userManagement.activated')"
								>Activated</b-badge
							>
							<b-badge variant="danger" v-if="!user.internalUser.activated" v-text="$t('userManagement.deactivated')"
								>Deactivated</b-badge
							>
						</dd>
						<dt><span v-text="$t('userManagement.firstName')"></span></dt>
						<dd>{{ user.internalUser.firstName }}</dd>
						<dt><span v-text="$t('userManagement.lastName')"></span></dt>
						<dd>{{ user.internalUser.lastName }}</dd>
						<dt><span v-text="$t('userManagement.email')"></span></dt>
						<dd>{{ user.internalUser.email }}</dd>
						<dt><span v-text="$t('userManagement.langKey')"></span></dt>
						<dd>{{ user.internalUser.langKey }}</dd>
						<dt><span v-text="$t('userManagement.createdBy')"></span></dt>
						<dd>{{ user.internalUser.createdBy }}</dd>
						<dt><span v-text="$t('userManagement.createdDate')"></span></dt>
						<dd>
							<span v-if="user.internalUser.createdDate">
								{{ $d(Date.parse(user.internalUser.createdDate), 'long') }}
							</span>
						</dd>
						<dt><span v-text="$t('userManagement.lastModifiedBy')">Last Modified By</span></dt>
						<dd>{{ user.internalUser.lastModifiedBy }}</dd>
						<dt><span v-text="$t('userManagement.lastModifiedDate')">Last Modified Date</span></dt>
						<dd>
							<span v-if="user.internalUser.lastModifiedDate">
								{{ $d(Date.parse(user.internalUser.lastModifiedDate), 'long') }}
							</span>
						</dd>
						<dt><span v-text="$t('userManagement.profiles')">Profiles</span></dt>
						<dd>
							<ul class="list-unstyled">
								<li v-for="authority of user.internalUser.authorities" :key="authority">
									<b-badge variant="info">{{ authority }}</b-badge>
								</li>
							</ul>
						</dd>
					</dl>
					<router-link custom v-slot="{ navigate }" :to="{ name: 'UserManagement' }">
						<button @click="navigate" class="btn btn-info">
							<font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"
								>Back</span
							>
						</button>
					</router-link>
				</div>
			</div>
		</div>
	</div>
</template>

<script lang="ts" src="./user-management-view.component.ts"></script>
<style scoped>
.custom-container {
	padding: 1rem;
	background-color: white;
	border-radius: 0.5rem;
}
</style>
