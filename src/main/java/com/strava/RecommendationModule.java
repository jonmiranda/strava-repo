/*Ryan Huang
 *
 */

package com.strava;

import com.strava.models.Segment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RecommendationModule {

    RankModule rankModule;

    public RecommendationModule(RankModule rankModule) {
        this.rankModule = rankModule;
    }

    public Segment getRecommendedSegment() {
        if (rankModule.getRankedSegments().size() > 0) {
            return rankModule.getRankedSegments().get(0);
        }
        return new Segment();
    }

    public void testSearch() {
        int adjacency_matrix[][];
        int numVertices;
        int source = 0;
        int destination = 0;
        int distance;
        Scanner scan = new Scanner(System.in);

        int[][] testMatrix = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0},

                {0, 0, 5, 999, 3, 999, 999, 999},
                {0, 999, 0, 1, 999, 999, 999, 999},
                {0, 999, 999, 0, 999, 6, 999, 1},
                {0, 999, 999, 999, 0, 2, 4, 999},
                {0, 999, 4, 999, 999, 0, 999, 999},
                {0, 999, 999, 999, 999, 999, 0, 3},
                {0, 999, 999, 999, 999, 4, 999, 0}
        };

        try {
            System.out.println("Enter the number of vertices");
            numVertices = scan.nextInt();

//            adjacency_matrix = new int[numVertices + 1][numVertices + 1];
//            System.out.println("Enter the Weighted matrix for the graph");
//
//            for (int i = 1; i <= numVertices; i++) {
//                for (int j = 1; j <= numVertices; j++) {
//                    adjacency_matrix[i][j] = scan.nextInt();
//
//                    if (i == j) {
//                        adjacency_matrix[i][j] = 0;
//                        continue;
//                    }
//                    if (adjacency_matrix[i][j] == 0) {
//                        adjacency_matrix[i][j] = SearchModule.MAX_VALUE;
//                    }
//                }
//            }

            System.out.println("Enter the source ");
            source = scan.nextInt();

            System.out.println("Enter the destination ");
            destination  = scan.nextInt();

            SearchModule searchModule = new SearchModule(numVertices);
            distance = searchModule.search(testMatrix, source, destination);
            searchModule.printPath();

            System.out.println("\nThe distance between source " + source +
            " and destination " + destination + " is " + distance);
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input format");
        }

        //Sample input/output
//        Enter the number of vertices
//        7
//        Enter the Weighted matrix for the graph
//        0 5 0 3 0 0 0
//        0 0 1 0 0 0 0
//        0 0 0 0 6 0 8
//        0 0 0 0 2 2 0
//        0 4 0 0 0 0 0
//        0 0 0 0 0 0 3
//        0 0 0 0 4 0 0
//        Enter the source
//        1
//        Enter the destination
//        7
//        The eval node is 1
//        The eval node is 4
//        The eval node is 2
//        The eval node is 6
//        The eval node is 5
//        The eval node is 3
//        The eval node is 7
//        The Path between 1 and 7 is
//        1	4	6	7
//        The distance between source 1 and destination 7 is 8
    }


}
