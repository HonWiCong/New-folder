/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TOrgContactPersonDetailComponent from '@/entities/t-org-contact-person/t-org-contact-person-details.vue';
import TOrgContactPersonClass from '@/entities/t-org-contact-person/t-org-contact-person-details.component';
import TOrgContactPersonService from '@/entities/t-org-contact-person/t-org-contact-person.service';
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
  describe('TOrgContactPerson Management Detail Component', () => {
    let wrapper: Wrapper<TOrgContactPersonClass>;
    let comp: TOrgContactPersonClass;
    let tOrgContactPersonServiceStub: SinonStubbedInstance<TOrgContactPersonService>;

    beforeEach(() => {
      tOrgContactPersonServiceStub = sinon.createStubInstance<TOrgContactPersonService>(TOrgContactPersonService);

      wrapper = shallowMount<TOrgContactPersonClass>(TOrgContactPersonDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tOrgContactPersonService: () => tOrgContactPersonServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTOrgContactPerson = { id: 123 };
        tOrgContactPersonServiceStub.find.resolves(foundTOrgContactPerson);

        // WHEN
        comp.retrieveTOrgContactPerson(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tOrgContactPerson).toBe(foundTOrgContactPerson);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTOrgContactPerson = { id: 123 };
        tOrgContactPersonServiceStub.find.resolves(foundTOrgContactPerson);

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
