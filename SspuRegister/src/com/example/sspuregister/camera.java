package com.example.sspuregister;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;






import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;  
import android.content.Intent;  
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;  
import android.hardware.Camera;  
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;  
import android.os.Environment;  
import android.view.Surface;  
import android.view.SurfaceHolder;  
import android.view.SurfaceHolder.Callback;  
import android.view.SurfaceView;  
import android.widget.ImageView;


public class camera extends Activity implements OnInitListener{
	private Cursor myCursor;
	private ToDoDB myToDoDB;
	private ListView studentListView;
	private Register register1;
	private EditText cardeditText;
	private TextView showsum;
	private String col;
	private String name;
	private String kid;
	private int sum;
	private TextToSpeech tts;
	
	private Camera mycamera;  
    private Camera.Parameters parameters = null;  
    private ImageView ivPic = null;
    private Bundle bundle = null;
    private int retimes=1;
    private String mode;
    private static String code="sspuregister";
    
    private SurfaceHolder holder;
    private int cameraPosition = 1;//0代表前置摄像头，1代表后置摄像头
    

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.camera);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		
        ivPic = (ImageView) findViewById(R.id.imageView1);  
		
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.surfaceView);  
        surfaceView.getHolder()  
                .setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
        holder = surfaceView.getHolder();//获得句柄
        surfaceView.getHolder().setFixedSize(176, 144); //设置Surface分辨率  
        surfaceView.getHolder().setKeepScreenOn(true);// 屏幕常亮  
        surfaceView.getHolder().addCallback(new SurfaceCallback());//为SurfaceView的句柄添加一个回调函数  
        
		studentListView=(ListView)findViewById(R.id.cameraListView);
		myToDoDB = new ToDoDB(this);
		register1 = new Register();
		tts = new TextToSpeech(this, this);
		name=register1.majorname;
		mode=register1.mode;
		showsum=register1.arrived;
		cardeditText=(EditText)findViewById(R.id.cameracard);  
		 
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
		String logtime=strmonth+strday;
		col="L"+logtime;
		
		myCursor=myToDoDB.selectinitstu(name,col);
		sum=myCursor.getCount();
		String strsum=Integer.toString(sum)+"人";
		showsum.setText(strsum);
		cardeditText.setOnKeyListener(onKey);  
		
		
	}
	
	OnKeyListener onKey=new OnKeyListener() {  
		
		  
		@Override
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if(keyCode == KeyEvent.KEYCODE_ENTER){
				kid=cardeditText.getText().toString();
				if(!kid.equals("")){
					selectstudent();
				}
				cardeditText.setFocusable(true);
				cardeditText.setFocusableInTouchMode(true);
				cardeditText.requestFocus();
				cardeditText.requestFocusFromTouch();
				
				
				return true;
			}
			return false;
		}  
		  
		};  
		
		
		public void change(View v) {  
	    	 int cameraCount = 0;
	         CameraInfo cameraInfo = new CameraInfo();
	         cameraCount = Camera.getNumberOfCameras();//得到摄像头的个数

	         for(int i = 0; i < cameraCount; i++ ) {
	             Camera.getCameraInfo(i, cameraInfo);//得到每一个摄像头的信息
	             if(cameraPosition == 1) {
	                 //现在是后置，变更为前置
	                 if(cameraInfo.facing  == Camera.CameraInfo.CAMERA_FACING_FRONT) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置  
	                	 mycamera.stopPreview();//停掉原来摄像头的预览
	                	 mycamera.release();//释放资源
	                	 mycamera = null;//取消原来摄像头
	                	 mycamera = Camera.open(i);//打开当前选中的摄像头
	                     try {
	                    	 mycamera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
	                    	
	                     } catch (IOException e) {
	                         // TODO Auto-generated catch block
	                         e.printStackTrace();
	                     }
	                     mycamera.setDisplayOrientation(90);  
	                     mycamera.startPreview();//开始预览
	                     cameraPosition = 0;
	                     return;
	                 }
	             } else {
	                 //现在是前置， 变更为后置
	                 if(cameraInfo.facing  == Camera.CameraInfo.CAMERA_FACING_BACK) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置  
	                	 mycamera.stopPreview();//停掉原来摄像头的预览
	                	 mycamera.release();//释放资源
	                     mycamera = null;//取消原来摄像头
	                     mycamera = Camera.open(i);//打开当前选中的摄像头
	                     try {
	                    	 mycamera.setPreviewDisplay(holder);//通过surfaceview显示取景画面
	                    	 
	                     } catch (IOException e) {
	                         // TODO Auto-generated catch block
	                         e.printStackTrace();
	                     }
	                     mycamera.setDisplayOrientation(90);  
	                     mycamera.startPreview();//开始预览
	                     cameraPosition = 1;
	                     return;
	                 }
	             }

	         }
	       
		}
	       
		
		
	private void selectstudent(){

		myCursor=myToDoDB.selectstudent(name,kid);
		myCursor.moveToFirst();
		    int flag=myCursor.getCount();
		    if(flag==0){
		    	Toast.makeText(camera.this, "该生信息未录入，请联系老师", Toast.LENGTH_LONG).show();
		    	if(mode.equals("11")){
		    		speak("该生信息未录入，请联系老师");
		    	}
		    	
		    	cardeditText.setText("");
		    	cardeditText.setFocusable(true);
		    	return;
		    }
		    
	    String times=myCursor.getString(5);
	    String stuname=myCursor.getString(3);
	    String stucode=myCursor.getString(2);
	    code=""+stucode+"/";
	    String checkrepeat=myCursor.getString(myCursor.getColumnIndex(col));
	    if(checkrepeat.equals("1")){
	    	Toast.makeText(camera.this, stuname+"已签到", Toast.LENGTH_SHORT).show();
	    	if(mode.equals("11")){
	    		speak(stuname+"已签到");
	    	}
	    	
	    	cardeditText.setText("");
	    	return;
	    }
	    mycamera.takePicture(null, null, new MyPictureCallback());  
	    int itimes=Integer.parseInt(times);
	    itimes=itimes+1;
	    times=Integer.toString(itimes);
	    myToDoDB.update(name,col,kid);
	    
	    myToDoDB.updatetimes(name,times,kid);
	    
	    
	    
	    SimpleCursorAdapter adapter = 
	     	    new SimpleCursorAdapter
	     	    (this, R.layout.cameralist, myCursor, new String[]
	     	        { "name","code","myclass" }, new int[]
	     	        { R.id.cameralistTextView1,R.id.cameramytextView1,R.id.cameramyclass});
	     	    studentListView.setAdapter(adapter);
	     	    
	    cardeditText.setText("");
	    if(mode.equals("11")){
	    	speak(stuname+"签到成功");
    	}
	    
	    retimes=1;
		sum=sum+1;
		String strsum=Integer.toString(sum)+"人";
		showsum.setText(strsum);
		
	}


	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		{
			
			int result = tts.setLanguage(Locale.CHINA);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED)
			{
				Toast.makeText(camera.this, "未安装讯飞语音引擎,无法语音朗读",
						Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	private void speak(String detail){
		 tts.speak(detail,
					TextToSpeech.QUEUE_FLUSH, null);
		
	}
	
	private final class MyPictureCallback implements PictureCallback {  
		  
        @Override  
        public void onPictureTaken(byte[] data, Camera camera) {  
            try {  
                bundle = new Bundle();  
                bundle.putByteArray("bytes", data); //将图片字节数据保存在bundle当中，实现数据交换  
                saveToSDCard(data); // 保存图片到sd卡中  

                camera.startPreview(); // 拍完照后，重新开始预览  
                setImageBitmap(data);  
                
  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    public void setImageBitmap(byte[] data) {  
        //Bitmap cameraBitmap = byte2Bitmap();  
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length); 
        // 根据拍摄的方向旋转图像（纵向拍摄时要需要将图像选择90度)  
        Matrix matrix = new Matrix();  
        if(cameraPosition==1){
        	 matrix.setRotate(camera.getPreviewDegree(this));  
        }else{
        	 matrix.setRotate(camera.getPreviewDegree(this)+180);  
        }
       
        bitmap = Bitmap  
                .createBitmap(bitmap, 0, 0, bitmap.getWidth(),  
                		bitmap.getHeight(), matrix, true);  

        ivPic.setImageBitmap(bitmap);  
    }  
    
    /** 
     * 将拍下来的照片存放在SD卡中 
     * @param data   
     * @throws IOException 
     */  
    public static void saveToSDCard(byte[] data) throws IOException {  
        Date date = new Date();  
      
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间  
        String filename = format.format(date) + ".jpg";  
        
        File fatherfile = new File(Environment.getExternalStorageDirectory()  
                + "/sspuregister/");  
        if (!fatherfile.exists()) { 
        	fatherfile.mkdir();  
        }  
        
        File fileFolder = new File(Environment.getExternalStorageDirectory()  
                + "/sspuregister/"+code+"");  
        if (!fileFolder.exists()) { 
            fileFolder.mkdir();  
        }  
        File jpgFile = new File(fileFolder, filename);  
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // 文件输出流  
        outputStream.write(data); // 写入sd卡中  
        outputStream.close(); // 关闭输出流  
    }  
  
  
    private final class SurfaceCallback implements Callback {  
  
        // 拍照状态变化时调用该方法  
        @Override  
        public void surfaceChanged(SurfaceHolder holder, int format, int width,  
                int height) {  
            parameters = mycamera.getParameters(); // 获取各项参数  
            parameters.setPictureFormat(PixelFormat.JPEG); // 设置图片格式  
            parameters.setPreviewSize(100, 100); // 设置预览大小  
            parameters.setPreviewFrameRate(5);  //设置每秒显示4帧  
            parameters.setPictureSize(width, height); // 设置保存的图片尺寸  
            parameters.setJpegQuality(80); // 设置照片质量  
        }  
  
        // 开始拍照时调用该方法  
    
		@Override  
        public void surfaceCreated(SurfaceHolder holder) {  
            try {  
            	mycamera = Camera.open(); // 打开摄像头  
            	mycamera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象  
            	mycamera.setDisplayOrientation(getPreviewDegree(camera.this));  
            	mycamera.startPreview(); // 开始预览  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
  
        }  
  
        // 停止拍照时调用该方法  
        @Override  
        public void surfaceDestroyed(SurfaceHolder holder) {  
            if (mycamera != null) {  
            	mycamera.release(); // 释放照相机  
            	mycamera = null;  
            }  
        }  
    }  
  

  
    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度  
 
	public static int getPreviewDegree(Activity activity) {  
        // 获得手机的方向  
        int rotation = activity.getWindowManager().getDefaultDisplay()  
                .getRotation();  
        int degree = 0;  
        // 根据手机的方向计算相机预览画面应该选择的角度  
        switch (rotation) {  
        case Surface.ROTATION_0:  
            degree = 90;  
            break;  
        case Surface.ROTATION_90: 
            degree = 0;  
            break;  
        case Surface.ROTATION_180:  
            degree = 270;  
            break;  
        case Surface.ROTATION_270:  
            degree = 180;  
            break;  
        }  
        return degree;  
    }  
    
    public void rephoto(View v) {
        if(retimes<2){
        	mycamera.takePicture(null, null, new MyPictureCallback()); 
        	retimes++;
        }else{
        	Toast.makeText(camera.this, "只能多拍一次",Toast.LENGTH_LONG).show();
        }
    	

	}
	
	
}
		  
		
		
	
	



