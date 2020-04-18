package Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qianmuyun.R;

public class MF_num_zxing extends Fragment {

    private View view;
    private EditText editText;
    private View zxing;
    private Activity activity;
    private IOoperating communication;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        try {
            communication = (IOoperating) context;
        }catch (ClassCastException e){
            e.printStackTrace();
            throw new ClassCastException("Activity 必须实现 IOoprating 接口");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.num_zxing,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initActivity();
    }

    private void initActivity() {
        zxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communication.onClick();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    hideKeyboard(editText);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    //此处为连接数据库，进行数据编号查询
                    String Number = editText.getText().toString().trim();
                    editText.setText("");
                    communication.onClick(Number);
                }
                return false;
            }
        });
    }

    /**
     * 隐藏软键盘
     *  context :上下文
     * @param view    :一般为EditText
     */
    private void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert manager != null;
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        editText = view.findViewById(R.id.editText_main);
        zxing = view.findViewById(R.id.zxing_main);
    }

    public interface IOoperating{
        void onClick();
        void onClick(String context);
    }
}
