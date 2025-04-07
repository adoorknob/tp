package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import instrument.Instrument;

import static ui.Ui.CRITICAL_QTY;
import static ui.Ui.RED;
import static ui.Ui.YELLOW;
import static ui.Ui.RESET;
import static ui.Ui.TEXTBORDER;
import static ui.Ui.WARNING_QTY;

//import static ui.Ui.*;

public class LowStockChecker {
    public static String printLowStock(Map.Entry<String, Integer> entry, HashMap<String, Integer> rentCount) {
        String instName = entry.getKey();
        Integer instCount = entry.getValue();
        Integer rentedCount = (rentCount.get(instName) == null ? 0 : rentCount.get(instName));
        Integer availCount = instCount - rentedCount;
        if (availCount < CRITICAL_QTY) { // critical, must replenish soon
            return (instName + ": " + RED + availCount + RESET + " available\n");
        } else if (availCount < WARNING_QTY) {
            return (instName + ": " + YELLOW + availCount + RESET + " available\n");
        }
        return "";
    }

    public static void checkAll(ArrayList<Instrument> instruments) {

        HashMap<String, Integer> stockCount = new HashMap<>();
        HashMap<String, Integer> rentCount = new HashMap<>();

        for (Instrument inst : instruments) {
            if (stockCount.containsKey(inst.name)) {
                Integer currCount = stockCount.get(inst.name);
                stockCount.replace(inst.name, currCount + 1);
            } else {
                stockCount.put(inst.name, 1);
            }
        }

        for (Instrument inst : instruments) {
            if (inst.isRented()) {
                if (rentCount.containsKey(inst.name)) {
                    Integer currCount = rentCount.get(inst.name);
                    rentCount.replace(inst.name, currCount + 1);
                } else {
                    rentCount.put(inst.name, 1);
                }
            }
        }

        String output = "";

        for (Map.Entry<String, Integer> entry : stockCount.entrySet()) {
            output += printLowStock(entry, rentCount);
        }
        if (output.isEmpty()) {
            System.out.println("Low Stock Checker: All instruments sufficiently stocked");
        } else {
            System.out.println("These stocks are running low: ");
            System.out.println(output);
        }
        System.out.println(TEXTBORDER);
    }
}
