/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from '@/shared/date/filters';
import TOrganizationService from '@/entities/t-organization/t-organization.service';
import { TOrganization } from '@/shared/model/t-organization.model';

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
  describe('TOrganization Service', () => {
    let service: TOrganizationService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new TOrganizationService();
      currentDate = new Date();
      elemDefault = new TOrganization(
        123,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
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
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        currentDate,
        0,
        currentDate,
        0,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            orgCompanyGstRegDate: dayjs(currentDate).format(DATE_FORMAT),
            orgCompanyGstDeregDate: dayjs(currentDate).format(DATE_FORMAT),
            confirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
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

      it('should create a TOrganization', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            orgCompanyGstRegDate: dayjs(currentDate).format(DATE_FORMAT),
            orgCompanyGstDeregDate: dayjs(currentDate).format(DATE_FORMAT),
            confirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            orgCompanyGstRegDate: currentDate,
            orgCompanyGstDeregDate: currentDate,
            confirmedDate: currentDate,
            enteredDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a TOrganization', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a TOrganization', async () => {
        const returnedFromService = Object.assign(
          {
            orgHqid: 1,
            orgHqBr: 'BBBBBB',
            orgCode: 'BBBBBB',
            orgBrn: 'BBBBBB',
            orgPtaxid: 1,
            orgDefaultTaxCode: 'BBBBBB',
            orgCompanyGstNo: 'BBBBBB',
            orgCompanyGstRegDate: dayjs(currentDate).format(DATE_FORMAT),
            orgCompanyGstDeregDate: dayjs(currentDate).format(DATE_FORMAT),
            orgPoTaxInclusive: 'BBBBBB',
            orgName: 'BBBBBB',
            orgNameOther: 'BBBBBB',
            orgShortname: 'BBBBBB',
            orgAddress: 'BBBBBB',
            orgShippingAddress: 'BBBBBB',
            orgBillingAddress: 'BBBBBB',
            orgPostcode: 'BBBBBB',
            orgCity: 'BBBBBB',
            orgState: 'BBBBBB',
            orgCountry: 'BBBBBB',
            orgOffPhone1: 'BBBBBB',
            orgOffPhone2: 'BBBBBB',
            orgOffPhone3: 'BBBBBB',
            orgOffFax1: 'BBBBBB',
            orgOffFax2: 'BBBBBB',
            orgCredittermid: 1,
            orgCreditLimit: 1,
            orgAgencyid: 1,
            orgDivision: 'BBBBBB',
            orgDistrict: 'BBBBBB',
            orgWebsite: 'BBBBBB',
            orgEmail: 'BBBBBB',
            orgSupplierCategory: 'BBBBBB',
            orgCurrencyCode: 'BBBBBB',
            orgType: 'BBBBBB',
            orgSectorid: 1,
            orgSector: 'BBBBBB',
            orgIndustry: 'BBBBBB',
            orgSainsGroup: 'BBBBBB',
            orgBumiputra: 'BBBBBB',
            orgUpkReg: 'BBBBBB',
            orgMofReg: 'BBBBBB',
            orgDesignation: 'BBBBBB',
            orgContpersonTitle: 'BBBBBB',
            orgContperson: 'BBBBBB',
            orgDirectline: 'BBBBBB',
            orgContpEmail: 'BBBBBB',
            orgContpHp: 'BBBBBB',
            orgRemarks: 'BBBBBB',
            orgActiveStatus: 'BBBBBB',
            orgCcGc: 'BBBBBB',
            orgCustomerCateCode: 'BBBBBB',
            orgVendorCateCode: 'BBBBBB',
            orgSalesCateCode: 'BBBBBB',
            orgOutstandingBalance: 1,
            orgOutstandingBalanceEx: 1,
            orgCompanyCode: 'BBBBBB',
            orgDcrownPostStatus: 'BBBBBB',
            confirmedBy: 1,
            confirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            enteredBy: 1,
            enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            modifiedBy: 1,
            modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            orgCompanyGstRegDate: currentDate,
            orgCompanyGstDeregDate: currentDate,
            confirmedDate: currentDate,
            enteredDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a TOrganization', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a TOrganization', async () => {
        const patchObject = Object.assign(
          {
            orgHqBr: 'BBBBBB',
            orgBrn: 'BBBBBB',
            orgDefaultTaxCode: 'BBBBBB',
            orgCompanyGstNo: 'BBBBBB',
            orgPoTaxInclusive: 'BBBBBB',
            orgShortname: 'BBBBBB',
            orgShippingAddress: 'BBBBBB',
            orgPostcode: 'BBBBBB',
            orgCity: 'BBBBBB',
            orgCountry: 'BBBBBB',
            orgOffPhone1: 'BBBBBB',
            orgOffPhone3: 'BBBBBB',
            orgCredittermid: 1,
            orgCreditLimit: 1,
            orgWebsite: 'BBBBBB',
            orgEmail: 'BBBBBB',
            orgType: 'BBBBBB',
            orgSector: 'BBBBBB',
            orgUpkReg: 'BBBBBB',
            orgMofReg: 'BBBBBB',
            orgContpersonTitle: 'BBBBBB',
            orgContperson: 'BBBBBB',
            orgContpEmail: 'BBBBBB',
            orgContpHp: 'BBBBBB',
            orgRemarks: 'BBBBBB',
            orgCcGc: 'BBBBBB',
            orgCustomerCateCode: 'BBBBBB',
            orgCompanyCode: 'BBBBBB',
            orgDcrownPostStatus: 'BBBBBB',
          },
          new TOrganization()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            orgCompanyGstRegDate: currentDate,
            orgCompanyGstDeregDate: currentDate,
            confirmedDate: currentDate,
            enteredDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a TOrganization', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of TOrganization', async () => {
        const returnedFromService = Object.assign(
          {
            orgHqid: 1,
            orgHqBr: 'BBBBBB',
            orgCode: 'BBBBBB',
            orgBrn: 'BBBBBB',
            orgPtaxid: 1,
            orgDefaultTaxCode: 'BBBBBB',
            orgCompanyGstNo: 'BBBBBB',
            orgCompanyGstRegDate: dayjs(currentDate).format(DATE_FORMAT),
            orgCompanyGstDeregDate: dayjs(currentDate).format(DATE_FORMAT),
            orgPoTaxInclusive: 'BBBBBB',
            orgName: 'BBBBBB',
            orgNameOther: 'BBBBBB',
            orgShortname: 'BBBBBB',
            orgAddress: 'BBBBBB',
            orgShippingAddress: 'BBBBBB',
            orgBillingAddress: 'BBBBBB',
            orgPostcode: 'BBBBBB',
            orgCity: 'BBBBBB',
            orgState: 'BBBBBB',
            orgCountry: 'BBBBBB',
            orgOffPhone1: 'BBBBBB',
            orgOffPhone2: 'BBBBBB',
            orgOffPhone3: 'BBBBBB',
            orgOffFax1: 'BBBBBB',
            orgOffFax2: 'BBBBBB',
            orgCredittermid: 1,
            orgCreditLimit: 1,
            orgAgencyid: 1,
            orgDivision: 'BBBBBB',
            orgDistrict: 'BBBBBB',
            orgWebsite: 'BBBBBB',
            orgEmail: 'BBBBBB',
            orgSupplierCategory: 'BBBBBB',
            orgCurrencyCode: 'BBBBBB',
            orgType: 'BBBBBB',
            orgSectorid: 1,
            orgSector: 'BBBBBB',
            orgIndustry: 'BBBBBB',
            orgSainsGroup: 'BBBBBB',
            orgBumiputra: 'BBBBBB',
            orgUpkReg: 'BBBBBB',
            orgMofReg: 'BBBBBB',
            orgDesignation: 'BBBBBB',
            orgContpersonTitle: 'BBBBBB',
            orgContperson: 'BBBBBB',
            orgDirectline: 'BBBBBB',
            orgContpEmail: 'BBBBBB',
            orgContpHp: 'BBBBBB',
            orgRemarks: 'BBBBBB',
            orgActiveStatus: 'BBBBBB',
            orgCcGc: 'BBBBBB',
            orgCustomerCateCode: 'BBBBBB',
            orgVendorCateCode: 'BBBBBB',
            orgSalesCateCode: 'BBBBBB',
            orgOutstandingBalance: 1,
            orgOutstandingBalanceEx: 1,
            orgCompanyCode: 'BBBBBB',
            orgDcrownPostStatus: 'BBBBBB',
            confirmedBy: 1,
            confirmedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            enteredBy: 1,
            enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
            modifiedBy: 1,
            modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            orgCompanyGstRegDate: currentDate,
            orgCompanyGstDeregDate: currentDate,
            confirmedDate: currentDate,
            enteredDate: currentDate,
            modifiedDate: currentDate,
          },
          returnedFromService
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of TOrganization', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a TOrganization', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a TOrganization', async () => {
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
