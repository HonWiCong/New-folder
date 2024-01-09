/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TBrandCodeDetailComponent from '@/entities/t-brand-code/t-brand-code-details.vue';
import TBrandCodeClass from '@/entities/t-brand-code/t-brand-code-details.component';
import TBrandCodeService from '@/entities/t-brand-code/t-brand-code.service';
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
  describe('TBrandCode Management Detail Component', () => {
    let wrapper: Wrapper<TBrandCodeClass>;
    let comp: TBrandCodeClass;
    let tBrandCodeServiceStub: SinonStubbedInstance<TBrandCodeService>;

    beforeEach(() => {
      tBrandCodeServiceStub = sinon.createStubInstance<TBrandCodeService>(TBrandCodeService);

      wrapper = shallowMount<TBrandCodeClass>(TBrandCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tBrandCodeService: () => tBrandCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTBrandCode = { id: 123 };
        tBrandCodeServiceStub.find.resolves(foundTBrandCode);

        // WHEN
        comp.retrieveTBrandCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tBrandCode).toBe(foundTBrandCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTBrandCode = { id: 123 };
        tBrandCodeServiceStub.find.resolves(foundTBrandCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tBrandCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tBrandCode).toBe(foundTBrandCode);
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
