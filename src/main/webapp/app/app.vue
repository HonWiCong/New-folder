<template>
	<div id="app">
		<!-- <ribbon></ribbon> -->
		<div class="layout">
			<side-bar></side-bar>
			<div class="container-fluid">
				<jhi-navbar></jhi-navbar>
				<div class="jh-card">
					<router-view></router-view>
				</div>
				<b-modal id="login-page" hide-footer lazy>
					<span data-cy="loginTitle" slot="modal-title" id="login-title" v-text="$t('login.title')">Sign in</span>
					<login-form></login-form>
				</b-modal>
			</div>
		</div>
	</div>
</template>
<style scoped>
.layout {
	/* height: 100vh; */
	display: flex;
	/* flex-direction: column; */
}
.container-fluid {
	/* flex: 1 1 auto; */
	/* position: relative; */
	display: flex;
	flex-direction: column;
	padding: 0;
}
.jh-card {
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	margin-top: 0;
}
</style>
<script lang="ts" src="./app.component.ts"></script>
