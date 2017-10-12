package org.jackson.coelho.game;

import org.jackson.coelho.game.menu.InitialMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

    @Autowired
    private InitialMenu initialMenu;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GameApplication.class);
        app.setBannerMode(Banner.Mode.OFF);

        app.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        initialMenu.init();
    }
}
