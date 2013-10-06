package ddsGrupo2.festival.ui

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;

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

class VenderComboPage extends VenderPage {
  
   val labelPrecioCombo = new Label("precioCombo")
    labelPrecioCombo.setOutputMarkupId(true)
    
    form.add(labelPrecioCombo)

  var combo = new Combo(FestivalesHome.getFestival)
  self = this

  //Solo para mostrar la lista
  var entradaSeleccionada: String = ""
  val listaEntradas: ListChoice[String] = new ListChoice("entradaSeleccionada",
    new PropertyModel[String](this, "entradaSeleccionada"),
    new EntradasModel(combo))

  val botonCombo = new AjaxSubmitLink("agregarAlCombo") {
    override def onSubmit(destino: AjaxRequestTarget, form: Form[_]) {
      try {
        destino.add(panelFeedback);
        agregarAlCombo(destino)
      } catch {
        case e: EntradaYaAgregadaException =>
          self.error(e.getMessage())
        case e: EntradaYaVendidaException =>
          self.error("La entrada no puede agregarse, ya esta vendida")
      }
    }
  }
   
   val botonQuitarCombo = new AjaxSubmitLink("quitarDelCombo") {
    override def onSubmit(destino: AjaxRequestTarget, form: Form[_]) {
    
    	quitarDelCombo(destino);
    
    }
  }

  form.add(botonCombo)
  form.add(botonQuitarCombo)
  form.add(listaEntradas)
  listaEntradas.setNullValid(false)
  listaEntradas.setOutputMarkupId(true)

  override def entradaAVender() {
    try {
      this.entrada.venderCombo(combo)
      this.info("El Combo fue vendido con exito")
      combo = new Combo(FestivalesHome.getFestival)
      entradaSeleccionada = ""
      //Reinicio la lista
      listaEntradas.setChoices(new EntradasModel(combo))
    } catch {
      case e: ComboVacioException =>
        this.error(e.getMessage())
    }
  }

  def agregarAlCombo(destino: AjaxRequestTarget) {
    this.entrada.agregarEntradaAlCombo(combo)
    this.actualizarPrecioCombo(destino)
    destino.add(listaEntradas)
  }
  
  def quitarDelCombo(destino: AjaxRequestTarget)
  {
     this.entrada.quitarEntradaDelCombo(this.entradaSeleccionada,combo)
    this.actualizarPrecioCombo(destino)
    destino.add(listaEntradas)
  }
  
  def actualizarPrecioCombo(destino: AjaxRequestTarget){
    this.calcularPrecioCombo()
     destino.add(labelPrecioCombo)
  }

  def calcularPrecioCombo() {
    entrada.calcularPrecioCombo(combo)
  }
  
}

class EntradasModel(var combo: Combo) extends AbstractReadOnlyModel[java.util.List[String]] {
  override def getObject(): java.util.List[String] = combo.entradas.map(ent => ent.nombre)
}