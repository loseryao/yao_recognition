package com.tiger.quicknews.activity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import com.tiger.quicknews.App;

import com.tiger.quicknews.R;
import com.tiger.quicknews.R.layout;
import com.tiger.quicknews.utils.StringUtils;
import com.tiger.quicknews.wedget.discrollview.DiscrollView;
import com.tiger.quicknews.wedget.slidingmenu.SlidingMenu;
import com.umeng.analytics.MobclickAgent;
import com.useridentify.db.IdenDBManager;
import com.useridentify.db.IdenSqlHelper;
import com.useridentify.service.*;
import com.useridentify.util.StringHandle;






import android.app.AlertDialog;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
@Fullscreen
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {
	
    
    @ViewById(R.id.buttonLogin)
    protected Button buttonLogin;
    @ViewById(R.id.buttonRegister)
    protected Button buttonRegister;
    @ViewById(R.id.textView1)
    protected TextView tv;
    private String cache;
    private IdenDBManager ids;
    private long exitTime = 0;
    private double back_pressed;
    /** 屏幕宽度 */
    final Activity ac = this;
    private StringHandle sh;
    App bean;
    private long mExitTime;
    @AfterViews
    public void initView(){
		//IdenSqlHelper ids = new IdenSqlHelper(this);
		bean = (App)getApplication();
		ids = new IdenDBManager(ac);
		sh = new StringHandle("0");
		tv.setText("   一场悄悄的识别...\nPowered by 01_Team");
		InputStream is = getResources().openRawResource(R.raw.iden);
		if(ids.empty()){
			sh.Excute(is, ids);
		}
		
		//bean.setIdenDBManager(ids);
		Log.e("Login", "Login");
		Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + getPackageCodePath();
           process = Runtime.getRuntime().exec("su"); // 切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
           os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
           os.flush();
           process.waitFor();
       } catch (Exception e) {
            // return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
       }
		
//    
//         MainActivity_.intent(this).start();
//         defaultFinishNotActivityHelper();
//         setCacheStr("MoreActivity", "");
		
    }
	@Click(R.id.buttonRegister)
	void ButtonRegisterClicked()
	{
		bean.close();
    	Builder builder=new AlertDialog.Builder(this);
	    builder.setTitle("请输入用户名");
	    final EditText ed = new EditText(this);
	    builder.setIcon(android.R.drawable.btn_star);
	    builder.setView(ed);
	    //Log.v("buttonRegister", "buttonRegister");
	    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            @Override  
            public void onClick(DialogInterface arg0, int arg1) {  
                //数据获取  
            	String UserId = ed.getText().toString();
            	App bean = (App)getApplication();
            	bean.setUserID(UserId);
            	IdenNotifyService_.intent(ac).start();
            	MainActivity_.intent(ac).start();
            	
            }
            });
	    builder.setNegativeButton("取消", null);
	    AlertDialog dialog=builder.create();
	    dialog.show();
	}
	@Click(R.id.buttonLogin)
	void ButtonLoginClicked() {
		bean.close();
		IdenInformService_.intent(ac).start();
		MainActivity_.intent(ac).start();
		  
	}
	@Override
	public void onResume() {
	        super.onResume();
	        MobclickAgent.onResume(this);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	            finish();
	            System.exit(0);
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onPause() {
	        super.onPause();
	        MobclickAgent.onPause(this);
	    }
	
}
