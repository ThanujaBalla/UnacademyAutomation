package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Pages.PaymentPage;

public class PaymentMatrixTest extends BaseTest {

    @DataProvider(name = "payments")
    public Object[][] payments() {
        return new Object[][] {
            {"TC062_UPI", "UPI", "SUCCESS"},
            {"TC063_DebitCard", "DEBIT_CARD", "SUCCESS"},
            {"TC064_CreditCard", "CREDIT_CARD", "SUCCESS"},
            {"TC065_NetBanking", "NET_BANKING", "SUCCESS"},
            {"TC066_InvalidCard", "INVALID_CARD", "INVALID_DETAILS"},
            {"TC067_InsufficientBalance", "INSUFFICIENT_FUNDS", "INSUFFICIENT_FUNDS"},
            {"TC068_NetworkInterruption", "NETWORK_FAILURE", "NETWORK_FAILURE"}
        };
    }

    @Test(dataProvider = "payments")
    public void paymentScenario(String id, String scenario, String expected) {
        // Ensure page is active; navigating to 'about:blank' or resetting page state prevents state-bleeding
        if (page != null) {
            page.navigate("about:blank");
        }
        
        PaymentPage payment = new PaymentPage(page);
        payment.openStub(scenario);
        
        // Assert with clean state
        Assert.assertEquals(payment.result(), expected, id + " failed scenario verification.");
    }
}