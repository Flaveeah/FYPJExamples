val l1 = List(1,2,3,4)
val l2 = l1.map(x => x*2)
val l3 = l1 map(_*2)

/*
> x substitute the elements in l1 and * 2 to every element
> (x => x*2) is a lambda expression which can be shorten to (_*2)
> The . between l1 & map is optional
*/

/*---------------------------------------------------------------------------*/

/*
  l.foldLeft(b)(f) aggregate all the elements in l
  with the binary function f and the base value b.
 */

val l4 = l1.foldLeft(2)((x,y) => x*y)

// In essence the above is computing
// (((0 + 1) + 2) + 3) + 4


/*
l.foldRight(b)(f) aggregate all the elements in l with the
binary function f and the base value b with right associativity.
 */

val l5 = l1.foldRight(2)((x,y) => x*y)

//In essence the above is computing
//0 + (1 + (2 + (3 + 4)))

val l6 = l1.foldLeft(0)((x,y) => x-y)
// In essence the above is computing
// (((0 - 1) - 2) - 3) - 4


val l7 = l1.foldRight(0)((x,y) => x-y)
// In essence the above computing
// 0 - (1+ (2 + ( 3 + 4 )))

// ∴ the foldLeft & foldRight will return the same results if x + y
//   but not in the case where x - y 　