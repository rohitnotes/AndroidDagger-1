package com.tkb.dagger.ui.main;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.tkb.dagger.BaseActivity;
import com.tkb.dagger.R;
import com.tkb.dagger.ui.main.post.PostsFragment;
import com.tkb.dagger.ui.main.profile.ProfileFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();
    }
    // Setting up navigation controller
    private void init() {
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);
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
            /**
             * android.R.id.home means hamburger item id. following code will close the navigation item
             * and if it's not open, it will not consume any click and will return false.
             */
            case android.R.id.home:{
                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }else {
                    return false;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile :{
                // Following code will clear all the backstack if user click on the profile page
                NavOptions options = new NavOptions.Builder().setPopUpTo(R.id.main,true).build();
                Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.profileScreen,
                        null, options);
                break;
            }
            case R.id.nav_posts :{
                if (isValidDestination(R.id.postsScreen)){
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.postsScreen);
                }
                break;
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Following method add a backStack for navigation, during transection from one navigation item to another, if
     * user press the back button, then other option will open which is in the back stack.
     *
     * This method also open the navigation drawer after clicking the hamburger
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),drawerLayout);
    }

    /**
     * following method will prevent creating same navigation object if user is already on the page.
     * @param destination
     * @return boolean
     */
    private boolean isValidDestination(int destination){
      return destination != Navigation.findNavController(this,R.id.nav_host_fragment).getCurrentDestination().getId();
    }
}
