/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.util.*;

/**
 *
 * @author dqhoa
 */
public class TableOutput {
    public static void printTable(String[] labels, List<String[]> data) {
        int[] spacings = new int[labels.length];

        for (int i = 0; i < spacings.length; ++i) {
            spacings[i] = labels[i].length();
        }

        int row_length = 0;
        for (String[] data_element : data) {
            if (spacings.length != data_element.length) {
                throw new RuntimeException("ERROR: Label length and data element length not matching");
            }
            int current_row_length = 0;
            for (int i = 0; i < spacings.length; ++i) {
                spacings[i] = Math.max(spacings[i], data_element[i].length());
                current_row_length += spacings[i] + 3;
            }

            row_length = Math.max(row_length, current_row_length);
        }

        System.out.println("");
        for (int i = 0; i < spacings.length; ++i) {
            TableOutput.printField(labels[i], spacings[i], i < spacings.length - 1);
        }
        TableOutput.newRow(row_length);

        for (String[] data_element : data) {
            for (int i = 0; i < spacings.length; ++i) {
                TableOutput.printField(data_element[i], spacings[i], i < spacings.length - 1);
            }
            TableOutput.newRow(0);
        }
    }

    public static void newRow(int border_length) {
        StringBuilder border = new StringBuilder();

        for (int i = 0; i < border_length; ++i) {
            border.append("-");
            if (i == border_length - 1) {
                border.append("\n");
            }
        }

        System.out.println("");
        System.out.print(border.toString());
    }

    public static void printField(String content, int spacing, boolean border) {
        StringBuilder spaces = new StringBuilder();

        for (int i = 0; i < spacing - content.length(); ++i) {
            spaces.append(" ");
        }
        if (border) {
            spaces.append(" | ");
        }

        System.out.print(content);
        System.out.print(spaces);
    }
}
