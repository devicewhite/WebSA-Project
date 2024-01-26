// Este arquivo foi escrito em 16/01/2024
// Todos os updates seguem no off
// A versão final será mantida como exclusiva por alguns dias
// E depois será distribuida gratuitamente.

package com.devicewhite;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;

public class WebSA
{
	private Activity currentActivity;
	private CefWebViewClient webViewClient;
	private HashMap<Integer, WebView> webViewMap;

	public WebSA(Activity activity)
	{
		currentActivity = activity;
		webViewClient = new CefWebViewClient();
		webViewMap = new HashMap<>();
		initializeCef();
	}

	private native void initializeCef();

	private native void notifyCefBrowserCreated(int id, int status);

	private void createBrowser(int id, String url, boolean hidden, boolean focused)
	{
		if(!webViewMap.containsKey(id))
		{
			WebView webView = createWebView();
			webViewMap.put(id, webView);
			notifyCefBrowserCreated(id, 1);
		}
		else notifyCefBrowserCreated(id, 0);
	}

	private void deleteBrowser(int id)
	{
		if(webViewMap.containsKey(id))
		{
			WebView webView = webViewMap.get(id);
			webViewMap.remove(id);
			webView.destroy();
		}
	}

	private WebView createWebView()
	{
		WebView webView = new WebView(currentActivity);
		webView.setWebViewClient(webViewClient);
		// incompleto por estar sobre testes 
		return webView;
	}

	private class CefWebViewClient extends WebViewClient
	{
		@Override
		public void onPageFinished(WebView view, String url)
		{
			// implementação do trigger event compatível com Cef
			// o resto do código ainda esta sobre testes
		}
	}
}
