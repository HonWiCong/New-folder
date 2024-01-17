/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TOrganizationComponent from '@/entities/t-organization/t-organization.vue';
import TOrganizationClass from '@/entities/t-organization/t-organization.component';
import TOrganizationService from '@/entities/t-organization/t-organization.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
	render: () => {},
	methods: {
		hide: () => {},
		show: () => {},
	},
};

describe('Component Tests', () => {
	describe('TOrganization Management Component', () => {
		let wrapper: Wrapper<TOrganizationClass>;
		let comp: TOrganizationClass;
		let tOrganizationServiceStub: SinonStubbedInstance<TOrganizationService>;

		beforeEach(() => {
			tOrganizationServiceStub = sinon.createStubInstance<TOrganizationService>(TOrganizationService);
			tOrganizationServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TOrganizationClass>(TOrganizationComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tOrganizationService: () => tOrganizationServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tOrganizationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTOrganizations();
			await comp.$nextTick();

			// THEN
			expect(tOrganizationServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tOrganizations[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tOrganizationServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tOrganizationServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTOrganization();
			await comp.$nextTick();

			// THEN
			expect(tOrganizationServiceStub.delete.called).toBeTruthy();
			expect(tOrganizationServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
