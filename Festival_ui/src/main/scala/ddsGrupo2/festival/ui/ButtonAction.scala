package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.panel._
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._

class ButtonAction[T <: RuntimeException] (
    val originPage: WebPage,
    val id : String,
	val action: () => Unit) extends Button(id) {
  

  override def onSubmit(){
       try{
          action.apply()
        }catch{
          case e: T =>
          originPage.error(e.getMessage())
        }
  }

}