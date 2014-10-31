import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@EnableAutoConfiguration
@ComponentScan
@PropertySource(value = "classpath:/application.properties")
public class NoteAppConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NoteAppConfiguration.class, args);
    }

    @Bean
    RestTemplate getRestTemplate(){
       return new RestTemplate();
    }

//    //MongoClient getMongoClient(){
//       return new MongoClient();
//    }
}

