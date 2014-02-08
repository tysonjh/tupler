package com.tysonjh
import scala.language.experimental.macros

object Tupler extends App {
  /**
   * Creates a Writes by resolving the fields of the case class at compile-time.
   * @tparam A the case class type
   * @return a Writes during compilation
   */
  def tupleWrites[A]: Writes[A] = macro TuplerMacroImpl.tupleWritesImpl[A]

  /**
   * Convert a case class into a sequence of string tuples where
   * each tuple pair consists of a field name and the value with toString called on it.
   * @param o the case class instance to convert
   * @param wps the implicit Writes for the case class
   * @return a sequence of string tuples (field name, field value)
   */
  def toTupleSeq[T](o: T)(implicit wps: Writes[T]): Seq[(String, String)] = wps.toTupleSeq(o)

}
