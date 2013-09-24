package com.imayam.smartlinks;

import java.io.IOException;
import com.imayam.music.*;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;
public class TestServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(TestServlet.class);
    int id = 0;
    public TestServlet() {
    	// com.imayam.music.DataAccess.updatemonthCount();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList newList = null;
        TestTO test = new TestTO();
        Date now = null;
        HttpSession session = request.getSession();

        try {
            newList = (ArrayList) session.getAttribute("result");
            if (newList == null) {
                newList = new ArrayList();
            } else {
                id = newList.size();
                now = new Date();
                TestTO checkTime = ((TestTO) newList.get(0));
                if (checkTime != null && checkTime.getExamStart() != null) {
                    long timeDiff = ((checkTime.getDuration() * 60) - ((now.getTime() - checkTime.getExamStart().getTime()) / 1000));
                    long diff = (timeDiff / 60);
                    checkTime.setTimeLeft(diff + ":" + (timeDiff - (diff * 60)));
                }
            }
            String action = request.getParameter("action");
            if ("start".equalsIgnoreCase(action)) {
                TestTO newTest = (TestTO) newList.get(id - 1);
                //test.setId(id);
                newTest.setStartTime(now);
                //newList.add(id, test);
                session.setAttribute("result", newList);
            } else if ("next".equalsIgnoreCase(action)) {
                TestTO newTest = (TestTO) newList.get(id - 1);
                newTest.setEndTime(now);
                //newList.add(id, newTest);
                newTest.setOption(request.getParameter("choice"));
                test.setId(id + 1);
                test.setStartTime(now);
                newList.add(id, test);
                session.setAttribute("result", newList);
            } else if ("reset".equalsIgnoreCase(action)) {
                session.removeAttribute("result");
            } else if ("details".equalsIgnoreCase(action)) {
                test.setDuration(Integer.parseInt(request.getParameter("duration")));
                test.setExamDate(request.getParameter("examDate"));
                test.setQuestionCount(Integer.parseInt(request.getParameter("questionCount")));
                test.setReference(request.getParameter("reference"));
                test.setId(1);
                test.setStartTime(new Date());
                test.setExamStart(new Date());
                newList.add(0, test);
                session.setAttribute("result", newList);

            }
            request.getRequestDispatcher("gre.jsp").forward(request, response);
        } catch (Exception ex) {
            logger.error("Error :" + ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
    }


}
