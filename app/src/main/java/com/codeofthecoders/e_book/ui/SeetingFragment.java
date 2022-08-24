package com.codeofthecoders.e_book.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.codeofthecoders.e_book.AboutusActivity;
import com.codeofthecoders.e_book.BuildConfig;
import com.codeofthecoders.e_book.LoginActivity;
import com.codeofthecoders.e_book.MainDashBoard;
import com.codeofthecoders.e_book.ProfileActivity;
import com.codeofthecoders.e_book.PurchaseBookActivity;
import com.codeofthecoders.e_book.R;


public class SeetingFragment extends Fragment {
    SwitchCompat switch_push, switch_theme;
    TextView txt_share_app,txt_my_download_book,txt_about_us,txt_profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            //switch_theme.setChecked(true);
            getActivity().setTheme(R.style.darktheme);
        } else {
            getActivity().setTheme(R.style.AppTheme);
        }


        View root = inflater.inflate(R.layout.fragment_seeting, container, false);
        switch_theme = (SwitchCompat) root.findViewById(R.id.switch_theme);
        txt_share_app = root.findViewById(R.id.txt_share_app);
        txt_my_download_book = root.findViewById(R.id.txt_my_download_book);
        txt_about_us = root.findViewById(R.id.txt_about_us);
        txt_profile = root.findViewById(R.id.txt_profile);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switch_theme.setChecked(true);
        }
        switch_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // prefManager.setIsNightModeEnabled(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Intent intent = new Intent(getActivity(), MainDashBoard.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    //prefManager.setIsNightModeEnabled(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Intent intent = new Intent(getActivity(), MainDashBoard.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

        txt_share_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });
        txt_my_download_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PurchaseBookActivity.class));
            }
        });
        txt_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AboutusActivity.class));
            }
        });
        txt_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });

        return root;
    }

}