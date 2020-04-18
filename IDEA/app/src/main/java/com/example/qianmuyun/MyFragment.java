package com.example.qianmuyun;

import DataUtils.DataPlusOrDel;
import DataUtils.FlowRadioGroup;
import DataUtils.SelectMessage;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MyFragment extends Fragment {
    private Activity activity;
    private FlowRadioGroup flowRadioGroup;
    private TextView textView;
    private static SelectMessage message;
    private IOoperating communication;
    private PopupWindow popupWindow;
    private PopupWindow popupWindows;
    private PopupWindow popupWindowss;
    private View contentView;
    private View popView;
    private View PopViews;
    private View view;
    private RadioButton radioButton;
    private LinearLayout linearLayout;
    private Button button_del;
    private Button button_no;
    private Button button_add;
    private Button button_No;
    private Button button_Yes;
    private Button buttonYes;
    private Button buttonNo;
    private EditText pop_edit;
    private int viewID = 0;             //控制view的增加和删除
    private int checkID = 0;            //控制view的click事件
    private int id = 0;
    private final int ID = 1001;
    private DataPlusOrDel dataPlusOrDel;

    public static MyFragment newInstance(SelectMessage selectMessage){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Message",selectMessage);
        myFragment.setArguments(bundle);
        return myFragment;
    }

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
        view = inflater.inflate(R.layout.myfragment_button,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        flowRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {
                if(checkedId == 1001){
                    popOnClick();
                }else {
                    switch (message.getTitle()) {
                        case "阶段：":
                            communication.onClick(message.getList().get(checkedId), null, null, 1);
                            break;
                        case "保健药：":
                            communication.onClick(message.getList().get(checkedId), null, null, 2);
                            break;
                        case "  消毒药":
                            try {
                                communication.onClick(message.getList().get(checkedId), message.getListdose().get(checkedId), message.getListmethon().get(checkedId),0);
                            }catch (Exception e){
                                communication.onClick(message.getList().get(checkedId),null,null,0);
                            }
                            break;
                        case "  疫苗":
                            try {
                                communication.onClick(message.getList().get(checkedId), message.getListdose().get(checkedId), message.getListmethon().get(checkedId),null);
                            }catch (Exception e){
                                communication.onClick(message.getList().get(checkedId),null,null,null);
                            }
                            break;
                    }
                }
                System.out.println(checkedId);
            }
        });
    }



    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.fragment_Text);
        dataPlusOrDel = new DataPlusOrDel();
        radioButton = new RadioButton(activity);
        if(getActivity() != null){
            if(getArguments() != null){
                final SelectMessage selectMessage = (SelectMessage) getArguments().getSerializable("Message");
                if(selectMessage != null){
                    textView.setText(selectMessage.getTitle());
                    flowRadioGroup = view.findViewById(R.id.radioGroup);
                    addView(activity,selectMessage);
                    initPopWindow();
                    initPopClick();
                }
            }
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消异步
    }

    private void addView(Context context,SelectMessage selectMessage){

        /*加载动态布局*/
        message = selectMessage;
        for(viewID = 0,id = 0;viewID<message.getN();viewID += 1,id += 1){
            RadioButton button = new RadioButton(context);
            button.setId(viewID);
            button.setTextColor(0xff000000);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            button.setText(selectMessage.getList().get(viewID));
            flowRadioGroup.addView(button);
        }
        addButton();
    }


    public void popOnClick() {
        if (popupWindow.isShowing()) {
            // 隐藏窗口，如果设置了点击窗口外消失，则不需要此方式隐藏
            popupWindow.dismiss();
        }else {
            popupWindow.showAsDropDown(view);
            radioButton.setChecked(false);
            button_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    popupWindows.showAsDropDown(view);
                    //TODO:此处添加pop后台逻辑  增加
                    button_Yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(pop_edit.getText().toString().trim().isEmpty()){
                                Toast.makeText(activity,"请输入需要增加的选项",Toast.LENGTH_SHORT).show();
                            }else if(!pop_edit.getText().toString().trim().equals("null")){
                                boolean flag = true;
                                for(int pip = 0;pip<message.getN();pip+=1){
                                    if(message.getList().get(pip).equals(pop_edit.getText().toString().trim())){
                                        Toast.makeText(activity,"此选项已经存在",Toast.LENGTH_SHORT).show();
                                        flag = false;
                                        break;
                                    }
                                }
                                if(flag){
                                    System.out.println(dataPlusOrDel.getDelNum());
                                    System.out.println(dataPlusOrDel.getPlusNum());
                                    System.out.println(message.getN());
                                    System.out.println(radioButton.getId());
                                    initButtonPlus();
                                    message.getAdd(pop_edit.getText().toString().trim());
                                    dataPlusOrDel.setListPlus(pop_edit.getText().toString().trim());
                                    popupWindows.dismiss();
                                }
                            }else {
                                Toast.makeText(activity,"添加的选项名称不能为null",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    button_No.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindows.dismiss();
                        }
                    });
                }
            });
            button_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    popupWindowss.showAsDropDown(view);
                    for(viewID = 0;viewID<message.getN();viewID += 1){
                        if(!message.getList().get(viewID).equals("null")){
                            RadioButton button = new RadioButton(activity);
                            button.setId(viewID);
                            button.setTextColor(0xff000000);
                            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                            button.setText(message.getList().get(viewID));
                            linearLayout.addView(button);
                        }

                    }
                    //TODO:此处添加pop后台逻辑  删除
                    buttonYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for(viewID = 0,id = 0; viewID <message.getN(); viewID+= 1){
                                RadioButton button = PopViews.findViewById(viewID);
                                if((!message.getList().get(viewID).equals("null")&&button.isChecked())){
                                    dataPlusOrDel.setListDel(message.getList().get(viewID));
                                    message.updataList(viewID);
                                    id += 1;
                                }
                            }
                            flowRadioGroup.removeAllViews();
                            for(viewID = 0;viewID<message.getN();viewID += 1){
                                if(!message.getList().get(viewID).equals("null")){
                                    RadioButton button = new RadioButton(activity);
                                    button.setTextColor(0xff000000);
                                    button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                                    button.setId(viewID);
                                    button.setText(message.getList().get(viewID));
                                    flowRadioGroup.addView(button);
                                }
                            }
                            addButton();
                            linearLayout.removeAllViews();
                            popupWindowss.dismiss();
                        }
                    });
                    buttonNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindowss.dismiss();
                        }
                    });
                }
            });
            button_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
    }

    public interface IOoperating{
        void onClick(String context,String mothod,String dose, int style);
        void onClick(String context,String mothod,String dose, String time);
    }


    //pop创建
    private void initPopWindow() {
        contentView = View.inflate(activity, R.layout.pop, null);
        button_add = contentView.findViewById(R.id.tv_add);
        button_del = contentView.findViewById(R.id.tv_delete);
        button_no = contentView.findViewById(R.id.tv_no);
        popupWindow = new PopupWindow(contentView,//弹出界面的引用
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT,//界面的宽高属性
                true);//是否可点击
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
    }

    //pop创建
    private void initPopClick() {
        popView = View.inflate(activity, R.layout.popplus, null);
        PopViews = View.inflate(activity, R.layout.popdelbutton, null);
        button_Yes = popView.findViewById(R.id.pop_Yes);
        button_No = popView.findViewById(R.id.pop_No);
        buttonYes = PopViews.findViewById(R.id.pop_yes);
        buttonNo = PopViews.findViewById(R.id.pop_no);
        pop_edit = popView.findViewById(R.id.pop_edit);
        linearLayout = PopViews.findViewById(R.id.pop_delete);
        popupWindows = new PopupWindow(popView,//弹出界面的引用
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT,//界面的宽高属性
                true);//是否可点击
        popupWindows.setTouchable(true);
        popupWindows.setOutsideTouchable(true);
        popupWindowss = new PopupWindow(PopViews,//弹出界面的引用
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT,//界面的宽高属性
                true);//是否可点击
        popupWindowss.setTouchable(true);
        popupWindowss.setOutsideTouchable(true);
    }

    public void initButtonPlus(){
//        flowRadioGroup.removeViewAt(id);
        flowRadioGroup.removeView(radioButton);
        RadioButton button = new RadioButton(activity);
        button.setId(message.getN());
        button.setTextColor(0xff000000);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        button.setText(pop_edit.getText().toString().trim());
        flowRadioGroup.addView(button);
        addButton();
    }


    public void addButton(){
        radioButton.setId(ID);
        radioButton.setTextColor(0xff000000);
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        radioButton.setText("添加/删除");
        flowRadioGroup.addView(radioButton);
    }

}
