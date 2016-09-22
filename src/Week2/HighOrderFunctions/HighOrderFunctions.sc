//Function Composition

val l1 = List(1,2,3)
val l2 = l1.map(((x:Int) => x*2) compose (y => y+1))

// (f compose g)(x) is same as f(g(x))



val l3 = l1.map(((x:Int) => x*2) andThen (y => y+1))

// (f andThen g) is same as g(f(x))


/*
Definition of Compose and andThen

 object Function { // this is like a package
    trait Function1[+A][-B] {
       def apply(x:A):B {...}
       // apply() gives us
       // the syntactic sugar form like f(x)
       def compose[C](that:C=>A):C=>B =
       {
         (c:C) => this(that(c))
       }
       def andThen[C](that:B=>C):A=>C =
       {
         (a:A) => that(this(a))
       }
    }
}


def f (x:Int):Int =
{
	x * 2
}

def g (y:Int):Int =
{
	y + 1
}


f compose g -->

f.compose(g) -->

[f/this,g/that] (c:C) => this(that(c)) -->(beta)

(c:C) => f(g(c))

suppose we apply (f compose g)(3)

from the above, we note that f compose g --->* (c:C)=>f(g(c))
thus, ((c:C)=>f(g(c)))(3) ---> f(g(3)) --->* f(4) --->* 8
*/

