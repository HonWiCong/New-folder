<template>
	<div class="row justify-content-center">
		<div class="custom-container col-12">
			<div class="tabs">
				<router-link
					class="tab-item"
					:class="$route.name === 'JhiUserEditDetail' ? 'active' : 'inactive'"
					:to="{ name: 'JhiUserEditDetail' }"
					>USER DETAILS / ROLE</router-link
				>
				<router-link
					class="tab-item"
					:class="$route.name === 'JhiUserEditAccessRight' ? 'active' : 'inactive'"
					:to="{ name: 'JhiUserEditAccessRight' }"
					>Access Right</router-link
				>
				<router-link class="tab-item" :class="$route.name === 'Include the name here' ? 'active' : 'inactive'" :to="{ name: '' }"
					>Another Link</router-link
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
