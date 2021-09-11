package wafna.streaminess

import akka.stream.scaladsl.Source

import scala.annotation.tailrec

object Collatz extends App {

  def collatz(floor: BigInt): List[BigInt] = {
    @tailrec def impl(n: BigInt, steps: List[BigInt]): List[BigInt] = {
      if (n < floor)
        n :: steps
      else if (0 == n % 2)
        impl(n / 2, n :: steps)
      else {
        val n1 = 3 * n + 1
        impl(n1 / 2, n1 :: n :: steps)
      }
    }
    impl(floor, List.empty)
  }

  Systematizer { implicit system =>
    Source(BigInt(1001).until(BigInt(5001), BigInt(2)))
      .map(n => n -> collatz(n))
      .runForeach {
        case (n, steps) =>
          println(s"[${steps.length}] ${steps.reverse.mkString(",")}")
      }
  }
}
