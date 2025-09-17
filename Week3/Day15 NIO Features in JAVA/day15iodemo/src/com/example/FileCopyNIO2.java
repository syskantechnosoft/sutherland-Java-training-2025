package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyNIO2 {
    public static void main(String[] args) {
        Path source = Paths.get("d:\\input.txt");
        Path target = Paths.get("d:\\output.txt");

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied using NIO.2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

