package reservaDeStock
import scala.collection.mutable.ArrayBuffer

abstract class Producto {

  val interesados: ArrayBuffer[Interesado] = ArrayBuffer()
  
  var stockMax: Int = 0
  var stockMin: Int = 0
  var stock: Int = 0
  var nombre: String = ""
  var puntoDePedido: Int = 0

  def stockMax_(max: Int) = stockMax = max
  def stockMin_(min: Int) = stockMin = min
  def stock_(cantidad: Int) = stock = cantidad
  def nombre_(nomb: String) = nombre = nomb
  def puntoDePedido_(cantidad: Int) = puntoDePedido = cantidad

  def hayStock() = stock > 0

  def consumir(cantidad: Int) = stock -= cantidad

  def excede(cantidad: Int): Boolean = {
    stock + cantidad > stockMax
  }

  def reservate(inventario: Inventario) = {
    if (this.hayStock()) {
      this.descontarStock(1)
      inventario.agregarReservado(this)
    } else {
      this.fabricate(inventario)
    }
  }

  def descontarStock(cant: Int) = {
    this.stock -= cant
  }
  
  def incrementarStock(cant: Int) = {
    this.stock += cant
  }

  def fabricate(inventario: Inventario)


def saleComponente(cantidad:Int) = { 
   	   for(interesado <- interesados)
   	     interesado.sale(this,cantidad)
	}
  
def entraComponente(cantidad:Int) = { 
   	   for(interesado <- interesados)
   	     interesado.entra(this,cantidad)
	}

def tenesStockMinimo = stockMin <= stock

def excedente(cantidad: Int) = (stock+cantidad) - stockMax 

	def agregarInteresado(interesado: Interesado) =
	  	interesados+=(interesado)
<<<<<<< HEAD

=======

>>>>>>> 62ac88272456879a8d9fc700a5725e3aba58c62d
}
