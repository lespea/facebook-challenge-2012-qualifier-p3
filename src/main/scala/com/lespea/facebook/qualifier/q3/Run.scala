package com.lespea.facebook.qualifier.q3

import scala.io.BufferedSource

final object Run extends App {
  def getProblems(file: BufferedSource) =
    file.getLines.drop(1)

  println("Stub")
}