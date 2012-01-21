package com.lespea.facebook.qualifier.q3

import scala.annotation.switch
import scala.collection.mutable.HashMap
import scala.io.{ BufferedSource, Source }
import java.net.URI

final object Solver {
  def solveProblem(problemSource: String): Seq[SolvedProblem] =
    solveProblem(new URI(problemSource))

  def solveProblem(uri: URI): Seq[SolvedProblem] =
    getProblems(Source.fromURI(uri)).zipWithIndex.map {
      case (p: String, i: Int) ⇒ Problem(i, p)
    }.toList.par.map(Solver.solve).toList

  def getProblems(file: BufferedSource) =
    file.getLines.drop(1)

  def solve(p: Problem): SolvedProblem = {
    val counts = HashMap[Char, Double]()

    p.problem filter {
      case c: Char ⇒ (c: @switch) match {
        case 'H' | 'A' | 'C' | 'K' | 'E' | 'R' | 'U' | 'P' ⇒ true
        case _ ⇒ false
      }
    } foreach {
      case c: Char ⇒ {
        counts(c) = counts.getOrElse(c, 0.0) + ((c: @switch) match {
          case 'H' | 'A' | 'K' | 'E' | 'R' | 'U' | 'P' ⇒ 1.0
          case 'C'                                     ⇒ 0.5
        })
      }
    }

    SolvedProblem(p, if (counts.isEmpty || counts.size != 8) 0 else counts.values.min.toInt)
  }
}