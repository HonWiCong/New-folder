import axios from 'axios';

import { ITOfficeCode } from '@/shared/model/t-office-code.model';

const baseApiUrl = 'api/t-office-codes';

export default class TOfficeCodeService {
	public find(id: number): Promise<ITOfficeCode> {
		return new Promise<ITOfficeCode>((resolve, reject) => {
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

	public retrieve(): Promise<any> {
		return new Promise<any>((resolve, reject) => {
			axios
				.get(baseApiUrl)
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

	public create(entity: ITOfficeCode): Promise<ITOfficeCode> {
		return new Promise<ITOfficeCode>((resolve, reject) => {
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

	public update(entity: ITOfficeCode): Promise<ITOfficeCode> {
		return new Promise<ITOfficeCode>((resolve, reject) => {
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

	public partialUpdate(entity: ITOfficeCode): Promise<ITOfficeCode> {
		return new Promise<ITOfficeCode>((resolve, reject) => {
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
