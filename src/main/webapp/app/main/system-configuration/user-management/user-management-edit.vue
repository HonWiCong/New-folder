<template>
	<div class="row justify-content-center">
		<div class="custom-container col-12">
			<div class="tabs">
				<router-link
					class="tab-item"
					:class="$route.path.includes('/user-detail') ? 'active' : 'inactive'"
					:to="
						$route.path.includes('new')
							? { name: 'UserManagementCreateDetail' }
							: { name: 'UserManagementEditDetail', params: { userId: $route.params.userId } }
					"
					>USER DETAILS / ROLE</router-link
				>
				<router-link
					class="tab-item"
					:class="$route.path.includes('/access-right') ? 'active' : 'inactive'"
					:to="
						$route.path.includes('new')
							? { name: 'UserManagementCreateAccessRight' }
							: { name: 'UserManagementEditAccessRight', params: { userId: $route.params.userId } }
					"
					>Access Right</router-link
				>
			</div>
			<router-view></router-view>
		</div>
	</div>
</template>

<script lang="ts" src="./user-management-edit.component.ts"></script>

<style scoped>
.custom-container {
	padding-left: 2rem;
	padding-right: 2rem;
}
.tabs {
	display: flex;
	gap: 0;
	margin-bottom: 1rem;
	background-color: #fff;
}
.active {
	border-bottom: 2px solid var(--primary);
}
.inactive {
}
.tab-item {
	padding: 1rem;
	text-decoration: none;
	color: #000;
	font-weight: 500;
}
</style>
