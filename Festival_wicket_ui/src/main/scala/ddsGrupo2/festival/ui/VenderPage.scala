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
  
  //Para que actualize el precio al principio
  this.calcularPrecio

  val labelPrecio = new Label("precio")
  labelPrecio.setOutputMarkupId(true)

  var self = this
  val categorias = new DropDownChoice("tipoPersona", new ComponentPropertyModel("descuentosValidos"))
  val puntoDeVenta: DropDownChoice[Int] = new DropDownChoice("puntoDeVenta", new ComponentPropertyModel("puntosDeVenta"))
  val cliente: DropDownChoice[String] = new DropDownChoice("cliente", new ComponentPropertyModel("clientes"))
   
  cliente.setNullValid(false) 
  puntoDeVenta.setNullValid(false)
  categorias.setNullValid(false)
  
  categorias.setOutputMarkupId(true)
  cliente.setOutputMarkupId(true)
  puntoDeVenta.setOutputMarkupId(true)
  
  val buttonVender = new ButtonAction[EntradaYaVendidaException](this, "vender",
    { () => this.vender() })

  setUp(buttonVender)

  agregarListaComponentesParaActualizarPrecio(List(sector, filas, butacas, categorias,cliente,puntoDeVenta))

  def calcularPrecio() = entrada.calcularPrecio()

  def vender() {
    this.entrada.venderEntrada()
    this.info("Entrada vendida con exito")
  }

  override def setUp(buttonVender: Button) {
    super.setUp(buttonVender)
    this.addOptions()
    this.addPrecio()
  }

  def addOptions() {
    form.add(categorias)
    form.add(puntoDeVenta)
    form.add(cliente)
  }
  
  def addPrecio(){
    form.add(labelPrecio)
  }

  
  
  //TO DO: Calcular precio con valores default 
  
  def agregarListaComponentesParaActualizarPrecio(list: List[FormComponent[_]]) {
    for (elem <- list) {
      elem.add(new AjaxFormComponentUpdatingBehavior("onchange") {
        override def onUpdate(target: AjaxRequestTarget) = {
          self.calcularPrecio()
          target.add(labelPrecio);
        }
      })
    }
  }

}
