/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TStateCodeDetailComponent from '@/entities/t-state-code/t-state-code-details.vue';
import TStateCodeClass from '@/entities/t-state-code/t-state-code-details.component';
import TStateCodeService from '@/entities/t-state-code/t-state-code.service';
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
  describe('TStateCode Management Detail Component', () => {
    let wrapper: Wrapper<TStateCodeClass>;
    let comp: TStateCodeClass;
    let tStateCodeServiceStub: SinonStubbedInstance<TStateCodeService>;

    beforeEach(() => {
      tStateCodeServiceStub = sinon.createStubInstance<TStateCodeService>(TStateCodeService);

      wrapper = shallowMount<TStateCodeClass>(TStateCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tStateCodeService: () => tStateCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTStateCode = { id: 123 };
        tStateCodeServiceStub.find.resolves(foundTStateCode);

        // WHEN
        comp.retrieveTStateCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tStateCode).toBe(foundTStateCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTStateCode = { id: 123 };
        tStateCodeServiceStub.find.resolves(foundTStateCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tStateCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tStateCode).toBe(foundTStateCode);
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
