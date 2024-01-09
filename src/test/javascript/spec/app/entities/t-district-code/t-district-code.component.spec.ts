/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TDistrictCodeComponent from '@/entities/t-district-code/t-district-code.vue';
import TDistrictCodeClass from '@/entities/t-district-code/t-district-code.component';
import TDistrictCodeService from '@/entities/t-district-code/t-district-code.service';
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
  describe('TDistrictCode Management Component', () => {
    let wrapper: Wrapper<TDistrictCodeClass>;
    let comp: TDistrictCodeClass;
    let tDistrictCodeServiceStub: SinonStubbedInstance<TDistrictCodeService>;

    beforeEach(() => {
      tDistrictCodeServiceStub = sinon.createStubInstance<TDistrictCodeService>(TDistrictCodeService);
      tDistrictCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TDistrictCodeClass>(TDistrictCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tDistrictCodeService: () => tDistrictCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tDistrictCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTDistrictCodes();
      await comp.$nextTick();

      // THEN
      expect(tDistrictCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tDistrictCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tDistrictCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tDistrictCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTDistrictCode();
      await comp.$nextTick();

      // THEN
      expect(tDistrictCodeServiceStub.delete.called).toBeTruthy();
      expect(tDistrictCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
