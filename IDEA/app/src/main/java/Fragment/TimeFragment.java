package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qianmuyun.R;


import ToolsUtils.TimeTools;

public class TimeFragment extends Fragment {

    private View view;
    private String timeRecorde;

    public static TimeFragment newInstance(String Title){
        TimeFragment timeFragment = new TimeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Title",Title);
        timeFragment.setArguments(bundle);
        return timeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView timeView = view.findViewById(R.id.time);
        String title;
        if (getArguments() != null) {
            title = (String) getArguments().getSerializable("Title");
            String[] time = TimeTools.getTime().split("-");
            timeRecorde = time[0] + "年" + time[1] + "月" + time[2] + "日";
            String timeText = title + "：" + timeRecorde;
            timeView.setText(timeText);
        }
    }

    public String getTimeRecorde() {
        return timeRecorde;
    }
}
