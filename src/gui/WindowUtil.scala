package gui

/**
  * Created by edzzn on 6/30/17.
  * Metodos Utiles para el manejo de ventanas
  */
object WindowUtil {

  def mjsAlerta(mensaje : String): Unit ={
    val dialog = new MensajeAlerta(mensaje)
    open(dialog)
  }

  import javax.swing._


  def open(obj : JDialog): Unit ={
    obj.pack()
    obj.setLocationRelativeTo(null)
    obj.setVisible(true)
  }
}
