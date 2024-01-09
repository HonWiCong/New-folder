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

describe('TIndustryCode e2e test', () => {
  const tIndustryCodePageUrl = '/t-industry-code';
  const tIndustryCodePageUrlPattern = new RegExp('/t-industry-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tIndustryCodeSample = {};

  let tIndustryCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-industry-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-industry-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-industry-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tIndustryCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-industry-codes/${tIndustryCode.id}`,
      }).then(() => {
        tIndustryCode = undefined;
      });
    }
  });

  it('TIndustryCodes menu should load TIndustryCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-industry-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TIndustryCode').should('exist');
    cy.url().should('match', tIndustryCodePageUrlPattern);
  });

  describe('TIndustryCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tIndustryCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TIndustryCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-industry-code/new$'));
        cy.getEntityCreateUpdateHeading('TIndustryCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tIndustryCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-industry-codes',
          body: tIndustryCodeSample,
        }).then(({ body }) => {
          tIndustryCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-industry-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tIndustryCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tIndustryCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TIndustryCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tIndustryCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tIndustryCodePageUrlPattern);
      });

      it('edit button click should load edit TIndustryCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TIndustryCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tIndustryCodePageUrlPattern);
      });

      it('edit button click should load edit TIndustryCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TIndustryCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tIndustryCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TIndustryCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tIndustryCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tIndustryCodePageUrlPattern);

        tIndustryCode = undefined;
      });
    });
  });

  describe('new TIndustryCode page', () => {
    beforeEach(() => {
      cy.visit(`${tIndustryCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TIndustryCode');
    });

    it('should create an instance of TIndustryCode', () => {
      cy.get(`[data-cy="industryName"]`).type('Movies enhance').should('have.value', 'Movies enhance');

      cy.get(`[data-cy="enteredBy"]`).type('37235').should('have.value', '37235');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T09:26').blur().should('have.value', '2024-01-08T09:26');

      cy.get(`[data-cy="modifiedBy"]`).type('85665').should('have.value', '85665');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T23:39').blur().should('have.value', '2024-01-08T23:39');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tIndustryCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tIndustryCodePageUrlPattern);
    });
  });
});
