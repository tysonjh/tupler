package com.tysonjh

/**
 * Created by tysonjh on 2/7/2014.
 */
trait Writes[A] {
  def toTupleSeq(o: A): Seq[(String, String)]
}
