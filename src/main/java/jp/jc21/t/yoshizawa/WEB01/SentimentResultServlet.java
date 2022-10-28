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
		setResult(string,request,response);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String string = "お布団から出たくない季節だね";
		setResult(string,request,response);
		
		
		
	}
	
	protected void setResult(String s,HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("string",s);
			Sentiment_doc result = Sentiment.getSentiment(s);
			String message = result.documents[0].sentiment;
			String  negative = String.valueOf(result.documents[0].confidenceScores.negative);
			String neutral = String.valueOf(result.documents[0].confidenceScores.neutral);
			String positive = String.valueOf(result.documents[0].confidenceScores.positive);
			
			
			request.setAttribute("sentiment",message);
			request.setAttribute("negative", negative);
			request.setAttribute("neutral",neutral);
			request.setAttribute("positive",positive);
			request.getRequestDispatcher("/WEB-INF/jsp/sentimentResult.jsp").forward(request,response);
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch (ServletException e) {
				e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		} 
	}

}
