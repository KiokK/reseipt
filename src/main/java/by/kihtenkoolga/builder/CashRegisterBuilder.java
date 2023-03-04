package by.kihtenkoolga.builder;

import by.kihtenkoolga.factory.model.ReceiptRequestInfo;
import by.kihtenkoolga.model.Product;

import java.util.Map;
/**Класс строитель чека для консоли и файла,
 * наследуется от {@link CashRegister}, чтобы иметь все поля для построения чека,
 * реализует интерфейс {@link Builder}*/
public class CashRegisterBuilder extends CashRegister implements Builder {
    //CashRegister - абстрактный, чтобы не создавать конструктор в CashRegisterBuilder

    @Override
    public void getData(ReceiptRequestInfo data) {
        this.shop = data.getShop();
        this.cashierNumber = data.getCashierNumber();
        this.card = data.getCard();
        this.products = data.getProducts();

        calculatePrices();
    }

    @Override
    public void setTop() {
        receipt = center("CASH RECEIPT", LEN) + "\n";
        receipt += center(shop.getName(), LEN) + "\n";
        receipt += center(shop.getAddress(), LEN) + "\n";
        receipt += center("Phone: " + shop.getPhone(), LEN) + "\n";

        receipt += String.format("CASHIER N%s \n", cashierNumber);
        receipt += String.format("DATE, TIME: %"+(LEN - 13)+"s\n", new java.util.Date());
        receipt += String.format("%" + LEN + "s\n", '-').replace(' ', '-');
    }

    @Override
    public void setProductsList() {
        int sec = (LEN - 3)/2;
        if ( (LEN - 3 - sec) % 2 == 0) sec--;
        int th = (LEN - 3 - sec)/2 - 2;
        receipt+=String.format("%-3s %-"+sec+"s %"+th+"s %"+th+"s%n",
                "QTY", "DESCRIBE", "PRICE","TOTAL");
        for (Map.Entry<Product, Integer> product_amount : products.entrySet()) {
            receipt+=createProductLine(product_amount.getValue(), product_amount.getKey(), sec, th);
        }
    }
    private String createProductLine(int count, Product product, int sec, int th) {
        return String.format("%-3s %-"+sec+"s %"+th+".2f$ %"+th+".2f$%n",
                count, product.getName(),
                product.getPrice(), product.getPrice()*count);

    }

    @Override
    public void setFlow() {
        receipt += String.format("%" + LEN + "s\n", '-').replace(' ', '-');
        receipt += String.format("TAXABLE TOT.%"+(LEN-14)+".2f $\n", price);
        //if a discount card exists and the goods are taken at a discount
        if (card != null && onDiscountCount != 0) {
            receipt += String.format("Discounted products (%.2f $): \n", discount);
            for (Product pr : onDiscount)
                receipt += pr.getName() + " -" + pr.getDiscountPercentage() + "%\n";
            if (onDiscountCount >= 5)
                receipt += String.format("Plus 10%% discount for %d products! \n", onDiscountCount);

        }
        setTotalPrice();
    }

    void setTotalPrice() {
        receipt += String.format("%" + LEN + "s\n", '=').replace(' ', '=');
        receipt += String.format("TOTAL.%% %"+(LEN-10)+".2f $\n", getTotalPrise());
    }

    public CashRegister getResult() {
        return new CashRegisterDate(price, discount, onDiscount, receipt, shop, products, cashierNumber, card);
    }

    /**Метод центрирования текста по указанной ширине*/
    private String center(String text, int len){
        if (len <= text.length())
            return text.substring(0, len);
        int before = (len - text.length())/2;
        if (before == 0)
            return String.format("%-" + len + "s", text);
        int rest = len - before;
        return String.format("%" + before + "s%-" + rest + "s", "", text);
    }

}
