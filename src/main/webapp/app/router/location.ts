import { Authority } from '@/shared/security/authority';

const TCountryCode = () => import('@/main/location/t-country-code/t-country-code.vue');
const TCountryCodeUpdate = () => import('@/main/location/t-country-code/t-country-code-update.vue');
const TCountryCodeDetails = () => import('@/main/location/t-country-code/t-country-code-details.vue');

const TStateCode = () => import('@/main/location/t-state-code/t-state-code.vue');
const TStateCodeUpdate = () => import('@/main/location/t-state-code/t-state-code-update.vue');
const TStateCodeDetails = () => import('@/main/location/t-state-code/t-state-code-details.vue');

const TDivisionCode = () => import('@/main/location/t-division-code/t-division-code.vue');
const TDivisionCodeUpdate = () => import('@/main/location/t-division-code/t-division-code-update.vue');
const TDivisionCodeDetails = () => import('@/main/location/t-division-code/t-division-code-details.vue');

const TCityCode = () => import('@/main/location/t-city-code/t-city-code.vue');
const TCityCodeUpdate = () => import('@/main/location/t-city-code/t-city-code-update.vue');
const TCityCodeDetails = () => import('@/main/location/t-city-code/t-city-code-details.vue');

const Location = () => import('@/main/location/location.vue');

export default [
	{
		path: '/location',
		component: Location,
		children: [
			{
				path: 't-country-code',
				name: 'LocationCountry',
				component: TCountryCode,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-country-code/:tCountryCodeId/view',
				name: 'LocationCountryView',
				component: TCountryCodeDetails,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-country-code/new',
				name: 'LocationCountryNew',
				component: TCountryCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-country-code/:tCountryCodeId/edit',
				name: 'LocationCountryEdit',
				component: TCountryCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-state-code',
				name: 'LocationState',
				component: TStateCode,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-state-code/:tStateCodeId/view',
				name: 'LocationStateView',
				component: TStateCodeDetails,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-state-code/new',
				name: 'LocationStateNew',
				component: TStateCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-state-code/:tStateCodeId/edit',
				name: 'LocationStateEdit',
				component: TStateCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-city-code',
				name: 'LocationCity',
				component: TCityCode,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-city-code/:tCityCodeId/view',
				name: 'LocationCityView',
				component: TCityCodeDetails,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-city-code/new',
				name: 'LocationCityNew',
				component: TCityCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-city-code/:tCityCodeId/edit',
				name: 'LocationCityEdit',
				component: TCityCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-division-code',
				name: 'LocationDivision',
				component: TDivisionCode,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-division-code/:tDivisionCodeId/view',
				name: 'LocationDivisionView',
				component: TDivisionCodeDetails,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-division-code/new',
				name: 'LocationDivisionNew',
				component: TDivisionCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
			{
				path: 't-division-code/:tDivisionCodeId/edit',
				name: 'LocationDivisionEdit',
				component: TDivisionCodeUpdate,
				meta: { authorities: [Authority.ADMIN] },
			},
		],
	},
];
