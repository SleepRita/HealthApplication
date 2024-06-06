package com.example.healthapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class AccountPatient extends AppCompatActivity  {
    // DrawerLayout - выдвижная панель
    //корневой контейнер интерфейса, позволяет расположить основные элементы приложения
    public DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;
    public boolean isPatient = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_patient);

        // Получаем ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.profile);
            ClientProfile f1 = new ClientProfile();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.conteiner,f1);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //ActionBarDrawerToggle, который добавит иконку меню для открытия и закрытия
        // Drawer и обеспечит анимацию иконки
        toggle = new ActionBarDrawerToggle(
                AccountPatient.this, drawer, R.string.drawer_open, R.string.drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView myNavigationView = findViewById(R.id.nav_view_patient);
        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.profileMenu) {
                    OnClick1(findViewById(R.id.profileMenu));
                    actionBar.setTitle(R.string.profile);
                    drawer.close();
                } else if (id == R.id.notificationsMenu) {
                    OnClick2(findViewById(R.id.notificationsMenu));
                    actionBar.setTitle(R.string.notifications);
                    drawer.close();
                } else if (id == R.id.guideMenu) {
                    OnClick3(findViewById(R.id.guideMenu));
                    actionBar.setTitle(R.string.guide);
                    drawer.close();
                } else if (id == R.id.monitoringMenu) {
                    OnClick4(findViewById(R.id.monitoringMenu));
                    actionBar.setTitle(R.string.monitoring);
                    drawer.close();
                } else if (id == R.id.supportMenu) {
                    OnClick5(findViewById(R.id.supportMenu));
                    actionBar.setTitle(R.string.support);
                    drawer.close();
                }
                return false;
            }
        });
    }


    // Обработка нажатия на иконку меню в ActionBar для открытия и закрытия Drawer
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnClick1(View view)
    {
        ClientProfile f1 = new ClientProfile();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner,f1);
        ft.commit();
    }

    public void OnClick2(View view)
    {
        ClientNotifications f2 = new ClientNotifications();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner,f2);
        ft.commit();
    }

    public void OnClick3(View view)
    {
        DiseasesGuide f3 = new DiseasesGuide();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner,f3);
        ft.commit();
    }

    public void OnClick4(View view)
    {
        PatientMonitoring f4 = new PatientMonitoring();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner,f4);
        ft.commit();
    }

    public void OnClick5(View view)
    {
        ToSupport f5 = new ToSupport();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.conteiner,f5);
        ft.commit();
    }
}
