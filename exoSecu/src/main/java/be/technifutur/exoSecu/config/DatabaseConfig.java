package be.technifutur.exoSecu.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Profile("h2")
    @Bean
    public DataSource getH2DataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:h2:mem:exoSecu");
        builder.driverClassName("org.h2.Driver");
        builder.username("sa");
        builder.password("1234");

        return builder.build();
    }
    @Profile("postgres")
    @Bean
    public DataSource getPostgresDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/exoSecu");
        builder.driverClassName("org.postgresql.Driver");
        builder.username("postgres");
        builder.password("19410719");

        return builder.build();
    }
}
