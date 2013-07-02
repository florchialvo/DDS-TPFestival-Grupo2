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
  
  @Test
  def testSeCreaUnComboCon5EntradasQueSuman2300YSeAplicaDescuento(){
	  var combo = new Combo(festival)
	  
	  var entrada = festival.nuevaEntrada(0, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada2 = festival.nuevaEntrada(1, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada3 = festival.nuevaEntrada(2, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada4 = festival.nuevaEntrada(0, 'B',new Fecha(2, 10, 2013), Mayor)
	  var entrada5 = festival.nuevaEntrada(1, 'B',new Fecha(2, 10, 2013), Mayor)
	  
	  combo.agregar(entrada)
	  combo.agregar(entrada2)
	  combo.agregar(entrada3)
	  combo.agregar(entrada4)
	  combo.agregar(entrada5)

	  
	  Assert.assertEquals(2070.00,combo.precio)
  
  }
  
  @Test
  def testSeCreaUnComboCon2EntradasQueSuman585YNoSeAplicaDescuento(){
	  var combo = new Combo(festival)
	  
	  var entrada = festival.nuevaEntrada(0, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada2 = festival.nuevaEntrada(1, 'A',new Fecha(2, 10, 2013), Jubilado)

	  
	  combo.agregar(entrada)
	  combo.agregar(entrada2)
	

	  
	  Assert.assertEquals(585.00,combo.precio)
  
  }
  
  
    @Test
  def testSeVendeUnComboYSeAgregaASuColeccionDeEntradasVendidasYALasEntradasVendidasDelFestival(){
	  var combo = new Combo(festival)
	  
	  var entrada = festival.nuevaEntrada(0, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada2 = festival.nuevaEntrada(1, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada3 = festival.nuevaEntrada(2, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada4 = festival.nuevaEntrada(0, 'B',new Fecha(2, 10, 2013), Mayor)
	  var entrada5 = festival.nuevaEntrada(1, 'B',new Fecha(2, 10, 2013), Mayor)
	  
	  combo.agregar(entrada)
	  combo.agregar(entrada2)
	  combo.agregar(entrada3)
	  combo.agregar(entrada4)
	  combo.agregar(entrada5)
	  
	  festival.vender(combo)

	  
	  Assert.assertEquals(true,combo.vendidas.contains(entrada))
	  Assert.assertEquals(true,combo.vendidas.contains(entrada2))
	  Assert.assertEquals(true,combo.vendidas.contains(entrada3))
	  Assert.assertEquals(true,combo.vendidas.contains(entrada4))
	  Assert.assertEquals(true,combo.vendidas.contains(entrada5))
	  
	  Assert.assertEquals(true,festival.entradasVendidas.contains(entrada))
	  Assert.assertEquals(true,festival.entradasVendidas.contains(entrada2))
	  Assert.assertEquals(true,festival.entradasVendidas.contains(entrada3))
	  Assert.assertEquals(true,festival.entradasVendidas.contains(entrada4))
	  Assert.assertEquals(true,festival.entradasVendidas.contains(entrada5))
	  
	  
  
  }
    
    @Test(expected = classOf[EntradaYaVendidaException])
  def testSeVendeUnComboCon2EntradasRepetidasSeProduceLaExcepcion(){
       var combo = new Combo(festival)
	  
	  var entrada = festival.nuevaEntrada(0, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada2 = festival.nuevaEntrada(1, 'A',new Fecha(2, 10, 2013), Jubilado)
	  
	  combo.agregar(entrada)
	  combo.agregar(entrada2)
	  combo.agregar(entrada2)
	  
	  festival.vender(combo)
    
    }
    
    @Test
  def testSeVendeUnComboCon2EntradasRepetidasSeProduceRollback(){
	  var combo = new Combo(festival)
	  
	  var entrada = festival.nuevaEntrada(0, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada2 = festival.nuevaEntrada(1, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada3 = festival.nuevaEntrada(2, 'A',new Fecha(2, 10, 2013), Mayor)
	  var entrada4 = festival.nuevaEntrada(0, 'B',new Fecha(2, 10, 2013), Mayor)
	  var entrada5 = festival.nuevaEntrada(1, 'B',new Fecha(2, 10, 2013), Mayor)
	  
	  combo.agregar(entrada)
	  combo.agregar(entrada2)
	  combo.agregar(entrada2)
	  combo.agregar(entrada3)
	  combo.agregar(entrada4)
	  combo.agregar(entrada5)
	  
	  try{
	  festival.vender(combo)
	  }
	  catch{
	    case e: EntradaYaVendidaException => {
	    
	  Assert.assertEquals(true,!(festival.entradasVendidas.contains(entrada)))
	  Assert.assertEquals(true,!(festival.entradasVendidas.contains(entrada2)))
	  Assert.assertEquals(true,!(festival.entradasVendidas.contains(entrada3)))
	  Assert.assertEquals(true,!(festival.entradasVendidas.contains(entrada4)))
	  Assert.assertEquals(true,!(festival.entradasVendidas.contains(entrada5)))
	    }
	    
	  }

	  
	 

	  
	  
  
  }
    
    
    @Test
  def testSeCambiaElPrecioBaseDeUnaCategoriaDeUnaBanda(){
      
      Categoria.crearCategoria('categoria5, 560)
      
      val rollings = new Banda(Categoria('categoria5))
      
      Categoria.modificar('categoria5, 120)
      
       Assert.assertEquals(120,rollings.getValorCategoria)
      
         
    }
  
    
      @Test
  def testSeCambiaLaCategoriaDeUnaBanda(){
      
      Categoria.crearCategoria('categoria5, 560)
      Categoria.crearCategoria('categoria6, 900)
      
      val rollings = new Banda(Categoria('categoria5))
      
      rollings.cambiarCategoria(Categoria('categoria6))
      
       Assert.assertEquals(Categoria('categoria6),rollings.categoria)
      
         
    }
    
  
}

