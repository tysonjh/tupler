package com.tysonjh
import scala.language.reflectiveCalls
import scala.reflect.macros._
import language.experimental.macros

/**
 * Created by tysonjh on 2/7/2014.
 */
object TuplerMacroImpl {
  def tupleWritesImpl[A: c.WeakTypeTag](c: Context): c.Expr[Writes[A]] = {
    import c.universe._
    val tpe = weakTypeOf[A]
    val fields = tpe.declarations.collect {
      case field if field.isMethod && field.asMethod.isCaseAccessor => field.name
    }

//    println("fields: " + fields)

    val fieldsToSeq = fields.map {
      field => q"(${field.toTermName.toString}, o.$field.toString())"
    }.toSeq

//    println("fieldsToSeq: " + fieldsToSeq)

    val quasi = q"""
        new Writes[$tpe] {
          def toTupleSeq(o: $tpe): Seq[(String, String)] = Seq(${fieldsToSeq: _*})
        }
      """

//    println("Quasi: " + quasi)

    c.Expr[Writes[A]](quasi)
  }
}