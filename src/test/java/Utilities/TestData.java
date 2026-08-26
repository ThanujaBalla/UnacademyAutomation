package Utilities;

public final class TestData {
    private TestData() {}
    public static String username() { return ConfigReader.getProperty("username"); }
    public static String password() { return ConfigReader.getProperty("password"); }
    public static String upi() { return ConfigReader.getProperty("upiId"); }
    public static String card() { return ConfigReader.getProperty("cardNumber"); }
    public static String expiry() { return ConfigReader.getProperty("cardExpiry"); }
    public static String cvv() { return ConfigReader.getProperty("cardCvv"); }
    public static String bank() { return ConfigReader.getProperty("netBankingBank"); }
}
