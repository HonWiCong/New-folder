/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TSectorCodeComponent from '@/entities/t-sector-code/t-sector-code.vue';
import TSectorCodeClass from '@/entities/t-sector-code/t-sector-code.component';
import TSectorCodeService from '@/entities/t-sector-code/t-sector-code.service';
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
  describe('TSectorCode Management Component', () => {
    let wrapper: Wrapper<TSectorCodeClass>;
    let comp: TSectorCodeClass;
    let tSectorCodeServiceStub: SinonStubbedInstance<TSectorCodeService>;

    beforeEach(() => {
      tSectorCodeServiceStub = sinon.createStubInstance<TSectorCodeService>(TSectorCodeService);
      tSectorCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TSectorCodeClass>(TSectorCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tSectorCodeService: () => tSectorCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tSectorCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTSectorCodes();
      await comp.$nextTick();

      // THEN
      expect(tSectorCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tSectorCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tSectorCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tSectorCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTSectorCode();
      await comp.$nextTick();

      // THEN
      expect(tSectorCodeServiceStub.delete.called).toBeTruthy();
      expect(tSectorCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
