package ddsGrupo2.festival.model

import uqbar.arena.persistence.annotations.PersistentClass
import org.uqbar.commons.model.Entity
import uqbar.arena.persistence.annotations.PersistentField
import uqbar.arena.persistence.annotations.Relation

@PersistentClass
class Entrada(var festival: Festival, var valorBase: Int, var noche: Noche, var persona: TipoPersona,
  var sector: String, var fila: Int, var numButaca: Int, var cliente: String = "", var puestoDeVenta: Int = 0)
  extends Entity {

  //Persistencia
  def this() = this(null, 0, null, null, "", 0, 0, "", 0)

  @Relation
  def getFestival(): Festival = festival
  def setFestival(f: Festival) = (festival = f)

  @PersistentField
  def getValorBase(): Int = valorBase
  def setValorBase(vb: Int) = (valorBase = vb)

  @Relation
  def getNoche(): Noche = noche
  def setNoche(n: Noche) = (noche = n)
  
  @Relation
  def getPersona(): TipoPersona = persona
  def setPersona(p: TipoPersona) = (persona = p)

  @PersistentField
  def getSector(): String = sector
  def setSector(s: String) = (sector = s)
  
  @PersistentField
  def getFila(): Int = fila
  def setFila(d: Int) = (fila = d)  
  
  @PersistentField
  def getButaca(): Int = numButaca
  def setButaca(b: Int) = (numButaca = b)    
  
  @PersistentField
  def getCliente(): String = cliente
  def setCliente(c: String) = (cliente = c)

  @PersistentField
  def getPuestoDeVenta(): Int = puestoDeVenta
  def setPuestoDeVenta(p: Int) = (puestoDeVenta = p)
  //

  def fecha = noche.fecha
  def valorExtraPorNoche = noche.valorExtra
  def descuento = festival.descuento(persona, valorBase)
  def precio = this.valorExtraPorNoche + valorBase - this.descuento
  def nombre = "Fecha:" + this.fecha.toString() +
    "-Fila:" + this.fila.toString() +
    "-Sector:" + this.sector.toString() +
    "-Butaca:" + this.numButaca.toString()

  def nombreFestival = festival.nombre

  def estasVendida(unaFila: Int, unSector: String, unaButaca: Int, unaFecha: Fecha) =
    unaFila == fila && unSector == sector &&
      unaButaca == numButaca && noche.correspondeA(unaFecha)

  def bandas = noche.bandas
}

@PersistentClass
class EntradaAnticipada(festival: Festival, valorBase: Int, noche: Noche, persona: TipoPersona, sector: String, fila: Int, numButaca: Int, cliente: String = "", puntoDeVenta: Int = 0)
  extends Entrada(festival: Festival, valorBase: Int, noche: Noche, persona: TipoPersona, sector: String, fila: Int, numButaca: Int, cliente: String, puntoDeVenta: Int) {
  //Persistencia
  def this() = this(null, 0, null, null, "", 0, 0, "", 0)
  //
  
  override def precio = super.precio * (1 - EntradaAnticipada.descuento)
}

object EntradaAnticipada {
  val descuento: Double = 0.15
}