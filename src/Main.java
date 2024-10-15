import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scan = new Scanner(System.in);



        File file = new File(args[0]);
        double[][] array = load(file);

        for (int row = 0; row < array.length; row++) {
            System.out.printf("Row %d Subtotal: %.3f\n", row, getRowSubTotal(array, row));
        }

        int longestRow = longestRow(array);
        for (int col = 0; col < longestRow; col++) {
            System.out.printf("Column %d Subtotal: %.3f\n", col, getColSubTotal(array, col));
        }

        System.out.printf("Array Total: %.3f\n", getTotal(array));
    }

    private static int getElementCount(double[][] array) {
        int count = 0;
        for (double[] row : array) {
            count += row.length;
        }
        return count;
    }

    private static int longestRow(double[][] array) {
        int longestRowSize = 0;
        for (double[] row : array) {
            if (row.length > longestRowSize) {
                longestRowSize = row.length;
            }
        }
        return longestRowSize;
    }

    private static double[][] load(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);

        int rows = 0;
        int cols = 0;

        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split("\\s+");
            if (line.length > cols) {
                cols = line.length;  // Find max columns
            }
            rows++;
        }
        scan.close();

        double[][] result = new double[rows][cols];

        scan = new Scanner(file);
        int currentRow = 0;
        while (scan.hasNextLine()) {
            String[] line = scan.nextLine().split("\\s+");
            for (int i = 0; i < line.length; i++) {
                result[currentRow][i] = Double.parseDouble(line[i]);
            }
            currentRow++;
        }
        scan.close();

        return result;
    }

    private static double getRowSubTotal(double[][] array, int row) {
        double total = 0.0;
        for (double element : array[row]) {
            total += element;
        }
        return total;
    }

    private static double getColSubTotal(double[][] array, int col) {
        double total = 0.0;
        for (double[] row : array) {
            if (col < row.length) {
                total += row[col];
            }
        }
        return total;
    }

    private static double getTotal(double[][] array) {
        double total = 0.0;
        for (double[] row : array) {
            for (double element : row) {
                total += element;
            }
        }
        return total;
    }
}
