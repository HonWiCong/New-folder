/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TOrganizationDetailComponent from '@/entities/t-organization/t-organization-details.vue';
import TOrganizationClass from '@/entities/t-organization/t-organization-details.component';
import TOrganizationService from '@/entities/t-organization/t-organization.service';
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
	describe('TOrganization Management Detail Component', () => {
		let wrapper: Wrapper<TOrganizationClass>;
		let comp: TOrganizationClass;
		let tOrganizationServiceStub: SinonStubbedInstance<TOrganizationService>;

		beforeEach(() => {
			tOrganizationServiceStub = sinon.createStubInstance<TOrganizationService>(TOrganizationService);

			wrapper = shallowMount<TOrganizationClass>(TOrganizationDetailComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: { tOrganizationService: () => tOrganizationServiceStub, alertService: () => new AlertService() },
			});
			comp = wrapper.vm;
		});

		describe('OnInit', () => {
			it('Should call load all on init', async () => {
				// GIVEN
				const foundTOrganization = { id: 123 };
				tOrganizationServiceStub.find.resolves(foundTOrganization);

				// WHEN
				comp.retrieveTOrganization(123);
				await comp.$nextTick();

				// THEN
				expect(comp.tOrganization).toBe(foundTOrganization);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTOrganization = { id: 123 };
				tOrganizationServiceStub.find.resolves(foundTOrganization);

				// WHEN
				comp.beforeRouteEnter({ params: { tOrganizationId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tOrganization).toBe(foundTOrganization);
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
