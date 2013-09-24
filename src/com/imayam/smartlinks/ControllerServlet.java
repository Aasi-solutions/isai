package com.imayam.smartlinks;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class ControllerServlet extends HttpServlet {

    static Logger logger = Logger.getLogger(ControllerServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("message", "");

            DataAccess dataObject = new DataAccess();
            ArrayList topTen = dataObject.topTenLinks();
            session.setAttribute("topTenList", topTen);

            String action = request.getParameter("action");
            logger.debug(action);
            if ("login".equalsIgnoreCase(action)) {
                login(request, response);
            } else if ("create".equalsIgnoreCase(action)) {
                createLink(request, response);
            } else if ("logout".equalsIgnoreCase(action)) {
                session.invalidate();
                topTenLinks(request, response);
            } else if ("Search SmartLinks".equalsIgnoreCase(action)) {
                search(request, response);
            } else if ("mylinks".equalsIgnoreCase(action)) {
                mylinks(request, response);
            } else if ("select".equalsIgnoreCase(action)) {
                selectLink(request, response);
            } else if ("share".equalsIgnoreCase(action)) {
                linksToShare(request, response);
            } else if ("Send".equalsIgnoreCase(action)) {
                sendEmail(request, response);
            } else if ("linkshare".equalsIgnoreCase(action)) {
                viewLinks(request, response);
            } else if ("loginpage".equalsIgnoreCase(action)) {
                forward(request, response, "login.jsp");
            } else if ("save".equalsIgnoreCase(action)) {
                saveLink(request, response);
            } else {
                topTenLinks(request, response);
            }

        } catch (Exception e) {
            logger.error("Error :" + e);
            //request.setAttribute("message", "Unable to process your request. Error : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public void saveLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataAccess dataObject = new DataAccess();
        String forwardPage = "mainlinks.jsp";
        HttpSession session = request.getSession();
        String message = null;

        String username = validUser(request);
        if (username == null) {
            message = "Please login to save link.";
            forwardPage = "login.jsp";
        } else {
            message = "Link saved to My SmartLinks";
            dataObject.saveLink(request.getParameter("id"), username);
        }
        session.setAttribute("message", message);
        request.getRequestDispatcher(forwardPage).forward(request, response);

    }

    public void forward(HttpServletRequest request, HttpServletResponse response, String forwardPage) throws Exception {
        HttpSession session = request.getSession();
        session.setAttribute("message", "");
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public void selectLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String urlId = request.getParameter("url_id");
        logger.debug("url id : " + urlId);
        String forwardPage = "mainlinks.jsp";
        String message = "Link added to share list. You may select more or click 'Share SmartLink'";
        HttpSession session = request.getSession();
        ArrayList selectedList = null;

        selectedList = (ArrayList) session.getAttribute("selectedList");
        if (selectedList == null) {
            selectedList = new ArrayList();
        }
        selectedList.add(urlId);

        session.setAttribute("message", message);
        session.setAttribute("selectedList", selectedList);
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public void linksToShare(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardPage = "shareLink.jsp";
        HttpSession session = request.getSession();
        StringBuffer message = new StringBuffer();

        String username = validUser(request);
        if (username == null) {
            session.setAttribute("message", "Please login to share your links.");
            forwardPage = "login.jsp";
        } else {
            forwardPage = "shareLink.jsp";
            message.append("Hi, \n\n");
            message.append("I recently found Imayam SmartLink, an online bookmark service. I found it useful. I am sharing with you few interesting links (url) through SmartLinks.\n");
            message.append("\nYou can check the links I have shared for free. Also, you may register with SmartLink and share your bookmarks for free.\n\n");
            message.append("Regards,\n");

            session.setAttribute("emailmessage", message);
        }

//        ArrayList selectedList = (ArrayList) session.getAttribute("selectedList");
//        DataAccess dataObject = new DataAccess();

//        ArrayList listToShare = dataObject.getListToShare(selectedList);

        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String forwardPage = "mainlinks.jsp";
        String message = "Email send successfully";
        DataAccess dataObject = new DataAccess();
        HttpSession session = request.getSession();

        try {

            String userMessage = request.getParameter("usermessage");
            String email = request.getParameter("email");
            ArrayList selectedList = (ArrayList) session.getAttribute("selectedList");
            String username = (String) session.getAttribute("username");

            dataObject.sendEmail(userMessage, email, selectedList, username);

        } catch (AddressException ae) {
            message = "Invalid email address. Email not send.";
            forwardPage = "shareLink.jsp";
        }
        session.setAttribute("message", message);
        request.getRequestDispatcher(forwardPage).forward(request, response);

    }

    public void mylinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardPage = null;
        String username = null;
        String message = null;
        HttpSession session = request.getSession();

        username = validUser(request);
        if (username == null) {
            message = "Please login to view your smartlinks";
            forwardPage = "login.jsp";
        } else {
            forwardPage = "mainlinks.jsp";
        }
        session.setAttribute("message", message);
        request.getRequestDispatcher(forwardPage).forward(request, response);

    }

    public void topTenLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataAccess dataObject = new DataAccess();

        ArrayList topTen = dataObject.topTenLinks();

        HttpSession session = request.getSession();
        session.setAttribute("topTenList", topTen);

        request.getRequestDispatcher("home.jsp").forward(request, response);

        dataObject = null;

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        String errorMessage = null;
        String forwardPage = null;

        DataAccess dataObject = new DataAccess();

        ArrayList topTen = dataObject.topTenLinks();
        session.setAttribute("topTenList", topTen);

        String correctPassword = dataObject.getPassword(username, password);
        logger.debug("correctPassword : " + correctPassword);
        if (correctPassword == null) {
            errorMessage = "Invalid Login/Password";
            forwardPage = "login.jsp";
        } else {
            ArrayList mylist = dataObject.getMyLinks(username);
            session.setAttribute("mylist", mylist);
            session.setAttribute("username", username);
            forwardPage = "mainlinks.jsp";
        }
        session.setAttribute("message", errorMessage);
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public void createLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardPage = "mainlinks.jsp";
        String message = "Link Created";
        String username = null;
        DataAccess dataObject = new DataAccess();
        HttpSession session = request.getSession();

        username = validUser(request);
        if (username == null) {
            message = "Please login before creating a SmartLink";
            forwardPage = "login.jsp";

        } else {
            String urlLink = request.getParameter("urllink");
            String urlName = request.getParameter("urlname");
            String urlDesc = request.getParameter("urldesc");
            String urlPrivate = request.getParameter("private");
            if (urlLink == null || urlLink.trim().length() == 0 || urlName == null ||
                urlName.trim().length() == 0) {
                message = "Please enter the required fileds";
                forwardPage = "createLink.jsp";
            } else {
                dataObject.createLink(urlLink, urlName, urlDesc, urlPrivate,
                                      username);
                ArrayList topTen = dataObject.topTenLinks();
                session.setAttribute("topTenList", topTen);
                ArrayList mylist = dataObject.getMyLinks(username);
                session.setAttribute("mylist", mylist);
                forwardPage = "mainlinks.jsp";
            }
        }

        session.setAttribute("message", message);
        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public void search(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardPage = "search.jsp";
        DataAccess dataObject = new DataAccess();
        HttpSession session = request.getSession();

        String searchString = request.getParameter("searchString");

        ArrayList searchResult = dataObject.search(searchString);
        session.setAttribute("searchResult", searchResult);

        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public void viewLinks(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardPage = "search.jsp";
        DataAccess dataObject = new DataAccess();
        HttpSession session = request.getSession();

        String shareId = request.getParameter("shareId");

        ArrayList viewList = dataObject.viewList(shareId);
        session.setAttribute("searchResult", viewList);

        request.getRequestDispatcher(forwardPage).forward(request, response);
    }

    public String validUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        logger.debug("Username : " + user);
        return user;
    }

}
