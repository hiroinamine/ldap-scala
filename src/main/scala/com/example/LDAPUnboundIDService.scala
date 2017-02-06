package com.example

import com.unboundid.ldap.sdk.{LDAPConnection, LDAPURL}

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

class LDAPUnboundIDService(host: String, port: Int) {

  def findName(userLogon: String): String = {
    Try {
      val conn = new LDAPConnection(host, port)
      conn.bind("CN=Teste UNEAR,OU=Usuarios_Grupos,OU=U-NEAR,OU=LMCORP,DC=lmcorp,DC=local", "LDAP@2017")

      val ldapurl = new LDAPURL(
        s"ldap:///OU=LMCORP,DC=lmcorp,DC=local?cn?sub?(sAMAccountName=$userLogon)")
      val result = conn.search(ldapurl.toSearchRequest)

      val entries = result.getSearchEntries
      val first = entries.asScala.headOption

      conn.close()
      first.map(_.getAttributeValue("cn")).getOrElse("not found")
    } match {
      case Success(name) => name
      case Failure(error) => error.getMessage
    }
  }

}
