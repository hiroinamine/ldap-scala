import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Hello" should "have tests" ignore {
    true should === (true)
  }
}
