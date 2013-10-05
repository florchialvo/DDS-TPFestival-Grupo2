package ddsGrupo2.festival.ui

import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.bindings.PropertyAdapter
import org.uqbar.arena.widgets.CheckBox
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.commons.utils.ApplicationContext
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend
import collection.JavaConversions._
import ddsGrupo2.festival.model._
import org.uqbar.commons.utils.Observable
import org.uqbar.arena.windows.SimpleWindow

@Observable
class NuevaEntradaWindow(owner: WindowOwner, model: EntradaApplicationModel) extends SimpleWindow[EntradaApplicationModel](owner, model) {

  override def createFormPanel(mainPanel: Panel) = {
    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))
    this.setTitle("Calculadora de Precios de Entradas")
    new Label(form).setText("Sector:")
    new TextBox(form).bindValueToProperty("sector")
    new Label(form).setText("Fila:")
    new TextBox(form).bindValueToProperty("fila")
    new Label(form).setText("Ingrese una Butaca")
    new TextBox(form).bindValueToProperty("numButaca")    
    new Label(form).setText("Categoria:")
    var selectorTipoPersona = new Selector[TipoPersona](form)
    selectorTipoPersona.allowNull(false)
    selectorTipoPersona.bindItemsToProperty("descuentosValidos")
    selectorTipoPersona.bindValueToProperty("tipoPersona")
    new Label(form).setText("Precio Total")
    var precio = new Label(form)
    precio.setWidth(100)
    precio.bindValueToProperty("precio")
  }

  override def addActions(actions: Panel) = {
    new Button(actions)
      .setCaption("Calcular Precio")
      .onClick(new MessageSend(this.getModelObject(), "calcularPrecio"))
      .setAsDefault.disableOnError
  }
}