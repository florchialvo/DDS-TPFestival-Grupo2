package festival.model

import scala.collection.mutable.ArrayBuffer

class Combo {

  val entradas: ArrayBuffer[Entrada] = ArrayBuffer()
 
  val unFestival:Festival
  
  def agregar(entrada:Entrada) = entradas += entrada

  def precioTotal():Double = entradas.map(_.precio).sum
  
  def venderEn(unFestival:Festival) = {
	try{
	   vendidas = ArrayBuffer()
 	   for(entrada<-entradas){
      unFestival.vender(entrada)
      vendidas += entrada
 	   }
	}catch(EntradaYaVendidaException e){
	   for(entrada<-vendidas)  unFestival.cancelar(entrada);
	   throw e
	}
  
}