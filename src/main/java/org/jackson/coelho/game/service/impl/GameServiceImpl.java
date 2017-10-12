package org.jackson.coelho.game.service.impl;

import com.google.gson.Gson;
import org.jackson.coelho.game.model.SavedGame;
import org.jackson.coelho.game.service.GameService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jackson on 12/10/17.
 */
@Service
public class GameServiceImpl implements GameService {

    @Override
    public void saveGame(SavedGame savedGame) {
        String userHomeFolder = System.getProperty("user.home");
        StringBuilder saveFolder = new StringBuilder(userHomeFolder).append(File.separator).append("savedGames").append(File.separator);

        try {
            final boolean[] saved = {false};
            Path path = Paths.get(saveFolder.toString());
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.list(path).forEach(file -> {
                String fileName = file.getFileName().toString();
                fileName = fileName.split("\\.")[0];
                if (fileName.equals(savedGame.getName())) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Do you want to overwrite the current saved game? (Y=Yes, N=No)");
                    String option = scanner.next();
                    if (option.toUpperCase().equals("Y")) {
                        saveFile(file, savedGame);
                        saved[0] = true;
                    } else {
                        System.out.println("Input another name for save the game");
                        String name = scanner.next();
                        savedGame.setName(name);
                    }
                }
            });

            if (!saved[0]) {
                saveFile(path, savedGame);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SavedGame> loadGames() {
        String userHomeFolder = System.getProperty("user.home");

        Path path = Paths.get(userHomeFolder.concat(File.separator).concat("savedGames").concat(File.separator));
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Fail to create the directory fo save games");
            }
        }

        List<SavedGame> savedGames = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Files.list(path).forEach(file -> {
                try {
                    String savedJson = Files.readAllLines(file).get(0);
                    SavedGame savedGame = gson.fromJson(savedJson, SavedGame.class);
                    savedGames.add(savedGame);
                } catch (IOException e) {
                    System.err.println("Fail to read the saved game");
                    System.err.println(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savedGames;
    }

    private void saveFile(Path file, SavedGame savedGame) {
        String json = new Gson().toJson(savedGame);
        try {
            if (Files.isDirectory(file)) {
                String stringBuilder = file.toString().concat(File.separator).concat(savedGame.getName()).concat(".json");
                Files.write(Paths.get(stringBuilder), Collections.singletonList(json));
            } else {
                Files.write(file, Collections.singletonList(json));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
