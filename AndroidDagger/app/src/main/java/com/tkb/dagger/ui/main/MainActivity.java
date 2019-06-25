package com.tkb.dagger.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.tkb.dagger.BaseActivity;
import com.tkb.dagger.R;
import com.tkb.dagger.ui.main.post.PostsFragment;
import com.tkb.dagger.ui.main.profile.ProfileFragment;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"Successfully logged in",Toast.LENGTH_LONG).show();
        openFragment();
    }

    void openFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new PostsFragment()).commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout :{
                sessionManager.logout();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
