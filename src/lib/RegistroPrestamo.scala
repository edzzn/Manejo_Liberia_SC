package lib

/**
  * Created by edzzn on 6/29/17.
  */
class RegistroPrestamo(obj : Any) extends Serializable{
  var reg = scala.collection.mutable.ListBuffer.empty[Prestamo]
  // Permite la deserealizacion
  if (obj.getClass == this.getClass){
    this.reg = obj.asInstanceOf[RegistroPrestamo].reg
  }

  override def toString: String = reg.mkString(" \n")

  def add(prestamo: Prestamo) : Unit ={
    reg += prestamo
  }

  def editPrestamo(id: String, estudiante : Estudiante, libro : Libro, fechaPrestamo : String, horaPrestamo : String): Unit ={
    var prestamo = getPrestamo(id)
    prestamo.edit(estudiante, libro,  fechaPrestamo, horaPrestamo)
  }

  def deletePrestamo(id : String): Unit ={
    val estudiante = getPrestamo(id)
    reg -= estudiante
  }

  def getPrestamo(id : String): Prestamo ={
    for (prestamo <- reg){
      if (prestamo.id.equals(id)) {
        return prestamo
      }
    }
    return null
  }

  def validatePrestamo(id : String) : Boolean = {
    for (prestamo <- reg){
      if (prestamo.id.equals(id)){
        return true
      }
    }
    return false
  }
}