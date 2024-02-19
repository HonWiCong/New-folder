import axios from 'axios';

import { ITSubunitCode } from '@/shared/model/t-subunit-code.model';

const baseApiUrl = 'api/t-subunit-codes';

export default class TSubunitCodeService {
	public find(id: number): Promise<ITSubunitCode> {
		return new Promise<ITSubunitCode>((resolve, reject) => {
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

	public create(entity: ITSubunitCode): Promise<ITSubunitCode> {
		return new Promise<ITSubunitCode>((resolve, reject) => {
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

	public update(entity: ITSubunitCode): Promise<ITSubunitCode> {
		return new Promise<ITSubunitCode>((resolve, reject) => {
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

	public partialUpdate(entity: ITSubunitCode): Promise<ITSubunitCode> {
		return new Promise<ITSubunitCode>((resolve, reject) => {
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
