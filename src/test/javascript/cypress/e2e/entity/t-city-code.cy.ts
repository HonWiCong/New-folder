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

describe('TCityCode e2e test', () => {
  const tCityCodePageUrl = '/t-city-code';
  const tCityCodePageUrlPattern = new RegExp('/t-city-code(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tCityCodeSample = {};

  let tCityCode;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-city-codes+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-city-codes').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-city-codes/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tCityCode) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-city-codes/${tCityCode.id}`,
      }).then(() => {
        tCityCode = undefined;
      });
    }
  });

  it('TCityCodes menu should load TCityCodes page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-city-code');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TCityCode').should('exist');
    cy.url().should('match', tCityCodePageUrlPattern);
  });

  describe('TCityCode page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tCityCodePageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TCityCode page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-city-code/new$'));
        cy.getEntityCreateUpdateHeading('TCityCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tCityCodePageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-city-codes',
          body: tCityCodeSample,
        }).then(({ body }) => {
          tCityCode = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-city-codes+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tCityCode],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tCityCodePageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TCityCode page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tCityCode');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tCityCodePageUrlPattern);
      });

      it('edit button click should load edit TCityCode page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TCityCode');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tCityCodePageUrlPattern);
      });

      it('edit button click should load edit TCityCode page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TCityCode');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tCityCodePageUrlPattern);
      });

      it('last delete button click should delete instance of TCityCode', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tCityCode').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tCityCodePageUrlPattern);

        tCityCode = undefined;
      });
    });
  });

  describe('new TCityCode page', () => {
    beforeEach(() => {
      cy.visit(`${tCityCodePageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TCityCode');
    });

    it('should create an instance of TCityCode', () => {
      cy.get(`[data-cy="cityCode"]`).type('Roads Generic solution').should('have.value', 'Roads Generic solution');

      cy.get(`[data-cy="cityName"]`).type('Islands Games index').should('have.value', 'Islands Games index');

      cy.get(`[data-cy="enteredBy"]`).type('90235').should('have.value', '90235');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T20:06').blur().should('have.value', '2024-01-08T20:06');

      cy.get(`[data-cy="modifyBy"]`).type('27594').should('have.value', '27594');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T20:08').blur().should('have.value', '2024-01-08T20:08');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tCityCode = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tCityCodePageUrlPattern);
    });
  });
});
