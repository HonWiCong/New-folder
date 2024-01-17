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

describe('TOrganization e2e test', () => {
	const tOrganizationPageUrl = '/t-organization';
	const tOrganizationPageUrlPattern = new RegExp('/t-organization(\\?.*)?$');
	const username = Cypress.env('E2E_USERNAME') ?? 'user';
	const password = Cypress.env('E2E_PASSWORD') ?? 'user';
	const tOrganizationSample = {};

	let tOrganization;

	beforeEach(() => {
		cy.login(username, password);
	});

	beforeEach(() => {
		cy.intercept('GET', '/api/t-organizations+(?*|)').as('entitiesRequest');
		cy.intercept('POST', '/api/t-organizations').as('postEntityRequest');
		cy.intercept('DELETE', '/api/t-organizations/*').as('deleteEntityRequest');
	});

	afterEach(() => {
		if (tOrganization) {
			cy.authenticatedRequest({
				method: 'DELETE',
				url: `/api/t-organizations/${tOrganization.id}`,
			}).then(() => {
				tOrganization = undefined;
			});
		}
	});

	it('TOrganizations menu should load TOrganizations page', () => {
		cy.visit('/');
		cy.clickOnEntityMenuItem('t-organization');
		cy.wait('@entitiesRequest').then(({ response }) => {
			if (response.body.length === 0) {
				cy.get(entityTableSelector).should('not.exist');
			} else {
				cy.get(entityTableSelector).should('exist');
			}
		});
		cy.getEntityHeading('TOrganization').should('exist');
		cy.url().should('match', tOrganizationPageUrlPattern);
	});

	describe('TOrganization page', () => {
		describe('create button click', () => {
			beforeEach(() => {
				cy.visit(tOrganizationPageUrl);
				cy.wait('@entitiesRequest');
			});

			it('should load create TOrganization page', () => {
				cy.get(entityCreateButtonSelector).click();
				cy.url().should('match', new RegExp('/t-organization/new$'));
				cy.getEntityCreateUpdateHeading('TOrganization');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOrganizationPageUrlPattern);
			});
		});

		describe('with existing value', () => {
			beforeEach(() => {
				cy.authenticatedRequest({
					method: 'POST',
					url: '/api/t-organizations',
					body: tOrganizationSample,
				}).then(({ body }) => {
					tOrganization = body;

					cy.intercept(
						{
							method: 'GET',
							url: '/api/t-organizations+(?*|)',
							times: 1,
						},
						{
							statusCode: 200,
							body: [tOrganization],
						}
					).as('entitiesRequestInternal');
				});

				cy.visit(tOrganizationPageUrl);

				cy.wait('@entitiesRequestInternal');
			});

			it('detail button click should load details TOrganization page', () => {
				cy.get(entityDetailsButtonSelector).first().click();
				cy.getEntityDetailsHeading('tOrganization');
				cy.get(entityDetailsBackButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOrganizationPageUrlPattern);
			});

			it('edit button click should load edit TOrganization page and go back', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TOrganization');
				cy.get(entityCreateSaveButtonSelector).should('exist');
				cy.get(entityCreateCancelButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOrganizationPageUrlPattern);
			});

			it('edit button click should load edit TOrganization page and save', () => {
				cy.get(entityEditButtonSelector).first().click();
				cy.getEntityCreateUpdateHeading('TOrganization');
				cy.get(entityCreateSaveButtonSelector).click();
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOrganizationPageUrlPattern);
			});

			it('last delete button click should delete instance of TOrganization', () => {
				cy.get(entityDeleteButtonSelector).last().click();
				cy.getEntityDeleteDialogHeading('tOrganization').should('exist');
				cy.get(entityConfirmDeleteButtonSelector).click();
				cy.wait('@deleteEntityRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(204);
				});
				cy.wait('@entitiesRequest').then(({ response }) => {
					expect(response.statusCode).to.equal(200);
				});
				cy.url().should('match', tOrganizationPageUrlPattern);

				tOrganization = undefined;
			});
		});
	});

	describe('new TOrganization page', () => {
		beforeEach(() => {
			cy.visit(`${tOrganizationPageUrl}`);
			cy.get(entityCreateButtonSelector).click();
			cy.getEntityCreateUpdateHeading('TOrganization');
		});

		it('should create an instance of TOrganization', () => {
			cy.get(`[data-cy="orgHqid"]`).type('14054').should('have.value', '14054');

			cy.get(`[data-cy="orgHqBr"]`).type('program markets').should('have.value', 'program markets');

			cy.get(`[data-cy="orgCode"]`).type('Games').should('have.value', 'Games');

			cy.get(`[data-cy="orgBrn"]`).type('IB Branch Tuna').should('have.value', 'IB Branch Tuna');

			cy.get(`[data-cy="orgPtaxid"]`).type('18328').should('have.value', '18328');

			cy.get(`[data-cy="orgDefaultTaxCode"]`).type('Product').should('have.value', 'Product');

			cy.get(`[data-cy="orgCompanyGstNo"]`).type('Kids syndicate').should('have.value', 'Kids syndicate');

			cy.get(`[data-cy="orgCompanyGstRegDate"]`).type('2024-01-08').blur().should('have.value', '2024-01-08');

			cy.get(`[data-cy="orgCompanyGstDeregDate"]`).type('2024-01-08').blur().should('have.value', '2024-01-08');

			cy.get(`[data-cy="orgPoTaxInclusive"]`)
				.type('indexing demand-driven Borders')
				.should('have.value', 'indexing demand-driven Borders');

			cy.get(`[data-cy="orgName"]`).type('open-source Clothing Chair').should('have.value', 'open-source Clothing Chair');

			cy.get(`[data-cy="orgNameOther"]`).type('redundant indexing Tools').should('have.value', 'redundant indexing Tools');

			cy.get(`[data-cy="orgShortname"]`).type('Pennsylvania').should('have.value', 'Pennsylvania');

			cy.get(`[data-cy="orgAddress"]`).type('Landing').should('have.value', 'Landing');

			cy.get(`[data-cy="orgShippingAddress"]`).type('world-class').should('have.value', 'world-class');

			cy.get(`[data-cy="orgBillingAddress"]`).type('Avon Automotive 6th').should('have.value', 'Avon Automotive 6th');

			cy.get(`[data-cy="orgPostcode"]`).type('Uruguay firewall Investor').should('have.value', 'Uruguay firewall Investor');

			cy.get(`[data-cy="orgCity"]`).type('South Metal').should('have.value', 'South Metal');

			cy.get(`[data-cy="orgState"]`).type('utilisation').should('have.value', 'utilisation');

			cy.get(`[data-cy="orgCountry"]`).type('wireless pixel envisioneer').should('have.value', 'wireless pixel envisioneer');

			cy.get(`[data-cy="orgOffPhone1"]`).type('white').should('have.value', 'white');

			cy.get(`[data-cy="orgOffPhone2"]`).type('Sleek CSS Fresh').should('have.value', 'Sleek CSS Fresh');

			cy.get(`[data-cy="orgOffPhone3"]`)
				.type('Incredible Fantastic Berkshire')
				.should('have.value', 'Incredible Fantastic Berkshire');

			cy.get(`[data-cy="orgOffFax1"]`).type('Switchable CSS').should('have.value', 'Switchable CSS');

			cy.get(`[data-cy="orgOffFax2"]`).type('Unbranded').should('have.value', 'Unbranded');

			cy.get(`[data-cy="orgCredittermid"]`).type('77435').should('have.value', '77435');

			cy.get(`[data-cy="orgCreditLimit"]`).type('24042').should('have.value', '24042');

			cy.get(`[data-cy="orgAgencyid"]`).type('16711').should('have.value', '16711');

			cy.get(`[data-cy="orgDivision"]`).type('De-engineered Chair').should('have.value', 'De-engineered Chair');

			cy.get(`[data-cy="orgDistrict"]`).type('monitor Georgia').should('have.value', 'monitor Georgia');

			cy.get(`[data-cy="orgWebsite"]`).type('Avon back-end').should('have.value', 'Avon back-end');

			cy.get(`[data-cy="orgEmail"]`).type('Gloves Spurs').should('have.value', 'Gloves Spurs');

			cy.get(`[data-cy="orgSupplierCategory"]`).type('circuit wireless').should('have.value', 'circuit wireless');

			cy.get(`[data-cy="orgCurrencyCode"]`).type('RSS Phased').should('have.value', 'RSS Phased');

			cy.get(`[data-cy="orgType"]`).type('Tools Executive').should('have.value', 'Tools Executive');

			cy.get(`[data-cy="orgSectorid"]`).type('94285').should('have.value', '94285');

			cy.get(`[data-cy="orgSector"]`).type('Parks bi-directional Officer').should('have.value', 'Parks bi-directional Officer');

			cy.get(`[data-cy="orgIndustry"]`).type('up Uzbekistan Technician').should('have.value', 'up Uzbekistan Technician');

			cy.get(`[data-cy="orgSainsGroup"]`).type('Borders').should('have.value', 'Borders');

			cy.get(`[data-cy="orgBumiputra"]`).type('distributed Car').should('have.value', 'distributed Car');

			cy.get(`[data-cy="orgUpkReg"]`).type('payment').should('have.value', 'payment');

			cy.get(`[data-cy="orgMofReg"]`).type('Senior programming withdrawal').should('have.value', 'Senior programming withdrawal');

			cy.get(`[data-cy="orgDesignation"]`).type('supply-chains').should('have.value', 'supply-chains');

			cy.get(`[data-cy="orgContpersonTitle"]`).type('Fantastic RAM Concrete').should('have.value', 'Fantastic RAM Concrete');

			cy.get(`[data-cy="orgContperson"]`).type('backing systemic').should('have.value', 'backing systemic');

			cy.get(`[data-cy="orgDirectline"]`).type('matrix rich Berkshire').should('have.value', 'matrix rich Berkshire');

			cy.get(`[data-cy="orgContpEmail"]`).type('Flats Armenia monitor').should('have.value', 'Flats Armenia monitor');

			cy.get(`[data-cy="orgContpHp"]`)
				.type('collaborative Granite compressing')
				.should('have.value', 'collaborative Granite compressing');

			cy.get(`[data-cy="orgRemarks"]`).type('frictionless').should('have.value', 'frictionless');

			cy.get(`[data-cy="orgActiveStatus"]`).type('Bulgarian synthesizing').should('have.value', 'Bulgarian synthesizing');

			cy.get(`[data-cy="orgCcGc"]`).type('Valleys supply-chains Outdoors').should('have.value', 'Valleys supply-chains Outdoors');

			cy.get(`[data-cy="orgCustomerCateCode"]`).type('Algeria Internal Savings').should('have.value', 'Algeria Internal Savings');

			cy.get(`[data-cy="orgVendorCateCode"]`).type('connecting Cambridgeshire').should('have.value', 'connecting Cambridgeshire');

			cy.get(`[data-cy="orgSalesCateCode"]`).type('transparent Awesome Tasty').should('have.value', 'transparent Awesome Tasty');

			cy.get(`[data-cy="orgOutstandingBalance"]`).type('60145').should('have.value', '60145');

			cy.get(`[data-cy="orgOutstandingBalanceEx"]`).type('72900').should('have.value', '72900');

			cy.get(`[data-cy="orgCompanyCode"]`).type('vertical').should('have.value', 'vertical');

			cy.get(`[data-cy="orgDcrownPostStatus"]`).type('RAM').should('have.value', 'RAM');

			cy.get(`[data-cy="confirmedBy"]`).type('68066').should('have.value', '68066');

			cy.get(`[data-cy="confirmedDate"]`).type('2024-01-08T18:55').blur().should('have.value', '2024-01-08T18:55');

			cy.get(`[data-cy="enteredBy"]`).type('87582').should('have.value', '87582');

			cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T06:56').blur().should('have.value', '2024-01-08T06:56');

			cy.get(`[data-cy="modifiedBy"]`).type('74017').should('have.value', '74017');

			cy.get(`[data-cy="modifiedDate"]`).type('2024-01-09T00:32').blur().should('have.value', '2024-01-09T00:32');

			cy.get(entityCreateSaveButtonSelector).click();

			cy.wait('@postEntityRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(201);
				tOrganization = response.body;
			});
			cy.wait('@entitiesRequest').then(({ response }) => {
				expect(response.statusCode).to.equal(200);
			});
			cy.url().should('match', tOrganizationPageUrlPattern);
		});
	});
});
