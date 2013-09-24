package com.imayam.smartlinks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class DataAccess {

    static Logger logger = Logger.getLogger(DataAccess.class);

    public static void saveLink(String linkId, String userName) throws Exception {
        Connection con = getConnection();
        Statement stmt = null;

        UserTO userTO = getUserInfo(con, userName);
        if (userTO == null) {
            con.close();
            throw new Exception();
        } else {

            StringBuffer sql = new StringBuffer();
            sql.append("insert into smart_mylinks(url_id, user_id ) ");
            sql.append("values('");
            sql.append(linkId);
            sql.append("', '");
            sql.append(userTO.getUserId());
            sql.append("')");
            logger.debug(sql);

            stmt = con.createStatement();
            boolean result = stmt.execute(sql.toString());

        }
        stmt.close();
        con.close();

    }

    public static ArrayList topTenLinks() throws Exception {

        ArrayList userList = new ArrayList();
        Statement stmt = null;

        Connection con = getConnection();

        //Get a Statement object
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT url_id, url_description, url_link, url_name, username FROM smart_url, phpbb_users WHERE smart_url.user_id = phpbb_users.user_id AND private =0 ORDER BY create_date DESC LIMIT 0 , 10");

        logger.debug("Display all results:");
        while (rs.next()) {
            LinkTO user = new LinkTO();
            String link = rs.getString("url_link");
            String name = rs.getString("url_name");
            String username = rs.getString("username");
            logger.debug("\turl_link " + link + "\turl_name = " + name);
            user.setLinkURL(link);
            user.setLinkName(name);
            user.setUser(username);
            user.setUrlId(rs.getString("url_id"));
            user.setDescription(rs.getString("url_description"));
            userList.add(user);
            user = null;
        } //end while loop

        rs.close();
        stmt.close();
        con.close();

        return userList;
    }

    public static String getPassword(String username, String password) throws
            Exception {

        Connection con = getConnection();

        String validatePassword = null;

        //Get a Statement object
        Statement stmt = con.createStatement();
        String sql = "SELECT user_password, md5('" + password +
                     "' ) as password FROM phpbb_users WHERE username = '" +
                     username + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            validatePassword = rs.getString("user_password");
            if (validatePassword.equals(rs.getString("password"))) {

            } else {
                validatePassword = null;
            }
            logger.debug("user_password : " + rs.getString("user_password"));
            logger.debug("password      : " + rs.getString("password"));
        }

        return validatePassword;
    }

    public static ArrayList getMyLinks(String username) throws Exception {

        Connection con = getConnection();

        //Get a Statement object
        Statement stmt = con.createStatement();
        String sql = "select distinct smart_url.url_id, url_link, url_name, url_description from smart_url, phpbb_users, smart_mylinks where smart_url.user_id = phpbb_users.user_id and phpbb_users.username = '" + username + "' or (smart_mylinks.user_id = phpbb_users.user_id and smart_mylinks.url_id = smart_url.url_id)";

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList userList = new ArrayList();

        while (rs.next()) {
            LinkTO user = new LinkTO();
            String link = rs.getString("url_link");
            String name = rs.getString("url_name");
            String description = rs.getString("url_description");
            user.setUrlId(rs.getString("url_id"));
            user.setLinkURL(link);
            user.setLinkName(name);
            user.setDescription(description);
            userList.add(user);
            user = null;
        }

        return userList;
    }

    public static void createLink(String urlLink, String urlName,
                                  String urlDesc, String urlPrivate,
                                  String userName) throws Exception {

        Connection con = getConnection();
        Statement stmt = null;

        UserTO userTO = getUserInfo(con, userName);
        if (userTO == null) {
            con.close();
            throw new Exception();
        } else {

            StringBuffer sql = new StringBuffer();
            sql.append("insert into smart_url(url_link, url_name, url_description, private, user_id )");
            sql.append("values('");
            sql.append(urlLink);
            sql.append("', '");
            sql.append(urlName);
            sql.append("', '");
            sql.append(urlDesc);
            sql.append("','");
            if (urlPrivate == null) {
                sql.append("");
            } else {
                sql.append(urlPrivate);
            }
            sql.append("',");
            sql.append(userTO.getUserId());
            sql.append(")");
            logger.debug(sql);

            stmt = con.createStatement();
            boolean result = stmt.execute(sql.toString());

        }
        stmt.close();
        con.close();
    }

    private static UserTO getUserInfo(Connection con, String userName) throws
            Exception {
        UserTO userTO = null;
        String sql =
                "SELECT user_id, username, user_email FROM phpbb_users WHERE username = '" +
                userName + "'";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            if (userTO == null) {
                userTO = new UserTO();
            }
            userTO.setUserId(res.getString("user_id"));
            userTO.setUsername(res.getString("username"));
            userTO.setUserEmail(res.getString("user_email"));
        }

        res.close();
        stmt.close();
        return userTO;
    }

    public static ArrayList search(String searchString) throws Exception {
        String userId = null;
        Statement stmt = null;

        Connection con = getConnection();
        stmt = con.createStatement();
        String sql = "SELECT url_link, url_ico, url_name, url_description, username, smart_url.user_id, smart_url.url_id FROM smart_url, phpbb_users WHERE (smart_url.user_id = phpbb_users.user_id and smart_url.private = 0) and (url_link LIKE '%" +
                     searchString + "%' or url_name LIKE '%" + searchString +
                     "%' or url_description like '%" + searchString + "%')";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList userList = new ArrayList();

        while (rs.next()) {
            LinkTO user = new LinkTO();
            String link = rs.getString("url_link");
            String name = rs.getString("url_name");
            String description = rs.getString("url_description");
            logger.debug("Search Result : " + link + name);
            user.setUrlId(rs.getString("url_id"));
            user.setLinkURL(link);
            user.setLinkName(name);
            user.setDescription(description);
            userList.add(user);
            user = null;
        }

        return userList;
    }


    public static ArrayList getListToShare(ArrayList selectedList) throws
            Exception {
        Statement stmt = null;

        Connection con = getConnection();
        stmt = con.createStatement();
        StringBuffer sql = new StringBuffer();
        sql.append(
                "SELECT url_link, url_name, url_description FROM smart_url where url_id in (");
        for (int i = 0; i < selectedList.size(); i++) {
            sql.append((String) (selectedList.get(i)));
            sql.append(",");
        }
        sql.append("0)");

        ResultSet rs = stmt.executeQuery(sql.toString());
        ArrayList userList = new ArrayList();

        while (rs.next()) {
            LinkTO user = new LinkTO();
            String link = rs.getString("url_link");
            String name = rs.getString("url_name");
            String description = rs.getString("url_description");
            user.setLinkURL(link);
            user.setLinkName(name);
            user.setDescription(description);
            userList.add(user);
            user = null;
        }

        return userList;

    }

    public static ArrayList viewList(String shareId) throws Exception {
        Statement stmt = null;
        String sql = "SELECT * FROM share_link WHERE share_id = '" + shareId +
                     "'";

        Connection con = getConnection();
        stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        ArrayList viewList = new ArrayList();

        while (rs.next()) {
            viewList.add(rs.getString("url_id"));
        }

        return getListToShare(viewList);

    }

    public static void sendEmail(String userMessage, String email,
                                 ArrayList selectedList, String username) throws
            Exception {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "localhost");
        props.setProperty("mail.user", "");
        props.setProperty("mail.password", "");

        Connection conn = getConnection();
        UserTO userTO = getUserInfo(conn, username);

        //Get key for table
        Calendar rightNow = Calendar.getInstance();
        String shareId = userTO.getUserId() +
                         String.valueOf(rightNow.getTimeInMillis());

        Session mailSession = Session.getDefaultInstance(props, null);
        //mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("SmartLinks from " + username);
        message.setFrom(new InternetAddress("admin@imayam.org",
                                            userTO.getUserEmail()));

        StringTokenizer st = new StringTokenizer(userMessage, "\n");
        StringBuffer addedNewLine = new StringBuffer();
        while (st.hasMoreTokens()) {
            addedNewLine.append(st.nextToken());
            addedNewLine.append("<br />");
        }

        StringBuffer formatMessage = new StringBuffer();
        formatMessage.append("<b>Message : </b><br /><br />" + addedNewLine);
        formatMessage.append(
                "<br /><br /><b>Click below to view : </b><br /><br />");
        formatMessage.append(
                "     <a href='http://www.imayam.org/controller?action=linkshare&shareId=" +
                shareId + "'>Shared Links</a>");

        message.setContent(formatMessage.toString(), "text/html");

        InternetAddress emailAddress = new InternetAddress();
        //StringTokenizer st = new StringTokenizer(email);
        message.addRecipients(Message.RecipientType.TO,
                              emailAddress.parse(email));

        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        transport.connect();
        transport.sendMessage(message,
                              message.getRecipients(Message.RecipientType.TO));
        transport.close();

        for (Iterator it = selectedList.iterator(); it.hasNext(); ) {
            String urlId = (String) it.next();
            //Save shared ID
            StringBuffer sql = new StringBuffer();
            sql.append(
                    "insert into share_link(share_id, user_id, send_email, url_id)");
            sql.append("values('");
            sql.append(shareId);
            sql.append("',");
            sql.append(userTO.getUserId());
            sql.append(", '");
            sql.append(email);
            sql.append("',");
            sql.append(urlId);
            sql.append(")");
            logger.debug(sql);
            Statement stmt = conn.createStatement();
            boolean result = stmt.execute(sql.toString());
            stmt.close();
        }

        conn.close();
    }

    private static Connection getConnection() throws Exception {

        //Register the JDBC driver for MySQL.
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imayam2_phpbb1", "imayam2_aasi",
                "aasi");
        //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imayam77_phpbb1","imayam77_phpbb1", "");

        return con;
    }

}
