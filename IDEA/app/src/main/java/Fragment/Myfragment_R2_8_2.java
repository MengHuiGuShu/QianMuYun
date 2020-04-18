package Fragment;

import DataUtils.Data_8;
import DataUtils.Data_81;
import DataUtils.SelectMessage;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.qianmuyun.R;

public class Myfragment_R2_8_2 extends Fragment {

    private RadioGroup radioGroup;
    private EditText et_jinliao;
    private EditText et_liao_day;
    private EditText et_liao_all;


    public static Myfragment_R2_8_2 newInstance(SelectMessage selectMessage){
        Myfragment_R2_8_2 myfragment_r2_8_2 = new Myfragment_R2_8_2();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Message",selectMessage);
        myfragment_r2_8_2.setArguments(bundle);
        return myfragment_r2_8_2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.myfragment_8_radio2,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        radioGroup = view.findViewById(R.id.R2_8_fragment_radioGroup);
        et_jinliao = view.findViewById(R.id.R2_8_jiliang);
        et_liao_all = view.findViewById(R.id.R2_8_touweiKG);
        et_liao_day = view.findViewById(R.id.R2_8_touweiDAY);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void Enter(IOoperating iOoperating){
        iOoperating.onClick_2(message());
    }

    public interface IOoperating{
        void onClick_2(Data_81 context);
    }

    public Data_81 message(){
        Data_81 data = new Data_81();
        return data;
    }
}
