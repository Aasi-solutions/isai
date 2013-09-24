package com.imayam.ticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.checkout.schema._2.NewOrderNotification;
import org.apache.log4j.Logger;

public class StatusServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(StatusServlet.class);

    public StatusServlet() {
    }

    private String getModifiedBufferedReader(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        //buffer.append(theOtherString);
        return buffer.toString();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataAccess data = new DataAccess();
        try {

            JAXBContext jc = JAXBContext.newInstance("com.google.checkout.schema._2");

            InputStream in = request.getInputStream();
            //logger.debug(getModifiedBufferedReader(in));
            Unmarshaller u = jc.createUnmarshaller();
            Object o = u.unmarshal(in);
            if (o instanceof NewOrderNotification) {
                NewOrderNotification order = (NewOrderNotification) o;
                logger.debug("Order Number : " + order.getGoogleOrderNumber());
                logger.debug("Billing Name : " + order.getBuyerBillingAddress().getContactName());
                logger.debug("Shipping Name : " + order.getBuyerShippingAddress().getContactName());
                logger.debug("Shipping Email : " + order.getBuyerBillingAddress().getEmail());
                logger.debug("Merchant Data : " + order.getShoppingCart().getMerchantPrivateData());
                data.saveGoogleOrder(order);
            }

        } catch (JAXBException je) {
            logger.error("Exception : ", je);
        } catch (IOException ioe) {
            logger.error("Exception : ", ioe);
        } catch (Exception e) {
            logger.error("Exception : ", e);
        }
    }

}
