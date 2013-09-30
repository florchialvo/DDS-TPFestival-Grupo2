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
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;

import ddsGrupo2.festival.model._
import ddsGrupo2.festival.model.exception._

class VenderPage extends EntradaBasicPage {
	val labelPrecio = new Label("precio")
	
    val botonPrecio = new AjaxSubmitLink("calcularPrecio") {
    override def onSubmit(destino: AjaxRequestTarget, form: Form) {
      entrada.calcularPrecio()
      destino.addComponent(labelPrecio);
    }
  }
  
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


  def addOptions() {
    form.add(new DropDownChoice("tipoPersona", this.descuentosValidos))
    form.add(botonPrecio)
    form.add(labelPrecio)
    labelPrecio.setOutputMarkupId(true)
  }
   
}