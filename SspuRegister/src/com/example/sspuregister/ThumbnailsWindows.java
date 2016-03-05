package com.example.sspuregister;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class ThumbnailsWindows extends LinearLayout {

	private static final String TAG = "ThumbnailsWindows";
	private Context mContext;
	private static ArrayList<String> paths = new ArrayList<String>();
	
	private ImageView imageView;
	
	public ThumbnailsWindows(Context context) {
		super(context);
		mContext = context;
		setupViews();
	}

	public ThumbnailsWindows(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		setupViews();
	}

	public void setupViews() {
		
		/**
		 * 显示大图时需要使用，当然可以直接在此类中定义！这样还好控制一些~到时候再改吧，赶这过年呢
		 */
		final LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
		View v = mLayoutInflater.inflate(R.layout.original_photo, null);
		imageView =  (ImageView) v.findViewById(R.id.original);
		
		Map<String,Bitmap> maps = new TreeMap<String, Bitmap>();
		try {
			maps = buildThum();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Iterator<String> it = maps.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String path = (String) it.next();  
			Bitmap bm = maps.get(path);  
			
			ImageButton image = new ImageButton(mContext); 
			image.setImageBitmap(bm);
			image.setId(i++);
			addView(image);
			image.setOnTouchListener(listener);
		}
		
		addView(v);
	}
	
	/**
	 * 定义按钮控件的Touch事件
	 */
	OnTouchListener listener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			/**
			 * 控件按下的时候显示当前略缩图的大图
			 */
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				String path = paths.get(v.getId());
				InputStream inputStream = null;
				try {
					inputStream = new FileInputStream(path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				imageView.setImageBitmap(bitmap);
			}
			return false;
		}
	};
	
	/**
	 * 获取图片地址列表
	 * @param file
	 * @return
	 */
	private static ArrayList<String> imagePath(File file) {
		ArrayList<String> list = new ArrayList<String>();

		File[] files = file.listFiles();
		for (File f : files) {
			list.add(f.getAbsolutePath());
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 读取sdcard文件夹中的图片，并生成略缩图
	 * @return
	 * @throws FileNotFoundException
	 */
	private Map<String,Bitmap> buildThum() throws FileNotFoundException {
		String code=showdetail.stucode;
		File baseFile = new File("/mnt/sdcard/sspuregister/"+code+"/");
		// 使用TreeMap，排序问题就不需要纠结了
		Map<String,Bitmap> maps = new TreeMap<String, Bitmap>();
		if (baseFile != null && baseFile.exists()) {
			paths = imagePath(baseFile);

			if (!paths.isEmpty()) {
				for (int i = 0; i < paths.size(); i++) {
					 BitmapFactory.Options options = new BitmapFactory.Options();
					 options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false
					 Bitmap bitmap =BitmapFactory.decodeFile(paths.get(i),options);
					 options.inJustDecodeBounds = false;
					 int be = options.outHeight/40;
					 if (be <= 0) {
						 be = 10;
					 }
					 options.inSampleSize = be;
					 bitmap = BitmapFactory.decodeFile(paths.get(i),options);
					 maps.put(paths.get(i), bitmap);
				}
			}
		}

		return maps;
	}
}

