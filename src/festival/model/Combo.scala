package festival.model

import festival.model.exception.EntradaYaVendidaException
import scala.collection.mutable.ArrayBuffer
import festival.model.exception.EntradaYaVendidaException

class Combo {

  val entradas: ArrayBuffer[Entrada] = ArrayBuffer()
 
  val unFestival:Festival
  	  
  val vendidas: ArrayBuffer[Entrada] = ArrayBuffer()
  
  def agregar(entrada:Entrada) = entradas += entrada

  def precioTotal():Double = entradas.map(_.precio).sum
  
  def venderEn(unFestival:Festival) = {
	try{
 	  for(entrada<-entradas){
      unFestival.vender(entrada)
      vendidas += entrada
 	   }
	} catch {
	  case e: EntradaYaVendidaException => {
	    for(entrada<-vendidas)  unFestival.cancelar(entrada);
	    throw e
	  }
	}
  }
   
  def descuento():Double = if(this.precioTotal>1000) 0.1 else 0
  
  def precio():Double = this.precioTotal*(1-this.descuento)
 
}