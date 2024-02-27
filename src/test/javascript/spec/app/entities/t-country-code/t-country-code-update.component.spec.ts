/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TCountryCodeUpdateComponent from '@/entities/t-country-code/t-country-code-update.vue';
import TCountryCodeClass from '@/entities/t-country-code/t-country-code-update.component';
import TCountryCodeService from '@/entities/t-country-code/t-country-code.service';

import TStateCodeService from '@/entities/t-state-code/t-state-code.service';
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
	describe('TCountryCode Management Update Component', () => {
		let wrapper: Wrapper<TCountryCodeClass>;
		let comp: TCountryCodeClass;
		let tCountryCodeServiceStub: SinonStubbedInstance<TCountryCodeService>;

		beforeEach(() => {
			tCountryCodeServiceStub = sinon.createStubInstance<TCountryCodeService>(TCountryCodeService);

			wrapper = shallowMount<TCountryCodeClass>(TCountryCodeUpdateComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: {
					tCountryCodeService: () => tCountryCodeServiceStub,
					alertService: () => new AlertService(),

					tStateCodeService: () =>
						sinon.createStubInstance<TStateCodeService>(TStateCodeService, {
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
				comp.tCountryCode = entity;
				tCountryCodeServiceStub.update.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tCountryCodeServiceStub.update.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});

			it('Should call create service on save for new entity', async () => {
				// GIVEN
				const entity = {};
				comp.tCountryCode = entity;
				tCountryCodeServiceStub.create.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tCountryCodeServiceStub.create.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTCountryCode = { id: 123 };
				tCountryCodeServiceStub.find.resolves(foundTCountryCode);
				tCountryCodeServiceStub.retrieve.resolves([foundTCountryCode]);

				// WHEN
				comp.beforeRouteEnter({ params: { tCountryCodeId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tCountryCode).toBe(foundTCountryCode);
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
