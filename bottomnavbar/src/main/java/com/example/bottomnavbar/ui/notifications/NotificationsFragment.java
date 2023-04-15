package com.example.bottomnavbar.ui.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavbar.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private IntentFilter intentFilter;
    private BatteryReceiver batteryReceiver;

    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button checkStatus = binding.buttonCheck;
        textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batteryReceiver = new BatteryReceiver();
                getActivity().registerReceiver(batteryReceiver, intentFilter);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (batteryReceiver != null) {
            getActivity().unregisterReceiver(batteryReceiver);
        }
        binding = null;
        textView = null;
    }

    private class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_COLD) {
                textView.setText("Battery Health = cold");
            } else if (deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD) {
                textView.setText("Battery Health = dead");
            } else if (deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD) {
                textView.setText("Battery Health = good");
            } else if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
                textView.setText("Battery Health = Overheat");
            } else if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
                textView.setText("Battery Health = Overvoltage");
            } else if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN) {
                textView.setText("Battery Health = Unknown");
            } else if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
                textView.setText("Battery Health = Unspecified Failure");
            }
        }
    }
}
