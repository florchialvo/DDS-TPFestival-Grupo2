package reservaDeStock

class Interesado {

  def cumpleRequisitoSalida(componente:Producto, cantidad:Int): Boolean = false
  
  def cumpleRequisitoEntrada(componente:Producto, cantidad:Int): Boolean = false
  
  def salida(componente:Producto, cantidad:Int) = {}
  
  def entrada(componente:Producto, cantidad:Int) = {}
  
  def sale(componente: Producto, cantidad: Int) = {
    if (this.cumpleRequisitoSalida(componente, cantidad)) {
      this.salida(componente, cantidad)
      }
  }
  
  def entra(componente: Producto, cantidad: Int) = {
    if (this.cumpleRequisitoEntrada(componente, cantidad))
      this.entrada(componente, cantidad)
  }
}

