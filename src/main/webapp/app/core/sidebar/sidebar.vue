<!-- eslint-disable prettier/prettier -->
<template>
	<div class="sidebar-layout">
		<div class="logo">SAINS</div>
		<template v-if="$route.path.includes('/location')">
			<div class="sidebar-items">
				<router-link
					:to="{ name: 'LocationCountry' }"
					class="item"
					:class="{ active: $route.path.includes('/location/t-country-code') }"
					>Country</router-link
				>
				<router-link
					:to="{ name: 'LocationState' }"
					class="item"
					:class="{ active: $route.path.includes('/location/t-state-code') }"
					>State</router-link
				>
				<router-link :to="{ name: 'LocationCity' }" class="item" :class="{ active: $route.path.includes('/location/t-city-code') }"
					>City</router-link
				>
				<router-link
					:to="{ name: 'LocationDivision' }"
					class="item"
					:class="{ active: $route.path.includes('/location/t-division-code') }"
					>Division</router-link
				>
			</div>
		</template>
		<template v-else-if="$route.path.includes('/system-configuration')">
			<div class="sidebar-items">
				<router-link :to="{ name: 'SupplierOrganization' }" class="item" :class="{ active: $route.path.includes('/supplier') }"
					>Supplier</router-link
				>
				<router-link :to="{ name: 'UserManagement' }" class="item" :class="{ active: $route.path.includes('/user-management') }"
					>User Management</router-link
				>
			</div>
		</template>
		<template v-else>
			<div class="sidebar-items">
				<router-link to="/" class="item" :class="{ active: $route.path === '/' }">Dashboard</router-link>
				<router-link to="/entities" class="item" :class="{ active: $route.path.includes('/entities') }">Entities</router-link>
				<router-link to="/admin" class="item" :class="{ active: $route.path.includes('/admin') }">Administration</router-link>
			</div>
		</template>
	</div>
</template>
<!-- eslint-disable prettier/prettier -->
<style scoped>
.sidebar-layout {
	/* height: min(100dvh, 100%); */
	/* height: 100%; */
	height: 100vh;
	position: sticky;
	left: 0;
	top: 0;
	background-color: white;
	padding: 1rem;
	border-right: 1px solid hsl(0, 0%, 90%);
}
.sidebar-items {
	display: flex;
	flex-direction: column;
	gap: 0.25rem;
}
.item {
	text-decoration: none;
	font-weight: normal;
	width: 11.25rem;
	padding: 0.5rem 1rem;
	border-radius: 0.5rem;
	transition: background-color 0.3s;
}
.item:hover {
	color: white;
	background-color: var(--primary);
}
.item.active {
	color: white;
	background-color: var(--primary);
}
.logo {
	font-size: 1.5rem;
	font-weight: 500;
	margin-bottom: 1rem;
	padding-left: 1rem;
	color: var(--primary);
}
</style>
