package wiki.wasabi.querydsl.project;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final EntityManager em;

    public Config(final EntityManager em) {
        this.em = em;
    }

}
