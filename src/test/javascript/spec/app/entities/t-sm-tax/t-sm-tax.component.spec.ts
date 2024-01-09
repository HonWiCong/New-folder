/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TSmTaxComponent from '@/entities/t-sm-tax/t-sm-tax.vue';
import TSmTaxClass from '@/entities/t-sm-tax/t-sm-tax.component';
import TSmTaxService from '@/entities/t-sm-tax/t-sm-tax.service';
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
  describe('TSmTax Management Component', () => {
    let wrapper: Wrapper<TSmTaxClass>;
    let comp: TSmTaxClass;
    let tSmTaxServiceStub: SinonStubbedInstance<TSmTaxService>;

    beforeEach(() => {
      tSmTaxServiceStub = sinon.createStubInstance<TSmTaxService>(TSmTaxService);
      tSmTaxServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TSmTaxClass>(TSmTaxComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tSmTaxService: () => tSmTaxServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tSmTaxServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTSmTaxs();
      await comp.$nextTick();

      // THEN
      expect(tSmTaxServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tSmTaxes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tSmTaxServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tSmTaxServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTSmTax();
      await comp.$nextTick();

      // THEN
      expect(tSmTaxServiceStub.delete.called).toBeTruthy();
      expect(tSmTaxServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
