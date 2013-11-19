package ddsGrupo2.festival.model

import scala.collection.mutable.Set
import uqbar.arena.persistence.annotations.PersistentClass
import uqbar.arena.persistence.annotations.Relation
import org.uqbar.commons.model.Entity
import collection.JavaConversions._

@PersistentClass
class Noche(var bandas: Set[Banda], var fecha: Fecha) extends Entity {

  //Persistencia
  def this() = this(Set(), new Fecha)
  
  @Relation
  def getBandas(): java.util.Set[Banda] = bandas
  def setBandas(bs: java.util.Set[Banda]) = bandas = bs
  
  @Relation
  def getFecha(): Fecha = fecha
  def setFecha(f: Fecha) = fecha = f
  //

  def agregarBanda(banda: Banda) = bandas += banda

  def valorExtra = this.mayorValorBandaMayorCategoria.getValorCategoria

  def mayorValorBandaMayorCategoria = this.bandas.maxBy({ banda => banda.getValorCategoria })

  def correspondeA(unaFecha: Fecha) = unaFecha == fecha
}