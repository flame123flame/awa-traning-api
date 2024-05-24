package awa.training.api.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpClientUtils {

	public static String doPost(String url, Object bodyValue, String content) {

		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		String resToSerivce = "";
		try {
			json = mapper.writeValueAsString(bodyValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.get(content + "; charset=utf-8");
		RequestBody body = RequestBody.create(mediaType, json);
		Response response = null;
		Request request = new Request.Builder().url(url).method("POST", body).addHeader("Content-Type", content)
				.build();
		try {
			Call call = client.newCall(request);
			response = call.execute();

			resToSerivce = response.body().string();
			response.close();
//			System.out.println(resToSerivce);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return resToSerivce;
	}

	public static String doGet(String url)  {
		OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(210, TimeUnit.MINUTES).build();
		client.connectTimeoutMillis();
		Request request = new Request.Builder().url(url).build();
		Response response  = null;
		String resToSerivce = "";
		try {
			Call call = client.newCall(request);
			response = call.execute();

			resToSerivce = response.body().string();
			response.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return resToSerivce;
	}


}
