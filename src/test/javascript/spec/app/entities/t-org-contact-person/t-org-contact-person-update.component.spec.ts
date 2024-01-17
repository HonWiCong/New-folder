/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TOrgContactPersonUpdateComponent from '@/entities/t-org-contact-person/t-org-contact-person-update.vue';
import TOrgContactPersonClass from '@/entities/t-org-contact-person/t-org-contact-person-update.component';
import TOrgContactPersonService from '@/entities/t-org-contact-person/t-org-contact-person.service';

import TOrganizationService from '@/entities/t-organization/t-organization.service';
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
	describe('TOrgContactPerson Management Update Component', () => {
		let wrapper: Wrapper<TOrgContactPersonClass>;
		let comp: TOrgContactPersonClass;
		let tOrgContactPersonServiceStub: SinonStubbedInstance<TOrgContactPersonService>;

		beforeEach(() => {
			tOrgContactPersonServiceStub = sinon.createStubInstance<TOrgContactPersonService>(TOrgContactPersonService);

			wrapper = shallowMount<TOrgContactPersonClass>(TOrgContactPersonUpdateComponent, {
				store,
				i18n,
				localVue,
				router,
				provide: {
					tOrgContactPersonService: () => tOrgContactPersonServiceStub,
					alertService: () => new AlertService(),

					tOrganizationService: () =>
						sinon.createStubInstance<TOrganizationService>(TOrganizationService, {
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
				comp.tOrgContactPerson = entity;
				tOrgContactPersonServiceStub.update.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tOrgContactPersonServiceStub.update.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});

			it('Should call create service on save for new entity', async () => {
				// GIVEN
				const entity = {};
				comp.tOrgContactPerson = entity;
				tOrgContactPersonServiceStub.create.resolves(entity);

				// WHEN
				comp.save();
				await comp.$nextTick();

				// THEN
				expect(tOrgContactPersonServiceStub.create.calledWith(entity)).toBeTruthy();
				expect(comp.isSaving).toEqual(false);
			});
		});

		describe('Before route enter', () => {
			it('Should retrieve data', async () => {
				// GIVEN
				const foundTOrgContactPerson = { id: 123 };
				tOrgContactPersonServiceStub.find.resolves(foundTOrgContactPerson);
				tOrgContactPersonServiceStub.retrieve.resolves([foundTOrgContactPerson]);

				// WHEN
				comp.beforeRouteEnter({ params: { tOrgContactPersonId: 123 } }, null, cb => cb(comp));
				await comp.$nextTick();

				// THEN
				expect(comp.tOrgContactPerson).toBe(foundTOrgContactPerson);
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
