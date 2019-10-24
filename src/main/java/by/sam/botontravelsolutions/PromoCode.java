package by.sam.botontravelsolutions;

import java.util.LinkedHashSet;
import java.util.Set;

public class PromoCode implements OpertaionProvider {
    private Set<String> userList = new LinkedHashSet<>();
    private String userName;
    private int length = 8;

    @Override
    public String getNameOperation() {
        return "/code";
    }

    @Override
    public String getOperation() {

        if (!userList.contains(userName)) {
            String pCode = "";
            String symbols = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
            int max = symbols.length() - 1;
            for (int i = 0; i < length; i++) {
                int position = (int) Math.floor(Math.random() * max);
                pCode = pCode + symbols.substring(position, position + 1);
            }
            userList.add(userName);
            return "Your promo code:  "+pCode;
        } else {
           return  "You have already received a promo code.";
        }
    }

    @Override
    public void setAttribute(String value) {
        this.userName=value;
    }

    @Override
    public String getOperationDescription() {
        return "Get promo code for buy travel-tour.";
    }

    public void setLength(int length) {
        this.length = length;
    }
}
