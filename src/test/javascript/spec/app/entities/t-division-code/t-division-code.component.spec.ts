/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TDivisionCodeComponent from '@/entities/t-division-code/t-division-code.vue';
import TDivisionCodeClass from '@/entities/t-division-code/t-division-code.component';
import TDivisionCodeService from '@/entities/t-division-code/t-division-code.service';
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
  describe('TDivisionCode Management Component', () => {
    let wrapper: Wrapper<TDivisionCodeClass>;
    let comp: TDivisionCodeClass;
    let tDivisionCodeServiceStub: SinonStubbedInstance<TDivisionCodeService>;

    beforeEach(() => {
      tDivisionCodeServiceStub = sinon.createStubInstance<TDivisionCodeService>(TDivisionCodeService);
      tDivisionCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TDivisionCodeClass>(TDivisionCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tDivisionCodeService: () => tDivisionCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tDivisionCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTDivisionCodes();
      await comp.$nextTick();

      // THEN
      expect(tDivisionCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tDivisionCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tDivisionCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tDivisionCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTDivisionCode();
      await comp.$nextTick();

      // THEN
      expect(tDivisionCodeServiceStub.delete.called).toBeTruthy();
      expect(tDivisionCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
