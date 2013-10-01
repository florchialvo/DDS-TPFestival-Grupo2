package ddsGrupo2.festival.model

import org.uqbar.commons.utils.Observable

import java.io.Serializable;

@Observable
class EntradaApplicationModel(val festival: Festival) extends Serializable {
  //Inicializacion por defecto
  var fechaNoche = this.fechas.head
  var sector = this.sectores.head
  var fila = this.filas.head
  var numButaca = this.butacas.head
  var tipoPersona = this.descuentosValidos.head

  var precio: Double = 0

  def build() = {
    if (festival.esAnticipada)
      new EntradaAnticipada(festival, festival.valorBase(fila, sector), festival.noche(fechaNoche), tipoPersona, sector, fila, numButaca)
    else
      new Entrada(festival, festival.valorBase(fila, sector), festival.noche(fechaNoche), tipoPersona, sector, fila, numButaca)
  }

  
  def calcularPrecio() {
    precio = EntradaApplicationModel.this.build.precio
  }

  def calcularPrecioCombo(unCombo : Combo) {
    precio = unCombo.precioTotal()
  }
  
  def venderEntrada() {
    festival.vender(EntradaApplicationModel.this.build)
  }

  def anularEntrada() {
    festival.cancelar(EntradaApplicationModel.this.build)
  }

  def agregarEntradaAlCombo(unCombo: Combo) {
    unCombo.agregar(EntradaApplicationModel.this.build)
  }

  def venderCombo(unCombo: Combo) {
    festival.vender(unCombo)
  }
  
  def sectores = festival.sectores
  def filas = List.range(1, 1 + this.cantFilas)
  def butacas = List.range(1, 1 + this.cantButacas)
  
  def cantFilas = festival.cantFilas(sector)
  def cantButacas = festival.cantButacas(sector, fila)
  def fechas = festival.fechas
  def descuentosValidos = festival.descuentosValidos
}