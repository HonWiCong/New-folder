<template>
	<div>
		<b-navbar toggleable="md" type="light" class="jh-navbar">
			<Breadcrumb />

			<b-navbar-toggle
				right
				class="jh-navbar-toggler d-lg-none"
				href="javascript:void(0);"
				data-toggle="collapse"
				target="header-tabs"
				aria-expanded="false"
				aria-label="Toggle navigation"
			>
				<font-awesome-icon icon="bars" />
			</b-navbar-toggle>

			<b-collapse is-nav id="header-tabs" class="hide-indicator">
				<b-navbar-nav class="ml-auto">
					<b-nav-item-dropdown right>
						<span slot="button-content" class="navbar-dropdown-menu" :class="{ active: subIsActive('/location') }">
							<MapPin class="icon" />
							<span>Location</span>
						</span>
						<b-dropdown-item
							:to="{ name: 'LocationCountry' }"
							tag="b-dropdown-item"
							:class="{ 'dropdown-item-active': subIsActive('/location/t-country-code') }"
						>
							<span>Country</span>
						</b-dropdown-item>
						<b-dropdown-item
							:to="{ name: 'LocationState' }"
							tag="b-dropdown-item"
							:class="{ 'dropdown-item-active': subIsActive('/location/t-state-code') }"
						>
							<span>State</span>
						</b-dropdown-item>
						<b-dropdown-item
							:to="{ name: 'LocationCity' }"
							tag="b-dropdown-item"
							:class="{ 'dropdown-item-active': subIsActive('/location/t-city-code') }"
						>
							<span>City</span>
						</b-dropdown-item>
						<b-dropdown-item
							:to="{ name: 'LocationDivision' }"
							tag="b-dropdown-item"
							:class="{ 'dropdown-item-active': subIsActive('/location/t-division-code') }"
						>
							<span>Division</span>
						</b-dropdown-item>
					</b-nav-item-dropdown>
					<b-nav-item-dropdown right>
						<span slot="button-content" class="navbar-dropdown-menu" :class="{ active: subIsActive('/system-configuration') }">
							<Settings class="icon" />
							<span>System Configuration</span>
						</span>
						<b-dropdown-item
							:to="{ name: 'SupplierOrganization' }"
							tag="b-dropdown-item"
							:class="{ 'dropdown-item-active': subIsActive('/system-configuration/supplier/t-organization') }"
						>
							<span>Supplier</span>
						</b-dropdown-item>
						<b-dropdown-item
							:to="{ name: 'UserManagement' }"
							tag="b-dropdown-item"
							:class="{ 'dropdown-item-active': subIsActive('/system-configuration/user-management') }"
						>
							<span>User Management</span>
						</b-dropdown-item>
					</b-nav-item-dropdown>
					<b-nav-item-dropdown id="languagesnavBarDropdown" right v-if="languages && Object.keys(languages).length > 1">
						<span slot="button-content">
							<font-awesome-icon icon="flag" />
							<span class="no-bold" v-text="$t('global.menu.language')">Language</span>
						</span>
						<b-dropdown-item
							v-for="(value, key) in languages"
							:key="`lang-${key}`"
							v-on:click="changeLanguage(key)"
							:class="{ active: isActiveLanguage(key) }"
						>
							{{ value.name }}
						</b-dropdown-item>
					</b-nav-item-dropdown>
					<b-nav-item-dropdown
						right
						href="javascript:void(0);"
						id="account-menu"
						:class="{ 'router-link-active': subIsActive('/account') }"
						active-class="active"
						class="pointer"
						data-cy="accountMenu"
					>
						<span slot="button-content" class="navbar-dropdown-menu">
							<User class="icon" />
							<span class="no-bold" v-text="$t('global.menu.account.main')"> Account </span>
						</span>
						<b-dropdown-item
							data-cy="settings"
							to="/account/settings"
							tag="b-dropdown-item"
							v-if="authenticated"
							active-class="active"
						>
							<font-awesome-icon icon="wrench" />
							<span v-text="$t('global.menu.account.settings')">Settings</span>
						</b-dropdown-item>
						<b-dropdown-item
							data-cy="passwordItem"
							to="/account/password"
							tag="b-dropdown-item"
							v-if="authenticated"
							active-class="active"
						>
							<font-awesome-icon icon="lock" />
							<span v-text="$t('global.menu.account.password')">Password</span>
						</b-dropdown-item>
						<b-dropdown-item data-cy="logout" v-if="authenticated" v-on:click="logout()" id="logout" active-class="active">
							<font-awesome-icon icon="sign-out-alt" />
							<span v-text="$t('global.menu.account.logout')">Sign out</span>
						</b-dropdown-item>
						<b-dropdown-item data-cy="login" v-if="!authenticated" v-on:click="openLogin()" id="login" active-class="active">
							<font-awesome-icon icon="sign-in-alt" />
							<span v-text="$t('global.menu.account.login')">Sign in</span>
						</b-dropdown-item>
						<b-dropdown-item
							data-cy="register"
							to="/register"
							tag="b-dropdown-item"
							id="register"
							v-if="!authenticated"
							active-class="active"
						>
							<font-awesome-icon icon="user-plus" />
							<span v-text="$t('global.menu.account.register')">Register</span>
						</b-dropdown-item>
					</b-nav-item-dropdown>
				</b-navbar-nav>
			</b-collapse>
		</b-navbar>
	</div>
</template>

<script lang="ts" src="./jhi-navbar.component.ts"></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.jh-navbar .hide-indicator .nav-link.dropdown-toggle::after {
	display: none !important;
}
.jh-navbar .dropdown-menu {
	opacity: 0 !important;
	transition: opacity 0.3s ease !important;
}
.jh-navbar .dropdown-menu.show {
	display: block !important;
	opacity: 1 !important;
	transition: opacity 0.3s ease !important;
}
.jh-navbar .dropdown-item-active {
	background-color: var(--primary);
}
.jh-navbar .dropdown-item-active span {
	color: white !important;
}
.jh-navbar .dropdown-item:hover {
	background-color: var(--primary);
}
.jh-navbar .dropdown-item:hover span {
	color: white !important;
}
</style>
<style scoped>
/* ==========================================================================
    Navbar
    ========================================================================== */
.jh-navbar {
	background-color: white;
	padding: 0.5rem 1rem;
}
.icon {
	width: 20px;
}
.active {
	background-color: var(--primary);
	color: white !important;
}
.navbar-title {
	font-size: 1em;
	font-weight: 500;
	color: var(--primary);
}
.navbar-dropdown-menu {
	padding: 0.5rem 1rem;
	border-radius: 0.5rem;
	color: black;
	font-weight: 400;
}
.navbar-dropdown-menu:hover {
	background-color: var(--primary);
	color: white !important;
}
.jh-navbar .jh-navbar-toggler {
	font-size: 1.5em;
	padding: 10px;
}

@media screen and (min-width: 768px) {
	.jh-navbar-toggler {
		display: none;
	}
}

@media screen and (min-width: 768px) and (max-width: 1150px) {
	span span {
		display: none;
	}
}

.navbar-brand.logo {
	padding: 5px 15px;
}

.logo .logo-img {
	height: 45px;
	display: inline-block;
	vertical-align: middle;
	width: 70px;
}

.logo-img {
	height: 100%;
	background: url('../../../content/images/logo-jhipster.png') no-repeat center center;
	background-size: contain;
	width: 100%;
	filter: drop-shadow(0 0 0.05rem white);
}
</style>
