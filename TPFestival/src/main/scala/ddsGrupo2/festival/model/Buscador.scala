package ddsGrupo2.festival.model
import java.io.Serializable
import collection.JavaConversions._

class Buscador (val coleccion:List[_]) extends Serializable{
  
  var resultado: java.util.List[_]  = List()
  
  def buscar = 
    this.resultado = this.coleccion
}