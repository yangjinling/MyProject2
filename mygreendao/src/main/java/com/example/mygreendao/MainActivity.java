package com.example.mygreendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import com.user.dao.DaoMaster;
import com.user.dao.DaoSession;
import com.user.dao.user;
import com.user.dao.userDao;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

public class MainActivity extends AppCompatActivity {

    private DaoMaster.DevOpenHelper mhelper;
    private SQLiteDatabase mdb;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private userDao mUserDao;
    private EditText editText;
    private Cursor mCursor;
    private List mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mhelper = new DaoMaster.DevOpenHelper(this, "user-db", null);
        mdb = mhelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mdb);
        mDaoSession = mDaoMaster.newSession();
        mUserDao = mDaoSession.getUserDao();
        String textColumn = userDao.Properties.Name.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";

        mCursor = mdb.query(mUserDao.getTablename(), mUserDao.getAllColumns(), null, null, null, null, orderBy);
        String[] from = {textColumn, userDao.Properties.Sex.columnName};
        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor, from,
                to);
//        setListAdapter(adapter);

        editText = (EditText) findViewById(R.id.editTextNote);

    }

    public void onMyButtonClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:
                addUser();
                break;
            case R.id.buttonSearch:
                search();
                break;
            default:
                Log.d("YJL", "what has gone wrong ?");
                break;
        }
    }

    public void addUser() {
        String noteText = editText.getText().toString();
        editText.setText("123");
        user user = new user(null,noteText,"12","西董",1);
        mUserDao.insert(user);
        mCursor.requery();
    }

    public void  search(){
        Query query = mUserDao.queryBuilder()
                .where(userDao.Properties.Name.eq("123"))
                .orderAsc(userDao.Properties.Age)
                .build();
        mList = query.list();
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_VALUES=true;
    }

}
