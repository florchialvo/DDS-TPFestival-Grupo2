package arenaUiPrueba
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.Application
import java.awt.Color
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Selector
import org.uqbar.arena.windows.MainWindow
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ContextFreeObservableProperty




class CalculadoraSimpleWindow(owner:WindowOwner) extends SimpleWindow[modelo](owner,(new modelo)) {

  
    override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Calculadora")
     this.setTaskDescription("")

    super.createMainTemplate(mainPanel)

  }

  def nuevoBotonCalculadora(caption:String, panel:Panel, metodoAEjecutar: String,w:Int,h:Int, to:Object)=
  
  {
    val boton = new Button(panel)
	   .setCaption(caption)
	   .onClick(new MessageSend(to, metodoAEjecutar))
	   .setFontSize(20)
	   
	   boton.setWidth(w)
	   boton.setHeigth(h)
	   
	
	  
	 
	
    
  }
  
  
	override def addActions(actionsPanel:Panel) = {
	  
	  
      actionsPanel.setLayout(new ColumnLayout(4))
	  
	  //YA SE QUE ESTA MAL HACER ESTO DE agregarNumero1... pero no se pueden mandar parametros
	  //y no se me ocurria como hacerlo sino
	   
	  nuevoBotonCalculadora("1",actionsPanel,"agregarNumero1",64,64,getModelObject)
	  nuevoBotonCalculadora("2",actionsPanel,"agregarNumero2",64,64,getModelObject)
	  nuevoBotonCalculadora("3",actionsPanel,"agregarNumero3",64,64,getModelObject)
	  nuevoBotonCalculadora("CE",actionsPanel,"resetear",64,64,getModelObject)
	  nuevoBotonCalculadora("4",actionsPanel,"agregarNumero4",64,64,getModelObject)
	  nuevoBotonCalculadora("5",actionsPanel,"agregarNumero5",64,64,getModelObject)
	  nuevoBotonCalculadora("6",actionsPanel,"agregarNumero6",64,64,getModelObject)
	  nuevoBotonCalculadora("+",actionsPanel,"sumar",64,64,getModelObject)
	  nuevoBotonCalculadora("7",actionsPanel,"agregarNumero7",64,64,getModelObject)
	  nuevoBotonCalculadora("8",actionsPanel,"agregarNumero8",64,64,getModelObject)
	  nuevoBotonCalculadora("9",actionsPanel,"agregarNumero9",64,64,getModelObject)
	  nuevoBotonCalculadora("-",actionsPanel,"restar",64,64,getModelObject)
	  nuevoBotonCalculadora("0",actionsPanel,"agregarNumero0",64,64,getModelObject)
	  nuevoBotonCalculadora("*",actionsPanel,"multiplicar",64,64,getModelObject)
	  nuevoBotonCalculadora("/",actionsPanel,"dividir",64,64,this)
	   nuevoBotonCalculadora("=",actionsPanel,"obtenerResultado",64,64,this)
	
	
	
	}
	
	def obtenerResultado()=
	{
	  getModelObject.obtenerResultado()
	  
	}
	
	def dividir() =
	{
	  
	  try{
	  getModelObject.dividir();
	  }
	  catch{
	    
	    case e:ArithmeticException =>{
	      
	      //NOSE PORQUE NO FUNCIONA
	      showInfo("Error!!: Division por 0!");
	      
	    
	    }
	  }
	  
	  
	}

	override def createFormPanel(mainPanel:Panel ) {
	
	 
    
   val labelVisor= new Label(mainPanel)
   
     labelVisor.setHeigth(100)
     labelVisor.setWidth(400)
     labelVisor.setFontSize(35)
   
    labelVisor.setBackground(Color.WHITE)
    labelVisor.bindValueToProperty("resultado")
    
    val actionsPanel=  new Panel(mainPanel)
 
    
	  
  showInfo("Error!!: División por cero!")
    
    
		

	
	}
}

object ConversorRunner extends Application with App {
	def createMainWindow():Window[_] = new CalculadoraSimpleWindow(this)
	start()
}
