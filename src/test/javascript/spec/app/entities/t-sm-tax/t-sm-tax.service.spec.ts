/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import TSmTaxService from '@/entities/t-sm-tax/t-sm-tax.service';
import { TSmTax } from '@/shared/model/t-sm-tax.model';
import { TaxStatus } from '@/shared/model/enumerations/tax-status.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('TSmTax Service', () => {
    let service: TSmTaxService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new TSmTaxService();
      currentDate = new Date();
      elemDefault = new TSmTax(
        123,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        TaxStatus.Y,
        0,
        currentDate,
        0,
        currentDate,
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            smTaxEnteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxConfirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a TSmTax', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            smTaxEnteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxConfirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            smTaxEnteredDate: currentDate,
            smTaxModifiedDate: currentDate,
            smTaxConfirmedDate: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a TSmTax', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a TSmTax', async () => {
        const returnedFromService = Object.assign(
          {
            smTaxCode: 'BBBBBB',
            smTaxDescription: 'BBBBBB',
            smTaxPercentage: 1,
            smTaxType: 'BBBBBB',
            smTaxGstCode: 'BBBBBB',
            smTaxGstType: 'BBBBBB',
            smTaxCollectedGlCode: 'BBBBBB',
            smTaxCollectedGlDesc: 'BBBBBB',
            smTaxCollectedCostCenter: 'BBBBBB',
            smTaxPaidGlCode: 'BBBBBB',
            smTaxPaidGlDesc: 'BBBBBB',
            smTaxPaidCostCenter: 'BBBBBB',
            smTaxTaxAuthority: 'BBBBBB',
            smTaxStatus: 'BBBBBB',
            smTaxEnteredBy: 1,
            smTaxEnteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxModifiedBy: 1,
            smTaxModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxConfirmedBy: 1,
            smTaxConfirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxNarration: 'BBBBBB',
            smTaxDisplay: 'BBBBBB',
            smTaxRcmFlag: 'BBBBBB',
            smTaxCga: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            smTaxEnteredDate: currentDate,
            smTaxModifiedDate: currentDate,
            smTaxConfirmedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a TSmTax', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a TSmTax', async () => {
        const patchObject = Object.assign(
          {
            smTaxPercentage: 1,
            smTaxGstType: 'BBBBBB',
            smTaxCollectedGlCode: 'BBBBBB',
            smTaxPaidGlCode: 'BBBBBB',
            smTaxPaidCostCenter: 'BBBBBB',
            smTaxTaxAuthority: 'BBBBBB',
            smTaxStatus: 'BBBBBB',
            smTaxEnteredBy: 1,
            smTaxModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxConfirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxNarration: 'BBBBBB',
            smTaxCga: 'BBBBBB',
          },
          new TSmTax()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            smTaxEnteredDate: currentDate,
            smTaxModifiedDate: currentDate,
            smTaxConfirmedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a TSmTax', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of TSmTax', async () => {
        const returnedFromService = Object.assign(
          {
            smTaxCode: 'BBBBBB',
            smTaxDescription: 'BBBBBB',
            smTaxPercentage: 1,
            smTaxType: 'BBBBBB',
            smTaxGstCode: 'BBBBBB',
            smTaxGstType: 'BBBBBB',
            smTaxCollectedGlCode: 'BBBBBB',
            smTaxCollectedGlDesc: 'BBBBBB',
            smTaxCollectedCostCenter: 'BBBBBB',
            smTaxPaidGlCode: 'BBBBBB',
            smTaxPaidGlDesc: 'BBBBBB',
            smTaxPaidCostCenter: 'BBBBBB',
            smTaxTaxAuthority: 'BBBBBB',
            smTaxStatus: 'BBBBBB',
            smTaxEnteredBy: 1,
            smTaxEnteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxModifiedBy: 1,
            smTaxModifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxConfirmedBy: 1,
            smTaxConfirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            smTaxNarration: 'BBBBBB',
            smTaxDisplay: 'BBBBBB',
            smTaxRcmFlag: 'BBBBBB',
            smTaxCga: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            smTaxEnteredDate: currentDate,
            smTaxModifiedDate: currentDate,
            smTaxConfirmedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of TSmTax', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a TSmTax', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a TSmTax', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
