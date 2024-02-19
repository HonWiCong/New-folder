import axios from 'axios';

import { ITUnitCode } from '@/shared/model/t-unit-code.model';

const baseApiUrl = 'api/t-unit-codes';

export default class TUnitCodeService {
	public find(id: number): Promise<ITUnitCode> {
		return new Promise<ITUnitCode>((resolve, reject) => {
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

	public create(entity: ITUnitCode): Promise<ITUnitCode> {
		return new Promise<ITUnitCode>((resolve, reject) => {
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

	public update(entity: ITUnitCode): Promise<ITUnitCode> {
		return new Promise<ITUnitCode>((resolve, reject) => {
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

	public partialUpdate(entity: ITUnitCode): Promise<ITUnitCode> {
		return new Promise<ITUnitCode>((resolve, reject) => {
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
