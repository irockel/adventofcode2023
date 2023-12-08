package de.grimmfrost.aoc2023;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * find path in the given unidirectional graph.
 */
public class PathFinder {
    Map<String, Path> paths = new HashMap<>();

    public int findPath(String inputFileLink) {
        int result;
        String traversal = "";
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains("=")) {
                    Path path = Path.createFromString(line);
                    paths.put(path.id, path);
                } else if (!line.isEmpty()) {
                    traversal = line;
                }
            }
            result = traversePath(traversal.trim());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private int traversePath(String traversal) {
        Path currentPath = paths.get("AAA");
        int traversals = 0;

        while (!currentPath.id.equals("ZZZ")) {
            for (int i = 0; i < traversal.length(); i++) {
                if (traversal.charAt(i) == 'L') {
                    currentPath = paths.get(currentPath.left);
                } else {
                    currentPath = paths.get(currentPath.right);
                }
                traversals++;

                if (currentPath.id.equals("ZZZ")) {
                    break;
                }
            }

        }

        return traversals;
    }

    public long findGhostPath(String inputFileLink) {
        long result;
        String traversal = "";
        Map<String, Path> startPaths = new HashMap<>();
        try (FileReader fr = new FileReader(inputFileLink);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains("=")) {
                    Path path = Path.createFromString(line);
                    paths.put(path.id, path);
                    if (path.id.endsWith("A")) {
                        startPaths.put(path.id, path);
                    }
                } else if (!line.isEmpty()) {
                    traversal = line;
                }
            }
            result = traverseGhostPath(traversal.trim(), startPaths);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private long traverseGhostPath(String traversal, Map<String, Path> startPaths) {

        Set<Long> traversals = new HashSet<>();
        for (Path ghostPath : startPaths.values()) {
            Long singleTraversals = 0L;
            while (!ghostPath.id.endsWith("Z")) {
                for (int i = 0; i < traversal.length(); i++) {
                    ghostPath = paths.get(ghostPath.getTokenForDirectionChar(traversal.charAt(i)));
                    singleTraversals++;

                    if (ghostPath.id.endsWith("Z")) {
                        break;
                    }
                }
            }

            traversals.add(singleTraversals);
        }

        Long[] factors = traversals.toArray(new Long[0]);
        Long[] trArray = traversals.toArray(new Long[0]);

        while(!traversalsEqual(trArray)) {
            for (int i = 0; i < trArray.length; i++) {
                while (traversalIsSmaller(trArray, i)) {
                    trArray[i] = factors[i] + trArray[i];
                }

            }

        }

        return trArray[0];
    }

    private boolean traversalIsSmaller(Long[] trArray, int index) {
        for (int i = 0; i < trArray.length; i++) {
            if (i != index) {
                if (trArray[i] > trArray[index]) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean traversalsEqual(Long[] trArray) {
        Long value = trArray[0];
        for (int i = 1; i < trArray.length; i++) {
            if (!value.equals(trArray[i])) {
                return false;
            }
        }

        return true;
    }

    private static class Path {
        String id;
        String left;
        String right;

        private Path(String id, String left, String right) {
            this.id = id;
            this.left = left;
            this.right = right;
        }

        public String getTokenForDirectionChar(char direction) {
            if (direction == 'L') {
                return this.left;
            }

            return this.right;
        }

        private static Path createFromString(String line) {
            String[] lineTokens = line.split("=");
            String id = lineTokens[0].trim();
            String[] routes = lineTokens[1].trim().substring(1, lineTokens[1].trim().length()-1).split(",");

            return (new Path(id, routes[0].trim(), routes[1].trim()));
        }
    }
}


