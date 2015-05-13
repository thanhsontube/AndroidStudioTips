package nt.son.androidstudiotips.PositionView;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Sonnt on 5/11/15.
 */
public class TsPopupWindow {
    PopupWindow popupWindow;
    View anchor;
    WindowManager windowManager;
    View rootView;
    int xoff;
    int yoff;
    int gravity = Gravity.NO_GRAVITY;

    public void showAsDropDown (){
        popupWindow.showAsDropDown(anchor,xoff, yoff);
    }

    public void showAtLocation() {
        popupWindow.showAtLocation(anchor, gravity, xoff, yoff);
    }
    public TsPopupWindow setAnchor(View anchor) {
        this.anchor = anchor;
        return this;
    }

    public TsPopupWindow setRootView(View view) {
        this.rootView = view;
        return this;
    }

    public TsPopupWindow setLocation (int x, int y) {
        this.xoff = x;
        this.yoff = y;
        return this;
    }

    public TsPopupWindow build() {
        popupWindow = new PopupWindow(anchor.getContext());
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(rootView);
        return this;

    }
}
