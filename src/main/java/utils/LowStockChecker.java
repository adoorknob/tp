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
    public static void printLowStock(Map.Entry<String, Integer> entry, HashMap<String, Integer> rentCount) {
        String instName = entry.getKey();
        Integer instCount = entry.getValue();
        Integer rentedCount = (rentCount.get(instName) == null ? 0 : rentCount.get(instName));
        Integer availCount = instCount - rentedCount;
        if (availCount < CRITICAL_QTY) { // critical, must replenish soon
            System.out.println(instName + ": " + RED + availCount + RESET + " available");
        } else if (availCount < WARNING_QTY) {
            System.out.println(instName + ": " + YELLOW + availCount + RESET + " available");
        }
    }

    public static void checkAll(ArrayList<Instrument> instruments) {
        System.out.println("These stocks are running low: ");

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

        for (Map.Entry<String, Integer> entry : stockCount.entrySet()) {
            printLowStock(entry, rentCount);
        }
        System.out.println(TEXTBORDER);
    }
}
