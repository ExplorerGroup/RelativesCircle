package com.android.relativescircle.commom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp封装类
 */
public class OkHttpClientManager {
	private static OkHttpClientManager _httpClientManager = null;
	private OkHttpClient _okHttpClient = null;
	private Handler _handler = null;
	private int _cacheSize = 10 * 1024 * 1024;
	private Context _context;
	private Gson _gson;
//	private CacheControl _cacheControl = null;

	private OkHttpClientManager(Context context) {
		_context = context;
		_okHttpClient = new OkHttpClient().newBuilder()
				.connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
				.build();
		_handler = new Handler(Looper.getMainLooper());
	}


	public static OkHttpClientManager getHttpInstance(Context context) {
		if (_httpClientManager == null) {
			synchronized (OkHttpClientManager.class) {
				if (_httpClientManager == null) {
					_httpClientManager = new OkHttpClientManager(context);
				}
			}
		}
		return _httpClientManager;
	}

	// ************ 对外公开的方法 ***************

	/**
	 * 异步的POST请求
	 *
	 * @param url      —— 接口地址
	 * @param callback —— 接口回调
	 * @param params   —— 接口参数
	 */
	public static void postAsyn(Context context, String url, final ResultCallback callback, Param... params) {
		getHttpInstance(context).postAsynServer(url, callback, params);
	}

	/**
	 * 同步的POST请求
	 *
	 * @param url    —— 接口地址
	 * @param params —— 接口参数
	 * @return —— 接口返回
	 * @throws IOException
	 */
	public static String postSync(Context context, String url, Param... params) throws IOException {
		return getHttpInstance(context).postSyncServer(url, params);
	}

	/**
	 * 同步的GET请求
	 *
	 * @param url —— 接口地址
	 * @return —— 接口返回
	 * @throws IOException
	 */
	public static String getSync(Context context, String url) throws IOException {
		return getHttpInstance(context).getSyncServer(url);
	}


	// ************ 内部实现方法 ***************
	private void postAsynServer(String url, final ResultCallback callback, Param... params) {
		Request request = buildPostRequest(url, params);
		deliveryResult(callback, request);
	}

	private String postSyncServer(String url, Param... params) throws IOException {
		Request request = buildPostRequest(url, params);
		Response response = _okHttpClient.newCall(request).execute();
		if (!response.isSuccessful()) {
			int code = response.code();
		}
//		Log.e("网络请求", "post请求结果：" + response.isSuccessful() + " | 缓存数据：" + response.cacheResponse() + " | 网络数据：" + response.networkResponse());
		return response.body().string();
	}

	private String getSyncServer(String url) throws IOException {
		final Request request = new Request.Builder()
				.url(url)
				.build();
		Call call = _okHttpClient.newCall(request);
		Response execute = call.execute();
//		Log.e("网络请求", "get请求结果：" + execute.isSuccessful() + " | 缓存数据：" + execute.cacheResponse() + " | 网络数据：" + execute.networkResponse());
		return execute.body().string();
	}


	private Request buildPostRequest(String url, Param[] params) {
		if (params == null) {
			params = new Param[0];
		}
		FormBody.Builder formBody = new FormBody.Builder();
		for (Param param : params) {
			formBody.add(param.key, param.value);
		}
		RequestBody requestBody = formBody.build();
		return new Request.Builder()/*.cacheControl(_cacheControl)*/.url(url).post(requestBody).build();
	}

	private void deliveryResult(final ResultCallback callback, Request request) {
		_okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {

			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				try {
					final String string = response.body().string();
					if (callback.mType == String.class) {
						sendSuccessResultCallback(string, callback);
					} else {
						Object o = _gson.fromJson(string, callback.mType);
						sendSuccessResultCallback(o, callback);
					}
				} catch (IOException e) {
					sendFailedStringCallback(response.request(), e, callback);
				} catch (com.google.gson.JsonParseException e) {
					sendFailedStringCallback(response.request(), e, callback);
				}
			}
		});
	}

	private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
		_handler.post(new Runnable() {
			@Override
			public void run() {
				if (callback != null)
					callback.onError(request, e);
			}
		});
	}

	private void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
		_handler.post(new Runnable() {
			@Override
			public void run() {
				if (callback != null) {
					callback.onResponse(object);
				}
			}
		});
	}

	/**
	 * 接口回调
	 * 接口返回：onError & onResponse
	 * request中可以取出接口返回值
	 *
	 * @param <T> —— 泛型参数
	 */
	public static abstract class ResultCallback<T> {
		Type mType;

		public ResultCallback() {
			mType = getSuperclassTypeParameter(getClass());
		}

		static Type getSuperclassTypeParameter(Class<?> subclass) {
			Type superclass = subclass.getGenericSuperclass();
			if (superclass instanceof Class) {
				throw new RuntimeException("Missing type parameter.");
			}
			ParameterizedType parameterized = (ParameterizedType) superclass;
			return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
		}

		public abstract void onError(Request request, Exception e);

		public abstract void onResponse(T response);
	}

	/**
	 * 接口参数类
	 */
	public static class Param {
		String key;
		String value;

		public Param() {
		}

		public Param(String key, String value) {
			this.key = key;
			this.value = value;
		}

	}
}
