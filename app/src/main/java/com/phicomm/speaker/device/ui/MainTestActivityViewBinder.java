//package com.phicomm.speaker.device.ui;
//
//import android.view.View;
//import android.widget.Button;
//import butterknife.ButterKnife;
//import butterknife.internal.DebouncingOnClickListener;
//import com.phicomm.speaker.device.R;
//
//public class MainTestActivityViewBinder<T extends MainTestActivity> implements ButterKnife.ViewBinder<T> {
//    public void bind(ButterKnife.Finder finder, final T target, Object source) {
//        View view = (View) finder.findRequiredView(source, R.id.bt_alter_mode, "field 'btAlterMode' and method 'onClick'");
//        target.btAlterMode = (Button) finder.castView(view, R.id.bt_alter_mode, "field 'btAlterMode'");
//        view.setOnClickListener(new DebouncingOnClickListener() {
//            /* class com.phicomm.speaker.device.ui.MainTestActivity$$ViewBinder.AnonymousClass1 */
//
//            @Override // butterknife.internal.DebouncingOnClickListener
//            public void doClick(View p0) {
//                target.onClick(p0);
//            }
//        });
//        View view2 = (View) finder.findRequiredView(source, R.id.bt_collect_music, "field 'btCollectMusic' and method 'onClick'");
//        target.btCollectMusic = (Button) finder.castView(view2, R.id.bt_collect_music, "field 'btCollectMusic'");
//        view2.setOnClickListener(new DebouncingOnClickListener() {
//            /* class com.phicomm.speaker.device.ui.MainTestActivity$$ViewBinder.AnonymousClass2 */
//
//            @Override // butterknife.internal.DebouncingOnClickListener
//            public void doClick(View p0) {
//                target.onClick(p0);
//            }
//        });
//        View view3 = (View) finder.findRequiredView(source, R.id.bt_enter_asr, "field 'btEnterAsr' and method 'onClick'");
//        target.btEnterAsr = (Button) finder.castView(view3, R.id.bt_enter_asr, "field 'btEnterAsr'");
//        view3.setOnClickListener(new DebouncingOnClickListener() {
//            /* class com.phicomm.speaker.device.ui.MainTestActivity$$ViewBinder.AnonymousClass3 */
//
//            @Override // butterknife.internal.DebouncingOnClickListener
//            public void doClick(View p0) {
//                target.onClick(p0);
//            }
//        });
//        View view4 = (View) finder.findRequiredView(source, R.id.bt_night_mode, "field 'btNightMode' and method 'onClick'");
//        target.btNightMode = (Button) finder.castView(view4, R.id.bt_night_mode, "field 'btNightMode'");
//        view4.setOnClickListener(new DebouncingOnClickListener() {
//            /* class com.phicomm.speaker.device.ui.MainTestActivity$$ViewBinder.AnonymousClass4 */
//
//            @Override // butterknife.internal.DebouncingOnClickListener
//            public void doClick(View p0) {
//                target.onClick(p0);
//            }
//        });
//        View view5 = (View) finder.findRequiredView(source, R.id.bt_get_unread, "field 'btGetUnread' and method 'onClick'");
//        target.btGetUnread = (Button) finder.castView(view5, R.id.bt_get_unread, "field 'btGetUnread'");
//        view5.setOnClickListener(new DebouncingOnClickListener() {
//            /* class com.phicomm.speaker.device.ui.MainTestActivity$$ViewBinder.AnonymousClass5 */
//
//            @Override // butterknife.internal.DebouncingOnClickListener
//            public void doClick(View p0) {
//                target.onClick(p0);
//            }
//        });
//        View view6 = (View) finder.findRequiredView(source, R.id.bt_history, "field 'btHistory' and method 'onClick'");
//        target.btHistory = (Button) finder.castView(view6, R.id.bt_history, "field 'btHistory'");
//        view6.setOnClickListener(new DebouncingOnClickListener() {
//            /* class com.phicomm.speaker.device.ui.MainTestActivity$$ViewBinder.AnonymousClass6 */
//
//            @Override // butterknife.internal.DebouncingOnClickListener
//            public void doClick(View p0) {
//                target.onClick(p0);
//            }
//        });
//    }
//
//    public void unbind(T target) {
//        target.btAlterMode = null;
//        target.btCollectMusic = null;
//        target.btEnterAsr = null;
//        target.btNightMode = null;
//        target.btGetUnread = null;
//        target.btHistory = null;
//    }
//}
