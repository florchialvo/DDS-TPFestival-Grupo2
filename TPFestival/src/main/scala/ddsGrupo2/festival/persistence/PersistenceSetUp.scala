package ddsGrupo2.festival.persistence

import uqbar.arena.persistence.Configuration
import ddsGrupo2.festival.model._
import scala.collection.mutable.Set

object PersistenceSetUp extends App {
  Configuration.configure()
  val valoresBaseQuilmes = Set(
    Sector("A", Fila(500, 10), Fila(400, 10), Fila(300, 10)),
    Sector("B", Fila(500, 10), Fila(500, 10), Fila(500, 10)),
    Sector("C", Fila(500, 100)))

  val valoresBaseRock = Set(
    Sector("A", Fila(100, 15), Fila(100, 15), Fila(100, 15)),
    Sector("B", Fila(500, 10), Fila(500, 10), Fila(500, 10)))

  FestivalesHome.agregarSiNoExiste(new Festival(valoresBaseQuilmes, null, "Quilmes Rock"))
  FestivalesHome.agregarSiNoExiste(new Festival(valoresBaseRock, null, "Festival Rock"))
  Console.println("Festivales:")
  Console.println(FestivalesHome.festivales)
}