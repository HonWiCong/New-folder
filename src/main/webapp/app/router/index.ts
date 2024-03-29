import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
	'beforeRouteEnter',
	'beforeRouteLeave',
	'beforeRouteUpdate', // for vue-router 2.2+
]);
import Router, { RouteConfig } from 'vue-router';

const Home = () => import('@/core/home/home.vue');
const Error = () => import('@/core/error/error.vue');
const Entities = () => import('@/core/entities/entities.vue');
const Administration = () => import('@/core/administration/administration.vue');
import account from '@/router/account';
import admin from '@/router/admin';
import entities from '@/router/entities';
import pages from '@/router/pages';
import location from '@/router/location';
import system_configuration from './system-configuration';

Vue.use(Router);

// prettier-ignore
const router = new Router({
	mode: 'history',
	routes: [
		{
			path: '/',
			name: 'Home',
			component: Home
		},
		{
			path: '/entities',
			name: 'Entities',
			component: Entities
		},
		{
			path: '/admin',
			name: 'Administration',
			component: Administration
		},
		{
			path: '/forbidden',
			name: 'Forbidden',
			component: Error,
			meta: { error403: true }
		},
		{
			path: '/not-found',
			name: 'NotFound',
			component: Error,
			meta: { error404: true }
		},
		...account,
		...admin,
		entities,
		...pages,
		...location,
		...system_configuration,
	]
});

export default router;
