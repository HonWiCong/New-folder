/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TStateCodeComponent from '@/entities/t-state-code/t-state-code.vue';
import TStateCodeClass from '@/entities/t-state-code/t-state-code.component';
import TStateCodeService from '@/entities/t-state-code/t-state-code.service';
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
  describe('TStateCode Management Component', () => {
    let wrapper: Wrapper<TStateCodeClass>;
    let comp: TStateCodeClass;
    let tStateCodeServiceStub: SinonStubbedInstance<TStateCodeService>;

    beforeEach(() => {
      tStateCodeServiceStub = sinon.createStubInstance<TStateCodeService>(TStateCodeService);
      tStateCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TStateCodeClass>(TStateCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tStateCodeService: () => tStateCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tStateCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTStateCodes();
      await comp.$nextTick();

      // THEN
      expect(tStateCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tStateCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tStateCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tStateCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTStateCode();
      await comp.$nextTick();

      // THEN
      expect(tStateCodeServiceStub.delete.called).toBeTruthy();
      expect(tStateCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
