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

import ddsGrupo2.festival.model._

class EntradaBasicPage extends WebPage {

  var entrada: EntradaApplicationModel = new EntradaApplicationModel(FestivalesHome.getFestival)
  val panelFeedback = new FeedbackPanel("feedback").setOutputMarkupId(true);
  val form = new Form("entradaForm", this.createModel)
  val puntoDeVenta: DropDownChoice[Int] = new DropDownChoice("puntoDeVenta", new ComponentPropertyModel("puntosDeVenta"))
  val cliente: DropDownChoice[String] = new DropDownChoice("cliente", new ComponentPropertyModel("clientes"))
  val sector: DropDownChoice[Char] = new DropDownChoice("sector", new ComponentPropertyModel("sectores"))
  val filas: DropDownChoice[Int] = new DropDownChoice("fila", new ComponentPropertyModel("filas"))
  val butacas: DropDownChoice[Int] = new DropDownChoice("numButaca", new ComponentPropertyModel("butacas"))
 
  cliente.setNullValid(false) 
  puntoDeVenta.setNullValid(false)
  sector.setNullValid(false)
  filas.setNullValid(false)
  butacas.setNullValid(false)

  cliente.setOutputMarkupId(true)
  puntoDeVenta.setOutputMarkupId(true)
  butacas.setOutputMarkupId(true)
  filas.setOutputMarkupId(true)
  sector.setOutputMarkupId(true)

  val buttonVolver = new Button("volver") {
    override def onSubmit() {
      this.setResponsePage(classOf[MenuPage])
    }
  }

  def setUp(actionButton: Button) {
    form.add(panelFeedback)
    form.add(new DropDownChoice("fechaNoche", new ComponentPropertyModel("fechas")))
    form.add(sector)
    form.add(filas)
    form.add(butacas)
    form.add(cliente)
    form.add(puntoDeVenta)
    sector.add(new AjaxFormComponentUpdatingBehavior("onchange") {
      override def onUpdate(target: AjaxRequestTarget) = {
        target.add(filas)
        target.add(butacas)
      }
    })
    form.add(buttonVolver)
    form.add(actionButton)
    this.add(form)
  }

  def createModel: CompoundPropertyModel[EntradaApplicationModel] = {
    new CompoundPropertyModel(this.entrada)
  }
}