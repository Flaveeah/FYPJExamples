  /*
 1) + indicates that A is a covariant type parameter,
  hence List[Nothing] <: List[A] for any A .

 2) To use List[Nothing] must always use List[+A]

 3) Nothing is the bottom type in the sub-typing hierarchy.
  */

sealed trait List[+A]
/*
  case object introduces a singleton value constructor
  so don't have to always type out Nil()
*/

  case object Nil extends List[Nothing]
  case class Cons[A](x: A, xs:List[A]) extends List[A]


def y = Cons(1,Cons(2,Nil))

def length[A](l:List[A]):Int = l match {
  case Nil => 0
  case Cons(_,xs) => 1 + length(xs)
}