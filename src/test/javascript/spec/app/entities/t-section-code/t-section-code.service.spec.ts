/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import TSectionCodeService from '@/entities/t-section-code/t-section-code.service';
import { TSectionCode } from '@/shared/model/t-section-code.model';
import { SectSainsSubsidiary } from '@/shared/model/enumerations/sect-sains-subsidiary.model';
import { SectPapActive } from '@/shared/model/enumerations/sect-pap-active.model';

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
	describe('TSectionCode Service', () => {
		let service: TSectionCodeService;
		let elemDefault;
		let currentDate: Date;

		beforeEach(() => {
			service = new TSectionCodeService();
			currentDate = new Date();
			elemDefault = new TSectionCode(
				123,
				'AAAAAAA',
				0,
				0,
				SectSainsSubsidiary.S,
				'AAAAAAA',
				'AAAAAAA',
				'AAAAAAA',
				'AAAAAAA',
				SectPapActive.A,
				'AAAAAAA',
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

			it('should create a TSectionCode', async () => {
				const returnedFromService = Object.assign(
					{
						id: 123,
						enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
						modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
					},
					elemDefault
				);
				const expected = Object.assign(
					{
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

			it('should not create a TSectionCode', async () => {
				axiosStub.post.rejects(error);

				return service
					.create({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should update a TSectionCode', async () => {
				const returnedFromService = Object.assign(
					{
						sectName: 'BBBBBB',
						sectHeadid: 1,
						sectActingHeadid: 1,
						sectSainsSubsidiary: 'BBBBBB',
						sectHrpayId: 'BBBBBB',
						sectPapId: 'BBBBBB',
						sectPapCode: 'BBBBBB',
						sectPapCompany: 'BBBBBB',
						sectPapActive: 'BBBBBB',
						sectCcCode: 'BBBBBB',
						enteredBy: 1,
						enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
						modifiedBy: 1,
						modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
					},
					elemDefault
				);

				const expected = Object.assign(
					{
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

			it('should not update a TSectionCode', async () => {
				axiosStub.put.rejects(error);

				return service
					.update({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should partial update a TSectionCode', async () => {
				const patchObject = Object.assign(
					{
						sectName: 'BBBBBB',
						sectActingHeadid: 1,
						sectHrpayId: 'BBBBBB',
						sectPapCompany: 'BBBBBB',
						sectPapActive: 'BBBBBB',
						sectCcCode: 'BBBBBB',
						enteredBy: 1,
					},
					new TSectionCode()
				);
				const returnedFromService = Object.assign(patchObject, elemDefault);

				const expected = Object.assign(
					{
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

			it('should not partial update a TSectionCode', async () => {
				axiosStub.patch.rejects(error);

				return service
					.partialUpdate({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should return a list of TSectionCode', async () => {
				const returnedFromService = Object.assign(
					{
						sectName: 'BBBBBB',
						sectHeadid: 1,
						sectActingHeadid: 1,
						sectSainsSubsidiary: 'BBBBBB',
						sectHrpayId: 'BBBBBB',
						sectPapId: 'BBBBBB',
						sectPapCode: 'BBBBBB',
						sectPapCompany: 'BBBBBB',
						sectPapActive: 'BBBBBB',
						sectCcCode: 'BBBBBB',
						enteredBy: 1,
						enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
						modifiedBy: 1,
						modifiedDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
					},
					elemDefault
				);
				const expected = Object.assign(
					{
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

			it('should not return a list of TSectionCode', async () => {
				axiosStub.get.rejects(error);

				return service
					.retrieve()
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should delete a TSectionCode', async () => {
				axiosStub.delete.resolves({ ok: true });
				return service.delete(123).then(res => {
					expect(res.ok).toBeTruthy();
				});
			});

			it('should not delete a TSectionCode', async () => {
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
