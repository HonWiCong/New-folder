/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import TDivisionCodeUpdateComponent from '@/entities/t-division-code/t-division-code-update.vue';
import TDivisionCodeClass from '@/entities/t-division-code/t-division-code-update.component';
import TDivisionCodeService from '@/entities/t-division-code/t-division-code.service';

import TCityCodeService from '@/entities/t-city-code/t-city-code.service';

import TDistrictCodeService from '@/entities/t-district-code/t-district-code.service';
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
  describe('TDivisionCode Management Update Component', () => {
    let wrapper: Wrapper<TDivisionCodeClass>;
    let comp: TDivisionCodeClass;
    let tDivisionCodeServiceStub: SinonStubbedInstance<TDivisionCodeService>;

    beforeEach(() => {
      tDivisionCodeServiceStub = sinon.createStubInstance<TDivisionCodeService>(TDivisionCodeService);

      wrapper = shallowMount<TDivisionCodeClass>(TDivisionCodeUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          tDivisionCodeService: () => tDivisionCodeServiceStub,
          alertService: () => new AlertService(),

          tCityCodeService: () =>
            sinon.createStubInstance<TCityCodeService>(TCityCodeService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          tDistrictCodeService: () =>
            sinon.createStubInstance<TDistrictCodeService>(TDistrictCodeService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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
        comp.tDivisionCode = entity;
        tDivisionCodeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tDivisionCodeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.tDivisionCode = entity;
        tDivisionCodeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(tDivisionCodeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTDivisionCode = { id: 123 };
        tDivisionCodeServiceStub.find.resolves(foundTDivisionCode);
        tDivisionCodeServiceStub.retrieve.resolves([foundTDivisionCode]);

        // WHEN
        comp.beforeRouteEnter({ params: { tDivisionCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tDivisionCode).toBe(foundTDivisionCode);
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
