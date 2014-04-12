/*
 * Copyright (C) 2013 Jai Romeo <designrifts@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.designrifts.ultimatethemeui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.pk.requestmanager.AppLoadListener;
import com.pk.requestmanager.PkRequestManager;
import com.pk.requestmanager.RequestSettings;
import com.pk.requestmanager.SendRequestListener;

public class IconRequest extends Activity implements OnClickListener, AppLoadListener, SendRequestListener
{
	// Log Tag for debugging
	@SuppressWarnings("unused")
	private static final String LOG_TAG = "Pkg";
	
	// Request Manager & Progress Dialog
	private PkRequestManager mRequestManager;
	private ProgressDialog progressDialog;
	
	// UI Handler
	private Handler mHandler;
	
	// Views
	private Button btnSend;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.holo_blue_dark)));
        getActionBar().setDisplayShowHomeEnabled(true);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Initialize your views (Button)
		initViews();
		
		// Initialize the PkRequestManager
		initRequestManager();
		
		// Initialize your progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMessage("");
		
		// Initialize your UI Handler. This is for modifying your UI from a background thread
		mHandler = new Handler();
	}
	
	/** Called when your activity... starts */
	@Override
	public void onStart()
	{
		super.onStart();
		
		// Set onClick & progress listeners
		setListeners();
	}
	
	/** Called when your activity is no longer visible */
	@Override
	public void onStop()
	{
		super.onStop();
		
		// Remove listeners to prevent weird issues
		removeListeners();
	}
	
	private void initViews()
	{
		btnSend = (Button)findViewById(R.id.button);
	}
	
	private void initRequestManager()
	{
		// Grab a reference to the manager and store it in a variable. This helps make code shorter.
		mRequestManager = PkRequestManager.getInstance(this);
		
		// Uncomment this line if you'd like to see extra information in your logcat
		mRequestManager.setDebugging(true);
		
		// Set your settings!
		mRequestManager.setSettings(new RequestSettings.Builder()
			.addEmailAddress(getString(R.string.request_email_addr)) // Email where the request will be sent to
			.emailSubject(getString(R.string.email_subject)) // Email Subject
			.emailPrecontent(getString(R.string.email_text)) // Text before the main request information
			.saveLocation(Environment.getExternalStorageDirectory().getAbsolutePath() + "/designriftsrequest") // Location to where the .zips and temporary files will be saved
			.appfilterName("appfilter.xml")	// Appfilter file to filter apps from (Must be in your assets folder)
			.createAppfilter(true) // True if you'd like to automatically generate an appfilter.xml for the requested apps
			.createZip(true) // True if you want to receive app icons with the email
			.filterAutomatic(true) // True if you want apps you support in your appfilter.xml to be filtered out from automatic requests
			.byteBuffer(2048) // Buffer size in bytes for writing to memory.
			.build());
	}
	
	private void setListeners()
	{
		mRequestManager.addAppLoadListener(this);
		mRequestManager.addSendRequestListener(this);
		
		btnSend.setOnClickListener(this);
	}
	
	private void removeListeners()
	{
		mRequestManager.removeAppLoadListener(this);
		mRequestManager.removeSendRequestListener(this);
		
		btnSend.setOnClickListener(null);
	}
	
	@Override
	public void onClick(View v)
	{
		// Workaround for an issue with the manager. Uncomment if you're not using listeners.
		// mRequestManager.setActivity(this);
		
		// Send the request! (if not already started)
		mRequestManager.sendAutomaticRequestAsync();
		
		// Show the progress dialog
		progressDialog.show();
	}
	
	@Override
	public void onAppPreload()
	{
		mHandler.post(new Runnable(){
			@Override
			public void run() {
				progressDialog.setTitle("Please wait...");
				progressDialog.setMessage("Preparing to gather app data");
				progressDialog.setIndeterminate(true);
			}
		});
	}
	
	@Override
	public void onAppLoading(final int status, final int progress) 
	{
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				progressDialog.setTitle("Please wait...");
				
				switch(progress) {
					case PkRequestManager.STATUS_LOADING_INSTALLED:
						progressDialog.setMessage("Gathering installed application info");
						break;
					case PkRequestManager.STATUS_LOADING_APPFILTER:
						progressDialog.setMessage("Reading appfilter");
						break;
					case PkRequestManager.STATUS_FILTERING:
						progressDialog.setMessage("Filtering applications");
						break;
				}
				
				progressDialog.setIndeterminate(false);
				progressDialog.setMax(PkRequestManager.MAX_PROGRESS);
				progressDialog.setSecondaryProgress(progress);
			}
		});
	}
	
	@Override
	public void onAppLoaded()
	{
		mHandler.post(new Runnable(){
			@Override
			public void run() {
				progressDialog.setTitle("Please wait...");
				progressDialog.setMessage("Loaded application info");
				progressDialog.setIndeterminate(true);
			}
		});
	}

	@Override
	public void onRequestStart(boolean automatic)
	{
		mHandler.post(new Runnable(){
			@Override
			public void run() {
				progressDialog.setTitle("Please wait...");
				progressDialog.setMessage("Preparing to build request");
				progressDialog.setIndeterminate(true);
			}
		});
	}
	
	@Override
	public void onRequestBuild(boolean automatic, final int progress) 
	{
		mHandler.post(new Runnable(){
			@Override
			public void run() {
				progressDialog.setTitle("Please wait...");
				progressDialog.setMessage("Building icon request");
				progressDialog.setIndeterminate(false);
				progressDialog.setMax(PkRequestManager.MAX_PROGRESS);
				progressDialog.setProgress(progress);
			}
		});
	}
	
	@Override
	public void onRequestFinished(boolean automatic, final boolean intentSuccessful, final Intent intent)
	{
		mHandler.post(new Runnable(){
			@Override
			public void run() {
				// Close progress dialog
				progressDialog.dismiss();
				
				// Start the intent manually if it was not successful
				if(!intentSuccessful) {
					startActivity(Intent.createChooser(intent, getString(R.string.send_email)));
				}
			}
		});
	}
}
