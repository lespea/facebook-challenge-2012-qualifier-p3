/**
 * Alfredo Spaghetti really likes soup, especially when it contains alphabet pasta.
 * Every day he constructs a sentence from letters, places the letters into a bowl
 * of broth and enjoys delicious alphabet soup.
 *
 * Today, after constructing the sentence, Alfredo remembered that the Facebook Hacker Cup starts today!
 * Thus, he decided to construct the phrase "HACKERCUP". As he already added the letters to the broth,
 * he is stuck with the letters he originally selected. Help Alfredo determine how many times he can
 * place the word "HACKERCUP" side-by-side using the letters in his soup.
 *
 *
 * Input
 *
 * The first line of the input file contains a single integer
 * 		T: the number of test cases.
 * 		T lines follow, each representing a single test case with a sequence of
 * 			upper-case letters and spaces: the original sentence Alfredo constructed.
 *
 *
 * Output
 *
 * Output T lines, one for each test case.
 * For each case, output "Case #t: n", where t is the test case number (starting from 1)
 * and n is the number of times the word "HACKERCUP" can be placed side-by-side using the
 * letters from the sentence.
 *
 *
 * Constraints
 *
 * 1 < T ≤ 20
 * Sentences contain only the upper-case letters A-Z and the space character
 * Each sentence contains at least one letter, and contains at most 1000 characters, including spaces
 *
 *
 */

package com.lespea.facebook.qualifier.q3

import scala.util.matching.Regex
import java.io.FileWriter
import java.io.File

final object Run extends App {
  if (args.length < 1)
    throw new RuntimeException("You must provide at least 1 argument as a problem source")

  val parser = """^(.+)(\.[^.]+)$""".r
  args.par foreach { f ⇒
    val out = try {
      val filename = parser.findFirstMatchIn(f) match {
        case Some(m) ⇒ m.group(1) + "_SOLVED" + m.group(2)
        case _       ⇒ f + "_SOLVED"
      }

      new FileWriter(filename)
    } catch { case e: Exception ⇒ throw e }

    try {
      out.write(Solver.solveProblem(new File(f).toURI) map { _.outStr } mkString "\n")
      out.write("\n")
    } finally {
      try { out.close() } catch { case _: Exception ⇒ }
    }
  }
}