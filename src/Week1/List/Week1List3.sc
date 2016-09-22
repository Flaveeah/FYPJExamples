/*
(scala) functional programming automatically help us
to identify the case objects and class ↓

    sealed trait List[+A]
    case object Nil extends List[Nothing]
    case class Cons[A](x:A, xs:List[A]) extends List[A]
 */


val x = (1,2)

def lenght[A](l:List[A]): Int =l match{
  case Nil => 0
  case x::xs => 1 + lenght(xs)
}


