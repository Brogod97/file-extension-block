package flow.fileextentionblock;

import flow.fileextentionblock.repository.ExtensionRepository;
import flow.fileextentionblock.service.ExtensionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ExtensionService extensionService() {
        return new ExtensionService(extensionRepository());
    }

    @Bean
    @Transactional
    public ExtensionRepository extensionRepository() {
        return new ExtensionRepository(dataSource);
    }
}
