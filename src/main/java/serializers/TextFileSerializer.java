package serializers;

import domain.Lawn;
import domain.LawnMowers;
import domain.Mower;
import domain.Position;
import enums.Orientation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextFileSerializer {

    /**
     * Crée une instance de LawnMowers qui contient la pelouse et les tondeuses spécifiées dans le fichier d'entrée de l'application
     * @param inputFilePath Chemin du fichier d'entrée
     * @return instance de LawnMowers
     */
    public LawnMowers processInputFileLines(String inputFilePath) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of(inputFilePath));
        if (inputLines.size() == 0) {
            throw new IllegalArgumentException("List of lines is empty, cannot process");
        }

        String[] lawnUpperRightPos = inputLines.get(0).split("\\s+");

        Lawn lawn = new Lawn(Integer.parseInt(lawnUpperRightPos[0]),
                Integer.parseInt(lawnUpperRightPos[1]));

        List<Mower> mowers = new ArrayList<>();
        for (int i=1; i < inputLines.size(); i+=2) {
            String mowerPos = inputLines.get(i);
            String mowerOrders = inputLines.get(i+1);

            String[] mowerPosArray = mowerPos.split("\\s+");

            Position mowerDefaultPos = new Position(Integer.parseInt(mowerPosArray[0]),
                    Integer.parseInt(mowerPosArray[1]));

            Orientation mowerDefaultOrientation = TextFileSerializer.deserializeOrientation(mowerPosArray[2]);

            Mower mower = new Mower(mowerDefaultPos, mowerDefaultOrientation, mowerOrders);
            mowers.add(mower);
        }

        return new LawnMowers(lawn, mowers);
    }

    /**
     * Renvoie le point cardinal correspondant à la chaîne de caractères passée en paramètre
     * @param cardinal Point cardinal (N,E,S,W)
     * @return L'enum Orientation correspondant au point cardinal (NORTH, EAST, SOUTH, WEST)
     */
    private static Orientation deserializeOrientation(String cardinal) {
        return switch (cardinal) {
            case "N" -> Orientation.NORTH;
            case "E" -> Orientation.EAST;
            case "S" -> Orientation.SOUTH;
            case "W" -> Orientation.WEST;
            default -> throw new UnsupportedOperationException("Orientation "+cardinal+" not supported");
        };
    }

    /**
     * Prépare chaque ligne qui correspond aux coordonnées de chaque tondeuse
     * @param mowers Liste des tondeuses à afficher
     */
    public void writeOutputFileLines(String outputPath, List<Mower> mowers) throws IOException {
        List<String> outputLines = new ArrayList<>();
        for (Mower mower : mowers) {
            outputLines.add(serializeMower(mower));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String outputLine : outputLines) {
                writer.write(outputLine);
                writer.newLine();
            }
        }
    }

    /**
     * Renvoie les coordonnées de la tondeuse sous un format spécifique ("1 3 N", "0, 0, W"...)
     * @return coordonnées de la tondeuse
     */
    private String serializeMower(Mower mower) {
        return mower.position().x()+" "+mower.position().y()+" "+ serializeOrientation(mower.orientation());
    }

    /**
     * Retourne l'orientation sous lettre correspondant au point cardinal
     * @return Point cardinal en une lettre (N,E,S,W)
     */
    private String serializeOrientation(Orientation orientation) {
        return switch (orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

}
