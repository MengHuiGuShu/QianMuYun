package ToolsUtils;

//回调函数
public class ThreadCallback {
    private boolean flag;
    private boolean sign;
    public void setFlag(){
        this.flag = false;
    }
    public void CallbackCarryOut(){
        this.flag = true;
    }
    public boolean getFlag(){
        return flag;
    }
    public void setSign(boolean sign){
        this.sign = sign;
    }
    public boolean getSign(){
        return this.sign;
    }
}
