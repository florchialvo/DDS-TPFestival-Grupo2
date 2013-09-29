package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._
import org.apache.wicket.feedback.FeedbackMessage

import ddsGrupo2.festival.model._
import ddsGrupo2.festival.model.exception._

class VenderPage extends EntradaBasicPage {

  val buttonVender = new ButtonAction[EntradaYaVendidaException](this, "vender",
    { () => this.entradaAVender() })

  setUp(buttonVender)

  def entradaAVender() {
    this.entrada.venderEntrada()
    this.info("Entrada vendida con exito")
  }

  override def setUp(buttonVender: Button) {
    super.setUp(buttonVender)
    this.addOptions()
  }
}
