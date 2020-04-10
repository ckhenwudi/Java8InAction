package lambdasinaction.chap6;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList( new Transaction(CurrencyEnum.EUR, 1500.0),
                                                                  new Transaction(CurrencyEnum.USD, 2300.0),
                                                                  new Transaction(CurrencyEnum.GBP, 9900.0),
                                                                  new Transaction(CurrencyEnum.EUR, 1100.0),
                                                                  new Transaction(CurrencyEnum.JPY, 7800.0),
                                                                  new Transaction(CurrencyEnum.CHF, 6700.0),
                                                                  new Transaction(CurrencyEnum.EUR, 5600.0),
                                                                  new Transaction(CurrencyEnum.USD, 4500.0),
                                                                  new Transaction(CurrencyEnum.CHF, 3400.0),
                                                                  new Transaction(CurrencyEnum.GBP, 3200.0),
                                                                  new Transaction(CurrencyEnum.USD, 4600.0),
                                                                  new Transaction(CurrencyEnum.JPY, 5700.0),
                                                                  new Transaction(CurrencyEnum.EUR, 6800.0) );
    public static void main(String ... args) {
        groupImperatively();
        groupFunctionally();

    }

    private static void groupImperatively() {
        Map<CurrencyEnum, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            CurrencyEnum currency = transaction.getCurrencyEnum();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally() {
        Map<CurrencyEnum, List<Transaction>> transactionsByCurrencies = transactions.stream().collect(groupingBy(Transaction::getCurrencyEnum));
        System.out.println(transactionsByCurrencies);
    }

    public static class Transaction {
        private final CurrencyEnum currencyEnum;
        private final double value;

        public Transaction(CurrencyEnum currency, double value) {
            this.currencyEnum = currency;
            this.value = value;
        }

        public CurrencyEnum getCurrencyEnum() {
            return currencyEnum;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currencyEnum + " " + value;
        }
    }

    public enum CurrencyEnum {
        EUR, USD, JPY, GBP, CHF
    }
}
