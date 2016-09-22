class Person(n:String,i:String) {
  private val name:String = n
  private val id:String   = i
  def getName():String = name
  def getId():String = id
}

trait NightOwl {
  def stayUpLate():Unit
}

class Student(n:String, i:String, g:Double) extends Person(n,i) with NightOwl {
  private var gpa = g
  def getGPA() = gpa
  def setGPA(g:Double) =
  {
    gpa = g
  }
  override def stayUpLate():Unit =
  {
    println("woohoo")
  }
}

class Staff(n:String, i:String, sal:Double) extends Person(n,i) {
  private var salary = sal
  def getSalary() = salary
  def setSalary(sal:Double) =
  {
    salary = sal
  }
}