package com.lespea.facebook.qualifier.q3

import org.scalatest.WordSpec
import org.scalatest.matchers.MustMatchers

trait TestProblem extends MustMatchers { this: WordSpec ⇒
  def testProblem(prob: String, answer: Int) {
    Solver.solve(Problem(0, prob)).answer must equal(answer)
  }
}

final class Tests extends WordSpec with TestProblem  {
  val word = "HACKERCUP"

  "A solver" should {
    "handle blank strings" in {
      testProblem("", 0)
    }

    "return the correct answer" when {
      "the problem has no correct characters" in testProblem("zqxt", 0)

      "the problem has some correct characters" in testProblem("HACKER", 0)

      "the problem has only one of a multiple character" in testProblem("HACKERUP", 0)

      "the problem has all the correct characters, but in the wrong case" in testProblem(word.toLowerCase, 0)

      "the problem has the right word in the right sequence" in testProblem(word, 1)

      "the problem has the right word in the wrong sequence" in testProblem(word.reverse, 1)

      "the problem has the right word several times" in testProblem(word + word, 2)
    }

    "run fast" when {
      "a large string has no wanted chars" in testProblem("a" * 1000000, 0)
      "many copies of the word exists" in testProblem(word * 1000000, 1000000)
    }
  }
}