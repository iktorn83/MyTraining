package pl.w_kowalczyk.mytraining.ui.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.ExecutionException;

import pl.w_kowalczyk.mytraining.R;
import pl.w_kowalczyk.mytraining.ui.application.fragments.activities.TabActivitiesFragment;
import pl.w_kowalczyk.mytraining.ui.application.fragments.analise.AnaliseFragment;
import pl.w_kowalczyk.mytraining.ui.application.fragments.calculator.CalculatorFragment;
import pl.w_kowalczyk.mytraining.ui.application.fragments.main.MainFragment;
import pl.w_kowalczyk.mytraining.ui.application.fragments.profile.ProfileFragment;
import pl.w_kowalczyk.mytraining.ui.application.fragments.user.UserFragment;
import pl.w_kowalczyk.mytraining.ui.application.presenter.ApplicationPresenter;
import pl.w_kowalczyk.mytraining.ui.application.presenter.ApplicationPresenterCompl;
import pl.w_kowalczyk.mytraining.ui.application.view.ApplicationView;
import pl.w_kowalczyk.mytraining.ui.base.BaseActivity;
import pl.w_kowalczyk.mytraining.ui.login.LoginActivity;
import pl.w_kowalczyk.mytraining.util.other.Downloader;

public class MainActivity extends BaseActivity implements ApplicationView {

    private ApplicationPresenter applicationPresenter;
    private Downloader task;
    private View headerView;


    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ImageButton userImage;
    private TextView username;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
        findViews();
        setToolbar();
        initDrawer();
        initHeader();
        setListeners();
        openMainFragment();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {

        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = MainFragment.class;
                setIcon(R.drawable.ic_apps_black_24dp);
                break;
            case R.id.nav_second_fragment:
                fragmentClass = ProfileFragment.class;
                setIcon(R.drawable.ic_portrait_black_24dp);
                break;
            case R.id.nav_third_fragment:
                fragmentClass = CalculatorFragment.class;
                setIcon(R.drawable.ic_settings_applications_black_24dp);
                break;
            case R.id.nav_fourth_fragment:
                fragmentClass = UserFragment.class;
                setIcon(R.drawable.ic_mode_edit_black_24dp);
                break;
            case R.id.nav_fifth_fragment:
                fragmentClass = AnaliseFragment.class;
                setIcon(R.drawable.ic_multiline_chart_black_24dp);
                break;
            case R.id.nav_sixth_fragment:
                fragmentClass = TabActivitiesFragment.class;
                setIcon(R.drawable.ic_fitness_center_black_24dp);
                break;
            case R.id.logout:
                openLoginScreen();

            default:
                fragmentClass = MainFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    private void setIcon(int drawableIcon) {
        getSupportActionBar().setHomeAsUpIndicator(drawableIcon);
    }

    @Override
    public void openLoginScreen() {
      //  applicationPresenter.onLogoutClicked();
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void initPresenter() {
        this.applicationPresenter = new ApplicationPresenterCompl(this);
    }

    private void openMainFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new MainFragment()).commit();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_apps_black_24dp);
    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
    }

    private void initHeader() {
        headerView = nvDrawer.getHeaderView(0);
        username = headerView.findViewById(R.id.header_username);
        userImage = headerView.findViewById(R.id.header_userImage);
        username.setText(applicationPresenter.onUserLogged());
        showAvatar();

    }

    public void showAvatar() {
        if (applicationPresenter.isAvatarUploaded()) {
            task = new Downloader(applicationPresenter.getAvatarSrcFromFirebase().toString());
            try {
                userImage.setImageBitmap(task.execute().get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void initDrawer() {
        drawerToggle = setupDrawerToggle();
        setupDrawerContent(nvDrawer);
    }

    private void setListeners() {
        mDrawer.addDrawerListener(drawerToggle);
        this.userImage.setOnClickListener(v -> pickImage());

    }

    public void pickImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == 1) {
                Uri selectedImage = data.getData();
                if (applicationPresenter.uploadImage(selectedImage)) {
                    showAvatar();
                    showMessage("Zaloguj się ponownie, aby zobaczyć zmiany!", Toast.LENGTH_SHORT);
                } else {
                    showMessage("Nie udalo sie wyslac pliku", Toast.LENGTH_SHORT);
                }

            }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Zamykanie programu")
                .setMessage("Na pewno chcesz wyjść z programu?")
                .setPositiveButton("Tak", (dialog, which) -> finish())
                .setNegativeButton("Nie", null)
                .show();
    }
}
