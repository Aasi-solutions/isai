package com.imayam.ticket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.imayam.rss.Rss;
import org.imayam.rss.ChannelType;
import java.util.List;
import org.imayam.rss.Item;
import java.beans.XMLEncoder;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import com.google.checkout.schema._2.ObjectFactory;
import com.google.checkout.schema._2.CheckoutShoppingCart;
import javax.xml.bind.Marshaller;
import com.google.checkout.sample.crypto.Base64Coder;
import com.google.checkout.schema._2.CheckoutShoppingCartElement;
import com.google.checkout.schema._2.CheckoutRedirect;
import com.google.checkout.schema._2.ShoppingCart;


public class ControllerServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(ControllerServlet.class);
    private String errorPage = "error.jsp";

    public ControllerServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getParameter("action");
            logger.debug("Ticketing action : " + action);
            if ("displaySchedule".equalsIgnoreCase(action)) {
                displaySchedule(request, response);
            } else if ("confirm".equalsIgnoreCase(action)) {
                selectTime(request, response);
            } else if ("checkout".equalsIgnoreCase(action)) {
                //checkout(request, response);
                xmlCheckout(request, response);
            } else if ("dream".equalsIgnoreCase(action)) {
                dream(request, response);
            } else if ("google".equalsIgnoreCase(action)) {
                googleCallBackTest(request, response);
            } else if ("getgoog".equalsIgnoreCase(action)) {
                displayGoogleOrders(request, response);
            } else if ("login".equalsIgnoreCase(action)) {
                login(request, response);
            } else if ("news".equalsIgnoreCase(action)) {
                rssFeed(request, response);
            } else if ("validate".equalsIgnoreCase(action)) {
                validateRegistration(request, response);
            } else {
                displaySchedule(request, response);
                //callGoogle(request, response);
            }
        } catch (Exception e) {
            logger.error("Exception : ", e);
            request.getRequestDispatcher(errorPage).forward(request, response);
        }
    }

    private void validateRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String forwardPage = "";
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        HttpSession session = request.getSession();

        DataAccess data = new DataAccess();
        if (data.isValidUser(email, phone)) {
            session.setAttribute("valid", email);
            selectTime(request, response);
        } else {
            session.removeAttribute("valid");
            String msg = "Sorry, invalid data or ticket purchased. If you need help, email us at sales@dreamminds.net.";
            request.setAttribute("message", msg);
            forwardPage = "ticket/validate.jsp";
            request.getRequestDispatcher(forwardPage).forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("dreamminds".equalsIgnoreCase(request.getParameter("username")) && "VaajiVaaji".equals(request.getParameter("password"))) {
            session.setAttribute("loginFlag", "ValidLogin");
            displayGoogleOrders(request, response);
        } else {
            request.setAttribute("message", "Invlid Login/Password");
            request.getRequestDispatcher("ticket/login.jsp").forward(request, response);
        }
    }

    private void displayGoogleOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardPage = "ticket/orderlist.jsp";
        HttpSession session = request.getSession();

        String loginFlag = (String) session.getAttribute("loginFlag");
        if (loginFlag == null) {
            forwardPage = "ticket/login.jsp";
        } else {
            session.removeAttribute("googlist");
            DataAccess data = new DataAccess();
            try {
                session.setAttribute("showNameList", data.getShowNames());
                String timeSelected = request.getParameter("selectedname");
                request.setAttribute("timeSelected", timeSelected);
                session.setAttribute("googlist", data.getGoogleOrders(timeSelected));
            } catch (SQLException ex) {
                logger.error("SQLException : ", ex);
                request.getRequestDispatcher(errorPage).forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    private void googleCallBackTest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer returnXML = new StringBuffer(
            "<?xml version='1.0' encoding='UTF-8'?><new-order-notification xmlns='http://checkout.google.com/schema/2' serial-number='85f54628-538a-44fc-8605-ae62364f6c71'><google-order-number>841171949014328</google-order-number><buyer-shipping-address><contact-name>John Smith</contact-name><email>johnsmith@example.com</email><address1>10 Example Road</address1><city>Sampleville</city><region>CA</region><postal-code>94141</postal-code><country-code>US</country-code></buyer-shipping-address><buyer-billing-address><contact-name>Bill Hu</contact-name><email>billhu@example.com</email>");
        returnXML.append("<address1>99 Credit Lane</address1><city>Mountain View</city><region>CA</region><postal-code>94043</postal-code><country-code>US</country-code></buyer-billing-address><buyer-id>294873009217523</buyer-id><fulfillment-order-state>NEW</fulfillment-order-state><financial-order-state>REVIEWING</financial-order-state><shopping-cart><cart-expiration><good-until-date>2007-12-31T23:59:59-05:00</good-until-date></cart-expiration><items><item><item-name> Adult for Periyar on May 20, 2007 8:00 PM (62)</item-name><item-description>One pack of nutritious dried food for emergencies.</item-description><quantity>3</quantity><tax-table-selector>food</tax-table-selector><unit-price currency='USD'>4.99</unit-price></item><item><item-name>Kid for Periyar on May 20, 2007 8:00 PM (62)</item-name><item-description>This portable MP3 player stores 500 songs.</item-description>");
        returnXML.append("<quantity>1</quantity><unit-price currency='USD'>179.99</unit-price></item></items></shopping-cart><order-adjustment><merchant-calculation-successful>true</merchant-calculation-successful><merchant-codes><coupon-adjustment><applied-amount currency='USD'>5.00</applied-amount><code>FirstVisitCoupon</code><calculated-amount currency='USD'>5.00</calculated-amount><message>You saved $5.00 for your first visit!</message></coupon-adjustment><gift-certificate-adjustment><applied-amount currency='USD'>10.00</applied-amount>");
        returnXML.append("<code>GiftCert12345</code><calculated-amount currency='USD'>10.00</calculated-amount><message>You saved $10.00 with this gift certificate!</message></gift-certificate-adjustment></merchant-codes><total-tax currency='USD'>11.05</total-tax><shipping><merchant-calculated-shipping-adjustment><shipping-name>SuperShip</shipping-name><shipping-cost currency='USD'>9.95</shipping-cost></merchant-calculated-shipping-adjustment></shipping></order-adjustment><order-total currency='USD'>190.98</order-total><buyer-marketing-preferences><email-allowed>false</email-allowed></buyer-marketing-preferences><timestamp>2006-05-03T17:32:11</timestamp></new-order-notification>");

        try {

            URL url;
            URLConnection urlConn;
            DataOutputStream printout;
            DataInputStream input;
            //url = new URL("http://cawnckhl7238c01.uswin.ad.vzwcorp.com:7001/smartlinks/getstatus");
            url = new URL("http://www.imayam.org/getstatus");
            // URL connection channel.
            urlConn = url.openConnection();
            // Let the run-time system (RTS) know that we want input.
            urlConn.setDoInput(true);
            // Let the RTS know that we want to do output.
            urlConn.setDoOutput(true);
            // No caching, we want the real thing.
            urlConn.setUseCaches(false);
            // Specify the content type.
            urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream());

            //String content = "name=" + URLEncoder.encode ("Buford Early") + "&email=" + URLEncoder.encode ("buford@known-space.com");
            logger.debug(returnXML.toString());
            printout.writeBytes(returnXML.toString());
            printout.flush();

            // Get response data.
            /*            input = new DataInputStream(urlConn.getInputStream());
                        String str;
                        while (null != ((str = input.readLine()))) {
                            System.out.println(str);
                            //textArea.appendText (str + "\n");
                        }
                        input.close(); */
            printout.close();

            logger.debug("Success");
        } catch (Exception e) {
            logger.error("Exception thrown : ", e);
        }
    }

    private void dream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataAccess data = new DataAccess();
        ScheduleTO scheduleTO = null;
        StringBuffer buffer = new StringBuffer();
        SimpleDateFormat date = new SimpleDateFormat("E, dd-MMM-yy");
        SimpleDateFormat time = new SimpleDateFormat("hh:mma");
        HttpSession session = request.getSession();
        session.removeAttribute("priceList");
        try {
            ArrayList schedule = data.getSchedule();
            Iterator iterator = schedule.iterator();
            if (iterator.hasNext()) {
                String movieName = null;
                String movieDate = null;
                int i = 0;
                while (iterator.hasNext()) {
                    scheduleTO = (ScheduleTO) iterator.next();
                    if (!scheduleTO.getMovieName().equalsIgnoreCase(movieName)) {
                        i = 0;
                        if (movieName != null) {
                            buffer.append("document.write('</td></tr>');");
                        }
                        buffer.append("document.write('<table width=\"400\" cellpadding=\"20\"><tr><td colspan=2>');");
                        buffer.append("document.write('<hr><br><b><div style=\"font-size: 150%; color:\\#f89d31;\">" + scheduleTO.getMovieName() + "</div></b><br><br>');");
                        buffer.append("document.write('<img width=\"400\" height=\"200\" src=\"" + scheduleTO.getImageLink() + "\" alt=\"" + scheduleTO.getMovieName() + "\"><br><br>');");
                        buffer.append("document.write('<div id=\"loginbox\"><h2>Show at : </h2><br>" + scheduleTO.getVenueName() + "<br>');");
                        buffer.append("document.write('" + scheduleTO.getAddress1() + "<br>');");
                        buffer.append("document.write('" + scheduleTO.getCity() + "," + scheduleTO.getState() + " " + scheduleTO.getZip() + "</div>');");

                        buffer.append("document.write('<div style=\"font-size: 130%; color:\\#f89d31;\">" + scheduleTO.getPromotion() + "</div>');");

                        buffer.append("document.write('<div id=\"tips_box\">');");
                        buffer.append("document.write('Click on the showtimes to purchase tickets.</div>');");
                        movieName = scheduleTO.getMovieName();
                        buffer.append("document.write('</td></tr><tr><td colspan=2>');");
                    }
                    if (!date.format(new Date(scheduleTO.getScheduleDateTime().getTime())).equalsIgnoreCase(movieDate)) {
                        buffer.append("document.write('</td></tr><tr><td width=\"30%\">" + date.format(new Date(scheduleTO.getScheduleDateTime().getTime())) + "</td><td width=\"70%\">');");
                        movieDate = date.format(new Date(scheduleTO.getScheduleDateTime().getTime()));
                        i = 0;
                    }
                    if ("N".equalsIgnoreCase(scheduleTO.getIsThirdPartyLink())) {
                        if (scheduleTO.getTicketsSold() < scheduleTO.getCapacity()) {
                            buffer.append("document.write(' <a href=\"http://www.imayam.org/ticket?action=confirm&amp;sch=" + scheduleTO.getScheduleId() + "\">" + time.format(new Date(scheduleTO.getScheduleDateTime().getTime())) + "</a>');");
                            if (i == 5) {
                                buffer.append("document.write('<br>');");
                                i = 0;
                            }
                        } else {
                            buffer.append("document.write(' " + time.format(new Date(scheduleTO.getScheduleDateTime().getTime())) + "');");
                            if (i == 5) {
                                buffer.append("document.write('<br>');");
                                i = 0;
                            }
                        }
                    } else {
                        buffer.append("document.write(' <a href=\"" + scheduleTO.getThirdPartyLink() + "\">" + time.format(new Date(scheduleTO.getScheduleDateTime().getTime())) + "</a>');");
                        if (i == 5) {
                            buffer.append("document.write('<br>');");
                            i = 0;
                        }
                    }
                    i++;
                }
            } else {
                buffer.append("document.write('No shows from dreamminds.net for this week.<br>Please check back later.');");
            }
            buffer.append("document.write('</td></tr></table>');");
            // Get the output stream
            PrintWriter out = response.getWriter();
            // Set the content type
            response.setContentType("text/plain");
            // Send the data
            out.print(buffer.toString());
            out.flush();

        } catch (SQLException ex) {
            logger.error("SQLException : ", ex);
            request.getRequestDispatcher("document.write ('Error')").forward(request, response);
        }

    }

    public void rssFeed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            JAXBContext jc = JAXBContext.newInstance("org.imayam.rss");
            Unmarshaller u = jc.createUnmarshaller();
            StringBuffer buffer = new StringBuffer();
            Object o = u.unmarshal(new URL("http://tamil.galatta.com/entertainment/livewire/rss/tamil.xml"));
            if (o instanceof Rss) {
                Rss rss = (Rss) o;
                List itemList = rss.getChannel().getItem();
                Iterator itemIterator = itemList.iterator();
                int i = 0;
                while (itemIterator.hasNext()) {
                    Item item = (Item) itemIterator.next();
                    if (item.getDescription().indexOf("Galatta") == -1 && item.getDescription().indexOf("our") == -1) {
                        buffer.append("document.write(\"<li><a href='" + item.getLink().trim() + "'>" + item.getTitle().trim() + "</a></li>&nbsp;&nbsp;\");");
                        buffer.append("document.write(\"" + item.getDescription().trim().substring(0, item.getDescription().indexOf(".")) + "\");");
                    }
                    if (i++ == 10) {
                        break;
                    }
                }
            }

            // Get the output stream
            PrintWriter out = response.getWriter();
            // Set the content type
            response.setContentType("text/plain");
            // Send the data
            out.print(buffer.toString());
            out.flush();

        } catch (Exception ex) {
            //No Action required
            logger.error("RSS Exception : ", ex);
        }

    }

    private void displaySchedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardPage = "ticket/schedule.jsp";
        HttpSession session = request.getSession();

        session.removeAttribute("schedule");
        session.removeAttribute("priceList");

        DataAccess data = new DataAccess();

        try {
            session.setAttribute("schedule", data.getSchedule());
        } catch (SQLException ex) {
            logger.error("SQLException : ", ex);
            request.getRequestDispatcher(errorPage).forward(request, response);
        }

        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    private void selectTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardPage = "ticket/confirm.jsp";
        HttpSession session = request.getSession();
        ArrayList priceList = null;

        session.removeAttribute("schedule");
        try {
            DataAccess data = new DataAccess();

            String scheduleId = request.getParameter("sch");
            if (scheduleId == null) {
                priceList = (ArrayList) session.getAttribute("priceList");
                if (priceList == null) {
                    //User hit the page direct
                    forwardPage = "ticket/schedule.jsp";
                }
            } else {
                priceList = data.getPrice(scheduleId);
            }

//            if (priceList == null) {
//                priceList = data.getPrice(request.getParameter("sch"));
//            }
            if (priceList != null) {
                PriceTO price = (PriceTO) priceList.get(0); //Check for the fist, since all will ahve the same record.
                if ("Y".equalsIgnoreCase(price.getValidateRegister()) && session.getAttribute("valid") == null) {
                    forwardPage = "/ticket/validate.jsp";
                }
                session.setAttribute("priceList", priceList);
            }
        } catch (SQLException ex) {
            logger.error("SQLException : ", ex);
            request.getRequestDispatcher(errorPage).forward(request, response);
            return;
        }
        //session.removeAttribute("valid");
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String forwardPage = "ticket/confirm.jsp";

        HttpSession session = request.getSession();
        ArrayList newPriceList = new ArrayList();
        DataAccess data = new DataAccess();
        int totalTickets = 0;
        int scheduleID = 0;
        String msg = null;

        ArrayList priceList = (ArrayList) session.getAttribute("priceList");

        if (priceList == null) {
            //In case customer hits confirm page directly
            displaySchedule(request, response);
        } else {

            for (int i = 0; i < priceList.size(); i++) {
                String item_name = (String) request.getParameter("item_name_" + String.valueOf(i + 1));
                String item_quantity = (String) request.getParameter("item_quantity_" + String.valueOf(i + 1));
                PriceTO price = (PriceTO) priceList.get(i);
                logger.debug("request name : " + item_name);
                logger.debug("TO name : " + price.getName());
                if (price.getName().equalsIgnoreCase(item_name)) {
                    price.setTicketCount(Integer.parseInt(("".equalsIgnoreCase(item_quantity)) ? "0" : item_quantity));
                }
                totalTickets = totalTickets + price.getTicketCount();
                scheduleID = price.getScheduleId(); //Since its the same schedule if for type of tickets its ok to set multiple times
                newPriceList.add(price);
                price = null;
            }

            if (data.checkAvailability(scheduleID, totalTickets)) {
                session.removeAttribute("valid"); //Since google checkout enabled. Removed valid info, so that he will not buy multiple tickets.
                session.removeAttribute("priceList");
                session.setAttribute("priceList", newPriceList);
                request.setAttribute("mode", "checkout");
            } else {
                //Show error message to user
                msg = "Sorry, not that many tickets available. You may try to decrease quantity.";
                request.setAttribute("message", msg);
            }

        }
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    private void xmlCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JAXBException {
        String forwardPage = errorPage;
        String sandboxURL = "https://sandbox.google.com/checkout/cws/v2/Merchant/812099310544625/merchantCheckout";
        String sandboxAuth = "812099310544625:P6x79GBcuop8YjmJhGxMDw";

        String prodURL = "https://checkout.google.com/cws/v2/Merchant/404372423280625/merchantCheckout";
        String prodAuth = "404372423280625:u88Y55nNywIjW5W1e8OL-Q";

        TicketBD ticketBD = new TicketBD();
        HttpSession session = request.getSession();
        ArrayList priceList = (ArrayList) session.getAttribute("priceList");

        JAXBContext jc = JAXBContext.newInstance("com.google.checkout.schema._2");
        ObjectFactory objFactory = new ObjectFactory();
        //CheckoutShoppingCart checkout = objFactory.createCheckoutShoppingCart();
        CheckoutShoppingCartElement checkoutElement = objFactory.createCheckoutShoppingCartElement();

        //checkout.setShoppingCart(ticketBD.buildShoppingCart(objFactory, priceList, request));
        //checkout.setCheckoutFlowSupport(ticketBD.buildCheckoutFlowSupport(objFactory));
        String email = (String) session.getAttribute("valid");
        ShoppingCart shoppintCart = ticketBD.buildShoppingCart(objFactory, priceList, email, request);
        if (shoppintCart == null) {
            //Show error message to user
            String msg = "Sorry, not that many tickets available. You may try to decrease quantity.";
            request.setAttribute("message", msg);
            forwardPage = "ticket/confirm.jsp";
            request.getRequestDispatcher(forwardPage).forward(request, response);
        } else {
            checkoutElement.setShoppingCart(shoppintCart);
            checkoutElement.setCheckoutFlowSupport(ticketBD.buildCheckoutFlowSupport(objFactory));

            //Connection
            URL url;
            URLConnection urlConn;
            DataOutputStream printout;
            DataInputStream input;
            url = new URL(prodURL);
            // URL connection channel.
            urlConn = url.openConnection();
            // Let the run-time system (RTS) know that we want input.
            urlConn.setDoInput(true);
            // Let the RTS know that we want to do output.
            urlConn.setDoOutput(true);
            // No caching, we want the real thing.
            urlConn.setUseCaches(false);
            // Specify the content type.
            urlConn.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
            urlConn.setRequestProperty("Accept", "application/xml; charset=UTF-8");
            urlConn.setRequestProperty("Authorization", "Basic " + Base64Coder.encode(prodAuth));
            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream());

//        logger.debug(returnXML.toString());
//        printout.writeBytes(returnXML.toString());
//        printout.flush();

            // create a Marshaller and marshal to System.out
            Marshaller m = jc.createMarshaller();
            //m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.TRUE);

            m.marshal(checkoutElement, printout);
            //m.marshal(checkout, System.out);

            // Get response data.
            input = new DataInputStream(urlConn.getInputStream());
//        String str;
//        while (null != ((str = input.readLine()))) {
//            logger.debug(str);
            //textArea.appendText (str + "\n");
//        }

            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            Object o = u.unmarshal(input);
            if (o instanceof CheckoutRedirect) {
                CheckoutRedirect redirect = (CheckoutRedirect) o;
                forwardPage = redirect.getRedirectUrl();
            }
            input.close();
            printout.close();
            //request.getRequestDispatcher(forwardPage).forward(request, response);
            logger.debug(forwardPage);
            session.removeAttribute("valid");
            session.removeAttribute("priceList");
            response.sendRedirect(forwardPage);
        }
    }
}
