package Fragment;

import DataUtils.Data_8;
import DataUtils.SelectMessage;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.qianmuyun.R;

public class Myfragment_R2_8_1 extends Fragment{
    private EditText et_jin_avg;
    private EditText et_cu_avg;
    private EditText et_style;
    private EditText et_jin_weight;
    private EditText et_cu_weight;
    private TextView button_plus;
    private TextView button_sub;
    private TextView textViewNumber;
    private String number;
    private int num;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static Myfragment_R2_8_1 newInstance(SelectMessage selectMessage){
        Myfragment_R2_8_1 myfragment_r2_8_1 = new Myfragment_R2_8_1();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Message",selectMessage);
        myfragment_r2_8_1.setArguments(bundle);
        return myfragment_r2_8_1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.myfragment_8_radio1,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button_sub.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                number = textViewNumber.getText().toString().trim();
                num = Integer.valueOf(number).intValue();
                if(num < 100){
                    num += 1;
                }
                number = Integer.toString(num);
                textViewNumber.setText("  "+number);
            }
        });
        button_plus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                number = textViewNumber.getText().toString().trim();
                num = Integer.valueOf(number).intValue();
                if(num > 0){
                    num -= 1;
                }
                number = Integer.toString(num);
                textViewNumber.setText("  "+number);
            }
        });
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        textViewNumber.setText("  "+"0");

    }

    private void initView(View view) {
        button_plus = view.findViewById(R.id.R2_8_plus);
        button_sub = view.findViewById(R.id.R2_8_sub);
        et_cu_avg = view.findViewById(R.id.R2_8_cu_avg);
        et_cu_weight = view.findViewById(R.id.R2_8_cu_weight);
        et_jin_avg = view.findViewById(R.id.R2_8_jin_avg);
        et_jin_weight = view.findViewById(R.id.R2_8_jin_weight);
        et_style = view.findViewById(R.id.R2_8_siliao_style);
        textViewNumber = view.findViewById(R.id.R2_8_number);
    }

    public void Enter(IOoperating iOoperating){
        iOoperating.onClick_1(message());
    }

    public interface IOoperating{
        void onClick_1(Data_8 context);
    }

    public Data_8 message(){
        Data_8 data = new Data_8();
        data.setSL_all_cu(et_cu_weight.getText().toString().trim());
        data.setSL_all_jin(et_jin_weight.getText().toString().trim());
        data.setSL_cu(et_cu_avg.getText().toString().trim());
        data.setSL_jin(et_jin_avg.getText().toString().trim());
        data.setSL_OneNeedNum(textViewNumber.getText().toString().trim());
        data.setSL_style(et_style.getText().toString().trim());
        return data;
    }
}
