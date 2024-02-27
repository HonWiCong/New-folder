import axios from 'axios';

import { ITOrganization } from '@/shared/model/t-organization.model';

const baseApiUrl = 'api/t-organizations';

export default class TOrganizationService {
	public find(id: number): Promise<ITOrganization> {
		return new Promise<ITOrganization>((resolve, reject) => {
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

	public findCustom(id: number): Promise<ITOrganization> {
		return new Promise<ITOrganization>((resolve, reject) => {
			axios
				.get(`${baseApiUrl}_custom/${id}`)
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

	public create(entity: ITOrganization): Promise<ITOrganization> {
		return new Promise<ITOrganization>((resolve, reject) => {
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

	public update(entity: ITOrganization): Promise<ITOrganization> {
		return new Promise<ITOrganization>((resolve, reject) => {
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

	public partialUpdate(entity: ITOrganization): Promise<ITOrganization> {
		return new Promise<ITOrganization>((resolve, reject) => {
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
