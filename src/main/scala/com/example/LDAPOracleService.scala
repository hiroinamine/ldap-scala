package com.example

import java.util
import javax.naming._
import javax.naming.directory.{InitialDirContext, SearchResult}

import scala.util.{Failure, Success, Try}

class LDAPOracleService(host: String, port: Int) {

  def findName(userLogon: String): String = {
    Try {
      // Set up the environment for creating the initial context
      val env = new util.Hashtable[String, String]()
      // Authenticate as S. User and password "mysecret"
      env.put(Context.SECURITY_AUTHENTICATION, "simple")
      env.put(Context.SECURITY_PRINCIPAL,
        "CN=Teste UNEAR,OU=Usuarios_Grupos,OU=U-NEAR,OU=LMCORP,DC=lmcorp,DC=local")
      env.put(Context.SECURITY_CREDENTIALS, "LDAP@2017")

      // Create the initial context
      val ctx = new InitialDirContext(env)

      val cursor = ctx.search(
        s"ldap://$host:$port/OU=LMCORP,DC=lmcorp,DC=local?cn?sub?(sAMAccountName=$userLogon)",
        "" /* ignored*/ ,
        null /* ignored */)
      ctx.close()

      first(cursor).map(_.getAttributes.get("cn").get().toString).getOrElse("not found")
    } match {
      case Success(name) => name
      case Failure(error) => error.getMessage
    }
  }

  def first(cursor: NamingEnumeration[SearchResult]): Option[SearchResult] = {
    if (cursor.hasMore) Some(cursor.next()) else None
  }

}
