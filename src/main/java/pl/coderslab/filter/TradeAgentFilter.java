package pl.coderslab.filter;

import lombok.extern.slf4j.Slf4j;
import pl.coderslab.entity.UserGroup;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/ta/*", "/ta"})
@Slf4j
public class TradeAgentFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession();
        Object sessionUserGroup = session.getAttribute("userGroup");

        if (sessionUserGroup == null) {
            res.sendRedirect("/login");
        } else {
            UserGroup userGroup = (UserGroup) sessionUserGroup;
            if ((userGroup == UserGroup.TRADE_AGENT) || (userGroup == UserGroup.SUPERUSER)) {
                log.trace("passing Trade Agent filter");
                chain.doFilter(req, res);
            }
        }
    }
}
