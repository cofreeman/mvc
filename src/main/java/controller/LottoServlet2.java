package controller;

import model.domain.TimeVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

@WebServlet("/lotto1")
public class LottoServlet1 extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringNumber = req.getParameter("number");
        int randNum;
        try {
            int number = Integer.parseInt(stringNumber);
            if (number < 1 || number > 6) {
                req.getRequestDispatcher("/lottoForm.html");
                return;
            } else {
                LocalTime now = LocalTime.now();
                int hour = now.getHour();
                int minute = now.getMinute();
                randNum = (int) (Math.random() * 6 + 1);
                req.setAttribute("time", new TimeVO(hour, minute));
                System.out.printf("전달된 값 : %d 추출된 값 : %d", number, randNum);
                if (randNum == number) {
                    req.getRequestDispatcher("/jspexam/success.jsp").forward(req,resp);
                } else {
                    req.getRequestDispatcher("/jspexam/fail.jsp").forward(req,resp);
                }
            }
        } catch (NumberFormatException e) {
            req.getRequestDispatcher("/lottoForm.html");
        }
    }
}
