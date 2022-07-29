package com.alap.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


//GestureDetectorCompat 单指点击手势
//GestureDetector的工作原理是，当我们接收到用户触摸消息时，将这个消息交给GestureDetector去加工，
// 我们通过设置侦听器获得GestureDetector处理后的手势。

//GestureDetector提供了两个侦听器接口，OnGestureListener处理单击类消息，OnDoubleTapListener处理双击类消息。
//
//OnGestureListener的接口有这几个：
//
//// 单击，触摸屏按下时立刻触发
//
//abstract boolean onDown(MotionEvent e);
//
//// 抬起，手指离开触摸屏时触发(长按、滚动、滑动时，不会触发这个手势)
//
//abstract boolean onSingleTapUp(MotionEvent e);
//
//// 短按，触摸屏按下后片刻后抬起，会触发这个手势，如果迅速抬起则不会
//
//abstract void onShowPress(MotionEvent e);
//
//// 长按，触摸屏按下后既不抬起也不移动，过一段时间后触发
//
//abstract void onLongPress(MotionEvent e);
//
//// 滚动，触摸屏按下后移动
//
//abstract boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
//
//// 滑动，触摸屏按下后快速移动并抬起，会先触发滚动手势，跟着触发一个滑动手势
//
//abstract boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
//
//OnDoubleTapListener的接口有这几个：
//
//// 双击，手指在触摸屏上迅速点击第二下时触发
//
//abstract boolean onDoubleTap(MotionEvent e);
//
//// 双击的按下跟抬起各触发一次
//
//abstract boolean onDoubleTapEvent(MotionEvent e);
//
//// 单击确认，即很快的按下并抬起，但并不连续点击第二下
//
//abstract boolean onSingleTapConfirmed(MotionEvent e);
//
//有时候我们并不需要处理上面所有手势，方便起见，Android提供了另外一个类SimpleOnGestureListener实现了如上接口，我们只需要继承SimpleOnGestureListener然后重载感兴趣的手势即可。
//
//ScaleGestureDetector 双指点击手势
//
////缩放时。返回值代表本次缩放事件是否已被处理。如果已被处理，那么detector就会重置缩
//
////放事件；如果未被处理，detector会继续进行计算，修改getScaleFactor()的返回值，直到被
//
////处理为止。因此，它常用在判断只有缩放值达到一定数值时才进行缩放。
//
//public boolean onScale(ScaleGestureDetector detector)；
//
//// 缩放开始。该detector是否处理后继的缩放事件。返回false时，不会执行onScale()。
//
//public boolean onScaleBegin(ScaleGestureDetector detector);
//
////缩放结束时
//
//public void onScaleEnd(ScaleGestureDetector detector);
//
//用法
//
//@Override
//
//public boolean onTouchEvent(MotionEvent event) {
//
//switch (event.getAction() & MotionEvent.ACTION_MASK) {
//
//case MotionEvent.ACTION_DOWN:
//
//break;
//
//case MotionEvent.ACTION_MOVE:
//
//break;
//
//case MotionEvent.ACTION_UP:
//
//break;
//
//case MotionEvent.ACTION_CANCEL:
//
//break;
//
//}
//
//this.mDetector.onTouchEvent(event);
//
//this.mScaleDetector.onTouchEvent(event);
//
//return true;
//
//}