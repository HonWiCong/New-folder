/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TSupplierCategoryComponent from '@/entities/t-supplier-category/t-supplier-category.vue';
import TSupplierCategoryClass from '@/entities/t-supplier-category/t-supplier-category.component';
import TSupplierCategoryService from '@/entities/t-supplier-category/t-supplier-category.service';
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
  describe('TSupplierCategory Management Component', () => {
    let wrapper: Wrapper<TSupplierCategoryClass>;
    let comp: TSupplierCategoryClass;
    let tSupplierCategoryServiceStub: SinonStubbedInstance<TSupplierCategoryService>;

    beforeEach(() => {
      tSupplierCategoryServiceStub = sinon.createStubInstance<TSupplierCategoryService>(TSupplierCategoryService);
      tSupplierCategoryServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TSupplierCategoryClass>(TSupplierCategoryComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          tSupplierCategoryService: () => tSupplierCategoryServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      tSupplierCategoryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTSupplierCategorys();
      await comp.$nextTick();

      // THEN
      expect(tSupplierCategoryServiceStub.retrieve.called).toBeTruthy();
      expect(comp.tSupplierCategories[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      tSupplierCategoryServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(tSupplierCategoryServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTSupplierCategory();
      await comp.$nextTick();

      // THEN
      expect(tSupplierCategoryServiceStub.delete.called).toBeTruthy();
      expect(tSupplierCategoryServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
