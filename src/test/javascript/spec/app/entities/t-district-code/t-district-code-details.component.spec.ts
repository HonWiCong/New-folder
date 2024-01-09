/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TDistrictCodeDetailComponent from '@/entities/t-district-code/t-district-code-details.vue';
import TDistrictCodeClass from '@/entities/t-district-code/t-district-code-details.component';
import TDistrictCodeService from '@/entities/t-district-code/t-district-code.service';
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
  describe('TDistrictCode Management Detail Component', () => {
    let wrapper: Wrapper<TDistrictCodeClass>;
    let comp: TDistrictCodeClass;
    let tDistrictCodeServiceStub: SinonStubbedInstance<TDistrictCodeService>;

    beforeEach(() => {
      tDistrictCodeServiceStub = sinon.createStubInstance<TDistrictCodeService>(TDistrictCodeService);

      wrapper = shallowMount<TDistrictCodeClass>(TDistrictCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tDistrictCodeService: () => tDistrictCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTDistrictCode = { id: 123 };
        tDistrictCodeServiceStub.find.resolves(foundTDistrictCode);

        // WHEN
        comp.retrieveTDistrictCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tDistrictCode).toBe(foundTDistrictCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTDistrictCode = { id: 123 };
        tDistrictCodeServiceStub.find.resolves(foundTDistrictCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tDistrictCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tDistrictCode).toBe(foundTDistrictCode);
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
