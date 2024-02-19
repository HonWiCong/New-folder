/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TUserRoleComponent from '@/entities/t-user-role/t-user-role.vue';
import TUserRoleClass from '@/entities/t-user-role/t-user-role.component';
import TUserRoleService from '@/entities/t-user-role/t-user-role.service';
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
	describe('TUserRole Management Component', () => {
		let wrapper: Wrapper<TUserRoleClass>;
		let comp: TUserRoleClass;
		let tUserRoleServiceStub: SinonStubbedInstance<TUserRoleService>;

		beforeEach(() => {
			tUserRoleServiceStub = sinon.createStubInstance<TUserRoleService>(TUserRoleService);
			tUserRoleServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TUserRoleClass>(TUserRoleComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tUserRoleService: () => tUserRoleServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tUserRoleServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTUserRoles();
			await comp.$nextTick();

			// THEN
			expect(tUserRoleServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tUserRoles[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tUserRoleServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tUserRoleServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTUserRole();
			await comp.$nextTick();

			// THEN
			expect(tUserRoleServiceStub.delete.called).toBeTruthy();
			expect(tUserRoleServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
