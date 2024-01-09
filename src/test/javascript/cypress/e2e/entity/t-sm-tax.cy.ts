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

describe('TSmTax e2e test', () => {
  const tSmTaxPageUrl = '/t-sm-tax';
  const tSmTaxPageUrlPattern = new RegExp('/t-sm-tax(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tSmTaxSample = {};

  let tSmTax;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-sm-taxes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-sm-taxes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-sm-taxes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tSmTax) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-sm-taxes/${tSmTax.id}`,
      }).then(() => {
        tSmTax = undefined;
      });
    }
  });

  it('TSmTaxes menu should load TSmTaxes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-sm-tax');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TSmTax').should('exist');
    cy.url().should('match', tSmTaxPageUrlPattern);
  });

  describe('TSmTax page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tSmTaxPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TSmTax page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-sm-tax/new$'));
        cy.getEntityCreateUpdateHeading('TSmTax');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSmTaxPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-sm-taxes',
          body: tSmTaxSample,
        }).then(({ body }) => {
          tSmTax = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-sm-taxes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tSmTax],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tSmTaxPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TSmTax page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tSmTax');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSmTaxPageUrlPattern);
      });

      it('edit button click should load edit TSmTax page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TSmTax');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSmTaxPageUrlPattern);
      });

      it('edit button click should load edit TSmTax page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TSmTax');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSmTaxPageUrlPattern);
      });

      it('last delete button click should delete instance of TSmTax', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tSmTax').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tSmTaxPageUrlPattern);

        tSmTax = undefined;
      });
    });
  });

  describe('new TSmTax page', () => {
    beforeEach(() => {
      cy.visit(`${tSmTaxPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TSmTax');
    });

    it('should create an instance of TSmTax', () => {
      cy.get(`[data-cy="smTaxCode"]`).type('eco-centric e-business').should('have.value', 'eco-centric e-business');

      cy.get(`[data-cy="smTaxDescription"]`).type('integrate').should('have.value', 'integrate');

      cy.get(`[data-cy="smTaxPercentage"]`).type('98021').should('have.value', '98021');

      cy.get(`[data-cy="smTaxType"]`).type('Investor up').should('have.value', 'Investor up');

      cy.get(`[data-cy="smTaxGstCode"]`).type('Orchestrator Card').should('have.value', 'Orchestrator Card');

      cy.get(`[data-cy="smTaxGstType"]`).type('deposit ROI human-resource').should('have.value', 'deposit ROI human-resource');

      cy.get(`[data-cy="smTaxCollectedGlCode"]`).type('brand core Guatemala').should('have.value', 'brand core Guatemala');

      cy.get(`[data-cy="smTaxCollectedGlDesc"]`).type('feed').should('have.value', 'feed');

      cy.get(`[data-cy="smTaxCollectedCostCenter"]`).type('Operations parse').should('have.value', 'Operations parse');

      cy.get(`[data-cy="smTaxPaidGlCode"]`).type('face Bedfordshire').should('have.value', 'face Bedfordshire');

      cy.get(`[data-cy="smTaxPaidGlDesc"]`)
        .type('ROI Pennsylvania Buckinghamshire')
        .should('have.value', 'ROI Pennsylvania Buckinghamshire');

      cy.get(`[data-cy="smTaxPaidCostCenter"]`).type('Planner').should('have.value', 'Planner');

      cy.get(`[data-cy="smTaxTaxAuthority"]`).type('Fish').should('have.value', 'Fish');

      cy.get(`[data-cy="smTaxStatus"]`).select('N');

      cy.get(`[data-cy="smTaxEnteredBy"]`).type('84836').should('have.value', '84836');

      cy.get(`[data-cy="smTaxEnteredDate"]`).type('2024-01-08T18:59').blur().should('have.value', '2024-01-08T18:59');

      cy.get(`[data-cy="smTaxModifiedBy"]`).type('42754').should('have.value', '42754');

      cy.get(`[data-cy="smTaxModifiedDate"]`).type('2024-01-08T19:57').blur().should('have.value', '2024-01-08T19:57');

      cy.get(`[data-cy="smTaxConfirmedBy"]`).type('23895').should('have.value', '23895');

      cy.get(`[data-cy="smTaxConfirmedDate"]`).type('2024-01-08T06:19').blur().should('have.value', '2024-01-08T06:19');

      cy.get(`[data-cy="smTaxNarration"]`).type('Extensions end-to-end Product').should('have.value', 'Extensions end-to-end Product');

      cy.get(`[data-cy="smTaxDisplay"]`).type('unleash').should('have.value', 'unleash');

      cy.get(`[data-cy="smTaxRcmFlag"]`).type('Chair').should('have.value', 'Chair');

      cy.get(`[data-cy="smTaxCga"]`).type('portals Brazilian').should('have.value', 'portals Brazilian');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tSmTax = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tSmTaxPageUrlPattern);
    });
  });
});
