package reservaDeStockTest


import festival.model._
import org.junit._
import org.junit.runner.RunWith
import org.junit.runner.RunWith
import org.junit.internal.runners.JUnit4ClassRunner
import reservaDeStock.Fabrica
import reservaDeStock.ProductoCompuesto
import reservaDeStock.Producto
import reservaDeStock.MateriaPrima
import exceptions.StockException

@RunWith(classOf[JUnit4ClassRunner])
class TestReserva {
  
  val fabrica:Fabrica = new Fabrica()
  
  val auto:ProductoCompuesto = new ProductoCompuesto()
  val puerta:MateriaPrima = new MateriaPrima()
  val rueda:MateriaPrima = new MateriaPrima()
  val ventana:MateriaPrima = new MateriaPrima()
 
  
  
  
  
  auto.stockMax_(20)
  auto.stockMin_(2)
  auto.stock_(5)
  auto.nombre_("Ferrari")
  auto.puntoDePedido_(3)
  
  auto.agregarComponente(puerta)
  auto.agregarComponente(puerta)
  
  auto.agregarComponente(ventana)
  auto.agregarComponente(ventana)
  
  auto.agregarComponente(rueda)
  auto.agregarComponente(rueda)
  auto.agregarComponente(rueda)
  auto.agregarComponente(rueda)
  
  puerta.stock_(5)
  rueda.stock_(4)
  ventana.stock_(10)
  

  
  
  
  @Test
  def fabricaReservarAutoYElInventarioDeLaFabricaLoContiene={
   
    fabrica.reservar(auto)
  
  Assert.assertTrue(fabrica.inventario.reservados.contains(auto))
  }
  
  @Test
  def fabricaReservarAutoYElStockBajaA4={
   
    fabrica.reservar(auto)
  
  Assert.assertTrue(auto.stock==4)
  }
  
    @Test
  def fabricaReservar3AutosYElValordeReservadosEs3={
   
    fabrica.reservar(auto)
    fabrica.reservar(auto)
    fabrica.reservar(auto)
    
    val dicAutos:Option[(Producto,Int)] = fabrica.inventario.reservados.find(p => (p._1.==(auto)&&p._2.==(3)))
     Assert.assertTrue(!(dicAutos.isEmpty))
  }
  
  
     @Test(expected = classOf[StockException])
  def fabricaReservaMateriaPrimaQueNoTieneStock={
   
       val llanta:MateriaPrima = new MateriaPrima()
       llanta.stock_(0)
       
    fabrica.reservar(llanta)

  }
  


}