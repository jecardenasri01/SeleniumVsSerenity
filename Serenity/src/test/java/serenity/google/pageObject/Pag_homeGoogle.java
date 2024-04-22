package serenity.google.pageObject;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.annotations.findby.By;

public class Pag_homeGoogle {
    public  static final Target INP_BUSQUEDA = Target.the("input busqueda home google").located(By.xpath("//*[@name='q']"));

}
