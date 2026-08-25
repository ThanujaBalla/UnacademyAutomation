package Pages;

import com.microsoft.playwright.*;

import Utilities.ConfigReader;

import Utilities.TestData;

public class PaymentPage {
    private final Page page;
    public PaymentPage(Page page) { this.page = page; }

    public void openStub(String scenario) {
        if (!"stub".equalsIgnoreCase(ConfigReader.getProperty("paymentMode"))) {
            throw new IllegalStateException("PaymentPage.openStub can only be used when paymentMode=stub");
        }
        String result = switch (scenario) {
            case "UPI", "DEBIT_CARD", "CREDIT_CARD", "NET_BANKING" -> "SUCCESS";
            case "INVALID_CARD" -> "INVALID_DETAILS";
            case "INSUFFICIENT_FUNDS" -> "INSUFFICIENT_FUNDS";
            case "NETWORK_FAILURE" -> "NETWORK_FAILURE";
            default -> throw new IllegalArgumentException("Unknown payment scenario: " + scenario);
        };
        String value = scenario.equals("UPI") ? TestData.upi() : TestData.card();
        page.setContent("""
            <html><body style='font-family:Arial;padding:30px'>
            <h1>Authorized Test Payment Gateway</h1>
            <p>This is a local deterministic simulator. No real payment is sent.</p>
            <label>Method <select id='method'><option>UPI</option><option>DEBIT_CARD</option><option>CREDIT_CARD</option><option>NET_BANKING</option></select></label><br><br>
            <input id='value' placeholder='Test payment value' value='%s'><br><br>
            <button id='payBtn' type='button'>Pay now</button>
            <h2 id='result'>PENDING</h2>
            <script>document.querySelector('#payBtn').onclick=()=>document.querySelector('#result').innerText='%s';</script>
            </body></html>
            """.formatted(value, result));
        page.locator("#method").selectOption(switch (scenario) {
            case "UPI" -> "UPI";
            case "DEBIT_CARD" -> "DEBIT_CARD";
            case "CREDIT_CARD" -> "CREDIT_CARD";
            case "NET_BANKING" -> "NET_BANKING";
            default -> "CREDIT_CARD";
        });
        page.locator("#payBtn").click();
        page.locator("#result").waitFor();
    }

    public String result() { return page.locator("#result").innerText(); }
}
