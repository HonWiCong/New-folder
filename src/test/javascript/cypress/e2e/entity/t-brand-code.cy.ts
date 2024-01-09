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

describe('TBrandCode e2e test', () => {
  const tBrandCodePageUrl = '/t-brand-code';
  const tBrandCodePageUrlPattern = new RegExp('/t-brand-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tBrandCodeSample = {};

  let tBrandCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-brand-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-brand-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-brand-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tBrandCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-brand-codes/${tBrandCode.id}`,
      }).then(() => {
        tBrandCode = undefined;
      });
    }
  });

  it('TBrandCodes menu should load TBrandCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-brand-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TBrandCode').should('exist');
    cy.url().should('match', tBrandCodePageUrlPattern);
  });

  describe('TBrandCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tBrandCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TBrandCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-brand-code/new$'));
        cy.getEntityCreateUpdateHeading('TBrandCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tBrandCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-brand-codes',
          body: tBrandCodeSample,
        }).then(({ body }) => {
          tBrandCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-brand-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tBrandCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tBrandCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TBrandCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tBrandCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tBrandCodePageUrlPattern);
      });

      it('edit button click should load edit TBrandCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TBrandCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tBrandCodePageUrlPattern);
      });

      it('edit button click should load edit TBrandCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TBrandCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tBrandCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TBrandCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tBrandCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tBrandCodePageUrlPattern);

        tBrandCode = undefined;
      });
    });
  });

  describe('new TBrandCode page', () => {
    beforeEach(() => {
      cy.visit(`${tBrandCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TBrandCode');
    });

    it('should create an instance of TBrandCode', () => {
      cy.get(`[data-cy="brandName"]`).type('paradigm bluetooth').should('have.value', 'paradigm bluetooth');

      cy.get(`[data-cy="enteredBy"]`).type('75933').should('have.value', '75933');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T16:24').blur().should('have.value', '2024-01-08T16:24');

      cy.get(`[data-cy="modifiedBy"]`).type('88933').should('have.value', '88933');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T13:27').blur().should('have.value', '2024-01-08T13:27');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tBrandCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tBrandCodePageUrlPattern);
    });
  });
});
