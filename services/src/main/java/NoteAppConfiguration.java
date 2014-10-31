import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@EnableAutoConfiguration
@ComponentScan
public class NoteAppConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NoteAppConfiguration.class, args);
    }

    @Bean
    RestTemplate getRestTemplate(){
       return new RestTemplate();
    }
}

