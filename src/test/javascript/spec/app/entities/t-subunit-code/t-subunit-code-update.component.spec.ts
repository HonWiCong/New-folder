/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TSubunitCodeUpdateComponent from '@/entities/t-subunit-code/t-subunit-code-update.vue';
import TSubunitCodeClass from '@/entities/t-subunit-code/t-subunit-code-update.component';
import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';

import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';
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
	describe('TSubunitCode Management Update Component', () => {
		let wrapper: Wrapper<TSubunitCodeClass>;
		let comp: TSubunitCodeClass;
		let tSubunitCodeServiceStub: SinonStubbedInstance<TSubunitCodeService>;

		beforeEach(() => {
			tSubunitCodeServiceStub = sinon.createStubInstance<TSubunitCodeService>(TSubunitCodeService);

			wrapper = shallowMount<TSubunitCodeClass>(TSubunitCodeUpdateComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: {
					tSubunitCodeService: () => tSubunitCodeServiceStub,
					alertService: () => new AlertService(),

					tUnitCodeService: () =>
						sinon.createStubInstance<TUnitCodeService>(TUnitCodeService, {
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
				comp.tSubunitCode = entity;
				tSubunitCodeServiceStub.update.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tSubunitCodeServiceStub.update.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});

			it('Should call create service on save for new entity', async () => {
				// GIVEN
				const entity = {};
				comp.tSubunitCode = entity;
				tSubunitCodeServiceStub.create.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tSubunitCodeServiceStub.create.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTSubunitCode = { id: 123 };
				tSubunitCodeServiceStub.find.resolves(foundTSubunitCode);
				tSubunitCodeServiceStub.retrieve.resolves([foundTSubunitCode]);

				// WHEN
				comp.beforeRouteEnter({ params: { tSubunitCodeId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tSubunitCode).toBe(foundTSubunitCode);
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
