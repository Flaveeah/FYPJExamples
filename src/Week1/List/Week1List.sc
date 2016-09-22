/*
Algebraic data type and pattern matching are useful in
case of handling recursive/nested data structures,
e.g. a linked list.
 */

//List[A]: A can be any type e.g(Int, Double, class)
//A is a type variable (AKA generics)

sealed trait List[A]
  //Nil[A]() is an empty List
  //Nil() takes no argument, it’s always (almost) singleton.

  case class Nil[A]() extends List[A]

  /*
    Cons[A] not an empty List
    x must be >= 1
   */
  case class Cons[A](x:A, xs: List[A]) extends List[A]


val y = Cons(1, Cons(2, Nil()))

/*
    y is a List that contains 3 sets of lists. 1, (2,Nil())
    1 is the first element of x aka the list head
    2 is the first element of xs
    Nil() is the second element of xs
    ∴ xs (2,Nil()) is the tail of the list
 */

def length[A](l: List[A]): Int = l match {
  case Nil() => 0
    // _ is a wild card pattern, it can be matched with anything.
  case Cons(_,xs) => 1 + length(xs)
}

