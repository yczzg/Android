package com.example.uigallary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements OnItemSelectedListener, ViewFactory {
    private ImageSwitcher mSwitcher;
    
    //ͼƬ����
    private Integer[] mThumbIds = { R.drawable.s1,
    		R.drawable.s2, R.drawable.s3,
    		R.drawable.s4, R.drawable.s5,
    		R.drawable.s6, R.drawable.s7};
    
    private Integer[] mImageIds = { R.drawable.l1,
    		R.drawable.l2, R.drawable.l3,
    		R.drawable.l4, R.drawable.l5,
    		R.drawable.l6, R.drawable.l7};
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���ô��������ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//���ý��沼��
		setContentView(R.layout.main);
		
		mSwitcher =(ImageSwitcher)findViewById(R.id.switcher);
		//ΪImageSwitcher���ù���
		mSwitcher.setFactory(this);
		//���ö���Ч��
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		Gallery g = (Gallery)findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));
		g.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> adapter, View v, int position, long id){
		//����ͼƬ��Դ
		mSwitcher.setImageResource(mImageIds[position]);
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> arg0){
		
	}

	@Override
	public View makeView(){
		ImageView i = new ImageView(this);
		//���ñ���ɫ
		i.setBackgroundColor(0xFF000000);
		//���þ�������
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		//���ò��ֲ���
		i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		return i;
	}
	
	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		
		public ImageAdapter(Context c) {
			mContext = c;
		}
		
		public int getCount() {
			return mThumbIds.length;
		}
		
		public Object getItem(int position){
			return position;
		}
		
		public long getItemId(int position) {
			return position;
		}
		
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView i = new ImageView(mContext);
			//����ͼƬ��Դ
			i.setImageResource(mThumbIds[position]);
			//���ñ߽����
			i.setAdjustViewBounds(true);
			//���ò��ֲ���
			i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			//���ñ�����Դ
			//i.setBackgroundResource(R.drawable)
			return i;
		}
	}
}
