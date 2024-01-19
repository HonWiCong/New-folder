import axios from 'axios';

import { ITSectionCode } from '@/shared/model/t-section-code.model';

const baseApiUrl = 'api/t-section-codes';

export default class TSectionCodeService {
	public find(id: number): Promise<ITSectionCode> {
		return new Promise<ITSectionCode>((resolve, reject) => {
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

	public create(entity: ITSectionCode): Promise<ITSectionCode> {
		return new Promise<ITSectionCode>((resolve, reject) => {
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

	public update(entity: ITSectionCode): Promise<ITSectionCode> {
		return new Promise<ITSectionCode>((resolve, reject) => {
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

	public partialUpdate(entity: ITSectionCode): Promise<ITSectionCode> {
		return new Promise<ITSectionCode>((resolve, reject) => {
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
