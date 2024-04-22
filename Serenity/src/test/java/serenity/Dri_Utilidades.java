package serenity;

import net.serenitybdd.core.Serenity;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.DriverTask;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.JavascriptExecutor;
import net.serenitybdd.screenplay.targets.ByTarget;

import java.util.Set;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Dri_Utilidades {
    public static Performable escribir(String texto, Target elemento) {
        esperar(elemento);
        resaltarElemento(elemento,"g");
        return Enter.theValue(texto).into(elemento);
    }

    public static Performable clickEn(Target elemento) {
        esperar(elemento);
        esperarHabilitado(elemento);
        resaltarElemento(elemento,"g");
        return Click.on(elemento);
    }

    public static Performable clickEnJs(Target elemento){
        esperar(elemento);
        esperarHabilitado(elemento);
        JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
        String xpath =((By.ByXPath) ((ByTarget) elemento).getLocator()).getRemoteParameters().value().toString();
        js.executeScript("arguments[0].click();", Serenity.getDriver().findElement(By.xpath(xpath)));
        Actor actor = theActorInTheSpotlight();
        actor.should("click en objeto "+elemento);
        return null;
    }
    public static Performable esperar(Target elementos) {
        return Wait.until(
                WebElementQuestion.the(elementos), WebElementStateMatchers.isPresent()
        ).forNoMoreThan(30).seconds();
    }

    public static Performable esperarHabilitado(Target elementos) {
        return Wait.until(
                WebElementQuestion.the(elementos), WebElementStateMatchers.isEnabled()
        ).forNoMoreThan(30).seconds();
    }
    public static Performable VerificarExistaElemento(Target elementos) {
        return Wait.until(
                WebElementQuestion.the(elementos), WebElementStateMatchers.isPresent()
        ).forNoMoreThan(5).seconds();
    }

    public static Performable esperarDesaparezca(Target elementos) {
        return Wait.until(
                //  WebElementQuestion.the(elementos), WebElementStateMatchers.isNotPresent()
                WebElementQuestion.the(elementos), WebElementStateMatchers.isNotPresent()
        ).forNoMoreThan(160).minutes();
    }
    public static Performable  compararTexto(String texto1, String texto2) {
        Actor actor = theActorInTheSpotlight();
        actor.attemptsTo(Ensure.that(texto1).isEqualTo(texto2));
        return null;
    }


    public static  String obtenerTexto(Target elemento) {
        Actor actor = theActorInTheSpotlight();
        return Text.of(elemento).answeredBy(actor).toString();
    }

    public static String obtenerTituloActualVentana() {
        Actor actor = theActorInTheSpotlight();
        return BrowseTheWeb.as(actor).getTitle();
    }

    public static String obtenerUrl() {
        return Serenity.getDriver().getCurrentUrl();
    }


    public static Performable enfocarNuevaVentana() {

        String currentWindow = Serenity.getDriver().getWindowHandle();
        Set<String> allWindows = Serenity.getDriver().getWindowHandles();

        for (String window : allWindows) {
            if (!window.contentEquals(currentWindow)) {
                Serenity.getDriver().switchTo().window(window);
                break;
            }
        }

        return new DriverTask(driver -> driver.switchTo().window(obtenerTituloActualVentana()));
    }



    public static String ObtenerColor(Target elemento) {
        String xpath = ((By.ByXPath) ((ByTarget) elemento).getLocator()).getRemoteParameters().value().toString();
        String colorRGB = Serenity.getDriver().findElement(By.xpath(xpath)).getCssValue("background-color").toString();
        String colorReal = convertirRGBColorReal(colorRGB);
        return colorReal;

    }


    public static String convertirRGBColorReal(String colorRgb) {
        String colorReal = null;
        switch (colorRgb) {
            case "rgba(0, 122, 188, 1)":
                colorReal = "azul";
                break;
            case "color rgba(0, 122, 188, 2)":
                colorReal = "azul";
                break;

        }
        return colorReal;
    }


    public static Performable iframe(String ventana) {
        return new DriverTask(driver -> Serenity.getDriver().switchTo().frame(ventana));
    }



    public static Performable resaltarElemento(Target elemento, String color) {

        JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
        String xpath =((By.ByXPath) ((ByTarget) elemento).getLocator()).getRemoteParameters().value().toString();

        if (color == "g") {
            js.executeScript("arguments[0].style.border = '2px solid red'", Serenity.getDriver().findElement(By.xpath(xpath)));
        } else {
            js.executeScript("arguments[0].style.border = '2px solid black'", Serenity.getDriver().findElement(By.xpath(xpath)));

        }
        return  null;
    }


}
