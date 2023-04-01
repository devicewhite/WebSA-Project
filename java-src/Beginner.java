package com.websa_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

public class Beginner {
	public static final int OVERLAY_PERMISSION_REQUEST_CODE = 123;
	public Activity activity = null;

	public Beginner(Activity activity) {
		this.activity = activity;
		checkSdkVersion();
	}
	
	private void checkSdkVersion() {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			checkOverlayPermission();
		} else {
			shutDownApp();
		}
	}

	public void checkOverlayPermission() {
		if(Settings.canDrawOverlays(activity)) {
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					startWebSA();
				}
			}, 1500);
		} else {
			requestOverlayPermission();
		}
	}

	private void requestOverlayPermission() {
		Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
		activity.startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
	}
	
	private void startWebSA() {
		new WebSA(activity);
	}

	private void shutDownApp() {
		activity.finishAffinity();
		System.exit(0);
	}
}

/*
 * Copyright (C) 2023 DeviceBlack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
