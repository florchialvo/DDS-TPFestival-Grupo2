package festival.model

object Categoria{
  var instances: Map[Symbol, Categoria] = Map()
  
  def apply(s: Symbol) = instances.apply(s)
  
  def crearCategoria(key :Symbol, valor: Int) = instances+= key -> new Categoria(valor)
}

class Categoria(valor: Int) {

  def getValor = valor
  
}