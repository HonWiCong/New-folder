/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TCityCodeDetailComponent from '@/entities/t-city-code/t-city-code-details.vue';
import TCityCodeClass from '@/entities/t-city-code/t-city-code-details.component';
import TCityCodeService from '@/entities/t-city-code/t-city-code.service';
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
  describe('TCityCode Management Detail Component', () => {
    let wrapper: Wrapper<TCityCodeClass>;
    let comp: TCityCodeClass;
    let tCityCodeServiceStub: SinonStubbedInstance<TCityCodeService>;

    beforeEach(() => {
      tCityCodeServiceStub = sinon.createStubInstance<TCityCodeService>(TCityCodeService);

      wrapper = shallowMount<TCityCodeClass>(TCityCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tCityCodeService: () => tCityCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTCityCode = { id: 123 };
        tCityCodeServiceStub.find.resolves(foundTCityCode);

        // WHEN
        comp.retrieveTCityCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tCityCode).toBe(foundTCityCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTCityCode = { id: 123 };
        tCityCodeServiceStub.find.resolves(foundTCityCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tCityCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tCityCode).toBe(foundTCityCode);
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
