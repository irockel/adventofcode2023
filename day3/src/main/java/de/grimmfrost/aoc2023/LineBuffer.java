package de.grimmfrost.aoc2023;

import java.util.ArrayList;

/**
 * small structure to represent a view of three lines on the gondola
 * schematic.
 */
public class LineBuffer {
    private final ArrayList<String> lines = new ArrayList<>();

    public LineBuffer(int width) {
        lines.add(".".repeat(width));
    }

    public void addLine(String line) {
        if (lines.size() == 3) {
            lines.removeFirst();
        }
        lines.add(line);
    }

    public String getAt(int i) {
        return lines.get(i);
    }

    public int length() {
        return lines.size();
    }
}
