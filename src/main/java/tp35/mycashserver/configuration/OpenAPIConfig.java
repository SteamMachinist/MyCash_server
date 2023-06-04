package tp35.mycashserver.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Contact contact = new Contact();
        contact.setName("TP 3-5 github: ");
        contact.setUrl("https://github.com/anastasia-1603/MyCash");

        Info info = new Info()
                .title("MyCash-server API")
                .version("1.0")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
