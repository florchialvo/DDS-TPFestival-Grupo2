package festival.model

import scala.collection.mutable.Set
import festival.model.exception.BusinessException
import festival.model._
import junit.framework.Assert
import org.junit._
import org.junit.runner.RunWith
import org.junit.internal.runners.JUnit4ClassRunner
import festival.model.exception.BusinessException
import org.junit.Test
import org.junit.internal.runners.JUnit4ClassRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnit4ClassRunner])
class EntradaTest {

  Categoria.crearCategoria('categoria1, 0)
  Categoria.crearCategoria('categoria2, 50)
  Categoria.crearCategoria('categoria3, 100)
  Categoria.crearCategoria('categoria4, 200)

  val ledZeppelin = new Banda(Categoria('categoria4))
  val ironMaiden = new Banda(Categoria('categoria4))
  val sodaStereo = new Banda(Categoria('categoria3))
  val noche1 = new Noche(Set(ledZeppelin, sodaStereo), new Fecha(2, 10, 2013))
  val festival = new FestivalMock(Set(), new Fecha(1, 6, 2013), Set(noche1), Map('A' -> Array(100, 100, 100), 'B' -> Array(500, 500, 500)), new Fecha().fechaActual)

  @Test
  def testUnMenorCompraEntradaConValorBase100YBandasDeCategoria1Paga90 {
    var noche = new Noche(Set(new Banda(Categoria('categoria1))), new Fecha(1, 1, 2014))
    var entrada = new Entrada(100, noche, new Menor(), 'A', 1);
    Assert.assertEquals(90.0, entrada.precio);
  }

  @Test
  def testJubiladoCompraEntradaConValorBase500EnNoche1Paga625 {
    var entrada = new Entrada(500, noche1, new Jubilado(), 'B', 1)
    Assert.assertEquals(625.0, entrada.precio);
  }

  @Test
  def testMayorCompraEntradaEl29_05_13SectorBFila1EnNoche1EsAnticipadaPaga595 {
    festival.fechaActual = new Fecha(29, 05, 2013)
    var entrada = festival.venderEntrada(1, 'B', new Fecha(2, 10, 2013), new Mayor())
    Assert.assertEquals(595.0, entrada.precio);
    Assert.assertEquals(classOf[EntradaAnticipada], entrada.getClass())
  }

  @Test
  def testMayorCompraEntradaEl09_06_13SectorBFila1EnNoche1NoEsAnticipadaPaga700 {
    festival.fechaActual = new Fecha(9, 06, 2013)
    var entrada = festival.venderEntrada(1, 'B', new Fecha(2, 10, 2013), new Mayor())
    Assert.assertEquals(700.0, entrada.precio);
    Assert.assertEquals(classOf[Entrada], entrada.getClass())
  }

  @Test(expected = classOf[BusinessException])
  def testUnaPersonaIntentaComprarEntradaYaVendidaSeLanzaUnaExcepcion {
    festival.venderEntrada(1, 'A', new Fecha(2, 10, 2013), new Mayor())
    festival.venderEntrada(1, 'A', new Fecha(2, 10, 2013), new Mayor())
  }
}

