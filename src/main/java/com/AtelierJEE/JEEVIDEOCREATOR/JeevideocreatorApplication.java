package com.AtelierJEE.JEEVIDEOCREATOR;

import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Creator;
import com.AtelierJEE.JEEVIDEOCREATOR.DAO.entities.Video;
import com.AtelierJEE.JEEVIDEOCREATOR.service.CreatorService;
import com.AtelierJEE.JEEVIDEOCREATOR.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JeevideocreatorApplication {
	@Autowired
	CreatorService CrService;

	@Autowired
	VideoService Vservice;



	public static void main(String[] args) {
		SpringApplication.run(JeevideocreatorApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {
		return args -> {
			for (int i = 0; i < 20; i++) {
				Creator c =CrService.AddCreator(new Creator(null,"Souhail "+i,"Souhail"+i+"@test ","test",new Date(),null));
				Video v1 = new Video(null,"JAVA course "+i,"url/","ImageUrl/","Learn Java easy",new Date(),c);
				Vservice.addVideo(v1);
				c.setVideos(List.of(v1));

			}
		};
	}

}
