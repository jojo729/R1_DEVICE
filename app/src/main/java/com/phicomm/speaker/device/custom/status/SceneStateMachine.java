package com.phicomm.speaker.device.custom.status;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;

public class SceneStateMachine extends StateMachine {
    private static final boolean DBG = true;
    private static long INTERMEDIATE_STATE_TIMEOUT_MILLIS = 2000;
    private static final int STATE_BLUETOOTH = 3;
    private static final int STATE_DOSS = 5;
    private static final int STATE_MUSIC = 1;
    private static final int STATE_NET_CONFIG = 2;
    private static final int STATE_READY = 0;
    private static final String TAG = "SceneStateMachine";
    private State mBluetoothState = new BluetoothState();
    private Context mContext;
    private int mCurrentScene;
    private State mDefaultState = new DefaultState();
    private State mDossState = new DossState();
    private State mEnteringBluetoothState = new EnteringBluetoothState();
    private State mEnteringDossState = new EnteringDossState();
    private State mEnteringMusicState = new EnteringMusicState();
    private State mEnteringNetConfigState = new EnteringNetConfigState();
    private State mExitingBluetoothState = new ExitingBluetoothState();
    private State mExitingDossState = new ExitingDossState();
    private State mExitingMusicState = new ExitingMusicState();
    private State mExitingNetConfigState = new ExitingNetConfigState();
    private State mMusicState = new MusicState();
    private State mNetConfigState = new NetConfigState();
    private State mReadyState = new ReadyState();
    private StatusChangedListener mStatusChangedListener;

    /* access modifiers changed from: package-private */
    public interface StatusChangedListener {
        void onStateChanged(int i);
    }

    public SceneStateMachine(Context context) {
        super(TAG);
        this.mContext = context;
        addState(this.mDefaultState, null);
        addState(this.mReadyState, this.mDefaultState);
        addState(this.mMusicState, this.mDefaultState);
        addState(this.mNetConfigState, this.mDefaultState);
        addState(this.mBluetoothState, this.mDefaultState);
        addState(this.mDossState, this.mDefaultState);
        addState(this.mEnteringDossState, this.mDefaultState);
        addState(this.mExitingDossState, this.mDefaultState);
        addState(this.mEnteringMusicState, this.mDefaultState);
        addState(this.mExitingMusicState, this.mDefaultState);
        addState(this.mEnteringNetConfigState, this.mDefaultState);
        addState(this.mExitingNetConfigState, this.mDefaultState);
        addState(this.mEnteringBluetoothState, this.mDefaultState);
        addState(this.mExitingBluetoothState, this.mDefaultState);
        setInitialState(this.mReadyState);
        setLogRecSize(3000);
        setLogOnlyTransitions(false);
    }

    class DefaultState extends State {
        DefaultState() {
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.e(SceneStateMachine.TAG, "unhandled msg=" + msg.what, new IllegalStateException());
            return true;
        }
    }

