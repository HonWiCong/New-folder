import {
	entityTableSelector,
	entityDetailsButtonSelector,
	entityDetailsBackButtonSelector,
	entityCreateButtonSelector,
	entityCreateSaveButtonSelector,
	entityCreateCancelButtonSelector,
	entityEditButtonSelector,
	entityDeleteButtonSelector,
	entityConfirmDeleteButtonSelector,
} from '../../support/entity';

describe('TUserRole e2e test', () => {
	const tUserRolePageUrl = '/t-user-role';
	const tUserRolePageUrlPattern = new RegExp('/t-user-role(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tUserRoleSample = {};

	let tUserRole;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-user-roles+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-user-roles').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-user-roles/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tUserRole) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-user-roles/${tUserRole.id}`,
			}).then(() => {
				tUserRole = undefined;
			});
		}
	});

	it('TUserRoles menu should load TUserRoles page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-user-role');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TUserRole').should('exist');
		cy.url().should('match', tUserRolePageUrlPattern);
	});

	describe('TUserRole page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tUserRolePageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TUserRole page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-user-role/new$'));
				cy.getEntityCreateUpdateHeading('TUserRole');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRolePageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-user-roles',
					body: tUserRoleSample,
				}).then(({ body }) => {
					tUserRole = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-user-roles+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tUserRole],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tUserRolePageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TUserRole page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tUserRole');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRolePageUrlPattern);
			});

			it('edit button click should load edit TUserRole page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TUserRole');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRolePageUrlPattern);
			});

			it('edit button click should load edit TUserRole page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TUserRole');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRolePageUrlPattern);
			});

			it('last delete button click should delete instance of TUserRole', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tUserRole').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tUserRolePageUrlPattern);

				tUserRole = undefined;
			});
		});
	});

	describe('new TUserRole page', () => {
		beforeEach(() => {
			cy.visit(`${tUserRolePageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TUserRole');
		});

		it('should create an instance of TUserRole', () => {
			cy.get(`[data-cy="sysuserId"]`).type('85996').should('have.value', '85996');

			cy.get(`[data-cy="usrRoleid"]`).type('5643').should('have.value', '5643');

			cy.get(`[data-cy="enteredBy"]`).type('30209').should('have.value', '30209');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-29T09:52').blur().should('have.value', '2024-01-29T09:52');

			cy.get(`[data-cy="modifiedBy"]`).type('67513').should('have.value', '67513');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-29T08:34').blur().should('have.value', '2024-01-29T08:34');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tUserRole = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tUserRolePageUrlPattern);
		});
	});
});
