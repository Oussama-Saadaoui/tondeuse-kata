import domain.LawnMowers;
import serializers.TextFileSerializer;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Expecting two args: [fileIn] [fileOut]");
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        TextFileSerializer textFileSerializer = new TextFileSerializer();

        try {
            // On récupère et initialise la pelouse ainsi que les tondeuses liées à celle-ci

            LawnMowers lawnMowers = textFileSerializer.processInputFileLines(inputFilePath);

            // Execution des ordres donnés à chaque tondeuse
            lawnMowers = lawnMowers.executeOrders();

            //  Ecrire toutes les positions finales des tondeuses dans le fichier de sortie
            textFileSerializer.writeOutputFileLines(outputFilePath, lawnMowers.mowers());
        } catch (IOException e) {
            System.out.println("An error has occured during the execution of the software :\n"+e);
        }
    }
}
