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
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import collection.JavaConversions._
import ddsGrupo2.festival.model._


abstract class BusquedaPage extends WebPage {

  val form = new Form[Busqueda[_]]("buscadorForm", BusquedaPage.this.createModel)
  val panelResultados = BusquedaPage.this.crearPanelResultados
  
  def getModelObject() = form.getModelObject()
  
  val buttonVolver = new Button("volver") {
    override def onSubmit() {
      setResponsePage(classOf[MenuPage])
    }
  }
  
  val botonBuscar = new AjaxSubmitLink("buscar") {
    override def onSubmit(destino: AjaxRequestTarget, form: Form[_]) {
      getModelObject().buscar
      destino.add(panelResultados)
    }
  }
  
  agregarPanelBuscador(form)
  form.add(panelResultados);
  form.add(botonBuscar)
  form.add(buttonVolver)
  form.add(new Label("titulo", titulo))
  add(form)

  def titulo:String
  def mostrarResultado(item: ListItem[Nothing])
  def crearBuscador: Buscador[_]
  def agregarPanelBuscador(form: Form[Busqueda[_]])
  
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
    new CompoundPropertyModel(new Busqueda(crearBuscador))
  }
}