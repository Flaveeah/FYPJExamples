/*
There are situations in which we need to deal with
partial functions,naturally, we can use the Option
type in Scala (similar to the Maybe type in Haskell).
 */

def div(x:Int)(y:Int) :Option[Int] = {
  if (y==0) None else Some(x/y)
}

val xs = List(0,1,2) map (x => div(2)(x))

val ys = xs filter (x => x.nonEmpty)

val zs = ys map(x => x match { case None => None case Some(y) => y})



//Using Scala builtin PartialFunction
def divs(v:Int): PartialFunction[Int,Int] = {
  case (s:Int) if s != 0 => v/s
}
val fs = List(0,1,2) collect ( divs(2) )