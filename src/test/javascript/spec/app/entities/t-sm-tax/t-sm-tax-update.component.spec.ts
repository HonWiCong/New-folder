/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TSmTaxUpdateComponent from '@/entities/t-sm-tax/t-sm-tax-update.vue';
import TSmTaxClass from '@/entities/t-sm-tax/t-sm-tax-update.component';
import TSmTaxService from '@/entities/t-sm-tax/t-sm-tax.service';

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
  describe('TSmTax Management Update Component', () => {
    let wrapper: Wrapper<TSmTaxClass>;
    let comp: TSmTaxClass;
    let tSmTaxServiceStub: SinonStubbedInstance<TSmTaxService>;

    beforeEach(() => {
      tSmTaxServiceStub = sinon.createStubInstance<TSmTaxService>(TSmTaxService);

      wrapper = shallowMount<TSmTaxClass>(TSmTaxUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          tSmTaxService: () => tSmTaxServiceStub,
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
        comp.tSmTax = entity;
        tSmTaxServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tSmTaxServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tSmTax = entity;
        tSmTaxServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tSmTaxServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTSmTax = { id: 123 };
        tSmTaxServiceStub.find.resolves(foundTSmTax);
        tSmTaxServiceStub.retrieve.resolves([foundTSmTax]);

        // WHEN
        comp.beforeRouteEnter({ params: { tSmTaxId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tSmTax).toBe(foundTSmTax);
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
