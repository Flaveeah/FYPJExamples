/*

Given in a list of integers can you use a map to compute whether if its a odd or even.
return back true or false.
false true false true.


Compute the min element and max element in the list using fold left and fold right.
can try average as well.

 */

val l = List(1,2,3,4,5,6,7,8)

val l2 = l.map(_ % 2 == 1)


/*
  > Finding the max element in List
  > Starting from the left of the list,
    where the base class is the Minimum of what Int can hold (Int:MinValue = -2147483648)
    identify the max value in List l

    ∴ Starting from -2147483648 find the max value in the list

 */
// Minimum and Maximum using fold

val l3 = l.foldLeft(Int.MinValue)(_ max _)
val min = l.max
val l4 = l.foldRight(Int.MaxValue)(_ min _)
val max = l.min


// Average of the List using fold
val l5 = l.foldRight(0.0)(_ + _)/l.length
val l6 = l.foldLeft(0.0)(_ + _)/l.length