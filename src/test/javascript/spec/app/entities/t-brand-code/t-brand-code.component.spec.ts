/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TBrandCodeComponent from '@/entities/t-brand-code/t-brand-code.vue';
import TBrandCodeClass from '@/entities/t-brand-code/t-brand-code.component';
import TBrandCodeService from '@/entities/t-brand-code/t-brand-code.service';
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
  describe('TBrandCode Management Component', () => {
    let wrapper: Wrapper<TBrandCodeClass>;
    let comp: TBrandCodeClass;
    let tBrandCodeServiceStub: SinonStubbedInstance<TBrandCodeService>;

    beforeEach(() => {
      tBrandCodeServiceStub = sinon.createStubInstance<TBrandCodeService>(TBrandCodeService);
      tBrandCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TBrandCodeClass>(TBrandCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tBrandCodeService: () => tBrandCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tBrandCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTBrandCodes();
      await comp.$nextTick();

      // THEN
      expect(tBrandCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tBrandCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tBrandCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tBrandCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTBrandCode();
      await comp.$nextTick();

      // THEN
      expect(tBrandCodeServiceStub.delete.called).toBeTruthy();
      expect(tBrandCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
