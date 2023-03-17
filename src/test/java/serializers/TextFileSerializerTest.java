package serializers;

import domain.Lawn;
import domain.LawnMowers;
import domain.Mower;
import domain.Position;
import enums.Orientation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TextFileSerializerTest {

    private TextFileSerializer textFileSerializer;

    @BeforeEach
    void initSerializer() {
        textFileSerializer = new TextFileSerializer();
    }

    @BeforeAll
    static void deleteOutputFiles() throws IOException, URISyntaxException {
        var outputPath = TextFileSerializerTest.class.getClassLoader().getResource("TestLawnMowerOut.txt");
        if (outputPath != null) {
            Files.deleteIfExists(Paths.get(outputPath.toURI()));
        }
    }

    @Test
    void processInputFileLines() throws IOException {
        File inputFile = new File(Objects.requireNonNull(TextFileSerializerTest.class.getClassLoader().getResource("TestLawnMowerIn.txt")).getFile());
        String inputPath = inputFile.getAbsolutePath();

        var expectedLawn = new Lawn(7,5);
        var expectedMowers = List.of(
                new Mower(new Position(2,3), Orientation.EAST, "AAGADAAGA"),
                new Mower(new Position(5,1), Orientation.SOUTH, "GGG"),
                new Mower(new Position(4,7), Orientation.NORTH, "AGA")
        );

        var expectedLawnMowers = new LawnMowers(expectedLawn, expectedMowers);
        var resultLawnMowers = textFileSerializer.processInputFileLines(inputPath);

        assertEquals(expectedLawnMowers, resultLawnMowers);
    }

    @Test
    void generateOutputFileLines() throws IOException {
        var outputPath = "src/test/resources/TestLawnMowerOut.txt";

        var subjectMowers = new ArrayList<Mower>();
        subjectMowers.add(new Mower(new Position(0,1), Orientation.NORTH, "ADA"));
        subjectMowers.add(new Mower(new Position(5,1), Orientation.SOUTH, "GAG"));

        var expectedOutput =
                "0 1 N"+System.lineSeparator()
                        +"5 1 S"+System.lineSeparator();

        textFileSerializer.writeOutputFileLines(outputPath, subjectMowers);
        var actualOutput = Files.readString(Path.of(outputPath));

        assertEquals(expectedOutput, actualOutput);
    }
}