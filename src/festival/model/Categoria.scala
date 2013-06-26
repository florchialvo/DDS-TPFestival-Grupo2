package festival.model

object Categoria{
  var instances: Map[Symbol, Categoria] = Map()
  
  def apply(s: Symbol) = instances.apply(s)
  
  def modificar(s: Symbol, valorNuevo: Int) = instances(s).valor = valorNuevo
  
  def crearCategoria(key :Symbol, valor: Int) = instances+= key -> new Categoria(valor)
}

class Categoria(var valor: Int) {

  def getValor = valor
  
}