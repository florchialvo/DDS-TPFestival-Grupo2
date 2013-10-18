package ddsGrupo2.festival.model

import org.uqbar.commons.utils.Observable
import collection.JavaConversions._

import java.io.Serializable;

@Observable
class EntradaApplicationModel(val festival: Festival) extends Serializable {
  //Inicializacion por defecto
  var fechaNoche = this.fechas.head
  var sector = this.sectores.head
  var fila = this.filas.head
  var numButaca = this.butacas.head
  var tipoPersona = this.descuentosValidos.head
  var cliente = this.clientes.head
  var puntoDeVenta = this.puntosDeVenta.head

  var precio: Double = 0
  var precioCombo: Double = 0

  def build() = {
    if (festival.esAnticipada)
      new EntradaAnticipada(festival, festival.valorBase(fila, sector), festival.noche(fechaNoche), tipoPersona, sector, fila, numButaca, cliente, puntoDeVenta)
    else
      new Entrada(festival, festival.valorBase(fila, sector), festival.noche(fechaNoche), tipoPersona, sector, fila, numButaca, cliente, puntoDeVenta)
  }

  def calcularPrecio() {
    precio = EntradaApplicationModel.this.build.precio
  }

  def calcularPrecioCombo(unCombo: Combo) {
    precioCombo = unCombo.precioTotal()
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

  def quitarEntradaDelCombo(nombreEntrada:String,unCombo: Combo) {
    unCombo.quitar(nombreEntrada)
  }
    
  def venderCombo(unCombo: Combo) {
    festival.vender(unCombo)
  }

  def sectores: java.util.List[Char] = festival.sectores.toList
  def filas: java.util.List[Int] = List.range(1, 1 + this.cantFilas)
  def butacas: java.util.List[Int] = List.range(1, 1 + this.cantButacas)
  def fechas: java.util.List[Fecha] = festival.fechas.toList
  def descuentosValidos: java.util.List[TipoPersona] = festival.descuentosValidos.toList

  def clientes: java.util.List[String] = List("Juan","Pepe","Mario","Kevin")
  def puntosDeVenta: java.util.List[Int] = List(1,2,3,4,5)
  
  def cantFilas = festival.cantFilas(sector)
  def cantButacas = festival.cantButacas(sector, fila)
}