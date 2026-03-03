package io.github.camaya045.cdiinjections.web;

import io.github.camaya045.cdiinjections.cdi.AppStats;
import io.github.camaya045.cdiinjections.cdi.CartBean;
import io.github.camaya045.cdiinjections.cdi.Requestinfo;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cdi/cart")
public class cdiCartServlet extends HttpServlet {

    @Inject CartBean cart; // Session-scoped state
    @Inject Requestinfo reqInfo; //Request-scoped state
    @Inject AppStats stats; //Application-scoped state

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if("add".equalsIgnoreCase(action)) cart.addItem();
        if("remove".equalsIgnoreCase(action)) cart.removeItem();

        int hitNumber = stats.hit();

        resp.getWriter().println("CDI Demo: Injection + Scopes");
        resp.getWriter().println("-------------------------");
        resp.getWriter().println("requestId =" + reqInfo.getRequestId());
        resp.getWriter().println("requestStarted = " + reqInfo.getStartedAt());
        resp.getWriter().println("cart.orderList = " + cart.getOrderList());
        resp.getWriter().println("app.totalHits = " + hitNumber);
        resp.getWriter().println();
        resp.getWriter().println("Try: /cdi/cart?action=add and /cdi/cart?action=remove");
    }
}
