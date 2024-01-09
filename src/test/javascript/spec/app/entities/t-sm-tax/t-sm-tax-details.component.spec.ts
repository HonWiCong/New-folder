/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TSmTaxDetailComponent from '@/entities/t-sm-tax/t-sm-tax-details.vue';
import TSmTaxClass from '@/entities/t-sm-tax/t-sm-tax-details.component';
import TSmTaxService from '@/entities/t-sm-tax/t-sm-tax.service';
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
  describe('TSmTax Management Detail Component', () => {
    let wrapper: Wrapper<TSmTaxClass>;
    let comp: TSmTaxClass;
    let tSmTaxServiceStub: SinonStubbedInstance<TSmTaxService>;

    beforeEach(() => {
      tSmTaxServiceStub = sinon.createStubInstance<TSmTaxService>(TSmTaxService);

      wrapper = shallowMount<TSmTaxClass>(TSmTaxDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tSmTaxService: () => tSmTaxServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTSmTax = { id: 123 };
        tSmTaxServiceStub.find.resolves(foundTSmTax);

        // WHEN
        comp.retrieveTSmTax(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tSmTax).toBe(foundTSmTax);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTSmTax = { id: 123 };
        tSmTaxServiceStub.find.resolves(foundTSmTax);

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
