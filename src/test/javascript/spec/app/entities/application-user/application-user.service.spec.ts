/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ApplicationUserService from '@/entities/application-user/application-user.service';
import { ApplicationUser } from '@/shared/model/application-user.model';

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
	describe('ApplicationUser Service', () => {
		let service: ApplicationUserService;
		let elemDefault;

		beforeEach(() => {
			service = new ApplicationUserService();
			elemDefault = new ApplicationUser(123, 'AAAAAAA', 'AAAAAAA');
		});

		describe('Service methods', () => {
			it('should find an element', async () => {
				const returnedFromService = Object.assign({}, elemDefault);
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

			it('should create a ApplicationUser', async () => {
				const returnedFromService = Object.assign(
					{
						id: 123,
					},
					elemDefault
				);
				const expected = Object.assign({}, returnedFromService);

				axiosStub.post.resolves({ data: returnedFromService });
				return service.create({}).then(res => {
					expect(res).toMatchObject(expected);
				});
			});

			it('should not create a ApplicationUser', async () => {
				axiosStub.post.rejects(error);

				return service
					.create({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should update a ApplicationUser', async () => {
				const returnedFromService = Object.assign(
					{
						ic: 'BBBBBB',
						status: 'BBBBBB',
					},
					elemDefault
				);

				const expected = Object.assign({}, returnedFromService);
				axiosStub.put.resolves({ data: returnedFromService });

				return service.update(expected).then(res => {
					expect(res).toMatchObject(expected);
				});
			});

			it('should not update a ApplicationUser', async () => {
				axiosStub.put.rejects(error);

				return service
					.update({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should partial update a ApplicationUser', async () => {
				const patchObject = Object.assign(
					{
						ic: 'BBBBBB',
						status: 'BBBBBB',
					},
					new ApplicationUser()
				);
				const returnedFromService = Object.assign(patchObject, elemDefault);

				const expected = Object.assign({}, returnedFromService);
				axiosStub.patch.resolves({ data: returnedFromService });

				return service.partialUpdate(patchObject).then(res => {
					expect(res).toMatchObject(expected);
				});
			});

			it('should not partial update a ApplicationUser', async () => {
				axiosStub.patch.rejects(error);

				return service
					.partialUpdate({})
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should return a list of ApplicationUser', async () => {
				const returnedFromService = Object.assign(
					{
						ic: 'BBBBBB',
						status: 'BBBBBB',
					},
					elemDefault
				);
				const expected = Object.assign({}, returnedFromService);
				axiosStub.get.resolves([returnedFromService]);
				return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
					expect(res).toContainEqual(expected);
				});
			});

			it('should not return a list of ApplicationUser', async () => {
				axiosStub.get.rejects(error);

				return service
					.retrieve()
					.then()
					.catch(err => {
						expect(err).toMatchObject(error);
					});
			});

			it('should delete a ApplicationUser', async () => {
				axiosStub.delete.resolves({ ok: true });
				return service.delete(123).then(res => {
					expect(res.ok).toBeTruthy();
				});
			});

			it('should not delete a ApplicationUser', async () => {
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
