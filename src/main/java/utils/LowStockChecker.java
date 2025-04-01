package utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import instrument.InstrumentList;
import instrument.Instrument;

import static ui.Ui.*;

public class LowStockChecker {
    public static void printLowStock(Map.Entry<String, Integer> entry) {
        String instName = entry.getKey();
        Integer instCount = entry.getValue();
        if (instCount < CRITICAL_QTY) { // critical, must replenish soon
            System.out.println(instName + ": " + RED + instCount + RESET);
        } else if (instCount < WARNING_QTY) {
            System.out.println(instName + ": " + YELLOW + instCount + RESET);
        }
    }

    public static void checkAll(ArrayList<Instrument> instruments) {
        System.out.println("These stocks are running low: ");

        HashMap<String, Integer> stockCount = new HashMap<>();

        for (Instrument inst : instruments) {
            if (stockCount.containsKey(inst.name)) {
                Integer currCount = stockCount.get(inst.name);
                stockCount.replace(inst.name, currCount + 1);
            } else {
                stockCount.put(inst.name, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : stockCount.entrySet()) {
            printLowStock(entry);
        }
        System.out.println(TEXTBORDER);
    }
}
