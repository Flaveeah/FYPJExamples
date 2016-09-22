/* regular expression derivative

re / c is defined as the regular expression obtained by removing the leading character c from re. (English definition)

(math definition)

()  / c     ---> Phi
Phi / c     ---> Phi
c'  / c     ---> ()                     if c' == c
c'  / c     ---> Phi                    otherwise
re* / c     ---> (re/c) re*             (note, because re* == (()+(re re*)))
re1+re2 / c ---> (re1/c) + (re2/c)
re1re2 / c  ---> (re1/c)re2             if () not in re1
re1re2 / c  ---> (re1/c)re2 + re2/c     if () in re1


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
case class Star(re1:RE) extends RE
case class Concat(re1:RE, re2:RE) extends RE
case class Choice(re1:RE, re2:RE) extends RE


def pp(r:RE):String = r match{
  case Eps => "()"
  case Phi => "Phi"
  case Lit(c) => c.toString
  case Star(re) => pp(re) ++ "a"
  case Concat(re1,re2) => "(" ++ pp(re1) ++ pp(re2) ++ ")"
  case Choice(re1,re2) => "(" ++ pp(re1) ++ "+" ++ pp(re2)  ++ ")"

}


/*
  checking for epsilon containment
    () in ()
    () in r*
    () not in Phi
    () not in c
    () in re1 + re2 if (() in re1) || (() in re2)
    () in re1re2 if (() in re1) && (() in re2)
 */
def epsIn(re:RE):Boolean = re match {
  case Eps => true
  case Star(_) => true
  case Phi => false
  case Lit(_) => false
  case Choice(re1,re2) => epsIn(re1) || epsIn(re2)
  case Concat(re1,re2) => epsIn(re1) && epsIn(re2)
}

def deriv(re:RE, c:Char):RE = re match {

  // ()/c  -->  Phi
  case Eps => Phi

  // Phi/c --> Phi
  case Phi => Phi

  // c'/c --> ()  if c' == c
  case Lit(c1) if c1 == c => Eps

  // c'/c --> Phi
  case Lit(c1) => Phi

  // re*/c --> (re/c) re*
  case Star(re1) => Concat(deriv(re1,c),re)

  // re1+re2/c --> (re1/c)+(re2/c)
  case Choice(re1,re2) => Choice(deriv(re1,c),deriv(re2,c))

   // re1 re2 /c --> (re1/c)re2 + re2/c   if () in re1
  case Concat(re1,re2) if epsIn(re1) => Choice(Concat(deriv(re1,c),re2),deriv(re2,c))

  // re1 re2/c --> (re1/c)re2  if () not in re1
  case Concat(re1,re2) => Concat(deriv(re1,c),re2)
}


// TODO #1: define a simplification function RE
/*
  ()re ---> re
  re() ---> re
  Phi re ---> Phi
  re Phi  ---> Phi
  Phi+re ---> re
  re+Phi ---> re
  re+re ---> re
  ()* ---> ()
  Phi* ---> ()
 */

def simp(re:RE):RE = re match {
  case Concat(Eps, re) => simp(re)
  case Concat (re,Eps) => simp(re)
  case Concat(Phi,re) => simp(Phi)
  case Concat(re,Phi) => simp(Phi)
  case Concat(re1,re2) => Concat(simp(re1),simp(re2))
  case Choice(Phi,re) => Choice(simp(Phi),simp(re))
  case Choice(re,Phi) => Choice(simp(re),simp(Phi))
  case Choice(re1,re2) => Choice(simp(re1),simp(re2))
  case Star(Eps) => Eps
  case Star(Phi) =>Eps
  case Star(re1) => Star(simp(re1))
  case re => re
}

// TODO #2: define a regular expression matching function

/*
  s to be a string or a List[Char]

  a string s can be matched with a regular expression re,  iff,
     1) s is "" and epsIn(re) or
     2) s is (c s1) (e.g., s == "hello", c is 'h', s1 is "ello") and (s1 can be matched with re/c)
*/

def mat(s:List[Char],re:RE): Boolean = s match{
  // s is ""
  //    1) s is "" and epsIn(re)
  case Nil => epsIn(re)

  // 2) s is (c s1) (e.g., s == "hello", c is 'h', s1 is "ello") and (s1 can be matched with re/c)
  case (c::s1) => mat(s1,simp(deriv(re,c)))
}


val s = "hello".toList
val re_h = Lit('h')
val re_e = Lit('e')
val re_l = Lit('l')
val re_o = Lit('o')
val re1 = Star(Choice(re_h,Choice(re_e,Choice(re_l,re_o))))
mat(s,re1)

/*
e.g.
s = "hello"
re = (h+e+l+o)*

s matched re  --->
"hello" matched (h+e+l+o)* --->
"ello" matched ((h+e+l+o)*)/h --->
"ello" matched ()(h+e+l+o)* ---> (simp)
"ello" matched (h+e+l+o)* --->
"llo" matched  ((h+e+l+o)*)/e --->
"llo" matched  (h+e+l+o)* --->
...
"o" matched ((h+e+l+o)*)/h -->
"o" matched (h+e+l+o)* -->
"" matched ((h+e+l+o)*)/o --->
"" matched (h+e+l+o)* --->
() in (h+e+l+o)* ---> true
*/
//TODO #1, translate the following math into scala test example

val s1 = "hello".toList
val re_h1 = Lit('h')
val re_e1= Lit('e')
val re_l1 = Lit('l')
val re_o1 = Lit('o')
val re_i1 = Lit('i')
val re2 = Concat(re_h1,Concat(re_e1,Concat(re_l1,Concat(re_l1,Choice(re_o1,re_i1)))))
mat(s1,re2)
pp(re2)


// TODO #2, test the following example
/*
s2 = "abaac"
re2 = ((ab+a)(baa+a))(c+ac)
*/

val s2 = "abaac".toList
val re_a = Lit('a')
val re_b = Lit('b')
val re_c = Lit('c')
val re3 =  Concat(Concat(Choice(Concat(re_a,re_b),re_a),Choice(Concat(re_b,Concat(re_a,re_a)),re_a)),Choice(re_c,Concat(re_a,re_c)))

mat(s2,re3)
pp(re3)

pp(deriv(re3,'a')    )
pp(simp(deriv(re3,'a') ) )

// TODO #3, apply simplification in the matchedWith method, in particular at the location where deriv() is applied.

