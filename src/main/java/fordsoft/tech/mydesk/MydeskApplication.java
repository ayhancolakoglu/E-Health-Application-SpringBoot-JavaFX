package fordsoft.tech.mydesk;


import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Bayram Ayhan Colakoglu
 */


@SpringBootApplication
public class MydeskApplication {

    /**
     * @param args
     * @Main This is the main method that starts the application
     */
    public static void main(String[] args) {
        Application.launch(FxApplication.class, args);
    }

}
