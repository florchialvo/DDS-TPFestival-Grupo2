package festival.model

import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnit4ClassRunner])
class EntradaTest {
  val ledZeppelin = new Banda(new Categoria(200))
  val ironMaiden = new Banda(new Categoria(200))
  val sodaStereo = new Banda(new Categoria(150))
  val noche1 = new Noche(List(ledZeppelin, sodaStereo), 1)

  @Test
  def testUnMenorCompraEntradaConValorBase100YBandasDeCategoria1Paga90 {
    def noche = new Noche(List(new Banda(new Categoria(0))), 2)
    def entrada = new Entrada(100, noche, new Menor(), 'A');
    Assert.assertEquals(90.0, entrada.precio);
  }

  @Test
  def testJubiladoCompraEntradaConValorBase500EnNoche1Paga625 {
    def entrada = new Entrada(500, noche1, new Jubilado(), 'B')
    Assert.assertEquals(625.0, entrada.precio);
  }
}