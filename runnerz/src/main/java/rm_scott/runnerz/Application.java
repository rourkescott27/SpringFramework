package rm_scott.runnerz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import rm_scott.runnerz.user.User;
import rm_scott.runnerz.user.UserHttpClient;
import rm_scott.runnerz.user.UserRestClient;

import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main ( String[] args ) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    UserHttpClient userHttpClient () {
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }

// CAUSES ERRORS WHEN TESTING JdbcClientRunRepositoryTest
//    @Bean
//    CommandLineRunner runner ( UserRestClient client ) {
//        return args -> {
//            List<User> users = client.findAll();
//            System.out.println(users);
//
//            User user = client.findById(1);
//            System.out.println(user);
//        };
//    }

}
