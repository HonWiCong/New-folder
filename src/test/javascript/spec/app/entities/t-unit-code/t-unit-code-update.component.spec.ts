/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TUnitCodeUpdateComponent from '@/entities/t-unit-code/t-unit-code-update.vue';
import TUnitCodeClass from '@/entities/t-unit-code/t-unit-code-update.component';
import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';

import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';
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
	describe('TUnitCode Management Update Component', () => {
		let wrapper: Wrapper<TUnitCodeClass>;
		let comp: TUnitCodeClass;
		let tUnitCodeServiceStub: SinonStubbedInstance<TUnitCodeService>;

		beforeEach(() => {
			tUnitCodeServiceStub = sinon.createStubInstance<TUnitCodeService>(TUnitCodeService);

			wrapper = shallowMount<TUnitCodeClass>(TUnitCodeUpdateComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: {
					tUnitCodeService: () => tUnitCodeServiceStub,
					alertService: () => new AlertService(),

					tSubunitCodeService: () =>
						sinon.createStubInstance<TSubunitCodeService>(TSubunitCodeService, {
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
				comp.tUnitCode = entity;
				tUnitCodeServiceStub.update.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tUnitCodeServiceStub.update.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});

			it('Should call create service on save for new entity', async () => {
				// GIVEN
				const entity = {};
				comp.tUnitCode = entity;
				tUnitCodeServiceStub.create.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tUnitCodeServiceStub.create.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTUnitCode = { id: 123 };
				tUnitCodeServiceStub.find.resolves(foundTUnitCode);
				tUnitCodeServiceStub.retrieve.resolves([foundTUnitCode]);

				// WHEN
				comp.beforeRouteEnter({ params: { tUnitCodeId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tUnitCode).toBe(foundTUnitCode);
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
