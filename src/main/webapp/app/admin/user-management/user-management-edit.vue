<template>
	<div class="row justify-content-center">
		<div class="col-11">
			<b-nav tabs>
				<b-nav-item :active="$route.name === 'JhiUserEditDetail'" :to="{ name: 'JhiUserEditDetail' }" exact
					>USER DETAILS / ROLE</b-nav-item
				>
				<b-nav-item :active="$route.name === 'JhiUserEditAccessRight'" :to="{ name: 'JhiUserEditAccessRight' }" exact
					>Access Right</b-nav-item
				>
				<b-nav-item :active="$route.name === ''" :to="{ name: '' }" exact>Another Link</b-nav-item>
			</b-nav>
			<router-view></router-view>
		</div>
	</div>
</template>

<script lang="ts" src="./user-management-edit.component.ts"></script>
