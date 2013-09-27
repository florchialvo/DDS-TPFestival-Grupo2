package ddsGrupo2.festival.uiarena

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

@Observable
class NuevaEntradaWindow(owner: WindowOwner, model: Festival) extends Dialog[Festival](owner, model) {

	override def createFormPanel(mainPanel: Panel) = {
		var form = new Panel(mainPanel)
		form.setLayout(new ColumnLayout(2))
		new Label(form).setText("Sector")
		new TextBox(form)//.bindValueToProperty("sector")
		new Label(form).setText("Fila")
		new TextBox(form)//.bindValueToProperty("fila")
		new Label(form).setText("Fecha") //Debería ser un selector?
		new TextBox(form)//.bindValueToProperty("fecha")		
		new Label(form).setText("Descuento")
		var selectorTipoPersona = new Selector[TipoPersona](form)
		selectorTipoPersona.allowNull(false)
		selectorTipoPersona.bindItems(new ObservableProperty(this, "descuentosValidos"))
		new Label(form).setText("Precio")
		new TextBox(form)//.bindValueToProperty("precio")		
	}

	override def addActions(actions: Panel) = {
		new Button(actions)
			.setCaption("Aceptar")
			.onClick(new MessageSend(this, "aceptar"))
			.setAsDefault.disableOnError
	}

	def aceptar = this.close();
	
	def descuentosValidos:java.util.Set[TipoPersona] = model.descuentosValidos

}