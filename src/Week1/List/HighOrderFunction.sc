/*
= lambda calculus =

== Language syntax ==

expressions E ::=  x => E | E(E)
values      v ::=  x => E | 1 , 2, 3 | True | False | ...

TODO: add the match { case ... } expression
TODO: add the pattern matching reduction rule

=== reduction rules ==


(beta-reduction)

-------------------------
 (x=>E1)(v) ~~~> [v/x]E1

(call by value rule #1)

  E1 ~~~> (x=>E3)
-------------------------
 E1(E2)  ~~~~> (x=>E3)(E2)

(call by value rule #2)
   E2  ~~~~> v
-------------------------
(x=>E3)(E2) ~~~~> (x=>E3)(v)

(alpha-renaming)
    y is free in E (y is never used in E)
-------------------------
(x=>E) ===  (y=> [y/x]E)

=== example ===
ex1.
(x=>x+1)(1) ~~~~>(beta) [1/x] x+1 ~~~~> 1+1 ~~~> 2
ex2.
(x=>x+1)((y=>y*y)(2)) ~~~~>(#2) (x=>x+1)(4) ~~~~>(beta) [4/x]x+1 ~~~~> 5
        ^^^^^^^^^^^^^
   sub derivation:  (y=>y*y)(2) ~~~~>(beta) [2/y]y*y ~~~~> 2*2 ~~~> 4

[v/x] denotes a substitution

ex3.
(x=>x+1)((x=>x*x)(2)) ~~~~~>(alpha) (x=>x+1)((y=>y*y)(2))

*/
case object Nil extends List[Nothing]
case class Cons [A](x:A, xs:List[A]) extends List[A]


sealed trait List[+A] {
  def map[B](f:A=>B):List[B] = this match {
    case Nil => Nil
    case x :: xs => f(x) :: xs.map(f)
  }
  /*
  def map[B](this:List[A], f:A=>B):List[B] = this match {
    case Nil => Nil
    case x::xs => f(x)::xs.map(f)
  }
  */
}


val l = List(1,2,) // Cons(1,Cons(2,Nil))

val l2 = l.map( x => x + 1)

// Cons(1,Cons(2,Nil)).map(x => x + 1) ~~~~>(beta)

//  [Cons(1,Cons(2,Nil))/this, x=>x+1 / f]
//    this match {
//      case Nil => Nil
//      case x::xs => f(x)::xs.map(f)
//   }   ~~~~> (alpha)

//  Cons(1,Cons(2,Nil)) match {
//    case Nil => Nil
//    case x::xs => (y=>y+1)(x)::xs.map(y=>y+1)
//  }  ~~~~>

//  [1/x, Cons(2,Nil)/xs]  (y=>y+1)(x)::xs.map(y=>y+1)
//  ~~~~~>

//  (y=>y+1)(1)::Cons(2,Nil).map(y=>y+1) ~~~>

//  2 ::  Cons(2,Nil).map(y=>y+1) ~~~~>  2 :: 3 :: Nil
//        ^^^^^^^^^^^^^^^^^^^^^^^^
//        sub derivation Cons(2,Nil).map(y=>y+1) ~~~~> (beta)
//                     [Cons(2,Nil)/this, y=>y+1/f]
//                           this match {
//                             case Nil => Nil
//                             case x::xs => f(x)::xs.map(f)
//                           }
//                         ~~~~~~~>
//						Cons(2,Nil) match { case Nil => Nil
//                            case x::xs => (y=>y+1)(x)::xs.map(y=>y+1)
//                       } ~~~~>
//                     [2/x, Nil/xs] (y=>y+1)(x)::xs.map(y=>y+1)
//                     ~~~~> (y=>y+1)(2) :: Nil.map(y=>y+1)
//                     ~~~~> 3 :: Nil
//