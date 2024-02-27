import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ITCityCode } from '@/shared/model/t-city-code.model';

const baseApiUrl = 'api/t-city-codes';

export default class TCityCodeService {
	public find(id: number): Promise<ITCityCode> {
		return new Promise<ITCityCode>((resolve, reject) => {
			axios
				.get(`${baseApiUrl}/${id}`)
				.then(res => {
					resolve(res.data);
				})
				.catch(err => {
					reject(err);
				});
		});
	}

	public retrieve(paginationQuery?: any): Promise<any> {
		return new Promise<any>((resolve, reject) => {
			axios
				.get(baseApiUrl + `?${paginationQuery}`)
				.then(res => {
					resolve(res);
				})
				.catch(err => {
					reject(err);
				});
		});
	}

	public delete(id: number): Promise<any> {
		return new Promise<any>((resolve, reject) => {
			axios
				.delete(`${baseApiUrl}/${id}`)
				.then(res => {
					resolve(res);
				})
				.catch(err => {
					reject(err);
				});
		});
	}

	public create(entity: ITCityCode): Promise<ITCityCode> {
		return new Promise<ITCityCode>((resolve, reject) => {
			axios
				.post(`${baseApiUrl}`, entity)
				.then(res => {
					resolve(res.data);
				})
				.catch(err => {
					reject(err);
				});
		});
	}

	public update(entity: ITCityCode): Promise<ITCityCode> {
		return new Promise<ITCityCode>((resolve, reject) => {
			axios
				.put(`${baseApiUrl}/${entity.id}`, entity)
				.then(res => {
					resolve(res.data);
				})
				.catch(err => {
					reject(err);
				});
		});
	}

	public partialUpdate(entity: ITCityCode): Promise<ITCityCode> {
		return new Promise<ITCityCode>((resolve, reject) => {
			axios
				.patch(`${baseApiUrl}/${entity.id}`, entity)
				.then(res => {
					resolve(res.data);
				})
				.catch(err => {
					reject(err);
				});
		});
	}
}
