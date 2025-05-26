import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/testing")
public class ChatBotServlet extends HttpServlet {

    ObjectMapper mapper;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Deployment directory: " + getServletContext().getRealPath("/"));
        ObjectMapper mapper = new ObjectMapper();
        READFILE rf = READFILE.getInstance();
        rf.ReadTheFiles();
        bot robot = bot.getInstance();

        try {
            // Read JSON input from request
            JsonNode jsonNode = mapper.readTree(req.getReader());
            String message = jsonNode.get("message").asText();
            System.out.println(message);
            // Get reply from bot
            String replyText = robot.Reply(message);

            // Prepare JSON response with single field "reply"
            Map<String, String> map = new HashMap<>();
            map.put("reply", replyText);
            String jsonResponse = mapper.writeValueAsString(map);

            // Set response headers and send JSON
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(jsonResponse);

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("{\"error\":\"Invalid JSON input\"}");
        }
    }
}
