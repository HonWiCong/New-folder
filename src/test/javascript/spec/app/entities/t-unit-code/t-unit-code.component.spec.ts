/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TUnitCodeComponent from '@/entities/t-unit-code/t-unit-code.vue';
import TUnitCodeClass from '@/entities/t-unit-code/t-unit-code.component';
import TUnitCodeService from '@/entities/t-unit-code/t-unit-code.service';
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
	describe('TUnitCode Management Component', () => {
		let wrapper: Wrapper<TUnitCodeClass>;
		let comp: TUnitCodeClass;
		let tUnitCodeServiceStub: SinonStubbedInstance<TUnitCodeService>;

		beforeEach(() => {
			tUnitCodeServiceStub = sinon.createStubInstance<TUnitCodeService>(TUnitCodeService);
			tUnitCodeServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TUnitCodeClass>(TUnitCodeComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tUnitCodeService: () => tUnitCodeServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tUnitCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTUnitCodes();
			await comp.$nextTick();

			// THEN
			expect(tUnitCodeServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tUnitCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tUnitCodeServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tUnitCodeServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTUnitCode();
			await comp.$nextTick();

			// THEN
			expect(tUnitCodeServiceStub.delete.called).toBeTruthy();
			expect(tUnitCodeServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
