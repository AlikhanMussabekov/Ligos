package ru.cs.ifmo.ligos;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import ru.cs.ifmo.ligos.config.AppProperties;
import ru.cs.ifmo.ligos.db.entities.Position;
import ru.cs.ifmo.ligos.db.repositories.TeamRepository;
import ru.cs.ifmo.ligos.db.services.TeamService;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class LigosApplication {

	private final Logger logger = LoggerFactory.getLogger(LigosApplication.class);

	private final TeamRepository teamRepository;

	@Autowired
	public LigosApplication(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC+3"));
	}

	public static void main(String[] args) {
		SpringApplication.run(LigosApplication.class, args);

	}
}
