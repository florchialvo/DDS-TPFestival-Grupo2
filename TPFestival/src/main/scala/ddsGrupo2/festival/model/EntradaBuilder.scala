package ddsGrupo2.festival.model

import org.uqbar.commons.utils.Observable

import java.io.Serializable;

@Observable
class EntradaBuilder(val festival: Festival) extends Serializable {
  var tipoPersona: TipoPersona = null
  //TODO: hay que usar fechas validas del festival
  var fechaNoche: Fecha = (new Fecha).fechaActual()
  var sector: Char = 0
  var fila: Int = 0
  var numButaca: Int = 0
  var precio: Double = 0

  def build() = {
    if (festival.esAnticipada)
      new EntradaAnticipada(festival, festival.valorBase(fila, sector), festival.noche(fechaNoche), tipoPersona, sector, fila, numButaca)
    else
      new Entrada(festival, festival.valorBase(fila, sector), festival.noche(fechaNoche), tipoPersona, sector, fila, numButaca)
  }

  def descuentosValidos = festival.descuentosValidos
  
  def calcularPrecio() {
    precio = this.build.precio
  }

  def calcularPrecioCombo(unCombo : Combo) {
    precio = unCombo.precioTotal()
  }
  
  def venderEntrada() {
    festival.vender(this.build)
  }

  def anularEntrada() {
    festival.cancelar(this.build)
  }

  def agregarEntradaAlCombo(unCombo: Combo) {
    unCombo.agregar(this.build)
  }

  def venderCombo(unCombo: Combo) {
    festival.vender(unCombo)
  }
  
 
}