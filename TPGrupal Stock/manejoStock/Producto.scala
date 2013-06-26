package stock.model

abstract class Producto(var stock:Int) {
	
	def reservate(inventario:Inventario){
	  if(this.hayStock()){
	    this.descontarStock()
	    inventario.agregarReservado(this)
	  }else{
		  this.fabricate(inventario)
	  }
	}
	
	def fabricate(inventario:Inventario)
	
	def hayStock() = stock > 0
	
	
	def descontarStock()= stock = stock-1
	
	def incrementarStock(cantidad:Int)= stock= stock+cantidad
	
}

class ComponenteCompuesto(stock:Int, componentes:List[Producto]) extends Producto(stock:Int) {
 
  def fabricate(inventario:Inventario){
    componentes.foreach {componente => componente.reservate(inventario)}
    inventario.agregarFabricado(this)
  }
  
}

class MateriaPrima(stock:Int) extends Producto(stock:Int){
   
    def fabricate(inventario:Inventario) {
    throw new StockException()
  }
}