package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SentimentResultServlet
 */
@WebServlet("/SentimentResult")
public class SentimentResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SentimentResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String string = request.getParameter("sentiment_string");
		request.setAttribute("string",string);
		
		try {
			Sentiment_doc result = Sentiment.getSentiment(string);
			String message = result.documents[0].sentiment;
			request.setAttribute("sentiment",message);
			request.getRequestDispatcher("/WEB-INF/jsp/sentimentResult.jsp").forward(request,response);
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
