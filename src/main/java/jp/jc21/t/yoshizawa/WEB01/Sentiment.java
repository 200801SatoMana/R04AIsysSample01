package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Sentiment_doc  message = getSentiment("Stepover Toehold With Facelock");
		if (message != null) {
			double negative = message.documents[0].confidenceScores.negative;
			double neutral = message.documents[0].confidenceScores.neutral;
			double positive = message.documents[0].confidenceScores.positive;
			System.out.println("negative:"+negative);
			System.out.println("neutral:"+neutral);
			System.out.println("positive:"+positive);
		}
	}


		
		
	
	static Sentiment_doc getSentiment(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();
		
		Language message = Json05.getLanguage(s);
		String language = message.documents[0].detectedLanguage.iso6391Name;
		

		String url = "https://r04jk3a13-text.cognitiveservices.azure.com/" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "f3619026ac864f56b96aa3a24576e3ec");
		

		Docs_s doc = new Docs_s();
		doc.id = "1";
		doc.language=language;
		doc.text = s;

		Source_s src = new Source_s();
		src.documents = new Docs_s[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		//InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, /*proxy,*/ map, jsonData);
		Sentiment_doc sentiment = null;
		if (reader != null) {
			sentiment = gson.fromJson(reader, Sentiment_doc.class);
			reader.close();
		}
		return sentiment;
	}

}

class Sentiment_doc {
	Documents_s[] documents;
	String[] errors;
	String modelVersion;
}

class Documents_s{
	Sentiment_p confidenceScores;
	String id;
	Sentence[] sentences;
	String sentiment;
	String[] warning;
}
class Sentiment_p{
	double negative;
	double neutral;
	double positive;
}
class Sentence{
	Targets[] targets;
	Sentiment_p confidenceScores;
	int length;
	int offset;
	Assessments[] assessments;
	String sentiment;
	String text;
}
class Assessments{
	Sentiment_p confidenceScores;
	boolean isNegated;
	int length;
	int offset;
	String sentiment;
	String great;
}
class Targets{
	Sentiment_p confidenceScores;
	int length;
	int offset;
	Relations[] relations;
	String sentiment;
	String text;
}
class Relations{
	String ref;
	String relationType;
}

class Source_s {
	Docs_s[] documents;
}

class Docs_s{
	String id;
	String language;
	String text;
	
}
