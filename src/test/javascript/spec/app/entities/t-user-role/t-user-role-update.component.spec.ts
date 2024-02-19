/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TUserRoleUpdateComponent from '@/entities/t-user-role/t-user-role-update.vue';
import TUserRoleClass from '@/entities/t-user-role/t-user-role-update.component';
import TUserRoleService from '@/entities/t-user-role/t-user-role.service';

import TUserRoleCodeService from '@/entities/t-user-role-code/t-user-role-code.service';

import ApplicationUserService from '@/entities/application-user/application-user.service';
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
	describe('TUserRole Management Update Component', () => {
		let wrapper: Wrapper<TUserRoleClass>;
		let comp: TUserRoleClass;
		let tUserRoleServiceStub: SinonStubbedInstance<TUserRoleService>;

		beforeEach(() => {
			tUserRoleServiceStub = sinon.createStubInstance<TUserRoleService>(TUserRoleService);

			wrapper = shallowMount<TUserRoleClass>(TUserRoleUpdateComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: {
					tUserRoleService: () => tUserRoleServiceStub,
					alertService: () => new AlertService(),

					tUserRoleCodeService: () =>
						sinon.createStubInstance<TUserRoleCodeService>(TUserRoleCodeService, {
							retrieve: sinon.stub().resolves({}),
						} as any),

					applicationUserService: () =>
						sinon.createStubInstance<ApplicationUserService>(ApplicationUserService, {
							retrieve: sinon.stub().resolves({}),
						} as any),
				},
			});
			comp = wrapper.vm;
		});

		describe('load', () => {
			it('Should convert date from string', () => {
				// GIVEN
				const date = new Date('2019-10-15T11:42:02Z');

				// WHEN
				const convertedDate = comp.convertDateTimeFromServer(date);

				// THEN
				expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
			});

			it('Should not convert date if date is not present', () => {
				expect(comp.convertDateTimeFromServer(null)).toBeNull();
			});
		});

		describe('save', () => {
			it('Should call update service on save for existing entity', async () => {
				// GIVEN
				const entity = { id: 123 };
				comp.tUserRole = entity;
				tUserRoleServiceStub.update.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tUserRoleServiceStub.update.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});

			it('Should call create service on save for new entity', async () => {
				// GIVEN
				const entity = {};
				comp.tUserRole = entity;
				tUserRoleServiceStub.create.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tUserRoleServiceStub.create.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTUserRole = { id: 123 };
				tUserRoleServiceStub.find.resolves(foundTUserRole);
				tUserRoleServiceStub.retrieve.resolves([foundTUserRole]);

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
