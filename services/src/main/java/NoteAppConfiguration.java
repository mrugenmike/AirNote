import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
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

import java.net.UnknownHostException;


@Controller
@EnableAutoConfiguration
@ComponentScan
@PropertySource(value = "classpath:/application.properties")
public class NoteAppConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NoteAppConfiguration.class, args);
    }

    @Value("${mongo.database.url}")
    String mongoUrl;

    @Value("${mongo.database.name}")
    String mongoDatabase;

    @Bean
    RestTemplate getRestTemplate(){
       return new RestTemplate();
    }

    @Bean
    MongoClient getMongoClient() throws UnknownHostException {
       return new MongoClient(new MongoClientURI(mongoUrl));
    }

    @Bean(name={"noteMetaInfo"})
    DBCollection getNotesCollection() throws UnknownHostException {
        return getMongoClient().getDB(mongoDatabase).getCollection("noteMetaInfo");
    }
}

