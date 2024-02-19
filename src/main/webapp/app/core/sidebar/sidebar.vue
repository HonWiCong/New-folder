<!-- eslint-disable prettier/prettier -->
<template>
	<div class="sidebar-layout">
		<b-navbar-brand class="logo" b-link to="/">
			<span class="logo-img"></span>
			<span v-text="$t('global.title')" class="navbar-title">sains</span> <span class="navbar-version">{{ version }}</span>
		</b-navbar-brand>
		<div class="sidebar-items">
			<router-link to="/" class="item" :class="{ active: $route.path === '/' }">Dashboard</router-link>
			<router-link to="/entities" class="item" :class="{ active: $route.path === '/entities' }">Entities</router-link>
			<router-link to="/admin" class="item" :class="{ active: $route.path === '/admin' }">Administration</router-link>
		</div>
	</div>
</template>
<!-- eslint-disable prettier/prettier -->
<style scoped>
.navbar-title {
	display: inline-block;
	vertical-align: middle;
	color: white;
}
.sidebar-layout {
	background-color: #353d47;
	padding: 1rem;
}
.sidebar-items {
	display: flex;
	flex-direction: column;
	gap: 0.25rem;
	margin-top: 1rem;
}
.item {
	color: white;
	text-decoration: none;
	font-weight: normal;
	width: 11.25rem;
	padding: 0.5rem 1rem;
	border-radius: 0.5rem;
	transition: background-color 0.3s;
}
.item:hover {
	background-color: rgba(255, 255, 255, 0.15);
}
.item.active {
	background-color: rgba(255, 255, 255, 0.25);
}
</style>
