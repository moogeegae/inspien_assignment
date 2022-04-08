package test;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Testapi {
	public static void main(String[] args) throws IOException, ParseException {
		//오늘 날짜
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String toDay = date.format(today);
		Calendar cday = Calendar.getInstance();
		cday.add(Calendar.DATE , +1);
		String sday = new java.text.SimpleDateFormat("yyyyMMdd").format(cday.getTime()); //자정일때는 다음날
		//스캐너로 개발한 rest api url 입력하여 확인
		Scanner in = new Scanner(System.in);
		System.out.print("area: ");
		int area = in.nextInt();
		System.out.print("time: ");
		int fctime = in.nextInt();
		//-------------------------------------------------------------------//
		URL url = new URL("http://localhost:8080/weather/"+area+"/"+fctime);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		InputStream is = conn.getInputStream();
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(is));
		} else {
			rd = new BufferedReader(new InputStreamReader(is));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line).append("\n");
		}
		rd.close();
		conn.disconnect();
		String result= sb.toString();
		System.out.println(url);
		System.out.println(result);
	}
}

