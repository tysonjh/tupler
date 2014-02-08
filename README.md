# Tupler

A macro that converts case classes into a sequence of string tuples e.g. `Seq[(String, String)]`. The implementation
is naive and just calls `.toString()` on the fields of the case class.

## Usage

The method `Tupler.toTupleSeq` is used to convert the case class, it requires an implicit `Writes` instance. The
`Writes` may be created using the `Tupler.tupleWrites` macro.

## Example

    case class Cat(name: String, colour: String, age: Int)
    implicit val writes: Writes[Cat] = Tupler.tupleWrites[Cat]

    val floydCat = Cat("floyd", "grey", 6)
    val result = Tupler.toTupleSeq(floydCat)

    assert(result === Seq(("name", "Floyd"), ("colour", "grey"), ("age", "6")))

## Installing Tupler

  1. clone the repository

  2. publish locally

    `sbt publish-local`

  3. include the dependency in your (Scala v2.10) project

    `libraryDependencies += "com.tysonjh" %% "tupler" % "0.1-SNAPSHOT"`

## Notes

Tupler is in very early development stages. Currently it's purpose is to sate my interest with macros. The intent is
to explore its usefulness towards creating URL parameters.
