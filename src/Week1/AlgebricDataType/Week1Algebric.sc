/*sealed requires all extensions to the trait Exp must be declared
in the same module (same scala sheet)
*/

//case makes a class “pattern-matchable”


sealed trait Exp
case class Val(v:Int) extends Exp
case class Plus(e1:Exp, e2:Exp) extends Exp
case class Minus(e1:Exp, e2:Exp) extends Exp
case class Mult(e1:Exp, e2:Exp) extends Exp
case class Div(e1:Exp, e2:Exp) extends Exp



def simp(e:Exp): Exp = e match {

  case Val(v) => e

    //Plus
  case Plus(Val(0), e2) => simp(e2) // 0 + e2 = e2
  case Plus(e1,e2) => Plus(simp(e1), simp(e2))

    //Minus
  case Minus(e1, Val(0)) => simp(e1) // e1 - 0 = e1

    //Plus & Minus
  case Minus(Plus(e1,e2),e3) if e2 == e3 => simp(e1) // (e1 + e2) - e3(which is also e2)

    //Minus
  case Minus(e1,e2) => Minus(simp(e1),simp(e2))

  //Multiply
  // if = a pattern matching guard to the case

  case Mult(e1, e2) if (e1 == 0 || e2 == 0) => Val(0) // 0 * e2 = 0 || e1 * 0 = 0
  case Mult(e1,e2) => Mult(simp(e1), simp(e2))

    //Divide
  case Div(Val(0),e2) => Val(0)
  case Div(e1,e2) if e1 == e2 => Val(1)
  case Div(e1,e2) => Div(simp(e1),simp(e2))

}


Plus(Val(1),Plus(Val(2),Plus(Val(3),Plus(Val(4),Val(5)))))

Minus(Val(3), Val(2))
Minus(Plus(Val(5),Val(3)),Val(6))

Mult(Val(0),Val(3))
Mult(Val(1), Val(0))
Mult(Val(3),Val(3))

Div(Val(3), Val(0))
Div(Val(3), Val(3))
Div(Val(4),Val(3))







