package pj;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bookControl")
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    BookDAO dao = new BookDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            List<Book> books = dao.getAll();
            request.setAttribute("books", books);

            request.getRequestDispatcher("/bookinfo.jsp").forward(request, response);

        } else if (action.equals("insert")) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            int price = Integer.parseInt(request.getParameter("price"));

            Book b = new Book();
            b.setTitle(title);
            b.setAuthor(author);
            b.setPrice(price);

            dao.insert(b);

            response.sendRedirect(request.getContextPath() + "/bookControl?action=list");

        } 
        else if (action.equals("delete")) {  
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteBook(id);   // DAO의 deleteBook 호출
            response.sendRedirect(request.getContextPath() + "/bookControl?action=list");
            return;
        }
    }
}
