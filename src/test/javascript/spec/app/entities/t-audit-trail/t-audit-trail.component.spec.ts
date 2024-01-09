/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TAuditTrailComponent from '@/entities/t-audit-trail/t-audit-trail.vue';
import TAuditTrailClass from '@/entities/t-audit-trail/t-audit-trail.component';
import TAuditTrailService from '@/entities/t-audit-trail/t-audit-trail.service';
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
  describe('TAuditTrail Management Component', () => {
    let wrapper: Wrapper<TAuditTrailClass>;
    let comp: TAuditTrailClass;
    let tAuditTrailServiceStub: SinonStubbedInstance<TAuditTrailService>;

    beforeEach(() => {
      tAuditTrailServiceStub = sinon.createStubInstance<TAuditTrailService>(TAuditTrailService);
      tAuditTrailServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TAuditTrailClass>(TAuditTrailComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tAuditTrailService: () => tAuditTrailServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tAuditTrailServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTAuditTrails();
      await comp.$nextTick();

      // THEN
      expect(tAuditTrailServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tAuditTrails[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tAuditTrailServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tAuditTrailServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTAuditTrail();
      await comp.$nextTick();

      // THEN
      expect(tAuditTrailServiceStub.delete.called).toBeTruthy();
      expect(tAuditTrailServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
