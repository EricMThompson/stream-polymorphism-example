package ca.thompsonem.example

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.stream.messaging.Source
import org.springframework.cloud.stream.test.binder.MessageCollector
import org.springframework.messaging.Message
import spock.lang.Specification

import java.util.concurrent.BlockingQueue

@SpringBootTest
class PolymorphSourceTest extends Specification {

    @Autowired
    PolymorphSourceApplication application

    @Autowired
    Source source

    @Autowired
    MessageCollector collector

    def "Serialization"() {
        when:
        application.sendFoo()
        application.sendBar()
        BlockingQueue<Message<?>> messages = collector.forChannel(source.output())

        then:
        (messages[0].payload as String).contains("_class")
        (messages[0].payload as String).contains("ca.thompsonem.example.Foo")
        (messages[1].payload as String).contains("_class")
        (messages[1].payload as String).contains("ca.thompsonem.example.Bar")
    }

}
