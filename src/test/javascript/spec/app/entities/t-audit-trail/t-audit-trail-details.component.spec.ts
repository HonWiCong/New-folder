/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TAuditTrailDetailComponent from '@/entities/t-audit-trail/t-audit-trail-details.vue';
import TAuditTrailClass from '@/entities/t-audit-trail/t-audit-trail-details.component';
import TAuditTrailService from '@/entities/t-audit-trail/t-audit-trail.service';
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
  describe('TAuditTrail Management Detail Component', () => {
    let wrapper: Wrapper<TAuditTrailClass>;
    let comp: TAuditTrailClass;
    let tAuditTrailServiceStub: SinonStubbedInstance<TAuditTrailService>;

    beforeEach(() => {
      tAuditTrailServiceStub = sinon.createStubInstance<TAuditTrailService>(TAuditTrailService);

      wrapper = shallowMount<TAuditTrailClass>(TAuditTrailDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tAuditTrailService: () => tAuditTrailServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTAuditTrail = { id: 123 };
        tAuditTrailServiceStub.find.resolves(foundTAuditTrail);

        // WHEN
        comp.retrieveTAuditTrail(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tAuditTrail).toBe(foundTAuditTrail);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTAuditTrail = { id: 123 };
        tAuditTrailServiceStub.find.resolves(foundTAuditTrail);

        // WHEN
        comp.beforeRouteEnter({ params: { tAuditTrailId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tAuditTrail).toBe(foundTAuditTrail);
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
