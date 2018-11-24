package ca.thompsonem.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.messaging.support.GenericMessage
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
class PolymorphSinkTest extends Specification {

    @Autowired
    Sink sink

    @Autowired
    PolymorphSinkApplication application

    def "Deserialization"() {
        given:
        Foo foo = new Foo("fff", LocalDateTime.of(2018, 10, 26, 0, 0))
        //Bar bar = new Bar("bbb")

        when:
        sink.input().send(new GenericMessage<>("{\"_class\":\"ca.thompsonem.example.Foo\",\"fooValue\":\"fff\",\"dateTime\":\"2018-10-26T00:00:00\"}"))
        sink.input().send(new GenericMessage<>("{\"_class\":\"ca.thompsonem.example.Bar\",\"barValue\":\"bbb\"}"))

        then:
        application.values[0] == "fff"
        application.values[1] == "bbb"
    }

}
