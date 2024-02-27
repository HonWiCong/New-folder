<!-- eslint-disable prettier/prettier -->
<template>
	<div>
		<h3>Administration page</h3>
		<div class="layout">
			<router-link to="/admin/user-management" active-class="active">
				<font-awesome-icon icon="users" />
				<span v-text="$t('global.menu.admin.userManagement')">User management</span>
			</router-link>
			<router-link to="/admin/metrics" active-class="active">
				<font-awesome-icon icon="tachometer-alt" />
				<span v-text="$t('global.menu.admin.metrics')">Metrics</span>
			</router-link>
			<router-link to="/admin/health" active-class="active">
				<font-awesome-icon icon="heart" />
				<span v-text="$t('global.menu.admin.health')">Health</span>
			</router-link>
			<router-link to="/admin/configuration" active-class="active">
				<font-awesome-icon icon="cogs" />
				<span v-text="$t('global.menu.admin.configuration')">Configuration</span>
			</router-link>
			<router-link to="/admin/logs" active-class="active">
				<font-awesome-icon icon="tasks" />
				<span v-text="$t('global.menu.admin.logs')">Logs</span>
			</router-link>
			<router-link v-if="openAPIEnabled" to="/admin/docs" active-class="active">
				<font-awesome-icon icon="book" />
				<span v-text="$t('global.menu.admin.apidocs')">API</span>
			</router-link>
		</div>
	</div>
</template>
<!-- eslint-disable prettier/prettier -->
<style scoped>
.layout {
	display: flex;
	flex-direction: column;
	gap: 0.25rem;
	margin-top: 1rem;
}
</style>
