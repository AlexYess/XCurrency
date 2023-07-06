package service;

import model.Currency;

import javax.xml.ws.http.HTTPException;
import java.util.*;

public class CurrencyService {
    private List<Currency> currencyList = new ArrayList<>();
    public Optional<Currency> GetCurrencyCode(String code) {
        for (Currency currency : currencyList) {
            if (currency.getCurrencyCode().equals(code)) {
                return Optional.of(currency);
            }
        }
        return Optional.empty();
    }
}
