package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import serenity.Dri_Utilidades;
import serenity.google.Task.Task_consultaGoogle;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class StepsD_ConsultaGoogle extends Dri_Utilidades {

    @Given("^Iniciar Test")
    public void IniciarTest() {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled("TEAM QC");
        theActorInTheSpotlight().attemptsTo(Open.url("https://www.google.com/"));
    }
    @When("^Realizar Primer Consulta (.*) y (.*)")
    public void RealizarPrimerConsulta(String consulta1,String consulta2){
        theActorInTheSpotlight().attemptsTo(Task_consultaGoogle.realizarConsulta(consulta1, consulta2));
    }
}
