package com.imayam.ticket;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import com.google.checkout.schema._2.AnyType;
import com.google.checkout.schema._2.CheckoutShoppingCart;
import com.google.checkout.schema._2.CheckoutShoppingCart.CheckoutFlowSupportType;
import com.google.checkout.schema._2.Item;
import com.google.checkout.schema._2.MerchantCheckoutFlowSupport;
import com.google.checkout.schema._2.Money;
import com.google.checkout.schema._2.ObjectFactory;
import com.google.checkout.schema._2.ShoppingCart;
import com.google.checkout.schema._2.ShoppingCart.ItemsType;
import org.apache.log4j.Logger;
import java.sql.SQLException;

public class TicketBD {
    private static Logger logger = Logger.getLogger(TicketBD.class);


    public ShoppingCart buildShoppingCart(ObjectFactory objectFactory, ArrayList priceList, String email, HttpServletRequest request) throws JAXBException, SQLException {
        ShoppingCart shoppingCart = objectFactory.createShoppingCart();
        SimpleDateFormat date = new SimpleDateFormat("E, dd-MMM-yy hh:mma");
        ItemsType itemsType = objectFactory.createShoppingCartItemsType();
        List itemList = itemsType.getItem();
        int scheduleID = 0;
        int totalTickets = 0;

        DataAccess data = new DataAccess();

        for (int i = 0; i < priceList.size(); i++) {
            String item_name = (String) request.getParameter("item_name_" + String.valueOf(i + 1));
            String item_quantity = (String) request.getParameter("item_quantity_" + String.valueOf(i + 1));
            PriceTO price = (PriceTO) priceList.get(i);
            scheduleID = price.getScheduleId();

            if (price.getName().equalsIgnoreCase(item_name) && !"0".equalsIgnoreCase(item_quantity)) {
                //price.setTicketCount(Integer.parseInt(("".equalsIgnoreCase(item_quantity)) ? "0" : item_quantity));
                String itemName = price.getName() + " for " + price.getMovie() + " on " + date.format(new Date(price.getScheduleDateTime().getTime()));
                String itemDescription = price.getVenue() + "@" + price.getCity() + "," + price.getState();
                logger.debug("Schedule ID " + i + " : " + scheduleID);
                itemList.add(createItem(objectFactory, itemName, itemDescription, scheduleID, Integer.parseInt(item_quantity), String.valueOf(price.getPrice())));
            }
            totalTickets = totalTickets + Integer.parseInt(item_quantity);
//            scheduleID = price.getScheduleId(); //Since its the same schedule if for type of tickets its ok to set multiple times
//            newPriceList.add(price);
//            price = null;
        }

        if (data.checkAvailability(scheduleID, totalTickets)) {
            shoppingCart.setItems(itemsType);
            if (email != null) {
                AnyType anyEmail = objectFactory.createAnyType();
                anyEmail.setAny(email);
                shoppingCart.setMerchantPrivateData(anyEmail);
            }
        } else {
            shoppingCart = null;
        }
        return shoppingCart;
    }

    public CheckoutFlowSupportType buildCheckoutFlowSupport(ObjectFactory objectFactory) throws JAXBException {
        CheckoutFlowSupportType flowSupport = objectFactory.createCheckoutShoppingCartCheckoutFlowSupportType();
        MerchantCheckoutFlowSupport merchant = objectFactory.createMerchantCheckoutFlowSupport();
        merchant.setContinueShoppingUrl("http://www.dreamminds.net");
        flowSupport.setMerchantCheckoutFlowSupport(merchant);
        return flowSupport;
    }


    public static Item createItem(ObjectFactory objFactory, String itemName, String description, int scheduleID, int quantity, String price) throws JAXBException {
        Item itemType = objFactory.createItem();

        itemType.setItemName(itemName);
        itemType.setItemDescription(description);
        itemType.setQuantity(quantity);

        Money money = objFactory.createMoney();
        money.setCurrency("USD");
        money.setValue(new BigDecimal(price));
        itemType.setUnitPrice(money);

        AnyType anySchedule = objFactory.createAnyType();
        anySchedule.setAny(new Integer(scheduleID));
        itemType.setMerchantPrivateItemData(anySchedule);

        return itemType;
    }
/*    public static void main(String args[]){
    	
    	try {
			com.imayam.music.DataAccess.updatemonthCount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
}
