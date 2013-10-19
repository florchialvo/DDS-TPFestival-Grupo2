package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.panel.Panel
import ddsGrupo2.festival.model.Buscador
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.CompoundPropertyModel

abstract class PanelBuscador extends Panel("panelBuscador") {
  var form: Form[Buscador[_]] = null
  
  def setUp = { 
    form = new Form("buscador")  
    createModel(form)
    addComponents(form)
    add(form)
  }

  def createModel(form: Form[Buscador[_]])
  def buscador = form.getModelObject()
  def addComponents(form: Form[Buscador[_]])
}