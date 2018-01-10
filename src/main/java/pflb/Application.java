package pflb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import static pflb.db.Connection.getConnection;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        getConnection();
        //SpringApplication.run(Application.class, args);
    }
}
