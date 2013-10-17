package ddsGrupo2.festival.model
import java.io.Serializable
import collection.JavaConversions._

class Busqueda[T] (var buscador: Buscador[T]) extends Serializable{
  
  var resultado: java.util.List[T]= List()
  
  def buscar = 
    resultado = buscador.buscar
}