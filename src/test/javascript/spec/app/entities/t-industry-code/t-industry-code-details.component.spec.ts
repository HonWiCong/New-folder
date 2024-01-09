/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TIndustryCodeDetailComponent from '@/entities/t-industry-code/t-industry-code-details.vue';
import TIndustryCodeClass from '@/entities/t-industry-code/t-industry-code-details.component';
import TIndustryCodeService from '@/entities/t-industry-code/t-industry-code.service';
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
  describe('TIndustryCode Management Detail Component', () => {
    let wrapper: Wrapper<TIndustryCodeClass>;
    let comp: TIndustryCodeClass;
    let tIndustryCodeServiceStub: SinonStubbedInstance<TIndustryCodeService>;

    beforeEach(() => {
      tIndustryCodeServiceStub = sinon.createStubInstance<TIndustryCodeService>(TIndustryCodeService);

      wrapper = shallowMount<TIndustryCodeClass>(TIndustryCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tIndustryCodeService: () => tIndustryCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTIndustryCode = { id: 123 };
        tIndustryCodeServiceStub.find.resolves(foundTIndustryCode);

        // WHEN
        comp.retrieveTIndustryCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tIndustryCode).toBe(foundTIndustryCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTIndustryCode = { id: 123 };
        tIndustryCodeServiceStub.find.resolves(foundTIndustryCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tIndustryCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tIndustryCode).toBe(foundTIndustryCode);
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
