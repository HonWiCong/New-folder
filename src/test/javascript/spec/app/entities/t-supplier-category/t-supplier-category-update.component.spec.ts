/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TSupplierCategoryUpdateComponent from '@/entities/t-supplier-category/t-supplier-category-update.vue';
import TSupplierCategoryClass from '@/entities/t-supplier-category/t-supplier-category-update.component';
import TSupplierCategoryService from '@/entities/t-supplier-category/t-supplier-category.service';

import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('TSupplierCategory Management Update Component', () => {
    let wrapper: Wrapper<TSupplierCategoryClass>;
    let comp: TSupplierCategoryClass;
    let tSupplierCategoryServiceStub: SinonStubbedInstance<TSupplierCategoryService>;

    beforeEach(() => {
      tSupplierCategoryServiceStub = sinon.createStubInstance<TSupplierCategoryService>(TSupplierCategoryService);

      wrapper = shallowMount<TSupplierCategoryClass>(TSupplierCategoryUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          tSupplierCategoryService: () => tSupplierCategoryServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.tSupplierCategory = entity;
        tSupplierCategoryServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tSupplierCategoryServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tSupplierCategory = entity;
        tSupplierCategoryServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tSupplierCategoryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTSupplierCategory = { id: 123 };
        tSupplierCategoryServiceStub.find.resolves(foundTSupplierCategory);
        tSupplierCategoryServiceStub.retrieve.resolves([foundTSupplierCategory]);

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
