package jdbc.configurations;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {
    private final DataSource dataSource;
    @PostConstruct
    public void initialize() {
        new ResourceDatabasePopulator(new ClassPathResource("db/migration/init.sql")).execute(dataSource);
    }
}
