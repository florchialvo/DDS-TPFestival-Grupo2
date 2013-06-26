package reservaDeStockTest

import org.junit._
import org.junit.runner.RunWith
import org.junit.runner.RunWith
import org.junit.internal.runners.JUnit4ClassRunner
import reservaDeStock._

@RunWith(classOf[JUnit4ClassRunner])
class TestReserva {

  val fabrica: Fabrica = new Fabrica()
  val auto: ProductoCompuesto = new ProductoCompuesto()
  val puerta: MateriaPrima = new MateriaPrima()
  val rueda: MateriaPrima = new MateriaPrima()
  val ventana: MateriaPrima = new MateriaPrima()

  @Before
  def setUp = {
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
  }

  @Test
  def fabricaReservarAutoYElInventarioDeLaFabricaLoContiene = {

    fabrica.reservar(auto)

    Assert.assertEquals(fabrica.inventario.reservados(auto), 1)
  }

  @Test
  def fabricaReservarAutoYElStockBajaA4 = {

    fabrica.reservar(auto)

    Assert.assertTrue(auto.stock == 4)
  }

  @Test
  def fabricaReservar3AutosYElValordeReservadosEs3 = {

    fabrica.reservar(auto)
    fabrica.reservar(auto)
    fabrica.reservar(auto)

    Assert.assertEquals(fabrica.inventario.reservados(auto), 3)
  }

  @Test(expected = classOf[StockException])
  def fabricaReservaMateriaPrimaQueNoTieneStock: Unit = {
    val llanta: MateriaPrima = new MateriaPrima()
    llanta.stock_(0)
    fabrica.reservar(llanta)
  }

  @Test(expected = classOf[StockException])
  def fabricaReservaProductoQueNoTieneStockDeUnoDeSusComponentes: Unit = {
    rueda.stock_(2)
    auto.stock_(0)
    fabrica.reservar(auto)
    if (fabrica.inventario.reservados.contains(puerta))
      Assert.assertEquals(0, fabrica.inventario.reservados(puerta))
    else
      Assert.assertFalse("Se reservaron productos!",
        fabrica.inventario.reservados.contains(puerta))
  }
}