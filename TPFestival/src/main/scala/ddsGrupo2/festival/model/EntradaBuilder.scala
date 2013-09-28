package ddsGrupo2.festival.model

import org.uqbar.commons.utils.Observable

@Observable
class EntradaBuilder(val festival:Festival) {
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
}