package com.example.xutilsexercise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xutilsexercise.R;
import com.example.xutilsexercise.bean.Person;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class MyAdapter extends  BaseAdapter{
	private Context context;
	private List<Person> list;
	private LayoutInflater mInflater;
	private ImageOptions options;
	public ViewHolder holder;
	public MyAdapter(Context context, List<Person> list) {
		this.context = context;
		this.list = list;
		this.mInflater=LayoutInflater.from(context);
		options=new ImageOptions.Builder().setLoadingDrawableId(R.drawable.ic_launcher)
				.setFailureDrawableId(R.drawable.ic_launcher).setUseMemCache(true).setCircular(true).build();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		holder=null;
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.itemone, null);
			holder=new ViewHolder();
			x.view().inject(holder,convertView);
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder) convertView.getTag();
		}
		Person bean=list.get(position);
		holder.tv_name.setText(bean.getName());
		x.image().bind(holder.iv_image, bean.getImgUrl(), options);
		return convertView;
	}

	class ViewHolder{
		@ViewInject(R.id.tv_name)
		private TextView tv_name;
		@ViewInject(R.id.iv_image)
		private ImageView iv_image;
	}
}
