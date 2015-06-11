package com.sina.stock;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;

import com.sina.stock.SinaStockInfo.ParseStockInfoException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SinaStockSampleActivity extends Activity implements
		OnClickListener {

	private Button btn;
	private TextView textView;
	private ImageView imageView;

	private SinaStockClient mClient;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);

		textView = (TextView) findViewById(R.id.txt);
		imageView = (ImageView) findViewById(R.id.image);

		initClient();
	}

	private void initClient() {
		mClient = SinaStockClient.getInstance();
	}

	@Override
	public void onClick(View v) {
		try {
			Toast.makeText(this, "cli", 0).show();
			Bitmap bm = mClient.getStockImage("sh600588",
					SinaStockClient.IMAGE_TYPE_MONTHLY);
			imageView.setImageBitmap(bm);
			List<SinaStockInfo> stockInfo = mClient
					.getStockInfo(new String[] { "sh600588" });
			if (stockInfo != null) {
				textView.setText(stockInfo.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(v.getId()+"");
	}
}