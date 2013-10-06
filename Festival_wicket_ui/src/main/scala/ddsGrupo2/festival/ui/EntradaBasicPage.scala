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
  val sector: DropDownChoice[Char] = new DropDownChoice("sector", new ComponentPropertyModel("sectores"))
  val filas: DropDownChoice[Int] = new DropDownChoice("fila", new ComponentPropertyModel("filas"))
  val butacas: DropDownChoice[Int] = new DropDownChoice("numButaca", new ComponentPropertyModel("butacas"))

    sector.setNullValid(false)
    filas.setNullValid(false)
    butacas.setNullValid(false)

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