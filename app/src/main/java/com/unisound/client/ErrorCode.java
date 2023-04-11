//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.unisound.client;

import com.unisound.common.y;
import com.unisound.sdk.ci;
import org.json.JSONException;
import org.json.JSONObject;

public class ErrorCode {
    public static final int ASRCLIENT_COMPRESS_PCM_ERROR = -30003;
    public static final int ASRCLIENT_INVALID_PARAMETERS = -30004;
    public static final int ASRCLIENT_MAX_SPEECH_TIMEOUT = -30002;
    public static final int ASRCLIENT_VAD_TIMEOUT = -30001;
    public static final int ASR_ERROR_RESETMODEL = -50001;
    public static final int ASR_FIXENGINE_AUTHORIZE_ERROR = -63608;
    public static final int ASR_FIXENGINE_COMMUNICATION_ERROR = -63602;
    public static final int ASR_FIXENGINE_COMPRESS_PCM_ERROR = -63603;
    public static final int ASR_FIXENGINE_ENGINE_ERROR = -63612;
    public static final int ASR_FIXENGINE_FATAL_ERROR = -63601;
    public static final int ASR_FIXENGINE_MAX_SPEECH_TIMEOUT = -63606;
    public static final int ASR_FIXENGINE_MEM_ALLOCATION_ERROR = -63604;
    public static final int ASR_FIXENGINE_MODEL_NOT_SUPPORT = -63611;
    public static final int ASR_FIXENGINE_RECOGNIZER_WRONG_OPS = -63609;
    public static final int ASR_FIXENGINE_SERVICE_NOT_RUNNING = -63605;
    public static final int ASR_FIXENGINE_TRANS_ERROR = -63600;
    public static final int ASR_FIXENGINE_UNKNOW_ERROR = -63999;
    public static final int ASR_FIXENGINE_VAD_TIMEOUT = -63607;
    public static final int ASR_FOURMIC_RECORDING_ERROR = -63544;
    public static final int ASR_INSERT_VOCABCONTENT_ERROR = -63005;
    public static final int ASR_INSERT_VOCABNAME_ERROR = -63004;
    public static final int ASR_INSERT_VOCAB_ENGINE_ERROR = -63006;
    public static final int ASR_SDK_ACTIVATE_ERROR = -63543;
    public static final int ASR_SDK_ACTIVATE_NO_PERMISSION = -67004;
    public static final int ASR_SDK_ACTIVATE_OVER_MAX_ACT_FREQUENCY = -67005;
    public static final int ASR_SDK_ACTIVATE_SIGN_ERROR = -67003;
    public static final int ASR_SDK_APPKEY_MD5_CHECK_ERROR = -63533;
    public static final int ASR_SDK_FIX_COMPILE_ERROR = -63504;
    public static final int ASR_SDK_FIX_COMPILE_NO_INIT = -63503;
    public static final int ASR_SDK_FIX_INSERTVOCAB_EXT_ERROR = -63505;
    public static final int ASR_SDK_FIX_LOADGRAMMAR_ERROR = -63506;
    public static final int ASR_SDK_FIX_RECOGNIZER_INIT_ERROR = -63501;
    public static final int ASR_SDK_FIX_RECOGNIZER_NO_INIT = -63502;
    public static final int ASR_SDK_INIT_ERROR = -63531;
    public static final int ASR_SDK_NO_NLURESULT_ERROR = -63551;
    public static final int ASR_SDK_SET_ASR_SERVER_ADDR_ERROR = -63542;
    public static final int ASR_SDK_START_ERROR = -63532;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_BAD_PARA = -65807;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_DECODE = -65803;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_ILLEGAL_CHAR = -65800;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_IO = -65804;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_NOT_CONSISTENCY = -65805;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_NO_WORD = -65802;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_SAME_DATA = -65808;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_TOO_MANY_WORDS = -65801;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ERR_WRONG_NEW_WORDS = -65806;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_EMPTY_WORD = -63407;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_ENCODE_ERROR = -63405;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_ERROR = -63406;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_EXCEED_MAXLIMIT = -63402;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_ILLEGAL = -63401;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_NETWORK_ERROR = -63403;
    public static final int ASR_SDK_UPLOAD_ONESHOT_ONLINE_SERVER_REFUSED = -63404;
    public static final int ASR_SDK_WAKEUP_COMPILE_ERROR = -63540;
    public static final int ASR_TOKEN_ERROR = -69001;
    public static final int CHECK_MD5_ERROR = -91154;
    public static final int COMPRESS_PCM_ERROR = -91156;
    public static final int CONNECT_ERROR = -90002;
    public static final int CONNECT_SELECT_ERROR = -90003;
    public static final int CONNECT_SELECT_TIMEOUT = -90004;
    public static final int DECODE_ERROR = -91005;
    public static final int ERR_BAD_PARA = 807;
    public static final int ERR_DECODE = 803;
    public static final int ERR_ILLEGAL_CHAR = 800;
    public static final int ERR_IO = 804;
    public static final int ERR_NOT_CONSISTENCY = 805;
    public static final int ERR_NO_WORD = 802;
    public static final int ERR_SAME_DATA = 808;
    public static final int ERR_TOO_MANY_WORDS = 801;
    public static final int ERR_WRONG_NEW_WORDS = 806;
    public static final int FAILED_START_RECORDING = -61001;
    public static final int GENERAL_COMPILER_INIT_ERROR = -64002;
    public static final int GENERAL_INIT_ERROR = -64001;
    public static final int GENERAL_NO_READ_PHONE_STATE_PERMISSION = -68001;
    public static final int GET_HOST_NAME_ERROR = -90013;
    public static final int GET_INFO_ERROR = -91134;
    public static final int GET_NO_RESPONSE_ERROR = -91010;
    public static final int GET_RESPONSE_ERROR = -91008;
    public static final int HANDLE_ERROR = -91138;
    public static final int HTTP_CONNECT_ERROR = -91007;
    public static final int HTTP_ERROR_ACTIVATE = -91745;
    public static final int HTTP_ERROR_APPKEY = -91744;
    public static final int HTTP_ERROR_COMPOSITE_SERVICES = -91740;
    public static final int HTTP_ERROR_CORE_SERVER_CONNECT_ERROR = -91743;
    public static final int HTTP_ERROR_DECODER_NOT_MATCH_SPEECH_CODING = -91831;
    public static final int HTTP_ERROR_DECODING_ERROR = -91825;
    public static final int HTTP_ERROR_ENGINE_ERROR = -91812;
    public static final int HTTP_ERROR_EXCEED_MAXIMUM_LENGTH = -91823;
    public static final int HTTP_ERROR_EXCEED_MAXIMUM_TEXT_LENGTH = -91830;
    public static final int HTTP_ERROR_FIND_NO_SERVER = -91742;
    public static final int HTTP_ERROR_FORBID = -91703;
    public static final int HTTP_ERROR_HTTP_VERION = -91725;
    public static final int HTTP_ERROR_INNER_SERVER = -91720;
    public static final int HTTP_ERROR_MD5_VALIDATION_ERROR = -91727;
    public static final int HTTP_ERROR_MIN = -91700;
    public static final int HTTP_ERROR_NEED_PAY = -91702;
    public static final int HTTP_ERROR_NLU_PARAMS_PARSE_ERROR = -91802;
    public static final int HTTP_ERROR_NLU_PROCESSING_ERROR = -91804;
    public static final int HTTP_ERROR_NLU_RESOURCE_INIT_ERROR = -91806;
    public static final int HTTP_ERROR_NLU_RETURN_MUST_JSON = -91800;
    public static final int HTTP_ERROR_NLU_RETURN_NULL = -91801;
    public static final int HTTP_ERROR_NOBASIC_SERVER = -91721;
    public static final int HTTP_ERROR_NOTFIND_SERVER = -91704;
    public static final int HTTP_ERROR_NO_CARRY_KEY_INFO = -91722;
    public static final int HTTP_ERROR_NO_CARRY_SESSIONID = -91730;
    public static final int HTTP_ERROR_NO_ENCRYPTION_NO_SESSIONID = -91733;
    public static final int HTTP_ERROR_NO_SERVICES = -91741;
    public static final int HTTP_ERROR_NO_SUPPORT_SERVICE = -91723;
    public static final int HTTP_ERROR_NULL_APPKEY_AND_USERID = -91824;
    public static final int HTTP_ERROR_NULL_TEXT = -91833;
    public static final int HTTP_ERROR_PARAMS_ERROR = -91736;
    public static final int HTTP_ERROR_POST_PROCESSING_ERROR = -91807;
    public static final int HTTP_ERROR_POST_TRAFFIC_PARAMETER_ERROR = -91808;
    public static final int HTTP_ERROR_PROXY_REQ = -91707;
    public static final int HTTP_ERROR_REQUESTSERVICE_NO_SESSIONID = -91734;
    public static final int HTTP_ERROR_REQUEST_TIMEOUT = -91814;
    public static final int HTTP_ERROR_REQUEST_TOO_FREQUENT = -91735;
    public static final int HTTP_ERROR_REQ_INFO_LOSE = -91724;
    public static final int HTTP_ERROR_RESOURCE_ALLOCATION_ERROR = -91815;
    public static final int HTTP_ERROR_RESOURCE_STATE_ERROR = -91813;
    public static final int HTTP_ERROR_SERVER_INNER_ERROR = -91739;
    public static final int HTTP_ERROR_SERVER_LOAD = -91732;
    public static final int HTTP_ERROR_SERVER_TIMEOUT = -91738;
    public static final int HTTP_ERROR_SERVICE_PARAM_ERROR = -91818;
    public static final int HTTP_ERROR_SERVICE_RESOURCE_SHORTAGE = -91811;
    public static final int HTTP_ERROR_SESSIONID_ERROR = -91746;
    public static final int HTTP_ERROR_TOKEN_ERROR = -91737;
    public static final int HTTP_ERROR_TOKEN_VALIDATION_ERROR = -91731;
    public static final int HTTP_ERROR_TRAFFIC_PARAMETER_ERROR = -91805;
    public static final int HTTP_ERROR_UNAUTHORIZED = -91701;
    public static final int HTTP_ERROR_UNKNOWN_ERROR = -91819;
    public static final int HTTP_ERROR_UNSUPPORTED_CODING = -91821;
    public static final int HTTP_ERROR_UNSUPPORTED_COMMOND = -91817;
    public static final int HTTP_ERROR_UNSUPPORTED_COMPRESSION_FORMAT = -91822;
    public static final int HTTP_ERROR_UNSUPPORTED_FAR = -91820;
    public static final int HTTP_ERROR_UNSUPPORTED_FIELD = -91826;
    public static final int HTTP_ERROR_UNSUPPORTED_REQUEST_COMMAND = -91726;
    public static final int HTTP_ERROR_UNSUPPORTED_SERVICE = -91816;
    public static final int HTTP_ERROR_UNSUPPORTED_TEXT = -91832;
    public static final int HTTP_REQ_ERROR = -91004;
    public static final int ILLEGAL_DEVICESN = -69002;
    public static final int INIT_ERROR = -91000;
    public static final int INVALID_INPUT_DATA = -91157;
    public static final int MAX_SPEECH_TIMEOUT = -91155;
    public static final int NETWORK_ERROR = -91003;
    public static final int NLU_OTHER_ERROR = -71003;
    public static final int NLU_REQUEST_EMPTY = -71002;
    public static final int NLU_SERVER_ERROR = -71001;
    public static final int NO_INIT_ERROR = -91001;
    public static final int NO_SECRET_ERROR = -91158;
    public static final int NO_START_ERROR = -91006;
    public static final int NO_SUPPORT_CODEC_ERROR = -91136;
    public static final int NO_SUPPORT_FORMAT_ERROR = -91135;
    public static final int OPTION_ID_ERROR = -91151;
    public static final int OPTION_PARAM_ERROR = -91152;
    public static final int OTHER_ERROR = -90020;
    public static final int RECOGNITION_EXCEPTION = -62001;
    public static final int RECOGNITION_TIMEOUT = -62002;
    public static final int RECOGNIZER_OK = 0;
    public static final int RECORDING_EXCEPTION = -61002;
    public static final int RECV_ERROR = -90008;
    public static final int RECV_SELECT_ERROR = -90009;
    public static final int RECV_SELECT_TIMEOUT = -90010;
    public static final int REQ_INIT_ERROR = -91002;
    public static final int SEND_ERROR = -90005;
    public static final int SEND_REQUEST_ERROR = -91009;
    public static final int SEND_SELECT_ERROR = -90006;
    public static final int SEND_SELECT_TIMEOUT = -90007;
    public static final int SER_IP_ADDRESS_ERROR = -91137;
    public static final int SET_CRYPT_VERSION_ERROR = -91153;
    public static final int SET_FCNTL_ERROR = -90012;
    public static final int SET_SERVICE_ERROR = -91133;
    public static final int SET_SOCKET_OPTION_ERROR = -90011;
    public static final int SOCKET_ERROR = -90001;
    public static final int SUCCESS = 0;
    public static final int TEXT_NULL_ERROR = -91131;
    public static final int TEXT_TOO_LONG_ERROR = -91132;
    public static final int TOKEN_CREATE_ERROR = -91141;
    public static final int TTS_ERROR_AUDIOSOURCE_OPEN = -73306;
    public static final int TTS_ERROR_GET_ENGINE_INFO = -73311;
    public static final int TTS_ERROR_GET_MODEL = -73301;
    public static final int TTS_ERROR_LOAD_MODEL = -73302;
    public static final int TTS_ERROR_OFFLINE_CHANGE_SPEAKER_FAIL = -73310;
    public static final int TTS_ERROR_OFFLINE_CHANGE_SPEAKER_IS_PROCESSING = -73312;
    public static final int TTS_ERROR_OFFLINE_ENGINE_IS_PROCESSING = -73309;
    public static final int TTS_ERROR_OFFLINE_ENGINE_NOT_INIT = -73308;
    public static final int TTS_ERROR_OFFLINE_SYNTHESIZER_SET_TEXT = -73304;
    public static final int TTS_ERROR_ONLINE_SYNTHESIZER_INIT = -73305;
    public static final int TTS_ERROR_PLAYING_EXCEPTION = -73307;
    public static final int TTS_ERROR_TEXT_UNUSEABLE = -73303;
    public static final int TTS_PLAYING_ERROR = 11;
    public static final int TTS_SYNTHESIZE_ERROR = 10;
    public static final int UPLOAD_SCENE_DATA_EMPTY = -63013;
    public static final int UPLOAD_SCENE_DATA_NETWORK_ERROR = -63012;
    public static final int UPLOAD_SCENE_DATA_SERVER_REFUSED = -63011;
    public static final int UPLOAD_SCENE_DATA_SIZE_IS_FORBIDDEN = -63025;
    public static final int UPLOAD_SCENE_DATA_TOO_FAST = -63019;
    public static final int UPLOAD_SCENE_ENCODE_ERROR = -63020;
    public static final int UPLOAD_SCENE_GENERAL_ERROR = -63021;
    public static final int UPLOAD_SCENE_INVALID_KEY = -63022;
    public static final int UPLOAD_SCENE_INVALID_VER = -63026;
    public static final int UPLOAD_SCENE_OUT_MAX_COUNT = -63014;
    public static final int UPLOAD_SCENE_STREAM_IO_ERR = -63023;
    public static final int UPLOAD_SCENE_TOO_LARGE = -63017;
    public static final int UPLOAD_SCENE_UNKNOWN_ERR = -63024;
    public static final int UPLOAD_USER_DATA_EMPTY = -63003;
    public static final int UPLOAD_USER_DATA_NETWORK_ERROR = -63002;
    public static final int UPLOAD_USER_DATA_SERVER_REFUSED = -63001;
    public static final int UPLOAD_USER_DATA_TOO_FAST = -63009;
    public static final int UPLOAD_USER_ENCODE_ERROR = -63010;
    public static final int UPLOAD_USER_TOO_LARGE = -63007;
    public static final int VPR_CLIENT_PARAM_ERROR = 400;
    public static final int VPR_REGISTE_AUDIO_SHORT_ERROR = 504;
    public static final int VPR_REGISTE_ERROR = 501;
    public static final int VPR_USERNAME_VPRDATA_ERROR = 502;
    public static final int VPR_VERIFY_AUDIO_ERROR = 503;

