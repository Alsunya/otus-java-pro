package web_server;

import javax.servlet.annotation.WebServlet;

@WebServlet("/multiply")
public class MultiplyServlet extends CalcServlet {
    @Override
    protected int operation(int a, int b) {
        return a * b;
    }
}
