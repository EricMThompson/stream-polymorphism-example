package ca.thompsonem.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableBinding(Source.class)
public class PolymorphSourceApplication {

    @Autowired
    private Source source;

    public static void main(String[] args) {
        SpringApplication.run(PolymorphSourceApplication.class, args);
    }

    public void sendFoo() {
        Foo foo = new Foo("fff", LocalDateTime.of(2018, 10, 26, 0, 0));
        source.output().send(new GenericMessage<>(foo));
    }

    public void sendBar() {
        Bar bar = new Bar("bbb");
        source.output().send(new GenericMessage<>(bar));
    }

}
