package space.harbour.hw12.servlets;

import space.harbour.day11.DBService;
import space.harbour.day11.dataset.AssessmentDataSet;
import space.harbour.day11.dataset.UserDataSet;
import space.harbour.hw12.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddAssessmentServlet extends HttpServlet {
    private DBService service;

    public AddAssessmentServlet(DBService service) {
        this.service = service;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("status", "Ready for new assessment!");
        response.getWriter().println(PageGenerator.instance().getPage("add_assessment.html", pageVariables));
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String assessmentGrade = request.getParameter("assessmentGrade");
        UserDataSet newUser = new UserDataSet(userName + "@mail.com", userName);
        AssessmentDataSet assessment= new AssessmentDataSet(Integer.parseInt(assessmentGrade));
        newUser.addAssessment(assessment);
        service.addUser(newUser);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, Object> pageVariables = new HashMap<>();
        if (userName.isEmpty() || assessmentGrade.isEmpty()) {
            pageVariables.put("status", "Assessment was not added as name or assessment grade is empty");
        } else {
            pageVariables.put("status", "Ready for new assessment!");
        }
        response.getWriter().println(PageGenerator.instance().getPage("add_assessment.html", pageVariables));
    }
}
