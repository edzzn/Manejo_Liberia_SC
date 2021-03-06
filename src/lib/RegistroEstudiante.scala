package lib

/**
  * Created by edzzn on 6/29/17.
  */

class RegistroEstudiante(obj : Any) extends Serializable{
  var reg = scala.collection.mutable.ListBuffer.empty[Estudiante]

  if (obj.getClass == this.getClass){
    this.reg = obj.asInstanceOf[RegistroEstudiante].reg
  }

  override def toString: String = reg.mkString(" \n")

  def add(estudiante: Estudiante) : Unit ={
    reg += estudiante
  }

  def editEstudiante(cedula : String, nombre : String, apellido : String): Unit ={
    var estudiante = getEstudiante(cedula)
    estudiante.edit(nombre, apellido)
  }

  def deleteEstudiante(cedula : String): Unit ={
    val estudiante = getEstudiante(cedula)
    reg -= estudiante
  }

  def getEstudiante(cedula : String): Estudiante ={
    for (estudiante <- reg){
      if (estudiante.cedula.equals(cedula)) {
        return estudiante
      }
    }
    return null
  }

  def validateEstudiante(cedula : String) : Boolean = {
    for (estudiante <- reg){
      if (estudiante.cedula.equals(cedula)){
        return true
      }
    }
    return false
  }
}