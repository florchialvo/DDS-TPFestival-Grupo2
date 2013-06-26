package reservaDeStockTest

import reservaDeStock.Fabrica
import reservaDeStock.ProductoCompuesto
import reservaDeStock.MateriaPrima
import org.junit.Test
import reservaDeStock.Auditoria

class TestManejoDeStock {
  
	val fabrica:Fabrica = new Fabrica()
  
	val volante:ProductoCompuesto = new ProductoCompuesto()
	volante.stock_=(2)
	  
	@Test
	def unaMateriaPrimaQuedaConStockMenorAlMinimoYGeneraPedidoDeCompra() {
               volante.agregarInteresado(Auditoria)
               volante.stockMin_=(2)
               volante.consumir(1)
               volante.saleComponente(1) 
	}
	
	
}