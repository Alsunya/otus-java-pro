package web_server;

import javax.servlet.annotation.WebServlet;

@WebServlet("/subtract")
public class SubtractServlet extends CalcServlet {
    @Override
    protected int operation(int a, int b) {
        return a - b;
    }
}
