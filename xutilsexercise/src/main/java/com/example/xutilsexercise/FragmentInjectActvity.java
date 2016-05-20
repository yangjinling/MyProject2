package com.example.xutilsexercise;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.example.xutilsexercise.fragment.FirstFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_fragment_inject_actvity)
public class FragmentInjectActvity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		//获取碎片管理者
		FragmentManager manager=getSupportFragmentManager();
		//获取事务
		FragmentTransaction transaction=manager.beginTransaction();

		transaction.replace(R.id.container, new FirstFragment());
		//提交事务
		transaction.commit();
	}
}
