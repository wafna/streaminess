package wafna.streaminess

import akka.NotUsed
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import java.nio.file.Paths
import scala.concurrent._
import scala.util.{Failure, Success}

object Demo1 extends App {
  Systematizer { implicit system =>
    val source: Source[Int, NotUsed] = Source(1 to 100)
    source.runForeach(i => println(i))
  }
}

object Demo2 extends App {
  Systematizer { implicit system =>
    val source: Source[Int, NotUsed] = Source(1 to 100)
    val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
    factorials
      .map(num => ByteString(s"$num\n"))
      .runWith(FileIO.toPath(Paths.get("factorials.txt")))

  }
}

object Demo3 extends App {
  Systematizer { implicit system =>
    def lineSink(filename: String): Sink[String, Future[IOResult]] =
      Flow[String]
        .map(num => ByteString(s"$num\n"))
        .toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)
    val source: Source[Int, NotUsed] = Source(1 to 101)
    val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

    factorials.map(_.toString).runWith(lineSink("factorial2.txt"))

  }
}
