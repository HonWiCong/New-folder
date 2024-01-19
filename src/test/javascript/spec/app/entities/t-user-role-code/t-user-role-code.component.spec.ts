/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TUserRoleCodeComponent from '@/entities/t-user-role-code/t-user-role-code.vue';
import TUserRoleCodeClass from '@/entities/t-user-role-code/t-user-role-code.component';
import TUserRoleCodeService from '@/entities/t-user-role-code/t-user-role-code.service';
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
	describe('TUserRoleCode Management Component', () => {
		let wrapper: Wrapper<TUserRoleCodeClass>;
		let comp: TUserRoleCodeClass;
		let tUserRoleCodeServiceStub: SinonStubbedInstance<TUserRoleCodeService>;

		beforeEach(() => {
			tUserRoleCodeServiceStub = sinon.createStubInstance<TUserRoleCodeService>(TUserRoleCodeService);
			tUserRoleCodeServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TUserRoleCodeClass>(TUserRoleCodeComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tUserRoleCodeService: () => tUserRoleCodeServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tUserRoleCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTUserRoleCodes();
			await comp.$nextTick();

			// THEN
			expect(tUserRoleCodeServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tUserRoleCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tUserRoleCodeServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tUserRoleCodeServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTUserRoleCode();
			await comp.$nextTick();

			// THEN
			expect(tUserRoleCodeServiceStub.delete.called).toBeTruthy();
			expect(tUserRoleCodeServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
