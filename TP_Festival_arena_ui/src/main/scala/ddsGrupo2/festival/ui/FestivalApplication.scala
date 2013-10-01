package ddsGrupo2.festival.ui

import org.uqbar.arena.Application
import ddsGrupo2.festival.model._
import scala.collection.mutable.Set

object FestivalApplication extends Application with App {

  var festival: Festival = FestivalesHome.getFestival
  var entradaAppModel: EntradaApplicationModel = null

  override def createMainWindow() = {
    new NuevaEntradaWindow(this, new EntradaApplicationModel(festival))
  }

  start()
}