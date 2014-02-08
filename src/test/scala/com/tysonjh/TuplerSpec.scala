package com.tysonjh

import org.scalatest.FunSpec

/**
 * Created by tysonjh on 2/7/2014.
 */
class TuplerSpec extends FunSpec {
  describe("a Tupler macro should") {
    it("make a tuple from a case class") {
      case class Cat(name: String, colour: String, age: Int)
      implicit val writes: Writes[Cat] = Tupler.tupleWrites[Cat]

      val floydCat = Cat("Floyd", "grey", 6)
      val result = Tupler.toTupleSeq(floydCat)

      assert(result === Seq(("name", "Floyd"), ("colour", "grey"), ("age", "6")))
    }
  }
}
