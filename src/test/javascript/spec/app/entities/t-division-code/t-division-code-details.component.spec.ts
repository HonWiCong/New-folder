/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TDivisionCodeDetailComponent from '@/entities/t-division-code/t-division-code-details.vue';
import TDivisionCodeClass from '@/entities/t-division-code/t-division-code-details.component';
import TDivisionCodeService from '@/entities/t-division-code/t-division-code.service';
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
  describe('TDivisionCode Management Detail Component', () => {
    let wrapper: Wrapper<TDivisionCodeClass>;
    let comp: TDivisionCodeClass;
    let tDivisionCodeServiceStub: SinonStubbedInstance<TDivisionCodeService>;

    beforeEach(() => {
      tDivisionCodeServiceStub = sinon.createStubInstance<TDivisionCodeService>(TDivisionCodeService);

      wrapper = shallowMount<TDivisionCodeClass>(TDivisionCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tDivisionCodeService: () => tDivisionCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTDivisionCode = { id: 123 };
        tDivisionCodeServiceStub.find.resolves(foundTDivisionCode);

        // WHEN
        comp.retrieveTDivisionCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tDivisionCode).toBe(foundTDivisionCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTDivisionCode = { id: 123 };
        tDivisionCodeServiceStub.find.resolves(foundTDivisionCode);

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
