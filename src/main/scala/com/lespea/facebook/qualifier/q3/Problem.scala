package com.lespea.facebook.qualifier.q3

final case class Problem(num: Int, problem: String)

final case class SolvedProblem(num: Int, problem: String, answer: Int) {
  def this(p: Problem, a: Int) = this(p.num, p.problem, a)
}
final object SolvedProblem {
  def apply(p: Problem, a: Int) = new SolvedProblem(p, a)
}