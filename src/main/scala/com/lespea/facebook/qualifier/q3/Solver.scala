package com.lespea.facebook.qualifier.q3

import scala.annotation.switch
import scala.collection.mutable.HashMap

final object Solver {
  def solve(p: Problem): Int = {
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

    val answer = if (counts.isEmpty) 0 else counts.values.min.toInt
    p.answer = Some(answer)

    return answer
  }
}