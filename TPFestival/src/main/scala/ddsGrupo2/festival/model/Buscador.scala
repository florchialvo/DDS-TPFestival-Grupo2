package ddsGrupo2.festival.model
import java.io.Serializable


class Buscador (val coleccion:List[_]) extends Serializable{
  
  var resultado: List[_] = List()
  
  def buscar=
  	{ this.resultado = this.coleccion }
  

  


}