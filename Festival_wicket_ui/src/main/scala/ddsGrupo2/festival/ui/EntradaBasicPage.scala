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

  var entrada: EntradaApplicationModel = new EntradaApplicationModel(FestivalesHome.getFestival)
  val panelFeedback = new FeedbackPanel("feedback").setOutputMarkupId(true);
  val sectores: java.util.List[Char] =
    new java.util.ArrayList[Char](entrada.sectores)
  val form = new Form("entradaForm", this.createModel)

  val buttonVolver = new Button("volver") {
    override def onSubmit() {
      this.setResponsePage(classOf[MenuPage])
    }
  }

  def setUp(actionButton: Button) {
    form.add(panelFeedback)
    form.add(new DropDownChoice("fechaNoche", this.fechas))

    val opcionesFilas: IModel = new FilasModel(entrada)
    val opcionesButacas: IModel = new ButacasModel(entrada)

    val sector: DropDownChoice = new DropDownChoice("sector", this.sectores)
    val filas: DropDownChoice = new DropDownChoice("fila", opcionesFilas)
    val butacas: DropDownChoice = new DropDownChoice("numButaca", opcionesButacas)

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

  def filas: java.util.List[Int] =
    new java.util.ArrayList[Int](entrada.filas)

  def butacas: java.util.List[Int] =
    new java.util.ArrayList[Int](entrada.butacas)

  def fechas: java.util.List[Fecha] =
    new java.util.ArrayList[Fecha](entrada.fechas)

  def descuentosValidos: java.util.List[TipoPersona] =
    new java.util.ArrayList[TipoPersona](entrada.descuentosValidos)

  def createModel: CompoundPropertyModel = {
    new CompoundPropertyModel(this.entrada)
  }
}

class FilasModel(var entrada: EntradaApplicationModel) extends AbstractReadOnlyModel {
  override def getObject(): java.util.List[Int] =
    new java.util.ArrayList[Int](List.range(1, 1 + entrada.cantFilas))
}

class ButacasModel(var entrada: EntradaApplicationModel) extends AbstractReadOnlyModel {
  override def getObject(): java.util.List[Int] =
    new java.util.ArrayList[Int](List.range(1, 1 + entrada.cantButacas))
}
