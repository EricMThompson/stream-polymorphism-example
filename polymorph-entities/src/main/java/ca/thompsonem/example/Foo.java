package ca.thompsonem.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Foo implements CommonInterface {

    private String fooValue;
    private LocalDateTime dateTime;

}
