package com.websa_project;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class Beginner {
	private static final int OVERLAY_PERMISSION_REQUEST_CODE = 123;

	public Beginner(Activity activity) {
		checkSdkVersion(activity);
	}
	
	private void checkSdkVersion(Activity activity) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			checkOverlayPermission(ativity);
		} else {
			shutDownApp(activity);
		}
	}

	public void checkOverlayPermission(Activity Activity) {
		if(Settings.canDrawOverlays(activity)) {
			requestOverlayPermission(ativity);
		} else {
			startWebSA(ativity);
		}
	}

	private void requestOverlayPermission(Activity Activity) {
		Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName()));
		activity.startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
	}
	
	private void startWebSA(Activity activity) {
		new WebSA(activity);
	}

	private void shutDownApp(Activity activity) {
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
