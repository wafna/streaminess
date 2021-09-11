package wafna.streaminess

import akka.NotUsed
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object Systematizer {
  def apply(f: ActorSystem[NotUsed] => Future[Any]): Unit = {
    implicit val system: ActorSystem[NotUsed] =
      ActorSystem(Behaviors.empty[NotUsed], "streaminess")
    implicit val executionContext: ExecutionContext = system.executionContext
    f(system).onComplete { t =>
      system.terminate()
      t match {
        case Success(r) => println(r)
        case Failure(e) => println(s"ERROR: ${e.getMessage}")
      }
    }
  }
}
