/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TSectorCodeDetailComponent from '@/entities/t-sector-code/t-sector-code-details.vue';
import TSectorCodeClass from '@/entities/t-sector-code/t-sector-code-details.component';
import TSectorCodeService from '@/entities/t-sector-code/t-sector-code.service';
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
  describe('TSectorCode Management Detail Component', () => {
    let wrapper: Wrapper<TSectorCodeClass>;
    let comp: TSectorCodeClass;
    let tSectorCodeServiceStub: SinonStubbedInstance<TSectorCodeService>;

    beforeEach(() => {
      tSectorCodeServiceStub = sinon.createStubInstance<TSectorCodeService>(TSectorCodeService);

      wrapper = shallowMount<TSectorCodeClass>(TSectorCodeDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { tSectorCodeService: () => tSectorCodeServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTSectorCode = { id: 123 };
        tSectorCodeServiceStub.find.resolves(foundTSectorCode);

        // WHEN
        comp.retrieveTSectorCode(123);
        await comp.$nextTick();

        // THEN
        expect(comp.tSectorCode).toBe(foundTSectorCode);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTSectorCode = { id: 123 };
        tSectorCodeServiceStub.find.resolves(foundTSectorCode);

        // WHEN
        comp.beforeRouteEnter({ params: { tSectorCodeId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.tSectorCode).toBe(foundTSectorCode);
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
