package reservaDeStockTest

import reservaDeStock.Fabrica
import reservaDeStock.ProductoCompuesto
import reservaDeStock.MateriaPrima
import org.junit.Test
import reservaDeStock.Auditoria
import reservaDeStock.ExcesoDeStock
import reservaDeStock.NotificacionPorMail
import org.junit.Assert



class TestManejoDeStock {
  
	val fabrica:Fabrica = new Fabrica()
  
	val volante:MateriaPrima = new MateriaPrima()
	val auto: ProductoCompuesto = new ProductoCompuesto()
	  
	@Test
	def unaMateriaPrimaQuedaConStockMenorAlMinimoYGeneraPedidoDeCompra() {
      	volante.stock_=(2)         
      	volante.agregarInteresado(Auditoria)
        volante.stockMin_=(2)
        volante.consumir(1)
        volante.saleComponente(1) 
        Assert.assertTrue(Auditoria.cumpleRequisitoSalida(volante, 1))
	}
	
	@Test
	def unaMateriaPrimaTieneExcesoDeStock() {
		val excesoDeStock: ExcesoDeStock = new ExcesoDeStock(true)
		volante.agregarInteresado(excesoDeStock)
		volante.stockMax_=(5)
		volante.stock_=(4)
		volante.entraComponente(3)
		Assert.assertTrue(excesoDeStock.cumpleRequisitoEntrada(volante, 3))
	}
	
	@Test
	def unProductoFinalEsVendidoMasDe3VecesGenerandoUnMail(){
		val notificacion: NotificacionPorMail = new NotificacionPorMail(3)
		auto.agregarInteresado(notificacion)
		auto.stock_=(5)
		auto.consumir(4)
		auto.saleComponente(4)
		Assert.assertTrue(notificacion.cumpleRequisitoSalida(auto, 4))
	}
}