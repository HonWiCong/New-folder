/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TUnitCodeDetailComponent from '@/entities/t-unit-code/t-unit-code-details.vue';
import TUnitCodeClass from '@/entities/t-unit-code/t-unit-code-details.component';
import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';
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
	describe('TUnitCode Management Detail Component', () => {
		let wrapper: Wrapper<TUnitCodeClass>;
		let comp: TUnitCodeClass;
		let tUnitCodeServiceStub: SinonStubbedInstance<TUnitCodeService>;

		beforeEach(() => {
			tUnitCodeServiceStub = sinon.createStubInstance<TUnitCodeService>(TUnitCodeService);

			wrapper = shallowMount<TUnitCodeClass>(TUnitCodeDetailComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: { tUnitCodeService: () => tUnitCodeServiceStub, alertService: () => new AlertService() },
			});
			comp = wrapper.vm;
		});

		describe('OnInit', () => {
			it('Should call load all on init', async () => {
				// GIVEN
				const foundTUnitCode = { id: 123 };
				tUnitCodeServiceStub.find.resolves(foundTUnitCode);

				// WHEN
				comp.retrieveTUnitCode(123);
				await comp.$nextTick();

				// THEN
				expect(comp.tUnitCode).toBe(foundTUnitCode);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTUnitCode = { id: 123 };
				tUnitCodeServiceStub.find.resolves(foundTUnitCode);

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
