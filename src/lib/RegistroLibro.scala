package lib

/**
  * Created by edzzn on 6/29/17.
  */

class RegistroLibro(obj : Any) extends Serializable{
  var reg = scala.collection.mutable.ListBuffer.empty[Libro]
  // Permite la deserealizacion
  if (obj.getClass == this.getClass){
    this.reg = obj.asInstanceOf[RegistroLibro].reg
  }

  override def toString: String = reg.mkString(" \n")

  def add(libro: Libro) : Unit ={
    reg += libro
  }

  def editLibro(isbn: String, nombre : String, autor : String, categoria : Categoria, numPag : Int, editorial: String, idioma : String): Unit ={
    var libro = getLibro(isbn)
    libro.edit(nombre, autor, categoria, numPag, editorial, idioma)
  }

  def deleteLibro(isbn : String): Unit ={
    val libro = getLibro(isbn)
    reg -= libro
  }

  def getLibro(isbn : String): Libro ={
    for (libro <- reg){
      if (libro.isbn.equals(isbn)) {
        return libro
      }
    }
    return null
  }

  def validateLibro(isbn : String) : Boolean = {
    for (libro <- reg){
      if (libro.isbn.equals(isbn)){
        return true
      }
    }
    return false
  }
}