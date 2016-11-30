import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Samson on 25-11-2016.
 */
public class Run {

    public static void main(String[] args) {

        List<Edge> edges = new ArrayList<>();
        int numberOfVertices = 0;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a file name: ");
        String fileName = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("files/" + fileName)))
        {
            boolean firstLine = true;
            String s;
            while ((s = br.readLine()) != null) {
                if (firstLine) {
                    numberOfVertices = Integer.valueOf(s);
                    firstLine = false;
                }
                String[] split = s.split("[\\s+]");
                for (int i = 1; i < split.length; i++) {
                    edges.add(new Edge(Integer.valueOf(split[0]), Integer.valueOf(split[i])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        UnweightedGraph<Object> g = new UnweightedGraph<>(edges, numberOfVertices);
        g.printEdges();

        UnweightedGraph<Object>.SearchTree dfs = g.dfs(0);

        if (numberOfVertices == dfs.getNumberOfVerticesFound()) {
            System.out.println("The graph is connected");
        }



    }
}
