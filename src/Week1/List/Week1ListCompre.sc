
/*
for ... yield denotes a sequence comprehension.

> for every single element in nats (if the num can be divided by 2) return, then +1 to nat yield means take out

> This syntax is like the Select statement in database

> This feature is also available in C# and its called LinQ

> People prefer this statement as it is closer to the select statement

> (<-) thie symbol means inclusive e.g (for every nat that is in nats (nat <- nats))

*/

val nats: List[Int] = List(1,2,3,4)
val even_plus_1: List[Int] = for (nat <- nats if nat % 2 == 0) yield (nat + 1)

//find the length of the list
val l1 = List(1,2,3)
l1.length

//Concatenation
val l2 = List(-1,-2,-3)
val l3 = l1 ++ l2

//Reverse
val l4 = l3.reverse

//Min & max

val min = l1.min
val max = l1.max

//Sorting
val sl1 = l3.sortWith(_<_)

//Sublist
val sl1a = l3.take(3)
val sl1b = l3.drop(3)
val sl2a = l3.drop(6)  // return empty list









