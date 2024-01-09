/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TTitleCodeComponent from '@/entities/t-title-code/t-title-code.vue';
import TTitleCodeClass from '@/entities/t-title-code/t-title-code.component';
import TTitleCodeService from '@/entities/t-title-code/t-title-code.service';
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
  describe('TTitleCode Management Component', () => {
    let wrapper: Wrapper<TTitleCodeClass>;
    let comp: TTitleCodeClass;
    let tTitleCodeServiceStub: SinonStubbedInstance<TTitleCodeService>;

    beforeEach(() => {
      tTitleCodeServiceStub = sinon.createStubInstance<TTitleCodeService>(TTitleCodeService);
      tTitleCodeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TTitleCodeClass>(TTitleCodeComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tTitleCodeService: () => tTitleCodeServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tTitleCodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTTitleCodes();
      await comp.$nextTick();

      // THEN
      expect(tTitleCodeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tTitleCodes[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tTitleCodeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tTitleCodeServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTTitleCode();
      await comp.$nextTick();

      // THEN
      expect(tTitleCodeServiceStub.delete.called).toBeTruthy();
      expect(tTitleCodeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
