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

describe('TOrgContactPerson e2e test', () => {
  const tOrgContactPersonPageUrl = '/t-org-contact-person';
  const tOrgContactPersonPageUrlPattern = new RegExp('/t-org-contact-person(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const tOrgContactPersonSample = {};

  let tOrgContactPerson;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/t-org-contact-people+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/t-org-contact-people').as('postEntityRequest');
    cy.intercept('DELETE', '/api/t-org-contact-people/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (tOrgContactPerson) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/t-org-contact-people/${tOrgContactPerson.id}`,
      }).then(() => {
        tOrgContactPerson = undefined;
      });
    }
  });

  it('TOrgContactPeople menu should load TOrgContactPeople page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('t-org-contact-person');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('TOrgContactPerson').should('exist');
    cy.url().should('match', tOrgContactPersonPageUrlPattern);
  });

  describe('TOrgContactPerson page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(tOrgContactPersonPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create TOrgContactPerson page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/t-org-contact-person/new$'));
        cy.getEntityCreateUpdateHeading('TOrgContactPerson');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tOrgContactPersonPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/t-org-contact-people',
          body: tOrgContactPersonSample,
        }).then(({ body }) => {
          tOrgContactPerson = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/t-org-contact-people+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              body: [tOrgContactPerson],
            }
          ).as('entitiesRequestInternal');
        });

        cy.visit(tOrgContactPersonPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details TOrgContactPerson page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('tOrgContactPerson');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tOrgContactPersonPageUrlPattern);
      });

      it('edit button click should load edit TOrgContactPerson page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TOrgContactPerson');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tOrgContactPersonPageUrlPattern);
      });

      it('edit button click should load edit TOrgContactPerson page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('TOrgContactPerson');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tOrgContactPersonPageUrlPattern);
      });

      it('last delete button click should delete instance of TOrgContactPerson', () => {
        cy.get(entityDeleteButtonSelector).last().click();
        cy.getEntityDeleteDialogHeading('tOrgContactPerson').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response.statusCode).to.equal(200);
        });
        cy.url().should('match', tOrgContactPersonPageUrlPattern);

        tOrgContactPerson = undefined;
      });
    });
  });

  describe('new TOrgContactPerson page', () => {
    beforeEach(() => {
      cy.visit(`${tOrgContactPersonPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('TOrgContactPerson');
    });

    it('should create an instance of TOrgContactPerson', () => {
      cy.get(`[data-cy="ocpOrgcodeid"]`).type('79934').should('have.value', '79934');

      cy.get(`[data-cy="ocpTitle"]`).type('synthesizing Frozen').should('have.value', 'synthesizing Frozen');

      cy.get(`[data-cy="ocpName"]`)
        .type('parsing Bedfordshire Buckinghamshire')
        .should('have.value', 'parsing Bedfordshire Buckinghamshire');

      cy.get(`[data-cy="ocpDesignation"]`).type('Internal function Technician').should('have.value', 'Internal function Technician');

      cy.get(`[data-cy="ocpTelephone1"]`).type('index Extended auxiliary').should('have.value', 'index Extended auxiliary');

      cy.get(`[data-cy="ocpHandphone"]`).type('Account alarm').should('have.value', 'Account alarm');

      cy.get(`[data-cy="ocpMail"]`).type('Fish').should('have.value', 'Fish');

      cy.get(`[data-cy="ocpSector"]`).type('payment Card Generic').should('have.value', 'payment Card Generic');

      cy.get(`[data-cy="ocpStatus"]`).type('FTP').should('have.value', 'FTP');

      cy.get(`[data-cy="enteredBy"]`).type('81356').should('have.value', '81356');

      cy.get(`[data-cy="enteredDate"]`).type('2024-01-08T10:16').blur().should('have.value', '2024-01-08T10:16');

      cy.get(`[data-cy="modifiedBy"]`).type('41022').should('have.value', '41022');

      cy.get(`[data-cy="modifiedDate"]`).type('2024-01-08T12:42').blur().should('have.value', '2024-01-08T12:42');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(201);
        tOrgContactPerson = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response.statusCode).to.equal(200);
      });
      cy.url().should('match', tOrgContactPersonPageUrlPattern);
    });
  });
});
