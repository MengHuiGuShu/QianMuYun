package Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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

import com.example.qianmuyun.MyFragment;
import com.example.qianmuyun.R;

public class ChangeTableFragment extends Fragment {

    private Activity activity;
    private View view;
    private EditText editText;
    private String vaule;
    private IOoperating communication;
    private int col;
    private int row;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (Activity) context;
        try {
            communication = (ChangeTableFragment.IOoperating) context;
        }catch (ClassCastException e){
            e.printStackTrace();
            throw new ClassCastException("Activity 必须实现 IOoprating 接口");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.changetablefragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.TableIn);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //点击确定的时候隐藏软键盘
                    hideKeyboard(editText);
                    vaule = editText.getText().toString().trim();
                    communication.onClick(vaule,col,row);
                    editText.setText("");
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

    public void initInputType(String type, int col, int row) {
        try {
            this.col = col;
            this.row = row;
        }catch (Exception e){
            System.out.println("1");
        }
        try {
            switch (type) {
                case "时间":
                    editText.setInputType(InputType.TYPE_CLASS_DATETIME);
                    break;
                case "是否":
                    InputFilter inputFilter = new InputFilter() {
                        @Override
                        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                            if (dest.length() <= 1) {
                                if (dest.charAt(end) == '是') {
                                    return "是";
                                } else if (dest.charAt(end) == '否') {
                                    return "否";
                                }
                            }
                            return "";
                        }
                    };
                    editText.setFilters(new InputFilter[]{inputFilter});
                    break;
                case "xxxx":                        //TODO:待补充，输入类型
                    break;
            }
        }catch (Exception e){
            System.out.println("2");
        }

    }

    public String returnTable(){
        return vaule;
    }

    public interface IOoperating{
        void onClick(String context, int col, int row);
    }
}
