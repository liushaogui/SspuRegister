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
    private int cameraPosition = 1;//0����ǰ������ͷ��1�����������ͷ
    

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
        holder = surfaceView.getHolder();//��þ��
        surfaceView.getHolder().setFixedSize(176, 144); //����Surface�ֱ���  
        surfaceView.getHolder().setKeepScreenOn(true);// ��Ļ����  
        surfaceView.getHolder().addCallback(new SurfaceCallback());//ΪSurfaceView�ľ�����һ���ص�����  
        
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
		String strsum=Integer.toString(sum)+"��";
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
	         cameraCount = Camera.getNumberOfCameras();//�õ�����ͷ�ĸ���

	         for(int i = 0; i < cameraCount; i++ ) {
	             Camera.getCameraInfo(i, cameraInfo);//�õ�ÿһ������ͷ����Ϣ
	             if(cameraPosition == 1) {
	                 //�����Ǻ��ã����Ϊǰ��
	                 if(cameraInfo.facing  == Camera.CameraInfo.CAMERA_FACING_FRONT) {//��������ͷ�ķ�λ��CAMERA_FACING_FRONTǰ��      CAMERA_FACING_BACK����  
	                	 mycamera.stopPreview();//ͣ��ԭ������ͷ��Ԥ��
	                	 mycamera.release();//�ͷ���Դ
	                	 mycamera = null;//ȡ��ԭ������ͷ
	                	 mycamera = Camera.open(i);//�򿪵�ǰѡ�е�����ͷ
	                     try {
	                    	 mycamera.setPreviewDisplay(holder);//ͨ��surfaceview��ʾȡ������
	                    	
	                     } catch (IOException e) {
	                         // TODO Auto-generated catch block
	                         e.printStackTrace();
	                     }
	                     mycamera.setDisplayOrientation(90);  
	                     mycamera.startPreview();//��ʼԤ��
	                     cameraPosition = 0;
	                     return;
	                 }
	             } else {
	                 //������ǰ�ã� ���Ϊ����
	                 if(cameraInfo.facing  == Camera.CameraInfo.CAMERA_FACING_BACK) {//��������ͷ�ķ�λ��CAMERA_FACING_FRONTǰ��      CAMERA_FACING_BACK����  
	                	 mycamera.stopPreview();//ͣ��ԭ������ͷ��Ԥ��
	                	 mycamera.release();//�ͷ���Դ
	                     mycamera = null;//ȡ��ԭ������ͷ
	                     mycamera = Camera.open(i);//�򿪵�ǰѡ�е�����ͷ
	                     try {
	                    	 mycamera.setPreviewDisplay(holder);//ͨ��surfaceview��ʾȡ������
	                    	 
	                     } catch (IOException e) {
	                         // TODO Auto-generated catch block
	                         e.printStackTrace();
	                     }
	                     mycamera.setDisplayOrientation(90);  
	                     mycamera.startPreview();//��ʼԤ��
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
		    	Toast.makeText(camera.this, "������Ϣδ¼�룬����ϵ��ʦ", Toast.LENGTH_LONG).show();
		    	if(mode.equals("11")){
		    		speak("������Ϣδ¼�룬����ϵ��ʦ");
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
	    	Toast.makeText(camera.this, stuname+"��ǩ��", Toast.LENGTH_SHORT).show();
	    	if(mode.equals("11")){
	    		speak(stuname+"��ǩ��");
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
	    	speak(stuname+"ǩ���ɹ�");
    	}
	    
	    retimes=1;
		sum=sum+1;
		String strsum=Integer.toString(sum)+"��";
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
				Toast.makeText(camera.this, "δ��װѶ����������,�޷������ʶ�",
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
                bundle.putByteArray("bytes", data); //��ͼƬ�ֽ����ݱ�����bundle���У�ʵ�����ݽ���  
                saveToSDCard(data); // ����ͼƬ��sd����  

                camera.startPreview(); // �����պ����¿�ʼԤ��  
                setImageBitmap(data);  
                
  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    public void setImageBitmap(byte[] data) {  
        //Bitmap cameraBitmap = byte2Bitmap();  
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length); 
        // ��������ķ�����תͼ����������ʱҪ��Ҫ��ͼ��ѡ��90��)  
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
     * ������������Ƭ�����SD���� 
     * @param data   
     * @throws IOException 
     */  
    public static void saveToSDCard(byte[] data) throws IOException {  
        Date date = new Date();  
      
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // ��ʽ��ʱ��  
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
        FileOutputStream outputStream = new FileOutputStream(jpgFile); // �ļ������  
        outputStream.write(data); // д��sd����  
        outputStream.close(); // �ر������  
    }  
  
  
    private final class SurfaceCallback implements Callback {  
  
        // ����״̬�仯ʱ���ø÷���  
        @Override  
        public void surfaceChanged(SurfaceHolder holder, int format, int width,  
                int height) {  
            parameters = mycamera.getParameters(); // ��ȡ�������  
            parameters.setPictureFormat(PixelFormat.JPEG); // ����ͼƬ��ʽ  
            parameters.setPreviewSize(100, 100); // ����Ԥ����С  
            parameters.setPreviewFrameRate(5);  //����ÿ����ʾ4֡  
            parameters.setPictureSize(width, height); // ���ñ����ͼƬ�ߴ�  
            parameters.setJpegQuality(80); // ������Ƭ����  
        }  
  
        // ��ʼ����ʱ���ø÷���  
    
		@Override  
        public void surfaceCreated(SurfaceHolder holder) {  
            try {  
            	mycamera = Camera.open(); // ������ͷ  
            	mycamera.setPreviewDisplay(holder); // ����������ʾ����Ӱ���SurfaceHolder����  
            	mycamera.setDisplayOrientation(getPreviewDegree(camera.this));  
            	mycamera.startPreview(); // ��ʼԤ��  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
  
        }  
  
        // ֹͣ����ʱ���ø÷���  
        @Override  
        public void surfaceDestroyed(SurfaceHolder holder) {  
            if (mycamera != null) {  
            	mycamera.release(); // �ͷ������  
            	mycamera = null;  
            }  
        }  
    }  
  

  
    // �ṩһ����̬���������ڸ����ֻ����������Ԥ��������ת�ĽǶ�  
 
	public static int getPreviewDegree(Activity activity) {  
        // ����ֻ��ķ���  
        int rotation = activity.getWindowManager().getDefaultDisplay()  
                .getRotation();  
        int degree = 0;  
        // �����ֻ��ķ���������Ԥ������Ӧ��ѡ��ĽǶ�  
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
        	Toast.makeText(camera.this, "ֻ�ܶ���һ��",Toast.LENGTH_LONG).show();
        }
    	

	}
	
	
}
		  
		
		
	
	



