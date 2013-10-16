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

abstract class BuscadorPage extends WebPage {

  var buscador: Buscador = new Buscador(coleccionBusqueda)
  val form = new Form("buscadorForm", this.createModel)
  val panelResultados = this.crearPanelResultados
  
  val buttonVolver = new Button("volver") {
    override def onSubmit() {
      setResponsePage(classOf[MenuPage])
    }
  }
  
  val botonBuscar = new AjaxSubmitLink("buscar") {
    override def onSubmit(destino: AjaxRequestTarget, form: Form[_]) {
      buscador.buscar;
      destino.add(panelResultados)
    }
  }

  form.add(this.panelResultados);
  form.add(botonBuscar)
  form.add(buttonVolver)
  form.add(new Label("titulo", titulo))
  this.add(form)

  def titulo:String
  def mostrarResultado(item: ListItem[Nothing])
  def coleccionBusqueda:List[_]
  
  def crearPanelResultados: WebMarkupContainer = {
    val panel = new WebMarkupContainer("panelResultados")
    panel.setOutputMarkupId(true);
    panel.add(
      new ListView("listaResultados", new ComponentPropertyModel("resultado")) {
        def populateItem(item: ListItem[Nothing]) = mostrarResultado(item)
      })
    return panel
  }

  def createModel: CompoundPropertyModel[Buscador] = {
    new CompoundPropertyModel(this.buscador)
  }
}