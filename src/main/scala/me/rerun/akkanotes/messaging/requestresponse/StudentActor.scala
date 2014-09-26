package me.rerun.akkanotes.messaging.requestresponse

import akka.actor.Actor
import akka.actor.ActorLogging
import me.rerun.akkanotes.messaging.protocols.TeacherProtocol._
import me.rerun.akkanotes.messaging.protocols.StudentProtocol._;
import akka.actor.Props

/*
 * The Student Actor class. 
 * 
 */

class StudentActor extends Actor with ActorLogging {

  def receive = {

    /*
     * This InitSignal is received from the DriverApp. 
     * On receipt, the Student sends a message to the Teacher actor. 
     * The teacher actor on receipt of the QuoteRequest responds with a QuoteResponse 
     */
    case InitSignal=> {
      val teacherRef=context.system.actorOf(Props[TeacherActor], "teacherActor") //Create Teacher Actor
      teacherRef!QuoteRequest
    }
    
    /*
     * The Student simply logs the quote received from the TeacherActor
     * 
     */
    case QuoteResponse(quoteString) => {
      log.info ("Received QuoteResponse from Teacher")
      log.info(s"Printing from Student Actor $quoteString")
    }

  }

}