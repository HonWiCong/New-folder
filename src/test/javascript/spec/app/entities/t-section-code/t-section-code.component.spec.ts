/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TSectionCodeComponent from '@/entities/t-section-code/t-section-code.vue';
import TSectionCodeClass from '@/entities/t-section-code/t-section-code.component';
import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';
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
	describe('TSectionCode Management Component', () => {
		let wrapper: Wrapper<TSectionCodeClass>;
		let comp: TSectionCodeClass;
		let tSectionCodeServiceStub: SinonStubbedInstance<TSectionCodeService>;

		beforeEach(() => {
			tSectionCodeServiceStub = sinon.createStubInstance<TSectionCodeService>(TSectionCodeService);
			tSectionCodeServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TSectionCodeClass>(TSectionCodeComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tSectionCodeService: () => tSectionCodeServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tSectionCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTSectionCodes();
			await comp.$nextTick();

			// THEN
			expect(tSectionCodeServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tSectionCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tSectionCodeServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tSectionCodeServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTSectionCode();
			await comp.$nextTick();

			// THEN
			expect(tSectionCodeServiceStub.delete.called).toBeTruthy();
			expect(tSectionCodeServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
