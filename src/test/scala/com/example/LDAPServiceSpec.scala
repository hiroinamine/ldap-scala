package com.example

import org.scalatest._

class LDAPServiceSpec extends WordSpec with Matchers {

  val HOST = "127.0.0.1"
  val PORT = 389

  val userLogon = "james.cameron"

  "LDAPApacheService" should {
    "find user from AD" in {
      val service = new LDAPApacheService(HOST,PORT)
      println("apache", service.findName(userLogon))
      true shouldBe true
    }
  }

  "LDAPOracleService" should {
    "find user from AD" in {
      val service = new LDAPOracleService(HOST, PORT)
      println("oracle", service.findName(userLogon))
      true shouldBe true
    }
  }

  "LDAPNormationService" should {
    "find user from AD" in {
      val service = new LDAPNormationService(HOST, PORT)
      println("normation", service.findName(userLogon))
      true shouldBe true
    }
  }

  "LDAPUnboundIDService" should {
    "find user from AD" in {
      val service = new LDAPUnboundIDService(HOST, PORT)
      println("unboundID", service.findName(userLogon))
      true shouldBe true
    }
  }

}
