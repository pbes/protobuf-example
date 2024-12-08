package hu.pbes.blog;

import hu.pbes.blog.protobuf.PetOuterClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        final var pet = PetOuterClass.Pet.newBuilder()
                .setName("Fido")
                .setAge(3)
                .addAllSkills(List.of("sit", "stay", "fetch"))
                .build();

        System.out.println(pet);

        try (FileOutputStream outputFile = new FileOutputStream("pet.bin")) {
            pet.writeTo(outputFile);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }

        try (FileInputStream inputFile = new FileInputStream("pet.bin")) {
            final var readPet = PetOuterClass.Pet.parseFrom(inputFile);
            System.out.println(readPet);
        } catch (IOException e) {
            System.err.println("Failed to read from file: " + e.getMessage());
        }
    }
}
