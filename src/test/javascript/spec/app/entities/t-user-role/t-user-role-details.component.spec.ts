/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TUserRoleDetailComponent from '@/entities/t-user-role/t-user-role-details.vue';
import TUserRoleClass from '@/entities/t-user-role/t-user-role-details.component';
import TUserRoleService from '@/entities/t-user-role/t-user-role.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
	describe('TUserRole Management Detail Component', () => {
		let wrapper: Wrapper<TUserRoleClass>;
		let comp: TUserRoleClass;
		let tUserRoleServiceStub: SinonStubbedInstance<TUserRoleService>;

		beforeEach(() => {
			tUserRoleServiceStub = sinon.createStubInstance<TUserRoleService>(TUserRoleService);

			wrapper = shallowMount<TUserRoleClass>(TUserRoleDetailComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: { tUserRoleService: () => tUserRoleServiceStub, alertService: () => new AlertService() },
			});
			comp = wrapper.vm;
		});

		describe('OnInit', () => {
			it('Should call load all on init', async () => {
				// GIVEN
				const foundTUserRole = { id: 123 };
				tUserRoleServiceStub.find.resolves(foundTUserRole);

				// WHEN
				comp.retrieveTUserRole(123);
				await comp.$nextTick();

				// THEN
				expect(comp.tUserRole).toBe(foundTUserRole);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTUserRole = { id: 123 };
				tUserRoleServiceStub.find.resolves(foundTUserRole);

				// WHEN
				comp.beforeRouteEnter({ params: { tUserRoleId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tUserRole).toBe(foundTUserRole);
			});
		});

		describe('Previous state', () => {
			it('Should go previous state', async () => {
				comp.previousState();
				await comp.$nextTick();

				expect(comp.$router.currentRoute.fullPath).toContain('/');
			});
		});
	});
});
