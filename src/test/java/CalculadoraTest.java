import org.example.Calculadora;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class CalculadoraTest {
    private Calculadora calc;

    public int randomIntCalc(int origin, int bound){
        Random r = new Random();
        int entero = r.nextInt(origin, bound);
        return entero;
    }

    @BeforeMethod
    public void setUp() {
        // Se ejecuta antes de cada test
        calc = new Calculadora();
    }

    @Test
    public void testSuma() {
        int resultado = calc.sumar(randomIntCalc(0, 100), randomIntCalc(0, 100));
        //Assert.assertEquals(resultado, 8, "La suma de 5 y 3 debe ser 8");
        Assert.assertTrue(resultado > 0 && resultado < 199, "La suma debe ser positiva y menor a 199");
    }

    @Test
    public void testSumaNegativa() {
        int param1 = randomIntCalc(-100, 0);
        int param2 = randomIntCalc(-100, 0);
        int resultado = calc.sumar(param1, param2);
        //int resultado = calc.restar(randomIntCalc(-100, 0), randomIntCalc(-100, 0));
        Assert.assertTrue(resultado < 0 && resultado >= -200, "La suma debe ser negativa y mayor o igual a -200");
    }

    @Test
    public void testResta() {
        int param1 = 6;
        int param2 = 3;
        int resultado = calc.restar(param1, param2);

        Assert.assertTrue(resultado < param1, "El resultado de la resta es menor al primer número");

        param2 = 0;
        resultado = calc.restar(param1, param2);
        Assert.assertEquals(resultado, param1, "El resultado es igual al primer número");

        param1 = 0;
        resultado = calc.restar(param1, param2);
        Assert.assertEquals(resultado, 0, "El resultado debe dar 0");

        param2 = 2;
        resultado = calc.restar(param1, param2);
        Assert.assertTrue(resultado < 0, "El resultado de la resta debe ser negativo");
    }

    // Este test depende de que testSuma se ejecute y pase
    @Test(dependsOnMethods = {"testSuma"})
    public void testSumaConDependencia() {
        int resultado = calc.sumar(2, 2);
        Assert.assertEquals(resultado, 4, "La suma de 2 y 2 debe ser 4");
    }

    @Test(dependsOnMethods = {"testResta"})
    public void testRestaConDependencia() {
        int resultado = calc.restar(2, 2);
        Assert.assertEquals(resultado, 0, "La resta de 2 y 2 debe ser 0");
    }
}
