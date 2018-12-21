package com.uiautomatorexample;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

//@RunWith(AndroidJUnit4.class)
//@SdkSuppress(minSdkVersion = 18)

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.uiautomatorexample", appContext.getPackageName());
//    }

    //기본 setting
//    @Before
//    public void testcase_initalize_1 () {
//
//    }
//    // @Before
//    public void testcase_initalize_2(){
//
//    }
//    @Test
//    public void testcase_app_run(){
//
//    }
//    //    @Test
//    public void testcase_app_close(){
//
//    }
//
    private UiDevice mDevice;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final int UI_TIMEOUT = 2000;

    @Before
    public void initialize() throws Exception{

        //디바이스(스마트폰) 객체 가져오기
        mDevice = UiDevice.getInstance(getInstrumentation());
        assertThat(mDevice, notNullValue());

        //슬립모드 상태에 있는 디바이스 화면 깨우기
        if (!mDevice.isScreenOn()){
            mDevice.wakeUp();
        }

        //홈 스크린으로 나가기
        mDevice.pressHome();
    }

    @Test
    public void testcase () throws Exception{

        //네이버 지도 앱 실행
        {
            // 앱 실행 메소드(함수)에 'Package Name'을 파라미터로 전달
            openApp("com.nhn.android.nmap");
        }

//        //네이버 지도 앱 메인 화면의 메뉴 버튼 클릭
//        {
//            ///버튼 객체를 가져오는 메소드(함수)에 Byselector 의 resource-id 를 파라미터로 전달
//            UiObject2 gnb_menu_btn = waitForObject(By.res("com.nhn.android.nmap:id/btn_drawer"));
//
//            //버튼 클릭이 동장학때까지 2초간 대기(timeout 이후 테스트 실패)
//            boolean clickAndWaitForResult =  gnb_menu_btn.clickAndWait(Until.newWindow(), UI_TIMEOUT);
//        }
//    }

        //네이버 지도 앱 상단 '검색 필드' 클릭
        {
            // '검색 필드' 객체를 가져오는 메소드(함수)에 BySelector 의 'resource-id'를 파라미터로 전달
            UiObject2 search_textview = waitForObject(By.res("com.nhn.android.nmap:id/search_textview"));

            // '검색 필드' 클릭이 동작할 때 까지 2초간 대기(TIMEOUT 이후 테스트 실패)
            boolean clickAndWaitForResult = search_textview.clickAndWait(Until.newWindow(),UI_TIMEOUT);
        }

    // 'Package Name'을 파라미터로 입력 받아 테스트 대상 앱을 실행하는 메소드(함수)
    private void openApp(String getPackageName){

        //안드로이드 런처가 구동될 때 까지 5초간 대기(Timeout 이후 실패)
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        //앱 구동하기
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(getPackageName);

        //이전에 호출된 인스턴스 초기화하기
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        //앱이 구동될 때 까지 5초간 대기(Timeout 이후 테스트 실패)
        mDevice.wait(Until.hasObject(By.pkg(getPackageName).depth(0)), LAUNCH_TIMEOUT);
    }

    // BySelector 을 파라미터로 입력 받아 UI Component 객체로 리턴하는 메소드(함수)
    private UiObject2 waitForObject(BySelector getBySelector) throws InterruptedException{

        //UI Component가 나타날 때 까지 2초간 대기(timeout 이후 실패)
        boolean waitForResult = mDevice.wait(Until.hasObject(getBySelector), UI_TIMEOUT);
        assertThat(waitForResult, is(true));

        //getBySelector에 해당되는 UI Component 호출하여 검색 조건이 일치하는 경우에 Ui Componet 객체 반환

//        UiObject2 uiObject = mDevice.findObject(getBySelector);
//
//        return uiObject;
          return mDevice.findObject(getBySelector);

    }

}
