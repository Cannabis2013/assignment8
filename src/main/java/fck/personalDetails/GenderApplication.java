package fck.personalDetails;

import fck.personalDetails.ConsoleIO.ConsoleApiInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenderApplication.class, args);
        ConsoleApiInfo.print();
    }
}
