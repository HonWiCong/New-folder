/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TOrgContactPersonComponent from '@/entities/t-org-contact-person/t-org-contact-person.vue';
import TOrgContactPersonClass from '@/entities/t-org-contact-person/t-org-contact-person.component';
import TOrgContactPersonService from '@/entities/t-org-contact-person/t-org-contact-person.service';
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
	describe('TOrgContactPerson Management Component', () => {
		let wrapper: Wrapper<TOrgContactPersonClass>;
		let comp: TOrgContactPersonClass;
		let tOrgContactPersonServiceStub: SinonStubbedInstance<TOrgContactPersonService>;

		beforeEach(() => {
			tOrgContactPersonServiceStub = sinon.createStubInstance<TOrgContactPersonService>(TOrgContactPersonService);
			tOrgContactPersonServiceStub.retrieve.resolves({ headers: {} });

			wrapper = shallowMount<TOrgContactPersonClass>(TOrgContactPersonComponent, {
				store,
				i18n,
				localVue,
				stubs: { bModal: bModalStub as any },
				provide: {
					tOrgContactPersonService: () => tOrgContactPersonServiceStub,
					alertService: () => new AlertService(),
				},
			});
			comp = wrapper.vm;
		});

		it('Should call load all on init', async () => {
			// GIVEN
			tOrgContactPersonServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

			// WHEN
			comp.retrieveAllTOrgContactPersons();
			await comp.$nextTick();

			// THEN
			expect(tOrgContactPersonServiceStub.retrieve.called).toBeTruthy();
			expect(comp.tOrgContactPeople[0]).toEqual(expect.objectContaining({ id: 123 }));
		});
		it('Should call delete service on confirmDelete', async () => {
			// GIVEN
			tOrgContactPersonServiceStub.delete.resolves({});

			// WHEN
			comp.prepareRemove({ id: 123 });
			expect(tOrgContactPersonServiceStub.retrieve.callCount).toEqual(1);

			comp.removeTOrgContactPerson();
			await comp.$nextTick();

			// THEN
			expect(tOrgContactPersonServiceStub.delete.called).toBeTruthy();
			expect(tOrgContactPersonServiceStub.retrieve.callCount).toEqual(2);
		});
	});
});
