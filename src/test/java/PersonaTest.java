import org.example.Persona;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonaTest {
    @DataProvider
    public Persona[] GeneradorPersona() {
        System.out.println("Genero casos");
        return new Persona[] {
                new Persona("Manuel","37.766.544","1993-21-12",30,true),
                new Persona("Carolina","47.733.564","2003-09-11",21,true),
                new Persona("Belen","41.872.928","1998-08-03",26,true),
                new Persona("Florencia","40.000.102","1996-02-02",28,true),
                new Persona("Gonzalo","45.462.218","2000-07-10",24,true)
        };
    }

    @Test(dataProvider = "GeneradorPersona", invocationCount = 2)
    public void testEdadBienCalculada(Persona p) {

        int edadReportada = p.getEdad();
        int edadReal= getEdad(p.getFechaNacimiento());
        Assert.assertTrue(edadReportada == edadReal);
        //Assert.assertTrue("Fallo en el calculo de la edad",edadReportada == edadReal);
    }

    @Test(invocationCount = 2)
    @Parameters({"nombre","dni","fnac"})
    public void testConParametros(@Optional("Pepe") String nombre , @Optional("20.345.678") String dni, @Optional("2001-2-3")String fNac)
    {
        Persona p = new Persona(nombre, dni, fNac, 0, false);
        System.out.println(p);
    }

    public int getEdad(String fechaNacimiento) {
        try {
            // Crea un objeto SimpleDateFormat para parsear la fecha de nacimiento.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Parsea la fecha de nacimiento.
            Date fechaNac = sdf.parse(fechaNacimiento);

            // Obtiene la fecha actual.
            Date fechaActual = new Date();

            // Crea un objeto Calendar para calcular la diferencia entre las fechas.
            Calendar calNac = Calendar.getInstance();
            Calendar calActual = Calendar.getInstance();
            calNac.setTime(fechaNac);
            calActual.setTime(fechaActual);

            // Calcula la diferencia de años.
            int edad = calActual.get(Calendar.YEAR) - calNac.get(Calendar.YEAR);

            // Verifica si el cumpleaños ya pasó este año.
            if (calNac.get(Calendar.MONTH) > calActual.get(Calendar.MONTH) ||
                    (calNac.get(Calendar.MONTH) == calActual.get(Calendar.MONTH) &&
                            calNac.get(Calendar.DAY_OF_MONTH) > calActual.get(Calendar.DAY_OF_MONTH))) {
                edad--;
            }

            return edad;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Manejo de errores
        }
    }
}
