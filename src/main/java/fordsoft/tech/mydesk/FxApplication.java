package fordsoft.tech.mydesk;

import fordsoft.tech.mydesk.controller.SelectionController;
import fordsoft.tech.mydesk.controller.SignupUserController;
import fordsoft.tech.mydesk.model.Doctor;
import fordsoft.tech.mydesk.model.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Properties;

/**
 * @author Bayram Ayhan Colakoglu
 * @Main This class includes the main methods init, start, stop etc.
 */


public class FxApplication extends Application {


    public static User currentuser = new User();
    private ConfigurableApplicationContext applicationContext;


    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(MydeskApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(SelectionController.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }


    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }


}
