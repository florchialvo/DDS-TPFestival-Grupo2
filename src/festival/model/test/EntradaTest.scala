package festival.model.test

import scala.collection.mutable.Set
import festival.model.exception.BusinessException
import festival.model._
import junit.framework.Assert
import org.junit._
import org.junit.runner.RunWith
import org.junit.internal.runners.JUnit4ClassRunner

@RunWith(classOf[JUnit4ClassRunner])
class EntradaTest {
  val ledZeppelin = new Banda(new Categoria(200))
  val ironMaiden = new Banda(new Categoria(200))
  val sodaStereo = new Banda(new Categoria(150))
  val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(2,10,2013))
  val festival = new Festival(Set(), new Fecha(1,6,2013), Set(noche1), Map('A'->Array(100,100,100), 'B'->Array(500,500,500)))
  
  @Test
  def testUnMenorCompraEntradaConValorBase100YBandasDeCategoria1Paga90 {
    def noche = new Noche(Set(new Banda(new Categoria(0))), new Fecha(1,1,2014))
    def entrada = new Entrada(100, noche, new Menor(), 'A', 1);
    Assert.assertEquals(90.0, entrada.precio);
  }

  @Test
  def testJubiladoCompraEntradaConValorBase500EnNoche1Paga625 {
    def entrada = new Entrada(500, noche1, new Jubilado(), 'B', 1)
    Assert.assertEquals(625.0, entrada.precio);
  }
  
  @Test
  def testMayorCompraEntradaAnticipadaConValorBase500EnNoche1Paga595 {
    def entrada = new EntradaAnticipada(500, noche1, new Mayor(), 'B', 1)
    Assert.assertEquals(595.0, entrada.precio);
  }
  
  @Test(expected=classOf[BusinessException])
  	def testUnaPersonaIntentaComprarEntradaYaVendidaSeLanzaUnaExcepcion{
    festival.venderEntrada(1, 'A', new Fecha(2,10,2013), new Mayor())
    festival.venderEntrada(1, 'A', new Fecha(2,10,2013), new Mayor())
  }
  
  @Test
  def testFechaToInt(){
    Assert.assertEquals(20130709, new Fecha(9,7,2013).toInt)
  }
}