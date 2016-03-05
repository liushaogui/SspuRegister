package com.example.sspuregister;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {
	public static Register instance = null;
	public static String majorname="请先选择课程";

	private ViewPager mTabPager;
	private ImageView mTabImg;// 动画图片
	private ImageView mTab1, mTab2, mTab3, mTab4;
	private int zero = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int one;// 单个水平动画位移
	private int two;
	private int three;
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private Spinner spcourse;
	private ArrayAdapter<String> adapcourse;

	private ToDoDB myToDoDB1;
	private Cursor myCursor1;
	private EditText in_kid;
	private EditText in_code;
	private EditText in_name;
	private EditText in_class;
	private TextView in_stat;
	private TextView sum;
	public static TextView arrived;
	private TextView teaname;
	private TextView teaphone;
	private TextView teaemail;
	private TextView teaaddr;
	
	private String logtime;
	
	private CheckBox checkphone;
	private CheckBox checksound;
	public static String mode;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registermain);
		instance = this;
		in_kid = (EditText) findViewById(R.id.in_kid);
		//in_kid.setText("aaa");
		setupViewComponent();
		myToDoDB1 = new ToDoDB(this);
		Calendar calendar = Calendar.getInstance();
		int month=calendar.get(Calendar.MONTH)+1;
		String strmonth=Integer.toString(month);
        if(month<10){
        	strmonth="0"+strmonth;
		}
		int day= calendar.get(Calendar.DAY_OF_MONTH);
		String strday=Integer.toString(day);
		if(day<10){
			strday="0"+strday;
	    }
		logtime=strmonth+strday;	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			Intent intent1 = new Intent();
			intent1.setClass(Register.this, about.class);
			startActivity(intent1);
			break;

		case R.id.exit:
			Intent intent = new Intent();
			intent.setClass(Register.this, Exit.class);
			startActivity(intent);
			break;

		}
		return super.onOptionsItemSelected(item);
	}

	private void setupViewComponent() {

		mTabPager = (ViewPager) findViewById(R.id.tabpager);
		mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());

		mTab1 = (ImageView) findViewById(R.id.img_weixin);
		mTab2 = (ImageView) findViewById(R.id.img_address);
		mTab3 = (ImageView) findViewById(R.id.img_friends);
		mTab4 = (ImageView) findViewById(R.id.img_settings);
		mTabImg = (ImageView) findViewById(R.id.img_tab_now);
		

		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.setregister, null);
		View view2 = mLi.inflate(R.layout.inputinfor, null);
		View view3 = mLi.inflate(R.layout.upload, null);
		View view4 = mLi.inflate(R.layout.manage, null);

		mTab1.setOnClickListener(new MyOnClickListener(0));
		mTab2.setOnClickListener(new MyOnClickListener(1));
		mTab3.setOnClickListener(new MyOnClickListener(2));
		mTab4.setOnClickListener(new MyOnClickListener(3));

		Display currDisplay = getWindowManager().getDefaultDisplay();// 获取屏幕当前分辨率
		int displayWidth = currDisplay.getWidth();
		int displayHeight = currDisplay.getHeight();
		one = displayWidth / 4; // 设置水平动画平移大小
		two = one * 2;
		three = one * 3;

		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			// @Override
			// public CharSequence getPageTitle(int position) {
			// return titles.get(position);
			// }

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mTabPager.setAdapter(mPagerAdapter);

	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);

		}
	};

	/*
	 * 页卡切换监听(原作者:D.Winter)
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_weixin_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_settings_normal));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(
						R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_weixin_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(
							R.drawable.tab_find_frd_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 获取
																				// back键

			if (menu_display) { // 如果 Menu已经打开 ，先关闭Menu
				menuWindow.dismiss();
				menu_display = false;
			} else {
				Intent intent = new Intent();
				intent.setClass(Register.this, Exit.class);
				startActivity(intent);
			}

		}

		return false;
	}

	public void managecourse(View v) {

		Intent intent = new Intent(Register.this, managecourse.class);
		startActivity(intent);
	}

	public void manageabsent(View v) {
		
		if(majorname=="请先选择课程"){
			Toast.makeText(Register.this, "请先选择课程", Toast.LENGTH_LONG).show();
			return;
		}
		
		myCursor1 = myToDoDB1.checklogin(logtime,majorname);
		int count=myCursor1.getCount();
		if(count==0){
			String col="L"+logtime;
			myToDoDB1.insertlogin(logtime, majorname);
			myToDoDB1.alterTable(majorname,col);
			
		}

		Intent intent = new Intent(Register.this, manageabsent.class);
		startActivity(intent);
	}

	public void manageontime(View v) {
		
		if(majorname=="请先选择课程"){
			Toast.makeText(Register.this, "请先选择课程", Toast.LENGTH_LONG).show();
			return;
		}
		
		myCursor1 = myToDoDB1.checklogin(logtime,majorname);
		int count=myCursor1.getCount();
		if(count==0){
			String col="L"+logtime;
			myToDoDB1.insertlogin(logtime, majorname);
			myToDoDB1.alterTable(majorname,col);
			
		}

		Intent intent = new Intent(Register.this, manageontime.class);
		startActivity(intent);

	}

	public void managedownload(View v) {
		
		if(!isConn(getApplicationContext())){
	           setNetworkMethod(Register.this);
	           return;
	        }

		Intent intent = new Intent(Register.this, managedownload.class);
		startActivity(intent);

	}

	public void upload(View v) {
		
		if(majorname=="请先选择课程"){
			Toast.makeText(Register.this, "请先选择课程", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(!isConn(getApplicationContext())){
	           setNetworkMethod(Register.this);
	           return;
	        }

		Intent intent = new Intent();
		intent.setClass(Register.this, uploadingActivity.class);
		startActivity(intent);

	}

	public void download(View v) {
		
		if(majorname=="请先选择课程"){
			Toast.makeText(Register.this, "请先选择课程", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(!isConn(getApplicationContext())){
	           setNetworkMethod(Register.this);
	           return;
	        }

		Intent intent = new Intent();
		intent.setClass(Register.this, downloadingActivity.class);
		startActivity(intent);

	}

	public void btnaddclick(View v) {
		setupInPutViewComponent();
		
		if(majorname=="请先选择课程"){
			in_stat.setText( "请先选择课程!");
			return;
		}
		
		
		if (in_kid.getText().toString().equals("")) {
			in_stat.setText("卡号不能为空");
			return;
		}
		if (in_code.getText().toString().equals("")) {
			in_stat.setText("学号不能为空");
			return;
		}
		if (in_name.getText().toString().equals("")) {
			in_stat.setText("姓名不能为空");
			return;
		}
		if (in_class.getText().toString().equals("")) {
			in_stat.setText("班级不能为空");
			return;
		}

		addTodo();

	}

	public void btnsearchclick(View v) {
		if(majorname=="请先选择课程"){
			in_stat.setText( "请先选择课程!");
			return;
		}
		
		in_kid = (EditText) findViewById(R.id.in_kid);
		in_code = (EditText) findViewById(R.id.in_code);
		in_name = (EditText) findViewById(R.id.in_name);
		in_class = (EditText) findViewById(R.id.in_class);
		in_stat = (TextView) findViewById(R.id.in_stat);
		in_stat.setText("查找模式已打开");
		in_kid.setText("");
		in_kid.setEnabled(true);
		in_kid.setFocusable(true);
		in_kid.setFocusableInTouchMode(true);
		in_kid.requestFocus();
		in_kid.requestFocusFromTouch();
		in_kid.setOnKeyListener(onKey); 
		

	}

	public void startregister(View v) {
		if(majorname=="请先选择课程"){
			Toast.makeText(Register.this, "请先选择课程", Toast.LENGTH_LONG).show();
			return;
		}
		
		myCursor1 = myToDoDB1.checklogin(logtime,majorname);
		int count=myCursor1.getCount();
		if(count==0){
			String col="L"+logtime;
			myToDoDB1.insertlogin(logtime, majorname);
			myToDoDB1.alterTable(majorname,col);
		}
		
		myCursor1 = myToDoDB1.select("mode");
		myCursor1.moveToFirst();
		String phone=myCursor1.getString(2).toString();
		myCursor1.moveToNext();
		String sound=myCursor1.getString(2).toString();
		mode=phone+sound;
		//Toast.makeText(Register.this, mode, Toast.LENGTH_LONG).show();
		if(phone.equals("1")){
			Intent intent = new Intent();
			intent.setClass(Register.this, camera.class);
			startActivity(intent);
		}else{
			Intent intent = new Intent();
			intent.setClass(Register.this, setregister.class);
			startActivity(intent);
		}
		
		
		

	}

	public void showcourse(View v) {

		spcourse = (Spinner) findViewById(R.id.selectcourse);
		sum=(TextView)findViewById(R.id.sum);
		arrived=(TextView)findViewById(R.id.arrived);
		teaname=(TextView)findViewById(R.id.teaname);
		teaphone=(TextView)findViewById(R.id.teaphone);
		teaemail=(TextView)findViewById(R.id.teaemail);
		teaaddr=(TextView)findViewById(R.id.teaaddr);
		
		//myToDoDB1 = new ToDoDB(this);
	    /* 取得DataBase里的数据 */
        myCursor1 = myToDoDB1.selectcourse();
        if(myCursor1.getCount()==0){
        	Toast.makeText(Register.this, "还没有任何课程,请先添加课程!", Toast.LENGTH_LONG).show();
			return;
        }
        
		SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.courselist, myCursor1, new String[]
	     	        { "name"}, new int[]
	     	        { R.id.courselist});
		spcourse.setAdapter(adapter);
	     	    
		
		spcourse.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
            	SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
            	majorname=sc.getString(1).toString();
            	myCursor1=myToDoDB1.select(majorname);
            	int isum=myCursor1.getCount();
            	String strsum=Integer.toString(isum)+"人";
            	sum.setText(strsum);
            	arrived.setText("开始后显示");
            	teaname.setText(sc.getString(2));
        		teaphone.setText(sc.getString(3));
        		teaemail.setText(sc.getString(4));
        		teaaddr.setText(sc.getString(5));
    	      
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
               
            }
        });

	}


	private void addTodo() {

		/* 添加数据到数据库 */
		String kid = in_kid.getText().toString();
		String code = in_code.getText().toString();
		String name = in_name.getText().toString();
		String myclass = in_class.getText().toString();
		int flag=0;
	    myCursor1=myToDoDB1.selectstudent(majorname,kid);
	    myCursor1.moveToFirst();
	    flag=myCursor1.getCount();
	    if(flag>0){
	    	String sname = myCursor1.getString(3);
	    	in_stat.setText(sname+"已存在!");
	    	in_kid.setText("");
	    	return ;
	    }
		myToDoDB1.insertstudent(majorname,kid, code, name, myclass);
		
		 myCursor1=myToDoDB1.selectstudent("student",kid);
		 myCursor1.moveToFirst();
		 flag=myCursor1.getCount();
		 if(flag==0){
			 myToDoDB1.insertstudent("student",kid, code, name, myclass);	
		 }
		/* 重新查询 */
		// myCursor.requery();
		/* 重新整理myListView */
		// myListView.invalidateViews();
		in_stat.setText(in_name.getText().toString() + "添加成功!");
		in_kid.setText("");
		in_code.setText("");
		in_name.setText("");
		in_class.setText("");
		in_kid.setEnabled(true);
		in_kid.setFocusable(true);
		in_kid.setFocusableInTouchMode(true);
		in_kid.requestFocus();
		in_kid.requestFocusFromTouch();
		
		

	}

	private void setupInPutViewComponent() {
		in_kid = (EditText) findViewById(R.id.in_kid);
		in_code = (EditText) findViewById(R.id.in_code);
		in_name = (EditText) findViewById(R.id.in_name);
		in_class = (EditText) findViewById(R.id.in_class);
		in_stat = (TextView) findViewById(R.id.in_stat);

	}
	
	OnKeyListener onKey=new OnKeyListener() {  
		
		  
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if(keyCode == KeyEvent.KEYCODE_ENTER){
			    String strkid=in_kid.getText().toString();
			    if(strkid.equals("")){
			    	in_kid.setFocusable(true);
			    	in_kid.setFocusableInTouchMode(true);
			    	in_kid.requestFocus();
			    	in_kid.requestFocusFromTouch();
			    	return true;
			    }
			    int flag=0;
			    myCursor1=myToDoDB1.selectstudent(majorname,strkid);
			    myCursor1.moveToFirst();
			    flag=myCursor1.getCount();
			    if(flag>0){
			    	String code = myCursor1.getString(2);
					String name = myCursor1.getString(3);
					String myclass = myCursor1.getString(4);
					in_code.setText(code); 
					in_name.setText(name); 
					in_class.setText(myclass);
			    	in_stat.setText(name+"已存在!");
			    	in_kid.setText("");
//			    	in_kid.setFocusable(true);
//			    	in_kid.setFocusableInTouchMode(true);
//			    	in_kid.requestFocus();
//			    	in_kid.requestFocusFromTouch();
			    	return true;
			    }
				
				myCursor1=myToDoDB1.selectstudent("student",strkid);
				myCursor1.moveToFirst();
				flag=myCursor1.getCount();
				if(flag>0){
					String code = myCursor1.getString(2);
					String name = myCursor1.getString(3);
					String myclass = myCursor1.getString(4);
					myToDoDB1.insertstudent(majorname,strkid, code, name, myclass);
					in_code.setText(code); 
					in_name.setText(name); 
					in_class.setText(myclass);
					in_stat.setText(name+"添加成功!");
					in_kid.setText("");
					
//					in_kid.setFocusable(true);
//			    	in_kid.setFocusableInTouchMode(true);
//			    	in_kid.requestFocus();
//			    	in_kid.requestFocusFromTouch();
			    	return true;
				}
				
				in_stat.setText("无记录请手动添加!");
				//in_kid.setText("");
				in_kid.setEnabled(false);
				in_code.setText(""); 
				in_name.setText(""); 
				in_class.setText("");
			}
			
		  return false;
		}  
		  
		}; 
		
		public void checkphone(View v) {
			checkphone=(CheckBox)findViewById(R.id.checkphone);
			
			if(checkphone.isChecked()){
				myToDoDB1.updatemode("phone","1");
			}else{
				myToDoDB1.updatemode("phone","0");
			}
			
		}
		
		public void checksound(View v) {
			checksound=(CheckBox)findViewById(R.id.checksound);
		
			if(checksound.isChecked()){
				myToDoDB1.updatemode("sound","1");
			}else{
				myToDoDB1.updatemode("sound","0");
			}

		}
		
		public void mode(View v) {
			checkphone=(CheckBox)findViewById(R.id.checkphone);
			checksound=(CheckBox)findViewById(R.id.checksound);
			myCursor1 = myToDoDB1.select("mode");
			myCursor1.moveToFirst();
			String phone=myCursor1.getString(2).toString();
			if(phone.equals("1")){
				checkphone.setChecked(true);
			}else{
				checkphone.setChecked(false);
			}
			myCursor1.moveToNext();
			String sound=myCursor1.getString(2).toString();
			if(sound.equals("1")){
				checksound.setChecked(true);
			}else{
				checksound.setChecked(false);
			}	

		}
		
		public static boolean isConn(Context context){
	        boolean bisConnFlag=false;
	        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo network = conManager.getActiveNetworkInfo();
	        if(network!=null){
	            bisConnFlag=conManager.getActiveNetworkInfo().isAvailable();
	        }
	        return bisConnFlag;
	    }
		
		public static void setNetworkMethod(final Context context){
	        //提示对话框
	        AlertDialog.Builder builder=new Builder(context);
	        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {
	            
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                Intent intent=null;
	                //判断手机系统的版本  即API大于10 就是3.0或以上版本 
	                if(android.os.Build.VERSION.SDK_INT>10){
	                    intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
	                }else{
	                    intent = new Intent();
	                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
	                    intent.setComponent(component);
	                    intent.setAction("android.intent.action.VIEW");
	                }
	                context.startActivity(intent);
	            }
	        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
	            
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                // TODO Auto-generated method stub
	                dialog.dismiss();
	            }
	        }).show();
	    }

		



}
