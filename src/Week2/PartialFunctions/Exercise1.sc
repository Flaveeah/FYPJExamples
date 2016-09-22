sealed trait Exp
case class Val(x:Int) extends Exp
case class Plus(e1:Exp, e2:Exp) extends Exp
case class Minus(e1:Exp, e2:Exp) extends Exp
case class Mult(e1:Exp, e2:Exp) extends Exp
case class Div(e1:Exp, e2:Exp) extends Exp


def eval(e:Exp): Option[Int] = e match {
  case Val(x) => Some(x)

  //Plus
  case Plus(e1,e2) => eval(e1) match {
    case None => None
    case Some(v1) => eval(e2) match {
      case None => None
      case Some(v2) => Some(v1+v2)
    }
  }

  //Minus
  case Minus(e1,e2) => eval(e1) match {
    case None => None
    case Some(v1) => eval(e2) match {
      case None => None
      case Some(v2) => Some(v1-v2)
    }
  }


   //Multiply
  case Mult(e1,e2) => eval(e1) match {
    case None => None
    case Some(v1) => eval(e2) match{
      case None => None
      case Some(v2) => Some(v1*v2)
    }
  }

   //Divide
  case Div(e1,e2) => eval(e1) match {
    case None => None
    case Some(v1) => eval(e2) match {
      case None => None
      case Some(0)  => None
      case Some(v2) => Some(v1/v2)
    }
  }

}

def eval1(v:Exp): PartialFunction[Exp,Exp] ={
  case (e1) if e1 != 0  => v/e1
}



