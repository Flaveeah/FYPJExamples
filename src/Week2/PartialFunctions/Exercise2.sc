// regular expression can be described as the following algebraic data type
// implement a pretty print function which prints a regular expression,
// e.g. pp(Star(Choice(L('a'),Seq(Empty,Phi)))) ---> (a+(()(\\)))*

/*
def pp(r:RE):String = r match
{
  case Phi   => "(\\)"
  case Empty => "()"
  case L(c)  => c.toString
  // TODO
}
*/

/*
regular expression
.*(codebunk).*

"" match with ()
Phi means empty language, no string can ever match Phi
"a" matches with a
"aaaaaa" matches with a*  because for all "a", "a" matches with a
"ab" matches with ab because ab is a concatenation, where "a" matches a and "b" matches b
"ab" matches with (ab)+c because "ab" matches with ab
"c" matches with (ab)+c because "c" matches with c

re ::= ()    // empty string or epsilon
     | Phi   // empty set or empty language
     | c     // character
     | re*   // 0 or more repetition
     | re re // concatenation
     | re+re // choice
*/

sealed trait RE
case object Eps extends RE
case object Phi extends RE
case class Lit(c:Char) extends RE
case class Star(re:RE) extends RE
case class Concat(re1:RE, re2:RE) extends RE
case class Choice(re1:RE, re2:RE) extends RE

def pp(r:RE):String = r match{
  case Eps => "()"
  case Phi => "Phi"
  case Lit(c) => c.toString
  case Star(re) => pp(re) ++ "a"
  case Concat(re1,re2) => pp(re1) ++ pp(re2)
  case Choice(re1,re2) => pp(re1) ++ "+" ++ pp(re2)

}

val re = Choice(Concat(Lit('a'), Lit('b')) ,Lit('c'))
pp(re)


