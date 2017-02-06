package com.example

import org.apache.directory.api.ldap.model.message.SearchScope
import org.apache.directory.ldap.client.api.LdapNetworkConnection

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

class LDAPApacheService(host: String, port: Int) {

  def findName(userLogon: String): String = {
    Try {
      val connection = new LdapNetworkConnection(host, port)
      // Don't do that ! Password in clear text = danger !
      // connection.bind("ou=example, dc=com", "secret")
      // The password is encrypted, but it does not protect against a MITM attack
      // connection.bind("ou=example, dc=com", "{crypt}wSiewPyxdEC2c")
      connection.bind(
        "CN=Teste UNEAR,OU=Usuarios_Grupos,OU=U-NEAR,OU=LMCORP,DC=lmcorp,DC=local",
        "LDAP@2017")

      val cursor = connection.search(
        "OU=LMCORP,DC=lmcorp,DC=local",
        s"(sAMAccountName=$userLogon)",
        SearchScope.SUBTREE,
        "cn")

      val first = cursor.iterator.asScala.toList.headOption

      connection.unBind()
      connection.close()

      first.map(_.get("cn").getString).getOrElse("not found")
    } match {
      case Success(name) => name
      case Failure(error) => error.getMessage
    }
  }

}
