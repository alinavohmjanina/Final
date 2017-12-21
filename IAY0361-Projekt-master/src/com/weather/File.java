package com.weather;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class File {
    protected static boolean mock = false;
    protected List<String> mockLines = new ArrayList<>();

    public List<String> read(String file) throws IOException {
        List<String> lines = new ArrayList<>();

        if (! this.isMock()) {
            Path path = Paths.get(file);

            lines = Files.readAllLines(path);
        } else {
            lines = this.mockLines;
        }

        return lines;
    }

    public boolean write(List<String> lines, String file) throws IOException {
        if (! this.isMock()) {
            Path path = Paths.get(file);

            Files.write(path, lines, StandardCharsets.UTF_8);
        } else {
            this.mockLines = lines;
        }

        return true;
    }

    public boolean isMock() {
        return mock;
    }

    public static void mock() {
        mock = true;
    }
}