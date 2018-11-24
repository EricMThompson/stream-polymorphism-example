package ca.thompsonem.example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableBinding(Sink.class)
public class PolymorphSinkApplication {

    @Getter
    @Setter
    private List<String> values = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(PolymorphSinkApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void receive(CommonInterface c) {
        if (c instanceof Foo) {
            System.out.println("It's a Foo");
            values.add(((Foo)c).getFooValue());
        }
        if (c instanceof Bar) {
            System.out.println("It's a Bar");
            values.add(((Bar)c).getBarValue());
        }
    }

}
