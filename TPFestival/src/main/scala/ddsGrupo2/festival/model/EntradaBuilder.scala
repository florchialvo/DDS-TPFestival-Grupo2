package ddsGrupo2.festival.model

import org.uqbar.commons.utils.Observable

import java.io.Serializable;

@Observable
class EntradaBuilder(val festival:Festival) extends Serializable {
	var noche:Noche = null
	var tipoPersona:TipoPersona = null
	var fecha:Fecha = null
	var sector:Char = 0
	var fila:Int = 0
	var precio:Double = 0
	
	
	def build:Entrada = festival.nuevaEntrada(fila, sector, fecha, tipoPersona)
	def descuentosValidos = festival.descuentosValidos
	def calcularPrecio() {
	  fecha = new Fecha().fechaActual
	  precio = this.build.precio
	}
	
	def venderEntrada(){
	  fecha = new Fecha().fechaActual
	  festival.vender(this.build)
	}
	
	def anularEntrada(){
	  fecha = new Fecha().fechaActual
	  festival.cancelar(this.build)
	}
}