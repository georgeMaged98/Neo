package code;

import code.generics.Node;
import code.generics.State;
import code.structures.CustomizedStringBuilder;
import code.structures.StateObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenGrid {

    public static void main(String[] args) throws IOException {
        System.out.println(genGrid());
        Matrix matrix = new Matrix(genGrid());
        StateObject stateObject = matrix.fillStateObject();

        Matrix.myWriter = new FileWriter("genGrid.txt");
        matrix.visualize(stateObject);

        Matrix.myWriter.close();

    }

    public static String genGrid() {

        CustomizedStringBuilder generatedGrid = new CustomizedStringBuilder(";");


        int minDimension = 5;
        int maxDimension = 5;
        int gridXDimension = generateRandomWithinRange(minDimension, maxDimension);
        int gridYDimension = generateRandomWithinRange(minDimension, maxDimension);

        generatedGrid.append(gridXDimension + "," + gridYDimension);
        int numberOfHostagesCarried = generateRandomWithinRange(1, 4);

        generatedGrid.append(numberOfHostagesCarried + "");

        Queue<String> indices = initializeArrayWithPositions(gridXDimension, gridYDimension);


        String neoPos = indices.poll();
        generatedGrid.append(neoPos);

        String telePos = indices.poll();
        generatedGrid.append(telePos);

        int numberOfHostages = generateRandomWithinRange(3, 10);
        int numberOfPills = generateRandomWithinRange(1, numberOfHostages);

        // generate pills
        CustomizedStringBuilder pills = new CustomizedStringBuilder(",");
        for (int i = 0; i < numberOfPills; i++)
            pills.append(indices.poll());

        // generate hostages
        CustomizedStringBuilder hostages = new CustomizedStringBuilder(",");
        for (int i = 0; i < numberOfHostages; i++) {
            int randHostageDamage = generateRandomWithinRange(1, 99);
            hostages.append(indices.poll() + "," + randHostageDamage);
        }


        int numberOfAgents = getAgentsNum();

        CustomizedStringBuilder agents = new CustomizedStringBuilder(",");
        for (int i = 0; i < numberOfAgents; i++)
            agents.append(indices.poll());


        int maxPairs = indices.size() / 4;

        int numberOfPadPais = generateRandomWithinRange(1, maxPairs<1?1:maxPairs);

        // generate pads
        CustomizedStringBuilder pads = new CustomizedStringBuilder(",");
        for (int i = 0; i < numberOfPadPais; i++) {
            String startPad = indices.poll();
            String finishPad = indices.poll();
            pads.append(startPad + "," + finishPad);
            pads.append(finishPad + "," + startPad);
        }

        generatedGrid.append(agents.getString());
        generatedGrid.append(pills.getString());
        generatedGrid.append(pads.getString());
        generatedGrid.append(hostages.getString());

        return generatedGrid.getString();

    }

    public static int getAgentsNum() {
        int cnt = 10;
        int num = 1;
        while (cnt-- > 0) {
            if (generateRandomWithinRange(0, 1) == 1)
                return num;
            num++;
        }
        return num;
    }

    public static int generateRandomWithinRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }


    public static Queue<String> initializeArrayWithPositions(int x, int y) {

        ArrayList<String> indices = new ArrayList<>();
        // initialize array with all possible positions
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                indices.add(i + "," + j);

        Collections.shuffle(indices);
        int size = indices.size();
        Queue<String> shuffledIndices = new LinkedList<>();
        for (int i = 0; i < size; i++)
            shuffledIndices.add(indices.get(i));

        return shuffledIndices;
    }

    public static String getRandomPos(ArrayList<String> positions) {
        Random rand = new Random();
        int idx = rand.nextInt(positions.size());
        String pos = positions.get(idx);
        positions.remove(pos);
        return pos;
    }
}
