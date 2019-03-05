package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class CurrencyText {

    private static final int BASE_DIVISOR = 1000;
    
    public static final Logger logger = Logger.getLogger(CurrencyText.class.getName());

    private static final String[] BASE10 = {
            "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
            "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ",
            "Nineteen ", "Twenty "
    };

    private static final String[] DECIMALS = {"Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

    private static final String[] BASES = {
            "",
            "Thousand ",
            "Million ",
            "Billion "
    };

    private List<Integer> sections = new ArrayList<>();

    public static void main(String[] args) {
        CurrencyText test = new CurrencyText();

        logger.info("Response for 0: " + test.prettyPrintCurrency(0));
        logger.info("Response for 54: " + test.prettyPrintCurrency(54));
        logger.info("Response for 333: " + test.prettyPrintCurrency(333));
        logger.info("Response for 5457: " + test.prettyPrintCurrency(5457));
        logger.info("Response for 1234: " + test.prettyPrintCurrency(1234));
        logger.info("Response for 15987: " + test.prettyPrintCurrency(15987));
        logger.info("Response for MAX INT: " + test.prettyPrintCurrency(Integer.MAX_VALUE));
    }

    private String prettyPrintCurrency(int amount) {

        sections.clear();

        if(amount < 0) throw new RuntimeException("Only accepts positive arguments");

        makeCalculations(amount);

        if(sections.isEmpty()) return "Zero Dollar";

        StringBuffer displayValue = new StringBuffer();
        for(int i = sections.size() - 1; i >= 0; i--) {
            displayValue.append(convertHundredsToText(sections.get(i)) + BASES[i]);
        }

        return displayValue.toString() + "Dollars";
    }

    private void makeCalculations(int amount) {
        if (amount == 0) return;

        int division = amount / BASE_DIVISOR;
        int mod = amount % BASE_DIVISOR;

        sections.add(mod);

        if (division > BASE_DIVISOR) {
            makeCalculations(division);
        } else if (division > 0){
            sections.add(division);
        }

    }

    private String convertHundredsToText(int num) {
        if (num > 0 && num <= 20) {
            return BASE10[num - 1];
        } else if (num < 100) {
            int unit = num % 10;
            int base10 = num / 10;
            return DECIMALS[base10 - 1] + BASE10[unit - 1];
        }else{
            int base10 = num % 100;
            int base100 = num / 100;
            return BASE10[base100 - 1] + "Hundred and " + convertHundredsToText(base10);
        }
    }
}
