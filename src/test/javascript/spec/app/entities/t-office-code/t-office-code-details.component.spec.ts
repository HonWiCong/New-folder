/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TOfficeCodeDetailComponent from '@/entities/t-office-code/t-office-code-details.vue';
import TOfficeCodeClass from '@/entities/t-office-code/t-office-code-details.component';
import TOfficeCodeService from '@/entities/t-office-code/t-office-code.service';
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
	describe('TOfficeCode Management Detail Component', () => {
		let wrapper: Wrapper<TOfficeCodeClass>;
		let comp: TOfficeCodeClass;
		let tOfficeCodeServiceStub: SinonStubbedInstance<TOfficeCodeService>;

		beforeEach(() => {
			tOfficeCodeServiceStub = sinon.createStubInstance<TOfficeCodeService>(TOfficeCodeService);

			wrapper = shallowMount<TOfficeCodeClass>(TOfficeCodeDetailComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: { tOfficeCodeService: () => tOfficeCodeServiceStub, alertService: () => new AlertService() },
			});
			comp = wrapper.vm;
		});

		describe('OnInit', () => {
			it('Should call load all on init', async () => {
				// GIVEN
				const foundTOfficeCode = { id: 123 };
				tOfficeCodeServiceStub.find.resolves(foundTOfficeCode);

				// WHEN
				comp.retrieveTOfficeCode(123);
				await comp.$nextTick();

				// THEN
				expect(comp.tOfficeCode).toBe(foundTOfficeCode);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTOfficeCode = { id: 123 };
				tOfficeCodeServiceStub.find.resolves(foundTOfficeCode);

				// WHEN
				comp.beforeRouteEnter({ params: { tOfficeCodeId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tOfficeCode).toBe(foundTOfficeCode);
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
