package web_server;

import javax.servlet.annotation.WebServlet;

@WebServlet("/div")
public class DivServlet extends CalcServlet {
    @Override
    protected int operation(int a, int b) {
        return a / b;
    }
}
