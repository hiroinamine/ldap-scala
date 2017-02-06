package com.example

import com.unboundid.ldap.sdk.LDAPURL
import ldap.sdk.ROSimpleAuthConnectionProvider

import scala.util.{Failure, Success, Try}

class LDAPNormationService(host: String, port: Int) {

  def findName(userLogon: String): String = {
    Try {
      val connProvider = new ROSimpleAuthConnectionProvider(
        "CN=Teste UNEAR,OU=Usuarios_Grupos,OU=U-NEAR,OU=LMCORP,DC=lmcorp,DC=local",
        "LDAP@2017",
        host, port)

      val ldapurl = new LDAPURL(
        s"ldap:///OU=LMCORP,DC=lmcorp,DC=local?cn?sub?(sAMAccountName=$userLogon)")

      val name = for {
        conn <- connProvider
        entry <- conn.search(ldapurl.toSearchRequest).headOption
      } yield {
        entry.valuesFor("cn").mkString
      }
      connProvider.close
      name.getOrElse("not found")
    } match {
      case Success(name) => name
      case Failure(error) => error.getMessage
    }
  }

}
