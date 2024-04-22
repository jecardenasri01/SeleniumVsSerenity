@cucumber
Feature: Ejemplo serenity
  @1
  Scenario Template: 123 - consulta google
    Given Iniciar Test
    When Realizar Primer Consulta <consulta1> y <consulta2>
    Examples:
    |consulta1|consulta2|
    |prueba 1|selenium 1|
    |prueba 2|selenium 2|