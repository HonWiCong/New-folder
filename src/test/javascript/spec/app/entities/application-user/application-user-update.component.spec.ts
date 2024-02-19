/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ApplicationUserUpdateComponent from '@/entities/application-user/application-user-update.vue';
import ApplicationUserClass from '@/entities/application-user/application-user-update.component';
import ApplicationUserService from '@/entities/application-user/application-user.service';

import UserService from '@/entities/user/user.service';

import TUserRoleService from '@/entities/t-user-role/t-user-role.service';

import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';

import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';

import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';

import TOfficeCodeService from '@/entities/t-office-code/t-office-code.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
	describe('ApplicationUser Management Update Component', () => {
		let wrapper: Wrapper<ApplicationUserClass>;
		let comp: ApplicationUserClass;
		let applicationUserServiceStub: SinonStubbedInstance<ApplicationUserService>;

		beforeEach(() => {
			applicationUserServiceStub = sinon.createStubInstance<ApplicationUserService>(ApplicationUserService);

			wrapper = shallowMount<ApplicationUserClass>(ApplicationUserUpdateComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: {
					applicationUserService: () => applicationUserServiceStub,
					alertService: () => new AlertService(),

					userService: () => new UserService(),

					tUserRoleService: () =>
						sinon.createStubInstance<TUserRoleService>(TUserRoleService, {
							retrieve: sinon.stub().resolves({}),
						} as any),

					tSectionCodeService: () =>
						sinon.createStubInstance<TSectionCodeService>(TSectionCodeService, {
							retrieve: sinon.stub().resolves({}),
						} as any),

					tUnitCodeService: () =>
						sinon.createStubInstance<TUnitCodeService>(TUnitCodeService, {
							retrieve: sinon.stub().resolves({}),
						} as any),

					tSubunitCodeService: () =>
						sinon.createStubInstance<TSubunitCodeService>(TSubunitCodeService, {
							retrieve: sinon.stub().resolves({}),
						} as any),

					tOfficeCodeService: () =>
						sinon.createStubInstance<TOfficeCodeService>(TOfficeCodeService, {
							retrieve: sinon.stub().resolves({}),
						} as any),
				},
			});
			comp = wrapper.vm;
		});

		describe('save', () => {
			it('Should call update service on save for existing entity', async () => {
				// GIVEN
				const entity = { id: 123 };
				comp.applicationUser = entity;
				applicationUserServiceStub.update.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(applicationUserServiceStub.update.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});

			it('Should call create service on save for new entity', async () => {
				// GIVEN
				const entity = {};
				comp.applicationUser = entity;
				applicationUserServiceStub.create.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(applicationUserServiceStub.create.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundApplicationUser = { id: 123 };
				applicationUserServiceStub.find.resolves(foundApplicationUser);
				applicationUserServiceStub.retrieve.resolves([foundApplicationUser]);

				// WHEN
				comp.beforeRouteEnter({ params: { applicationUserId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.applicationUser).toBe(foundApplicationUser);
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
