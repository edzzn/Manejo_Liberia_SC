package lib

/**
  * Created by edzzn on 6/29/17.
  */
class Estudiante(val cedula : String, var nombre : String, var apellido : String) extends Serializable{

  override def toString : String = s"$cedula\t$nombre\t$apellido"

  def edit(nombreN : String, apellidoN : String): Unit ={
    nombre = nombreN
    apellido = apellidoN
  }

}