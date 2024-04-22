package serenity.google.Task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import serenity.Dri_Utilidades;

import static serenity.google.pageObject.Pag_homeGoogle.INP_BUSQUEDA;

public class Task_consultaGoogle extends Dri_Utilidades {
    public static Performable realizarConsulta(String consulta1, String consulta2){
        return Task.where("Iniciar consulta en google ",
                Dri_Utilidades.escribir(consulta1, INP_BUSQUEDA),
                Dri_Utilidades.escribir(consulta2, INP_BUSQUEDA)
        );
    }
}
