/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TSubunitCodeComponent from '@/entities/t-subunit-code/t-subunit-code.vue';
import TSubunitCodeClass from '@/entities/t-subunit-code/t-subunit-code.component';
import TSubunitCodeService from '@/entities/t-subunit-code/t-subunit-code.service';
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
	describe('TSubunitCode Management Component', () => {
		let wrapper: Wrapper<TSubunitCodeClass>;
		let comp: TSubunitCodeClass;
		let tSubunitCodeServiceStub: SinonStubbedInstance<TSubunitCodeService>;

		beforeEach(() => {
			tSubunitCodeServiceStub = sinon.createStubInstance<TSubunitCodeService>(TSubunitCodeService);
			tSubunitCodeServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TSubunitCodeClass>(TSubunitCodeComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tSubunitCodeService: () => tSubunitCodeServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tSubunitCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTSubunitCodes();
			await comp.$nextTick();

			// THEN
			expect(tSubunitCodeServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tSubunitCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tSubunitCodeServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tSubunitCodeServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTSubunitCode();
			await comp.$nextTick();

			// THEN
			expect(tSubunitCodeServiceStub.delete.called).toBeTruthy();
			expect(tSubunitCodeServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
