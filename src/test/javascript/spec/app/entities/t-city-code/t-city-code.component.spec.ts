/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TCityCodeComponent from '@/entities/t-city-code/t-city-code.vue';
import TCityCodeClass from '@/entities/t-city-code/t-city-code.component';
import TCityCodeService from '@/entities/t-city-code/t-city-code.service';
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
  describe('TCityCode Management Component', () => {
    let wrapper: Wrapper<TCityCodeClass>;
    let comp: TCityCodeClass;
    let tCityCodeServiceStub: SinonStubbedInstance<TCityCodeService>;

    beforeEach(() => {
      tCityCodeServiceStub = sinon.createStubInstance<TCityCodeService>(TCityCodeService);
      tCityCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TCityCodeClass>(TCityCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tCityCodeService: () => tCityCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tCityCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTCityCodes();
      await comp.$nextTick();

      // THEN
      expect(tCityCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tCityCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tCityCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tCityCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTCityCode();
      await comp.$nextTick();

      // THEN
      expect(tCityCodeServiceStub.delete.called).toBeTruthy();
      expect(tCityCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