    public ErrorCode() {
    }

    public static ci createOralError(int var0) {
        ci var1;
        switch(var0) {
            case -62001:
                var1 = new ci(var0, "识别异常");
                break;
            case -61002:
                var1 = new ci(var0, "录音异常");
                break;
            case -61001:
                var1 = new ci(var0, "启动录音失败");
                break;
            case -30003:
                var1 = new ci(var0, "数据压缩错误");
                break;
            case -30002:
                var1 = new ci(var0, "说话时间超出限制");
                break;
            default:
                var1 = new ci(-10001, "服务器连接错误");
        }

        return var1;
    }

    public static String toJsonMessage(int var0) {
        JSONObject var1 = new JSONObject();

        try {
            var1.put("errorCode", var0);
            var1.put("errorMsg", toMessage(var0));
        } catch (JSONException var3) {
            var3.printStackTrace();
            y.c(new Object[]{"ErrorCode", "errorCode Not Define."});
        }

        return var1.toString();
    }

    public static String toMessage(int var0) {
        String var1;
        switch(var0) {
            case -91833:
                var1 = "用户传入的文本为空";
                break;
            case -91832:
                var1 = "用户传入不支持的文本格式";
                break;
            case -91831:
                var1 = "解码器与语音编码不匹配";
                break;
            case -91830:
                var1 = "文本长度超过最大文本长度";
                break;
            case -91826:
                var1 = "不支持的领域错误";
                break;
            case -91825:
                var1 = "解码错误";
                break;
            case -91824:
                var1 = "appKey和userId为空";
                break;
            case -91823:
                var1 = "请求的语音文件超过最大语音文件长度";
                break;
            case -91822:
                var1 = "不支持的压缩格式";
                break;
            case -91821:
                var1 = "不支持的编码率";
                break;
            case -91820:
                var1 = "不支持远讲错误";
                break;
            case -91819:
                var1 = "未知错误";
                break;
            case -91818:
                var1 = "服务参数错误";
                break;
            case -91817:
                var1 = "不支持的命令";
                break;
            case -91816:
                var1 = "不支持的服务";
                break;
            case -91815:
                var1 = "资源分配错误";
                break;
            case -91814:
                var1 = "请求超时";
                break;
            case -91813:
                var1 = "资源状态错误";
                break;
            case -91812:
                var1 = "引擎返回错误";
                break;
            case -91811:
                var1 = "服务资源不足";
                break;
            case -91808:
                var1 = "trafficParameter设置错误";
                break;
            case -91807:
                var1 = "后处理错误";
                break;
            case -91806:
                var1 = "nlu资源初始化错误";
                break;
            case -91805:
                var1 = "trafficParameter错误";
                break;
            case -91804:
                var1 = "nlu处理过程中出现错误";
                break;
            case -91802:
                var1 = "nlu参数解析错误";
                break;
            case -91801:
                var1 = "nlu返回结果为空";
                break;
            case -91800:
                var1 = "nlu返回格式错误";
                break;
            case -91746:
                var1 = "找不到此sessionID";
                break;
            case -91745:
                var1 = "激活功能出错";
                break;
            case -91744:
                var1 = "AppKey错误";
                break;
            case -91743:
                var1 = "核心服务连接错误";
                break;
            case -91742:
                var1 = "找不到请求的服务器";
                break;
            case -91741:
                var1 = "没有基础服务可以连接";
                break;
            case -91740:
                var1 = "组合服务全部返回错误";
                break;
            case -91739:
                var1 = "服务器内部异常";
                break;
            case -91738:
                var1 = "服务器已经超时，客户端两次请求间隔太长";
                break;
            case -91737:
                var1 = "产生token错误";
                break;
            case -91736:
                var1 = "参数错误";
                break;
            case -91735:
                var1 = "请求太过频繁";
                break;
            case -91734:
                var1 = "没有该服务";
                break;
            case -91733:
                var1 = "请求无sessionID";
                break;
            case -91732:
                var1 = "找不到核心服务";
                break;
            case -91731:
                var1 = "Token验证错误";
                break;
            case -91730:
                var1 = "未带sessionID错误";
                break;
            case -91727:
                var1 = "验证错误";
                break;
            case -91726:
                var1 = "不支持的请求命令";
                break;
            case -91725:
                var1 = "HTTP版本不受支持";
                break;
            case -91724:
                var1 = "本次请求信息丢失";
                break;
            case -91723:
                var1 = "服务连接错误";
                break;
            case -91722:
                var1 = "未携带关键信息";
                break;
            case -91721:
                var1 = "没有基础服务可以连接";
                break;
            case -91720:
                var1 = "服务器内部错误";
                break;
            case -91707:
                var1 = "代理认证请求";
                break;
            case -91704:
                var1 = "服务器找不到给定的资源";
                break;
            case -91703:
                var1 = "禁止";
                break;
            case -91702:
                var1 = "需要付款";
                break;
            case -91701:
                var1 = "未授权";
                break;
            case -91700:
                var1 = "错误请求";
                break;
            case -91158:
                var1 = "没有设置secret";
                break;
            case -91157:
                var1 = "输入数据非法";
                break;
            case -91156:
                var1 = "传输pcm过程中失败";
                break;
            case -91155:
                var1 = "请求时间过长";
                break;
            case -91154:
                var1 = "服务器签名验证错误";
                break;
            case -91153:
                var1 = "设置加密版本号错误";
                break;
            case -91152:
                var1 = "参数设置错误";
                break;
            case -91151:
                var1 = "参数设置 ID 错误";
                break;
            case -91141:
                var1 = "Token产生错误";
                break;
            case -91138:
                var1 = "句柄错误";
                break;
            case -91137:
                var1 = "IP 地址设置错误";
                break;
            case -91136:
                var1 = "解码格式不支持";
                break;
            case -91135:
                var1 = "语音格式不支持";
                break;
            case -91134:
                var1 = "获取信息错误";
                break;
            case -91133:
                var1 = "服务设置错误";
                break;
            case -91132:
                var1 = "文本文件过长";
                break;
            case -91131:
                var1 = "文本文件为空";
                break;
            case -91010:
            case -91009:
            case -91008:
            case -91006:
            case -91004:
            case -91001:
            case -91000:
            case -90020:
            case -90013:
            case -90012:
            case -90011:
            case -90009:
            case -90008:
            case -90007:
            case -90006:
            case -90005:
            case -90004:
            case -90003:
            case -90002:
            case -90001:
                var1 = "网络错误";
                break;
            case -91007:
                var1 = "联网失败";
                break;
            case -91005:
                var1 = "解码错误";
                break;
            case -91003:
            case -90010:
            case -62002:
                var1 = "网络超时";
                break;
            case -91002:
                var1 = "设备无网络，联网失败";
                break;
            case -73312:
                var1 = "离线tts正在执行切换发音人，请稍后再试";
                break;
            case -73311:
                var1 = "离线tts获取引擎信息错误，请先执行init并设置本地模式！";
                break;
            case -73310:
                var1 = "离线tts切换发音人模型错误";
                break;
            case -73309:
                var1 = "离线tts引擎正在执行合成，请稍等再试";
                break;
            case -73308:
                var1 = "离线tts引擎未初始化，请确认执行init并接收init回调！";
                break;
            case -73307:
                var1 = "播放异常错误";
                break;
            case -73306:
                var1 = "播放线程打开audioSource出错";
                break;
            case -73305:
                var1 = "在线合成初始化错误";
                break;
            case -73304:
                var1 = "离线合成设置文成出错";
                break;
            case -73303:
                var1 = "合成文本不可用";
                break;
            case -73302:
                var1 = "模型加载失败！";
                break;
            case -73301:
                var1 = "模型生成失败！";
                break;
            case -71003:
                var1 = "语义理解: 其他错误";
                break;
            case -71002:
                var1 = "语义理解: 语义请求结果为空";
                break;
            case -71001:
                var1 = "语义理解: 语义服务访问异常";
                break;
            case -69002:
                var1 = "设备DeviceSn长度超出限制(最多24个字符)";
                break;
            case -69001:
                var1 = "token校验失败";
                break;
            case -68001:
                var1 = "没有READ_PHONE_STATE权限";
                break;
            case -67005:
                var1 = "激活失败：超过了最大激活频率";
                break;
            case -67004:
                var1 = "激活失败：没有激活权限";
                break;
            case -67003:
                var1 = "激活失败：签名错误,设备appkey和secret不匹配";
                break;
            case -65808:
                var1 = "上传在线oneshot唤醒词错误：无新增词，不需要处理";
                break;
            case -65807:
                var1 = "上传在线oneshot唤醒词错误：参数错误";
                break;
            case -65806:
                var1 = "上传在线oneshot唤醒词错误：新增加的唤醒词在已经生效的词列表中";
                break;
            case -65805:
                var1 = "上传在线oneshot唤醒词错误：唤醒词不一致：客户端上传的生效唤醒词和服务器端保存的不一致";
                break;
            case -65804:
                var1 = "上传在线oneshot唤醒词错误：IO读取失败";
                break;
            case -65803:
                var1 = "上传在线oneshot唤醒词错误：编解码失败";
                break;
            case -65802:
                var1 = "上传在线oneshot唤醒词错误：没有唤醒词";
                break;
            case -65801:
                var1 = "上传在线oneshot唤醒词错误：超过唤醒词上限值";
                break;
            case -65800:
                var1 = "上传在线oneshot唤醒词错误：含有非法字符";
                break;
            case -64002:
                var1 = "离线编译引擎未初始化";
                break;
            case -64001:
                var1 = "程序初始化异常，请尝试重新调用init";
                break;
            case -63612:
                var1 = "引擎使用错误";
                break;
            case -63611:
                var1 = "引擎模型ID错误";
                break;
            case -63609:
                var1 = "引擎OPS错误";
                break;
            case -63608:
                var1 = "引擎授权错误";
                break;
            case -63607:
                var1 = "引擎VAD超时";
                break;
            case -63606:
                var1 = "引擎超时";
                break;
            case -63605:
                var1 = "引擎没有运行";
                break;
            case -63604:
                var1 = "引擎内存申请错误";
                break;
            case -63603:
                var1 = "引擎PCM压缩错误";
                break;
            case -63602:
                var1 = "引擎通讯错误";
                break;
            case -63601:
                var1 = "引擎致命错误";
                break;
            case -63551:
                var1 = "语义结果为空异常";
                break;
            case -63544:
                var1 = "4mic录音错误";
                break;
            case -63543:
                var1 = "激活失败";
                break;
            case -63542:
                var1 = "语言识别服务地址设置失败";
                break;
            case -63540:
                var1 = "离线唤醒模型编译失败";
                break;
            case -63533:
                var1 = "appkey校验失败";
                break;
            case -63532:
                var1 = "开始识别异常";
                break;
            case -63531:
                var1 = "引擎初始化异常";
                break;
            case -63506:
                var1 = "离线引擎加载模型失败";
                break;
            case -63505:
                var1 = "离线引擎Ext编译加载模型失败";
                break;
            case -63504:
                var1 = "离线识别模型编译失败";
                break;
            case -63503:
                var1 = "离线引擎没有初始化,不能编译用户数据";
                break;
            case -63502:
                var1 = "离线引擎没有初始化";
                break;
            case -63501:
                var1 = "离线引擎初始化错误";
                break;
            case -63407:
                var1 = "上传在线oneshot唤醒词错误：含有null或空字符串";
                break;
            case -63406:
                var1 = "上传在线oneshot唤醒词失败";
                break;
            case -63405:
                var1 = "上传在线oneshot唤醒词错误：编码失败";
                break;
            case -63404:
                var1 = "上传在线oneshot唤醒词错误：服务器拒绝";
                break;
            case -63403:
                var1 = "上传在线oneshot唤醒词错误：网络错误";
                break;
            case -63402:
                var1 = "上传在线oneshot唤醒词错误：超过唤醒词上限值";
                break;
            case -63401:
                var1 = "上传在线oneshot唤醒词错误：含有非法字符";
                break;
            case -63026:
                var1 = "上传场景数据无效的SDK版本号";
                break;
            case -63025:
                var1 = "上传场景数据上传的数据体积异常";
                break;
            case -63024:
                var1 = "上传场景数据未知异常";
                break;
            case -63023:
                var1 = "上传场景数据上传的数据流异常";
                break;
            case -63022:
                var1 = "上传场景数据无效的AppKey";
                break;
            case -63021:
                var1 = "上传场景数据:常见错误";
                break;
            case -63020:
                var1 = "上传场景数据:编码失败";
                break;
            case -63019:
                var1 = "上传场景数据:上传过于频繁";
                break;
            case -63017:
                var1 = "上传场景数据:内容太多";
                break;
            case -63013:
                var1 = "上传场景数据:不能为空";
                break;
            case -63012:
                var1 = "上传场景数据:网络连接失败";
                break;
            case -63011:
                var1 = "上传场景数据:服务器拒绝";
                break;
            case -63010:
                var1 = "上传个性化数据:编码失败";
                break;
            case -63009:
                var1 = "上传个性化数据:上传过于频繁";
                break;
            case -63007:
                var1 = "上传个性化数据:内容太多";
                break;
            case -63003:
                var1 = "上传个性化数据:不能为空";
                break;
            case -63002:
                var1 = "上传个性化数据:网络连接失败";
                break;
            case -63001:
                var1 = "上传个性化数据:服务器拒绝";
                break;
            case -62001:
                var1 = "识别异常";
                break;
            case -61002:
                var1 = "录音异常";
                break;
            case -61001:
                var1 = "启动录音失败";
                break;
            case -50001:
                var1 = "正在重置模型，请勿重复调用";
                break;
            case -30004:
                var1 = "非法参数错误";
                break;
            case -30003:
                var1 = "数据压缩错误";
                break;
            case -30002:
                var1 = "说话时间超出限制";
                break;
            case 0:
                var1 = "操作成功";
                break;
            case 400:
                var1 = "VPR客户端参数错误";
                break;
            case 501:
                var1 = "声纹注册或验证异常";
                break;
            case 502:
                var1 = "重复用户名或者声纹持久化异常";
                break;
            case 503:
                var1 = "无法提取声纹特征（声音超级短，或空白语音）";
                break;
            case 504:
                var1 = "VPR语音太短错误(最短注册5s,匹配2s)";
                break;
            default:
                var1 = "其他异常";
        }

        return var1;
    }

    public ci createBasicError(int var1) {
        ci var2;
        if (var1 == 0) {
            var2 = null;
        } else {
            var1 = this.toProfession(var1);
            var2 = new ci(var1, this.getMessage(var1));
        }

        return var2;
    }

    public ci createPremiumError(int var1) {
        ci var2;
        if (var1 == 0) {
            var2 = null;
        } else {
            var2 = new ci(var1, this.getMessage(this.toProfession(var1)));
        }

        return var2;
    }

    public ci createProfessionError(int var1) {
        ci var2;
        if (var1 == 0) {
            var2 = null;
        } else {
            var2 = new ci(var1, this.getMessage(this.toProfession(var1)));
        }

        return var2;
    }

    public String getMessage(int var1) {
        String var2 = toMessage(var1);
        if (var2 == null) {
            var2 = "错误:" + var1;
        }

        return var2;
    }

    public int toProfession(int var1) {
        return var1;
    }
}
