@WebServlet("/simple-status")
public class SimpleStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.getWriter().write(
            "API Rate Limiter & Quota Manager is running"
        );
    }
}
