/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TCountryCodeDetailComponent from '@/entities/t-country-code/t-country-code-details.vue';
import TCountryCodeClass from '@/entities/t-country-code/t-country-code-details.component';
import TCountryCodeService from '@/entities/t-country-code/t-country-code.service';
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
  describe('TCountryCode Management Detail Component', () => {
    let wrapper: Wrapper<TCountryCodeClass>;
    let comp: TCountryCodeClass;
    let tCountryCodeServiceStub: SinonStubbedInstance<TCountryCodeService>;

    beforeEach(() => {
      tCountryCodeServiceStub = sinon.createStubInstance<TCountryCodeService>(TCountryCodeService);

      wrapper = shallowMount<TCountryCodeClass>(TCountryCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tCountryCodeService: () => tCountryCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTCountryCode = { id: 123 };
        tCountryCodeServiceStub.find.resolves(foundTCountryCode);

        // WHEN
        comp.retrieveTCountryCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tCountryCode).toBe(foundTCountryCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTCountryCode = { id: 123 };
        tCountryCodeServiceStub.find.resolves(foundTCountryCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tCountryCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tCountryCode).toBe(foundTCountryCode);
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
