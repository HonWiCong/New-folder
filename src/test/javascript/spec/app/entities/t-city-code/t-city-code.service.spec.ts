/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import TCityCodeService from '@/entities/t-city-code/t-city-code.service';
import { TCityCode } from '@/shared/model/t-city-code.model';

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
	describe('TCityCode Service', () => {
		let service: TCityCodeService;
		let elemDefault;
		let currentDate: Date;

		beforeEach(() => {
			service = new TCityCodeService();
			currentDate = new Date();
			elemDefault = new TCityCode(123, 'AAAAAAA', 'AAAAAAA', 0, currentDate, 0, currentDate);
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

			it('should create a TCityCode', async () => {
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

			it('should not create a TCityCode', async () => {
				axiosStub.post.rejects(error);

				return service
					.create({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should update a TCityCode', async () => {
				const returnedFromService = Object.assign(
					{
						cityCode: 'BBBBBB',
						cityName: 'BBBBBB',
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

			it('should not update a TCityCode', async () => {
				axiosStub.put.rejects(error);

				return service
					.update({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should partial update a TCityCode', async () => {
				const patchObject = Object.assign(
					{
						cityCode: 'BBBBBB',
						cityName: 'BBBBBB',
						enteredDate: dayjs(currentDate).format(DATE_TIME_FORMAT),
						modifiedBy: 1,
					},
					new TCityCode()
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

			it('should not partial update a TCityCode', async () => {
				axiosStub.patch.rejects(error);

				return service
					.partialUpdate({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should return a list of TCityCode', async () => {
				const returnedFromService = Object.assign(
					{
						cityCode: 'BBBBBB',
						cityName: 'BBBBBB',
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
				return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
					expect(res).toContainEqual(expected);
				});
			});

			it('should not return a list of TCityCode', async () => {
				axiosStub.get.rejects(error);

				return service
					.retrieve()
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should delete a TCityCode', async () => {
				axiosStub.delete.resolves({ ok: true });
				return service.delete(123).then(res => {
					expect(res.ok).toBeTruthy();
				});
			});

			it('should not delete a TCityCode', async () => {
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
