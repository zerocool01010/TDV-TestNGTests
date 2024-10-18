import org.example.Calculadora;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculadoraTest {
    private Calculadora calc;

    @BeforeMethod
    public void setUp() {
        // Se ejecuta antes de cada test
        calc = new Calculadora();
    }

    @Test
    public void testSuma() {
        int resultado = calc.sumar(5, 3);
        Assert.assertEquals(resultado, 8, "La suma de 5 y 3 debe ser 8");
    }

    @Test
    public void testResta() {
        int resultado = calc.restar(10, 4);
        Assert.assertEquals(resultado, 6, "La resta de 10 y 4 debe ser 6");
    }

    // Este test depende de que testSuma se ejecute y pase
    @Test(dependsOnMethods = {"testSuma"})
    public void testSumaConDependencia() {
        int resultado = calc.sumar(2, 2);
        Assert.assertEquals(resultado, 4, "La suma de 2 y 2 debe ser 4");
    }
}
