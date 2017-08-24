package com.wiggins.mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.wiggins.mvvm.BR;
import com.wiggins.mvvm.R;
import com.wiggins.mvvm.adapter.MyBaseAdapter;
import com.wiggins.mvvm.base.BaseActivity;
import com.wiggins.mvvm.bean.User;
import com.wiggins.mvvm.databinding.ActivityUsersBinding;
import com.wiggins.mvvm.utils.UIUtils;
import com.wiggins.mvvm.widget.TitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用户信息
 * @Author 一花一世界
 */
public class UsersActivity extends BaseActivity {

    private UsersActivity mActivity;
    private TitleView titleView;
    private ListView mLvUsers;
    private List<User> users;
    private boolean isUpdate = false;
    private MyBaseAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUsersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        binding.setUsers(this);
        mActivity = this;

        initView();
        initData();
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.user_data));
        titleView.setLeftImgOnClickListener();
        mLvUsers = (ListView) findViewById(R.id.lv_users);
    }

    private void initData() {
        if (users == null) {
            users = new ArrayList<>();
        }
        for (int i = 0; i < 30; i++) {
            users.add(new User("小明", "一花一世界", "http://pic.sc.chinaz.com/files/pic/pic9/201412/apic8065.jpg"));
        }
        if (adapter == null) {
            adapter = new MyBaseAdapter<>(mActivity, R.layout.item_users, BR.user, users);
            mLvUsers.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                users.clear();
                for (int i = 0; i < 30; i++) {
                    if (!isUpdate) {
                        users.add(new User("小红", "我爱西红柿", "http://pic.sc.chinaz.com/files/pic/pic9/201505/apic11963.jpg"));
                    } else {
                        users.add(new User("小明", "一花一世界", "http://pic.sc.chinaz.com/files/pic/pic9/201412/apic8065.jpg"));
                    }
                }
                if (!isUpdate) {
                    isUpdate = true;
                } else {
                    isUpdate = false;
                }
                adapter.setData(users);
                break;
            case R.id.btn_resource:
                Intent intent = new Intent(mActivity, ResourceActivity.class);
                startActivity(intent);
                break;
        }
    }
}