import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.LinkedList;

/**
 * Assignment mmn 14 - Data Structures and Introduction to Algorithms
 * @author Daniel Dubinskey - 209134543
 * @author Yonatan Tzukerman - 325999290
 */
public class Main {

    private static LinkedList<Heap> lists;
    private static int HeapType;

    private static void processFunction(String command) {
        //
        if (command.equals("MakeHeap")) {
            // create a new heap and insert into the list based on the heap type originally selected
            switch (HeapType) {
                case 1 -> lists.add(new MergeableHeapSorted());
                case 2 -> lists.add(new MergeableHeapUnsorted());
                case 3 -> lists.add(new ForeignHeapUnsorted());
            }
        }
        else if (command.startsWith("Insert")) {
            // insert the value stated into the heap
            double num = Double.parseDouble(command.substring(7));
            assert lists.peekLast() != null;
            lists.peekLast().insert(num);
        }
        else if (command.equals("Minimum")) {
            // print out minimum value
            assert lists.peekLast() != null;
            System.out.println(lists.peekLast().minimum());
        }
        else if (command.equals("ExtractMin")) {
            // remove the minimum value
            assert lists.peekLast() != null;
            lists.peekLast().extractMin();
        }
        else if (command.startsWith("Union")) {
            // merge 2 heaps together
            try {
                int index1 = Integer.parseInt(command.split(" ")[1]) - 1;
                int index2 = Integer.parseInt(command.split(" ")[2]) - 1;
                lists.add(lists.get(index1).union(lists.get(index2)));
            }
            catch (NullPointerException err) {
                System.out.println("It appears the the Union command isn't using the right syntax." +
                        "Please use the following syntax: Union x y" +
                        "for x, y the number of the heap you wish to merge (by order of creation)");
            }

        }
        else if (command.equals("Print")) {
            // print a heap
            int numberOfArguments = command.split(" ").length;
            assert lists.peekLast() != null;
            Heap heap = (numberOfArguments == 1) ? lists.peekLast() : lists.get(Integer.parseInt(command.split(" ")[1]));
            System.out.println(heap.toString());
        }
        else {
            // if the command isn't recognized print an error and stop the program to stop further issues
            System.out.println(("Error! unknown command - " + command));
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        lists = new LinkedList<>();

        // Ask the user for what Heap configuration the program should use
        System.out.println("Hello! Please choose the type of Heap to use ^-^");
        System.out.println("1. Sorted Heap \n2. Unsorted Heap \n3. Unsorted Foreign Heaps");
        System.out.println("My decision: ");
        HeapType = scan.nextInt();
        // error handling
        if (HeapType < 1 || HeapType > 3) return;

        // make sure the user has inserted a text file named input.txt containing the commands
        System.out.println("Please insert a text filled named 'input.txt' into the project folder before continuing");
        System.out.println("""
                Here is a list of all available commands and their syntax:\s
                ==================================================================\s
                1. MakeHeap - creating an empty heap.\s
                2. Insert - insert items into the current heap.\s
                3. Print {optional: x} - prints out the current heap, or a specific heap if given the option\s
                4. Minimum - prints out the smallest number in the current heap\s
                5. ExtractMin - removes the minimum number from the current heap\s
                6. Union x y - merge 2 heaps together into a new one (example: Unoin 1 2)
                ==================================================================\s""");
        System.out.println("Press any key to continue.");
        System.in.read();

        try {
            // open up the text file
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                // handling every line in file
                String data = myReader.nextLine();
                processFunction(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            // error handling
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
