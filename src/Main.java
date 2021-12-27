import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        LinkedList<Heap> lists = new LinkedList<>();

        System.out.println("Hello! Please choose the type of Heap to use ^-^");
        System.out.println("1. Sorted Heap \n2. Unsorted Heap \n3. Unsorted Foreign Heaps");
        System.out.println("My decision: ");
        int choice = scan.nextInt();

        if (choice < 1 || choice > 3) return;

        System.out.println("Please insert a text filled named 'input.txt' into the project folder before continuing");
        System.in.read();

        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);

                if (data.equals("MakeHeap")) {
                    switch (choice) {
                        case 1 -> lists.add(new MergeableHeapSorted());
                        case 2 -> lists.add(new MergeableHeapUnsorted());
                        case 3 -> lists.add(new foreignLists());
                    }
                }
                else if (data.startsWith("Insert")) {
                    double num = Double.parseDouble(data.substring(7));
                    lists.peekLast().insert(num);
                }
                else if (data.equals("Minimum")) {
                    System.out.println(lists.peekLast().minimum());
                }
                else if (data.equals("ExtractMin")) {
                    lists.peekLast().extractMin();
                }
                else if (data.startsWith("Union")) {
                    int index1 = Integer.parseInt(data.split(" ")[1]);
                    int index2 = Integer.parseInt(data.split(" ")[2]);
                    lists.add(lists.get(index1-1).union(lists.get(index1-1), lists.get(index2-1)));
                }
                else if (data.equals("Print")) {
                    System.out.println(lists.peekLast().toString());
                }
               // System.out.println(lists.peekLast());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
