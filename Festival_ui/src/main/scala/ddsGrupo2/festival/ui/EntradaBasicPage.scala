package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.panel._
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

import collection.JavaConversions._

import ddsGrupo2.festival.model._

class EntradaBasicPage extends WebPage {

  var entrada: EntradaBuilder = null
  val panelFeedback = new FeedbackPanel("feedback").setOutputMarkupId(true);
  val form = new Form("entradaForm", this.createModel)

  val buttonVolver = new Button("volver") {
    override def onSubmit() {
      this.setResponsePage(classOf[MenuPage])
    }
  }

  def setUp(actionButton: Button) {
    form.add(panelFeedback)
    form.add(new DropDownChoice("fechaNoche", this.fechas))
    val sector: DropDownChoice = new DropDownChoice("sector", this.sectores)
    val filas: DropDownChoice = new DropDownChoice("fila", this.filas)
    val butacas: DropDownChoice = new DropDownChoice("numButaca", this.butacas)

    sector.setNullValid(false)
    filas.setNullValid(false)
    butacas.setNullValid(false)

    butacas.setOutputMarkupId(true)
    filas.setOutputMarkupId(true)

    form.add(sector)
    form.add(filas)
    form.add(butacas)
    sector.add(new AjaxFormComponentUpdatingBehavior("onchange") {
      override def onUpdate(target: AjaxRequestTarget) = {
        target.addComponent(filas)
        target.addComponent(butacas)
      }
    })
    form.add(buttonVolver)
    form.add(actionButton)
    this.add(form)
  }

  def addOptions() {
    form.add(new DropDownChoice("tipoPersona", this.descuentosValidos))
  }

  def descuentosValidos: java.util.List[TipoPersona] =
    new java.util.ArrayList[TipoPersona](entrada.festival.descuentosValidos)

  def sectores: java.util.List[Char] =
    new java.util.ArrayList[Char](entrada.festival.sectores)

  def filas: java.util.List[Int] =
    new java.util.ArrayList[Int](List.range(1, 1 + entrada.festival.cantFilas(entrada.sector)))

  def butacas: java.util.List[Int] =
    new java.util.ArrayList[Int](List.range(1, 1 + entrada.festival.cantButacas(entrada.sector, entrada.fila)))

  def fechas: java.util.List[Fecha] =
    new java.util.ArrayList[Fecha](entrada.festival.fechas)

  def createModel: CompoundPropertyModel = {
    this.entrada = new EntradaBuilder(FestivalesHome.getFestival)
    entrada.fechaNoche = this.fechas.head
    entrada.sector = this.sectores.head
    entrada.fila = this.filas.head
    entrada.numButaca = this.butacas.head
    entrada.tipoPersona = this.descuentosValidos.head
    new CompoundPropertyModel(this.entrada)
  }
}