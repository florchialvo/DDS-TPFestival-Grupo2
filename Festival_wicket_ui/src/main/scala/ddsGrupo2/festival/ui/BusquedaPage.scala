package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.panel._
import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import collection.JavaConversions._
import ddsGrupo2.festival.model._
import org.apache.wicket.markup.html.list.ListView
import org.uqbar.commons.model.UserException


abstract class BusquedaPage(val panelBuscador: PanelBuscador) extends WebPage {

  val panelFeedback = new FeedbackPanel("feedback").setOutputMarkupId(true)
  val form = new Form[Busqueda[_]]("buscadorForm", this.createModel)
  val panelResultados = this.crearPanelResultados
  val self = this
  
  def getModelObject() = form.getModelObject()
  
  val buttonVolver = new Button("volver") {
    override def onSubmit() {
      setResponsePage(classOf[MenuPage])
    }
  }
  
  val botonBuscar = new AjaxSubmitLink("buscar") {
    override def onSubmit(destino: AjaxRequestTarget, form: Form[_]) {
      try{
    	destino.add(panelFeedback)
        getModelObject().buscar
        destino.add(panelResultados)
      }catch{
        case e: UserException =>
        self.error(e.getMessage())
      }
    }
  }
  
  form.add(panelFeedback)
  form.add(panelBuscador)
  form.add(panelResultados)
  form.add(botonBuscar)
  form.add(buttonVolver)
  form.add(new Label("titulo", titulo))
  add(form)

  def titulo:String
  def mostrarResultado(item: ListItem[Nothing])
  
  def crearPanelResultados: WebMarkupContainer = {
    val panel = new WebMarkupContainer("panelResultados")
    panel.setOutputMarkupId(true);
    panel.add(
      new ListView("listaResultados", new ComponentPropertyModel("resultado")) {
        def populateItem(item: ListItem[Nothing]) = mostrarResultado(item)
      })
    return panel
  }

  def createModel: CompoundPropertyModel[Busqueda[_]] = {
    new CompoundPropertyModel(new Busqueda(panelBuscador.buscador))
  }
}