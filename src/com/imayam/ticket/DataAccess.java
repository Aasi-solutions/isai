package com.imayam.ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.checkout.schema._2.Item;
import com.google.checkout.schema._2.NewOrderNotification;
import org.apache.log4j.Logger;

public class DataAccess {
    static Logger logger = Logger.getLogger(DataAccess.class);
    public DataAccess() {
    }

    public boolean isValidUser(String email, String phone) throws SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = getConnection();
        String sql = "SELECT email, phone FROM ticket_sivaji_premier where email = ? and issued = 'N'";
        if (phone == null || email == null) {
            return result;
        }
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dataPhone = scrub(rs.getString("phone"));
                String userPhone = scrub(phone);
                if (userPhone.equalsIgnoreCase(dataPhone)) {
                    logger.debug("Valid user : " + email);
                    result = true;
                    ps.close();
                } else {
                    logger.debug("InValid user : " + email);
                }
            }
        } finally {
            rs.close();
            ps.close();
            con.close();
        }
        return result;
    }

    private void markIssued(String email) throws SQLException {

        logger.debug("Marking email issued : " + email);
        PreparedStatement ps = null;
        Connection con = getConnection();

        String sql = "update ticket_sivaji_premier set issued = 'Y' where email = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.executeUpdate();

    }

    private String scrub(String phone) {
        StringBuffer newPhone = new StringBuffer();
        char[] phoneChar = phone.toCharArray();
        for (int i = 0; i < phoneChar.length; i++) {
            if (phoneChar[i] == '0' || phoneChar[i] == '1' || phoneChar[i] == '2' || phoneChar[i] == '3' || phoneChar[i] == '4' || phoneChar[i] == '5' || phoneChar[i] == '6' || phoneChar[i] == '7' || phoneChar[i] == '8' || phoneChar[i] == '9') {
                newPhone.append(phoneChar[i]);
            }
        }
        return newPhone.toString();
    }

    public void saveGoogleOrder(NewOrderNotification order) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();

            if (order.getShoppingCart().getMerchantPrivateData() != null) {
                String mail = (String) order.getShoppingCart().getMerchantPrivateData().getAny();
                String newMail = mail.substring(mail.indexOf(">")+1);
                logger.debug(newMail);
                markIssued(newMail);
            }

            List itemList = (List) order.getShoppingCart().getItems().getItem();
            logger.debug("Item Size : " + itemList.size());
            for (int i = 0; i < itemList.size(); i++) {
                String sql = "INSERT INTO ticket_google_order(order_number, billing_name, shipping_name, email, order_date, itemname, quantity) VALUES(?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                Item item = (Item) itemList.get(i);
                String itemName = item.getItemName();
                int qty = item.getQuantity();

                ps.setString(1, order.getGoogleOrderNumber());
                ps.setString(2, order.getBuyerBillingAddress().getContactName());
                ps.setString(3, order.getBuyerShippingAddress().getContactName());
                ps.setString(4, order.getBuyerShippingAddress().getEmail());
                ps.setTimestamp(5, new Timestamp(order.getTimestamp().getTimeInMillis()));
                ps.setString(6, itemName);
                ps.setInt(7, qty);
                ps.executeUpdate();

                //int start = itemName.indexOf("(");
                //int end = itemName.indexOf(")");

                String scheduleId = (String) item.getMerchantPrivateItemData().getAny();
                String newSch = scheduleId.substring(scheduleId.indexOf(">")+1);
                logger.debug(newSch);
                decreaseAvailability(qty, Integer.parseInt(newSch));
            }
        } finally {
            ps.close();
            con.close();
        }
    }

    public ArrayList getGoogleOrders(String showName) throws SQLException {
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        ArrayList orderList = new ArrayList();
        int totalTickets = 0;
        logger.debug("Show Name to display : " + showName);
        try {
            con = getConnection();
            statement = con.createStatement();
            String sql = null;
            if (showName != null) {
                sql = "SELECT order_number, billing_name, shipping_name, email, order_date, itemname, quantity FROM ticket_google_order where itemname like '%" + showName + "%' order by order_number";
            } else {
                sql = "SELECT order_number, billing_name, shipping_name, email, order_date, itemname, quantity FROM ticket_google_order order by order_number";
            }
            rs = statement.executeQuery(sql.toString());

            while (rs.next()) {
                orderList.add(rs.getString("order_number") + "|" + rs.getString("billing_name") + "|" + rs.getString("itemname") + "|" + rs.getString("quantity"));
                logger.debug("To Display : " + rs.getString("order_number"));
                totalTickets = totalTickets + Integer.parseInt(rs.getString("quantity"));
            }
            orderList.add(" |" +  " |" + " |" + totalTickets);
            return orderList;
        } finally {
            rs.close();
            statement.close();
            con.close();
        }
    }

    public ArrayList getShowNames() throws SQLException {

        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        ArrayList orderList = new ArrayList();

        try {
            con = getConnection();
            statement = con.createStatement();
            String sql = "SELECT distinct SUBSTRING_INDEX(itemname, 'for', -1) as ShowName FROM ticket_google_order order by showName asc";
            rs = statement.executeQuery(sql.toString());

            while (rs.next()) {
                orderList.add(rs.getString("ShowName"));
                logger.debug("To Display : " + rs.getString("ShowName"));
            }

            return orderList;
        } finally {
            rs.close();
            statement.close();
            con.close();
        }
    }

    public int decreaseAvailability(int ticketCount, int scheduleId) throws SQLException {

        Connection con = getConnection();
        logger.debug("Updating scheudle ID : " + scheduleId);
        String sql = "update ticket_schedule set tickets_sold = tickets_sold + ? where schedule_id = ?";
        PreparedStatement ps = null;
        int result = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ticketCount);
            ps.setInt(2, scheduleId);
            result = ps.executeUpdate();
            logger.debug("Return Value : " + result);
        } finally {
            ps.close();
            con.close();
        }
        return result;
    }

    public boolean checkAvailability(int scheduleId, int totalTickets) throws SQLException {

        Connection con = getConnection();
        ResultSet rs = null;
        boolean result = false;

        logger.debug("Updating scheudle ID : " + scheduleId);
        String sql = "select seat_capacity, tickets_sold from ticket_schedule where schedule_id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, scheduleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("seat_capacity") >= (rs.getInt("tickets_sold") + totalTickets)) {
                    result = true;
                }
            }
            logger.debug("Return Value : " + result);
            return result;
        } finally {
            ps.close();
            con.close();
        }
    }


    public ArrayList getSchedule() throws SQLException {

        ArrayList scheduleList = new ArrayList();
        Statement stmt = null;

        Connection con = getConnection();

        PreparedStatement ps = con.prepareStatement("update ticket_schedule set active = 0 where show_time < now()");
        ps.execute();

        //Get a Statement object
        stmt = con.createStatement();

        StringBuffer sql = new StringBuffer("SELECT v.venue_id, v.venue_name, v.address1, v.city, v.state, v.zip, m.movie_id, m.movie_name, t.show_time, t.schedule_id, m.notes, m.image_link, m.promotion, ");
        sql.append("t.tickets_sold, t.seat_capacity, t.ig_flag, t.ig_link  ");
        sql.append("FROM ticket_schedule t, ticket_venue v, ticket_movie m ");
        sql.append("where t.venue_id = v.venue_id and t.movie_id = m.movie_id and m.active = 1 and v.active = 1 and t.active = 1 ");
        sql.append("order by v.venue_id, m.movie_id, t.show_time");

        ResultSet rs = stmt.executeQuery(sql.toString());

        while (rs.next()) {
            ScheduleTO schedule = new ScheduleTO();

            schedule.setVenueId(rs.getInt("venue_id"));
            schedule.setVenueName(rs.getString("venue_name"));
            schedule.setMovieId(rs.getInt("movie_id"));
            schedule.setMovieName(rs.getString("movie_name"));
            schedule.setScheduleDateTime(rs.getTimestamp("show_time"));
            schedule.setScheduleId(rs.getInt("schedule_id"));
            schedule.setNotes(rs.getString("notes"));
            schedule.setImageLink(rs.getString("image_link"));
            schedule.setPromotion(rs.getString("promotion"));
            schedule.setAddress1(rs.getString("address1"));
            schedule.setCity(rs.getString("city"));
            schedule.setState(rs.getString("state"));
            schedule.setZip(rs.getString("zip"));
            schedule.setCapacity(rs.getInt("seat_capacity"));
            schedule.setTicketsSold(rs.getInt("tickets_sold"));
            schedule.setIsThirdPartyLink(rs.getString("ig_flag"));
            schedule.setThirdPartyLink(rs.getString("ig_link"));
            scheduleList.add(schedule);
            schedule = null;
        }

        rs.close();
        stmt.close();
        con.close();

        return scheduleList;
    }

    public ArrayList getPrice(String scheduleId) throws SQLException {

        ArrayList priceList = new ArrayList();
        Statement stmt = null;
        StringBuffer sql = new StringBuffer();

        Connection con = getConnection();

        //Get a Statement object
        stmt = con.createStatement();

        sql.append("SELECT m.movie_name, v.venue_name, v.address1, v.city, v.state, v.zip, s.show_time, p.name, p.price, s.validate, s.ig_flag, s.ig_link ");
        sql.append("FROM ticket_price p, ticket_schedule s, ticket_movie m, ticket_venue v ");
        sql.append("where p.movie_id = s.movie_id and p.venue_id = s.venue_id and v.venue_id = s.venue_id and m.movie_id = s.movie_id and s.schedule_id = p.schedule_id and s.schedule_id = ");
        sql.append(scheduleId);

        ResultSet rs = stmt.executeQuery(sql.toString());

        while (rs.next()) {
            PriceTO price = new PriceTO();

            price.setName(rs.getString("name"));
            price.setPrice(rs.getFloat("price"));
            price.setMovie(rs.getString("movie_name"));
            price.setVenue(rs.getString("venue_name"));
            price.setAddress1(rs.getString("address1"));
            price.setCity(rs.getString("city"));
            price.setState(rs.getString("state"));
            price.setZip(rs.getString("zip"));
            price.setScheduleDateTime(rs.getTimestamp("show_time"));
            price.setScheduleId(Integer.parseInt(scheduleId));
            price.setValidateRegister(rs.getString("validate"));
            priceList.add(price);
            price = null;
        }

        rs.close();
        stmt.close();
        con.close();

        return priceList;
    }

    private static Connection getConnection() throws SQLException {

        Connection con = null;
        //Register the JDBC driver for MySQL.
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam2_phpbb1", "imayam2_aasi", "aasi");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam77_phpbb1","imayam77_phpbb1", "");
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        }
        return con;
    }


}
