ldap-scala
---
Simple project to find a user through LDAP protocol.

## Build

```shell
$ activator test
```

## LDAP Client API:

#### UnboundID
https://docs.ldap.com/ldap-sdk/docs/getting-started/index.html

#### Oracle native
http://docs.oracle.com/javase/jndi/tutorial/TOC.html

#### Apache Directory LDAP API
http://directory.apache.org/api/

#### Scala-ldap
Scala-ldap is a scala wrapper around UnboundID LDAP SDK.
https://github.com/Normation/scala-ldap
> Finally, we don’t have a maven repository with the Scala-LDAP dependencies available,
> so you are going to need to build them in order to use Scala-LDAP (see the build chapter below)


## LDAP URLs:
pattern: ldap://host:port/DN?attributes?scope?filter

Default:
	- port: 389
	- DN: zero-length DN (server root DSE)
	- attributes: all atributes
	- scope: restricted base entry
	- filter: (objectClass=*)

ldap://ds.example.com:389/dc=example,dc=com?givenName,sn,cn?sub?(uid=john.doe)
