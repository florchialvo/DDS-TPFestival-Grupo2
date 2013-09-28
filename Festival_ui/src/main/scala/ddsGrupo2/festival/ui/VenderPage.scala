package ddsGrupo2.festival.ui

import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._

import ddsGrupo2.festival.model._

class VenderPage extends AnularPage {
    var entrada: EntradaBuilder = null
//    override val form = new Form("entradaForm", this.createModel)

    override def addFields(form: Form) = {
        super.addFields(form)
        form.add(new DropDownChoice("sector", this.sectores))
        form.add(new TextField("fila"))
        form.add(new DropDownChoice("tipoPersona", this.descuentosValidos))
    }

    def descuentosValidos: java.util.List[TipoPersona] =
        new java.util.ArrayList[TipoPersona](this.entrada.festival.descuentosValidos)

    def sectores: java.util.List[Char] =
        new java.util.ArrayList[Char](this.entrada.festival.sectores)

    override def createModel: CompoundPropertyModel = {
        this.entrada = new EntradaBuilder(FestivalesHome.getFestival)
        return new CompoundPropertyModel(this.entrada)
    }
}