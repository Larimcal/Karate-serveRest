# language:en
# encoding: UTF-8

@run @regressive
Feature: Autenticação de Usuário

  Background:
    * def headers = read('file:src/test/java/serveRest/domain/headers.yaml')
    * def config = read('file:src/test/java/serveRest/domain/config.yaml')
    * def url_auth = config.url_auth
    * def Manipulation = Java.type('serveRest.support.Manipulation')
    * def email = Manipulation.getEmail()
    * def password = Manipulation.getPassword()

  @run
  Scenario Outline: Gerar token de acesso válido
    * def body =
    """
    {
      "email": "#(email)",
      "password": "#(password)"
    }
    """
    Given url url_auth
    And path "/login"
    And headers headers.headers_auth
    And request body
    And retry until responseStatus == <status>
    When method POST
    Then status <status>
    And match response contains { "authorization": "#notnull" }

    Examples:
      | status |
      | 200    |
