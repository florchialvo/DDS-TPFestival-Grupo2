package ddsGrupo2.festival.model

import ddsGrupo2.festival.model.exception._
import scala.collection.mutable.ArrayBuffer
import java.io.Serializable

class Combo(val unFestival: Festival) extends Serializable{

    val entradas: ArrayBuffer[Entrada] = ArrayBuffer()


    def agregar(entrada: Entrada) {
      unFestival.validarEntrada(entrada)
      if(entradas.exists(e => e.estasVendida(entrada.fila, 
          entrada.sector,entrada.numButaca, entrada.fecha))) throw new EntradaYaAgregadaException("La entrada no puede agregarse al combo")
      entradas += entrada
    }
      
    def precioTotal(): Double = entradas.map(_.precio).sum

    def venderEn(unFestival: Festival) = {
            for (entrada <- entradas) {
                unFestival.vender(entrada) 
            }
        }


    def descuento(): Double = if (this.precioTotal > 1000) 0.1 else 0

    def precio(): Double = this.precioTotal * (1 - this.descuento)
    
    def estaVacio() = entradas.isEmpty
    }