/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TSupplierCategoryDetailComponent from '@/entities/t-supplier-category/t-supplier-category-details.vue';
import TSupplierCategoryClass from '@/entities/t-supplier-category/t-supplier-category-details.component';
import TSupplierCategoryService from '@/entities/t-supplier-category/t-supplier-category.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('TSupplierCategory Management Detail Component', () => {
    let wrapper: Wrapper<TSupplierCategoryClass>;
    let comp: TSupplierCategoryClass;
    let tSupplierCategoryServiceStub: SinonStubbedInstance<TSupplierCategoryService>;

    beforeEach(() => {
      tSupplierCategoryServiceStub = sinon.createStubInstance<TSupplierCategoryService>(TSupplierCategoryService);

      wrapper = shallowMount<TSupplierCategoryClass>(TSupplierCategoryDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tSupplierCategoryService: () => tSupplierCategoryServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTSupplierCategory = { id: 123 };
        tSupplierCategoryServiceStub.find.resolves(foundTSupplierCategory);

        // WHEN
        comp.retrieveTSupplierCategory(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tSupplierCategory).toBe(foundTSupplierCategory);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTSupplierCategory = { id: 123 };
        tSupplierCategoryServiceStub.find.resolves(foundTSupplierCategory);

        // WHEN
        comp.beforeRouteEnter({ params: { tSupplierCategoryId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tSupplierCategory).toBe(foundTSupplierCategory);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
