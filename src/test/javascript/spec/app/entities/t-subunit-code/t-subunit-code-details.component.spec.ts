/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TSubunitCodeDetailComponent from '@/entities/t-subunit-code/t-subunit-code-details.vue';
import TSubunitCodeClass from '@/entities/t-subunit-code/t-subunit-code-details.component';
import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';
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
	describe('TSubunitCode Management Detail Component', () => {
		let wrapper: Wrapper<TSubunitCodeClass>;
		let comp: TSubunitCodeClass;
		let tSubunitCodeServiceStub: SinonStubbedInstance<TSubunitCodeService>;

		beforeEach(() => {
			tSubunitCodeServiceStub = sinon.createStubInstance<TSubunitCodeService>(TSubunitCodeService);

			wrapper = shallowMount<TSubunitCodeClass>(TSubunitCodeDetailComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: { tSubunitCodeService: () => tSubunitCodeServiceStub, alertService: () => new AlertService() },
			});
			comp = wrapper.vm;
		});

		describe('OnInit', () => {
			it('Should call load all on init', async () => {
				// GIVEN
				const foundTSubunitCode = { id: 123 };
				tSubunitCodeServiceStub.find.resolves(foundTSubunitCode);

				// WHEN
				comp.retrieveTSubunitCode(123);
				await comp.$nextTick();

				// THEN
				expect(comp.tSubunitCode).toBe(foundTSubunitCode);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTSubunitCode = { id: 123 };
				tSubunitCodeServiceStub.find.resolves(foundTSubunitCode);

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