    class ReadyState extends SteadyState {
        ReadyState() {
            super();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State, com.phicomm.speaker.device.custom.status.SceneStateMachine.SteadyState
        public void enter() {
            SceneStateMachine.this.mCurrentScene = 0;
            super.enter();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "READY process " + msg.what + " msg.");
            switch (msg.what) {
                case 13:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringDossState).setRecoveryState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringDossState);
                    break;
                case 14:
                case 16:
                case 18:
                default:
                    return false;
                case 15:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringMusicState).setRecoveryState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringMusicState);
                    break;
                case 17:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringNetConfigState).setRecoveryState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                    break;
                case 19:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringBluetoothState).setRecoveryState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringBluetoothState);
                    break;
            }
            return true;
        }
    }

    class EnteringMusicState extends EnteringIntermediateState {
        EnteringMusicState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "EnteringMusicState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 103:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mMusicState);
                    return true;
                default:
                    return false;
            }
        }
    }

    class MusicState extends SteadyState {
        MusicState() {
            super();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State, com.phicomm.speaker.device.custom.status.SceneStateMachine.SteadyState
        public void enter() {
            SceneStateMachine.this.mCurrentScene = 1;
            super.enter();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "MusicState process " + msg.what + " msg.");
            switch (msg.what) {
                case 13:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingMusicState).setDestState(5);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingMusicState);
                    break;
                case 16:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingMusicState).setDestState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingMusicState);
                    break;
                case 17:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingMusicState).setDestState(2);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingMusicState);
                    break;
                case 19:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingMusicState).setDestState(3);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingMusicState);
                    break;
                case 104:
                    Log.e(SceneStateMachine.TAG, "We may have had a failure on playing, recover to READY.");
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    class ExitingMusicState extends ExitingIntermediateState {
        ExitingMusicState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "ExitingMusicState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 101:
                case 105:
                case 107:
                    Log.d(SceneStateMachine.TAG, "maybe useful to next state, defer msg=" + msg.what);
                    SceneStateMachine.this.deferMessage(msg);
                    break;
                case 102:
                case 103:
                case 106:
                default:
                    return false;
                case 104:
                    switch (this.destState) {
                        case 0:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                            break;
                        case 2:
                            ((EnteringIntermediateState) SceneStateMachine.this.mEnteringNetConfigState).setRecoveryState(0);
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                            break;
                        case 3:
                            ((EnteringIntermediateState) SceneStateMachine.this.mEnteringBluetoothState).setRecoveryState(0);
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringBluetoothState);
                            break;
                        case 5:
                            ((EnteringIntermediateState) SceneStateMachine.this.mEnteringDossState).setRecoveryState(0);
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringDossState);
                            break;
                    }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState
        public void timeout() {
            Log.d(SceneStateMachine.TAG, "exiting MUSIC timeout, if we're transitioning to ready we recover to MUSIC, otherwise we will defer this to the next entering state.");
            Log.d(SceneStateMachine.TAG, "timeout destState=" + this.destState);
            switch (this.destState) {
                case 0:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                case 2:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringNetConfigState).setRecoveryState(1);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                    break;
                case 3:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringBluetoothState).setRecoveryState(1);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringBluetoothState);
                    break;
                case 5:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringDossState).setRecoveryState(1);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringDossState);
                    break;
            }
            super.timeout();
        }
    }

    class EnteringNetConfigState extends EnteringIntermediateState {
        EnteringNetConfigState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "EnteringNetConfigState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 105:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mNetConfigState);
                    return true;
                default:
                    return false;
            }
        }
    }

    class NetConfigState extends SteadyState {
        NetConfigState() {
            super();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State, com.phicomm.speaker.device.custom.status.SceneStateMachine.SteadyState
        public void enter() {
            SceneStateMachine.this.mCurrentScene = 2;
            super.enter();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "NetConfigState process " + msg.what + " msg.");
            switch (msg.what) {
                case 18:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingNetConfigState);
                    break;
                case 106:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    class ExitingNetConfigState extends ExitingIntermediateState {
        ExitingNetConfigState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "ExitingNetConfigState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 106:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    return true;
                default:
                    return false;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState
        public void timeout() {
            Log.d(SceneStateMachine.TAG, "exiting NET CONFIG timeout, recover to NET CONFIG state.");
            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
            super.timeout();
        }
    }

    class EnteringBluetoothState extends EnteringIntermediateState {
        EnteringBluetoothState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "EnteringBluetoothState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 107:
                    Log.d(SceneStateMachine.TAG, "bt mode is on");
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mBluetoothState);
                    return true;
                default:
                    return false;
            }
        }
    }

    class BluetoothState extends SteadyState {
        BluetoothState() {
            super();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State, com.phicomm.speaker.device.custom.status.SceneStateMachine.SteadyState
        public void enter() {
            SceneStateMachine.this.mCurrentScene = 3;
            super.enter();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "BluetoothState process " + msg.what + " msg.");
            switch (msg.what) {
                case 13:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingBluetoothState).setDestState(5);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingBluetoothState);
                    break;
                case 15:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingBluetoothState).setDestState(1);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingBluetoothState);
                    break;
                case 17:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingBluetoothState).setDestState(2);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingBluetoothState);
                    break;
                case 20:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingBluetoothState).setDestState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingBluetoothState);
                    break;
                case 108:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    class ExitingBluetoothState extends ExitingIntermediateState {
        ExitingBluetoothState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "ExitingBluetoothState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 101:
                case 103:
                case 105:
                    Log.d(SceneStateMachine.TAG, "maybe useful to next state, defer msg=" + msg.what);
                    SceneStateMachine.this.deferMessage(msg);
                    return true;
                case 102:
                case 104:
                case 106:
                case 107:
                default:
                    return false;
                case 108:
                    switch (this.destState) {
                        case 0:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                            return true;
                        case 1:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringMusicState);
                            return true;
                        case 2:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                            return true;
                        case 3:
                        case 4:
                        default:
                            return true;
                        case 5:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringDossState);
                            return true;
                    }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState
        public void timeout() {
            Log.d(SceneStateMachine.TAG, "exiting BLUETOOTH timeout, if we're transitioning to ready we recover to BLUETOOTH, otherwise we will defer this to the next entering state.");
            Log.d(SceneStateMachine.TAG, "timeout destState=" + this.destState);
            switch (this.destState) {
                case 0:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                case 1:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringMusicState).setRecoveryState(3);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringMusicState);
                    break;
                case 2:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringNetConfigState).setRecoveryState(3);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                    break;
                case 5:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringDossState).setRecoveryState(3);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringDossState);
                    break;
            }
            super.timeout();
        }
    }

    class EnteringDossState extends EnteringIntermediateState {
        EnteringDossState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "EnteringDossState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 101:
                    Log.d(SceneStateMachine.TAG, "unisound has entered doss mode.");
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mDossState);
                    return true;
                default:
                    return false;
            }
        }
    }

    class DossState extends SteadyState {
        DossState() {
            super();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State, com.phicomm.speaker.device.custom.status.SceneStateMachine.SteadyState
        public void enter() {
            SceneStateMachine.this.mCurrentScene = 5;
            super.enter();
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "DossState process " + msg.what + " msg.");
            switch (msg.what) {
                case 14:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingDossState).setDestState(0);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingDossState);
                    break;
                case 15:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingDossState).setDestState(1);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingDossState);
                    break;
                case 16:
                case 18:
                default:
                    return false;
                case 17:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingDossState).setDestState(2);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingDossState);
                    break;
                case 19:
                    ((ExitingIntermediateState) SceneStateMachine.this.mExitingDossState).setDestState(3);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mExitingDossState);
                    break;
            }
            return true;
        }
    }

    class ExitingDossState extends ExitingIntermediateState {
        ExitingDossState() {
            super();
        }

        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState, com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            Log.d(SceneStateMachine.TAG, "ExitingDossState process " + msg.what + " msg.");
            if (super.processMessage(msg)) {
                return true;
            }
            switch (msg.what) {
                case 102:
                    switch (this.destState) {
                        case 0:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                            return true;
                        case 1:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringMusicState);
                            return true;
                        case 2:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                            return true;
                        case 3:
                            SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringBluetoothState);
                            return true;
                        default:
                            return true;
                    }
                case 103:
                case 105:
                case 107:
                    Log.d(SceneStateMachine.TAG, "maybe useful to next state, defer msg=" + msg.what);
                    SceneStateMachine.this.deferMessage(msg);
                    return true;
                case 104:
                case 106:
                default:
                    return false;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState
        public void timeout() {
            Log.d(SceneStateMachine.TAG, "exiting DOSS timeout, if we're transitioning to ready we recover to DOSS, otherwise we will defer this to the next entering state.");
            Log.d(SceneStateMachine.TAG, "timeout destState=" + this.destState);
            switch (this.destState) {
                case 0:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                case 1:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringMusicState).setRecoveryState(5);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringMusicState);
                    break;
                case 2:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringNetConfigState).setRecoveryState(5);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringNetConfigState);
                    break;
                case 3:
                    ((EnteringIntermediateState) SceneStateMachine.this.mEnteringBluetoothState).setRecoveryState(5);
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mEnteringBluetoothState);
                    break;
            }
            super.timeout();
        }
    }

    class EnteringIntermediateState extends IntermediateState {
        int recoveryState = 0;

        EnteringIntermediateState() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void setRecoveryState(int state) {
            this.recoveryState = state;
            Log.d(SceneStateMachine.TAG, "exit may have succeeded. if we failed to enter, we will recover to " + this.recoveryState + " state");
        }

        /* access modifiers changed from: package-private */
        @Override // com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState
        public void timeout() {
            Log.d(SceneStateMachine.TAG, "entering state timeout, recover to " + this.recoveryState + " state");
            switch (this.recoveryState) {
                case 0:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mReadyState);
                    break;
                case 1:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mMusicState);
                    break;
                case 2:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mNetConfigState);
                    break;
                case 3:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mBluetoothState);
                    break;
                case 5:
                    SceneStateMachine.this.transitionTo(SceneStateMachine.this.mDossState);
                    break;
            }
            super.timeout();
        }
    }

    class ExitingIntermediateState extends IntermediateState {
        int destState = 0;

        ExitingIntermediateState() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void setDestState(int state) {
            this.destState = state;
            Log.d(SceneStateMachine.TAG, "after exiting doss we are going to " + this.destState + "state");
        }
    }

    class IntermediateState extends State {
        private Runnable mTimeoutRunnable = new Runnable() {
            /* class com.phicomm.speaker.device.custom.status.SceneStateMachine.IntermediateState.AnonymousClass1 */

            public void run() {
                IntermediateState.this.timeout();
            }
        };

        IntermediateState() {
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public void enter() {
            Log.d(SceneStateMachine.TAG, "intermediate state enter.");
            SceneStateMachine.this.getHandler().postDelayed(this.mTimeoutRunnable, SceneStateMachine.INTERMEDIATE_STATE_TIMEOUT_MILLIS);
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public void exit() {
            Log.d(SceneStateMachine.TAG, "intermediate state exit.");
            SceneStateMachine.this.getHandler().removeCallbacks(this.mTimeoutRunnable);
        }

        /* access modifiers changed from: package-private */
        public void timeout() {
            SceneStateMachine.this.sendMessage(1002);
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public boolean processMessage(Message msg) {
            switch (msg.what) {
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    Log.d(SceneStateMachine.TAG, "CMD during an intermediate state, defer them.");
                    SceneStateMachine.this.deferMessage(msg);
                    return true;
                default:
                    Log.d(SceneStateMachine.TAG, "IntermediateState SUPER processMessage dispatch msg to descendants.");
                    return false;
            }
        }
    }

    class SteadyState extends State {
        SteadyState() {
        }

        @Override // com.android.internal.util.IState, com.android.internal.util.State
        public void enter() {
            SceneStateMachine.this.mStatusChangedListener.onStateChanged(SceneStateMachine.this.mCurrentScene);
        }
    }

    public void registerStatusChangedListener(StatusChangedListener listener) {
        this.mStatusChangedListener = listener;
    }
}
