/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TSectionCodeDetailComponent from '@/entities/t-section-code/t-section-code-details.vue';
import TSectionCodeClass from '@/entities/t-section-code/t-section-code-details.component';
import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';
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
	describe('TSectionCode Management Detail Component', () => {
		let wrapper: Wrapper<TSectionCodeClass>;
		let comp: TSectionCodeClass;
		let tSectionCodeServiceStub: SinonStubbedInstance<TSectionCodeService>;

		beforeEach(() => {
			tSectionCodeServiceStub = sinon.createStubInstance<TSectionCodeService>(TSectionCodeService);

			wrapper = shallowMount<TSectionCodeClass>(TSectionCodeDetailComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: { tSectionCodeService: () => tSectionCodeServiceStub, alertService: () => new AlertService() },
			});
			comp = wrapper.vm;
		});

		describe('OnInit', () => {
			it('Should call load all on init', async () => {
				// GIVEN
				const foundTSectionCode = { id: 123 };
				tSectionCodeServiceStub.find.resolves(foundTSectionCode);

				// WHEN
				comp.retrieveTSectionCode(123);
				await comp.$nextTick();

				// THEN
				expect(comp.tSectionCode).toBe(foundTSectionCode);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTSectionCode = { id: 123 };
				tSectionCodeServiceStub.find.resolves(foundTSectionCode);

				// WHEN
				comp.beforeRouteEnter({ params: { tSectionCodeId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tSectionCode).toBe(foundTSectionCode);
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
