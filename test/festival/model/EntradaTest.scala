package festival.model

import scala.collection.mutable.Set
import festival.model._
import festival.model.exception.EntradaYaVendidaException
import junit.framework.Assert
import org.junit._
import org.junit.runner.RunWith
import org.junit.internal.runners.JUnit4ClassRunner
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
  
  val valoresBase = Map('A' -> Array(100, 100, 100), 'B' -> Array(500, 500, 500))
  val festival = new FestivalMock(valoresBase, new Fecha(1, 6, 2013), new Fecha().fechaActual)
  festival.agregarNoche(noche1);
  festival.agregarDescuento(Dama);
  festival.agregarDescuento(Jubilado);
  festival.agregarDescuento(Menor);
  festival.agregarDescuento(Mayor);
  
  //Politicas De Descuentos
  var festivalDescuentoMenores12 = new FestivalMock(valoresBase, new Fecha(1, 6, 2013), new Fecha().fechaActual)
  festivalDescuentoMenores12.agregarNoche(noche1);
  festivalDescuentoMenores12.agregarDescuento(MenorDe12Acompaniado)
  festivalDescuentoMenores12.agregarDescuento(Dama)
  festivalDescuentoMenores12.vender(new Entrada(festival, 100, noche1, Mayor, 'A', 2))
  
  @Test
  def testUnMenorCompraEntradaConValorBase100YBandasDeCategoria1Paga90 {
    var noche = new Noche(Set(new Banda(Categoria('categoria1))), new Fecha(1, 1, 2014))
    var entrada = new Entrada(festival, 100, noche, Menor, 'A', 1);
    Assert.assertEquals(90.0, entrada.precio);
  }

  @Test
  def testJubiladoCompraEntradaConValorBase500EnNoche1Paga625 {
    var entrada = new Entrada(festival, 500, noche1, Jubilado, 'B', 1)
    Assert.assertEquals(625.0, entrada.precio);
  }

  @Test
  def testMayorCompraEntradaEl29_05_13SectorBFila1EnNoche1EsAnticipadaPaga595 {
    festival.fechaActual = new Fecha(29, 05, 2013)
    var entrada = festival.nuevaEntrada(1, 'B', new Fecha(2, 10, 2013), Mayor)
    Assert.assertEquals(595.0, entrada.precio);
    Assert.assertEquals(classOf[EntradaAnticipada], entrada.getClass())
  }

  @Test
  def testMayorCompraEntradaEl09_06_13SectorBFila1EnNoche1NoEsAnticipadaPaga700 {
    festival.fechaActual = new Fecha(9, 06, 2013)
    var entrada = festival.nuevaEntrada(1, 'B', new Fecha(2, 10, 2013), Mayor)
    Assert.assertEquals(700.0, entrada.precio);
    Assert.assertEquals(classOf[Entrada], entrada.getClass())
  }

  @Test(expected = classOf[EntradaYaVendidaException])
  def testUnaPersonaIntentaComprarEntradaYaVendidaSeLanzaUnaExcepcion {
    festival.vender(new Entrada(festival, 100, noche1, Mayor, 'A', 1))
    festival.vender(new Entrada(festival, 100, noche1, Mayor, 'A', 1))
  }
  
  @Test
  def testJubiladoCompraEntradaParaFestivalQueAceptaDescuentoMenores12Anios(){
    var entrada = festivalDescuentoMenores12.nuevaEntrada(1, 'A', new Fecha(2, 10,2013), Jubilado)
    Assert.assertEquals(300.00, entrada.precio)
    Assert.assertEquals(classOf[Entrada], entrada.getClass())
  }
  
  @Test
  def testDamaCompraEntradaParaFestivalQueAceptaDescuentoParaDamasYJubilados(){
    var entrada = festivalDescuentoMenores12.nuevaEntrada(1, 'A', new Fecha(2, 10, 2013), Dama)
    Assert.assertEquals(280.00, entrada.precio)
  }
  
  @Test
  def testDamaCompraEntradaParaFestivalQueAceptaDescuentoParaDamasYJubiladosPeroSeVendioElPorcentajeMaximo(){
	festivalDescuentoMenores12.setPorcentajeDamas(20)
    var entrada = festivalDescuentoMenores12.nuevaEntrada(1, 'A', new Fecha(2, 10, 2013), Dama)
    Assert.assertEquals(300.00, entrada.precio)
  }
}

