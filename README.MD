识别结果 实现 SpeechUnderstanderListener 接口

NativeANTEngine 识别结果第一入口

## 核心代码说明
```bash

#  hook 原始语音命令，采用新的 unisound api
app/src/main/java/com/phicomm/speaker/device/utils/PhicommUtils.java

#  解决签名验证问题
app/src/main/java/com/phicomm/speaker/device/utils/PmsHookBinderInvocationHandler
app/src/main/java/com/phicomm/speaker/device/utils/ServiceManagerWraper



```

## 编译方法
```bash

# 环境 jdk >= 1.8; android sdk
./gradlew NewPackage:assemble

# outupt apk file
NewPackage/build/outputs/apk/debug/NewPackage-release.apk
```

## 使用方法

```bash

# 安装 NewPackage-release.apk 
# 禁用系统 app  com.phicomm.speaker.device

```


### 服务端返回成功结果记录

#### 播放新闻  cn.yunzhisheng.news
```json
{
	"semantic": {
		"intent": {
			"keyword": "HEADLINE"
		}
	},
	"source": "nlu",
	"general": {
		"title": "伊朗重申无意以“分步走”方式重回伊核协议",
		"audio": "http://audio.leting.io/222dc44ba750f496220de392245fac74_brand.mp3",
		"text": "为您播放新闻",
		"type": "AT"
	},
	"displayProcessRecord": {
		"nluSlotInfos": [],
		"matchResult": {
			"keyword": "HEADLINE",
			"service": "cn.yunzhisheng.news",
			"code": "SEARCH"
		},
		"timeCosts": {
			"to do slot filling ": "0",
			"createResultFromMatch": "122",
			"prepare parameters": "0",
			"modify nlp resource": "0",
			"total time cost": "219",
			"doAnswer": "122",
			"nlp doAnalysis": "15",
			"preprocess": "0",
			"nlp post": "5",
			"time cost in nlu exec": "229",
			"createNlpRequest": "6",
			"prepare praram for nlu exec": "0",
			"executeNlu": "206",
			"dispatch": "51",
			"modify and filter score": "1",
			"post process origin ner result": "1",
			"text rewriter": "5",
			"create analyzed request": "0",
			"split": "0",
			"writeContext": "13"
		},
		"confidence": "0.9969894400953795",
		"otherRecord": {
			"OriginalNER": "[播放 新闻\ttype=0\tscore=0.98888, 播放 新闻/audio_category\ttype=1\tscore=0.8653615384615385, 播放 新闻/audio_cate_subcate\ttype=1\tscore=0.8653615384615385, 播放 新闻/audio_category\ttype=2\tscore=0.4]",
			"category": "[ScoredCategory [category=audio, score=0.9, type=RULE], ScoredCategory [category=setting.mp, score=0.75, type=RULE], ScoredCategory [category=新闻, score=0.6749, ], ScoredCategory [category=others, score=0.3063888, type=CNN], ScoredCategory [category=应用, score=0.1176, ], ScoredCategory [category=setting, score=0.107274935, type=CNN]]",
			"NluPriority": "10",
			"MedicalNer": "{}",
			"NER": "[播放 新闻\ttype=-1\tscore=1.0, 播放 新闻\ttype=0\tscore=0.889992, 播放 新闻/audio_category\ttype=1\tscore=0.7788253846153846, 播放 新闻/audio_cate_subcate\ttype=1\tscore=0.7788253846153846]",
			"useResult": "NLU",
			"NonRepeatText": "播放 新闻",
			"weightedScoreMap": "{IDIOM_SOLITAIRE=.00,SPECIFIED_LAYER_1=.00,ONLY_NEED_WORD_STRING_TEMPLATE=.99}",
			"matchedNlgValueTemplateInfos": "1:intent~[keyword:HEADLINE]\tcontext_status~[vendorName:LETING]\t"
		},
		"matchedType": "ONLY_NEED_WORD_STRING_TEMPLATE",
		"matchedPatternId": "cn.yunzhisheng.news:NEWS_HEADLINE@5_1",
		"matchedInput": "播放新闻"
	},
	"responseId": "ab7c5452a7ec4a268cdade17261e0d86",
	"history": "cn.yunzhisheng.news",
	"data": {
		"result": {
			"errorCode": 0,
			"totalTime": 113,
			"news": [ {
				"updatedTime": "2021-04-21 14:53:32",
				"title": "街道办回应：尽快找产权单位协助调查",
				"id": "2610214",
				"summary": "",
				"imageUrl": "http://image.leting.io/0fe9d7424cf8bd291b4a25b312f88025.jpg",
				"humanTime": "18分钟前",
				"tags": "",
				"duration": 65,
				"audioUrl": "http://audio.leting.io/ff43a0d15a68c6ea6da82eb95d299705.mp3"
			}],
			"dataSourceName": "乐听头条",
			"count": 1,
			"searchType": "recommend"
		}
	},
	"text": "播放新闻",
	"originIntent": {
		"nluSlotInfos": []
	},
	"service": "cn.yunzhisheng.news",
	"asr_recongize": "播放新闻。",
	"code": "SEARCH",
	"rc": 0
}
```

#### 打开氛围灯  cn.yunzhisheng.setting
```json
{
	"semantic": {
		"intent": {
			"operations": [{
				"operator": "ACT_OPEN",
				"operands": "AmbientLight"
			}]
		}
	},
	"source": "nlu",
	"general": {
		"quitDialog": "true",
		"text": "好的，已为您打开灯",
		"type": "T",
		"actionAble": "true"
	},
	"displayProcessRecord": {
		"nluSlotInfos": [],
		"matchResult": {
			"operations": [{
				"operator": "ACT_OPEN",
				"operands": "OBJ_LIGHT"
			}],
			"service": "cn.yunzhisheng.setting",
			"code": "SETTING_EXEC"
		},
		"timeCosts": {
			"to do slot filling ": "0",
			"createResultFromMatch": "18",
			"prepare parameters": "0",
			"modify nlp resource": "0",
			"total time cost": "1173",
			"nlp doAnalysis": "22",
			"preprocess": "0",
			"nlp post": "12",
			"time cost in nlu exec": "1191",
			"createNlpRequest": "9",
			"prepare praram for nlu exec": "0",
			"executeNlu": "1162",
			"dispatch": "56",
			"modify and filter score": "0",
			"post process origin ner result": "0",
			"text rewriter": "1042",
			"create analyzed request": "0",
			"split": "0",
			"writeContext": "11"
		},
		"confidence": "0.99",
		"otherRecord": {
			"OriginalNER": "[打开 氛围 灯\ttype=0\tscore=0.9745968833525908, 打开/song_ler 氛围/video 灯\ttype=1\tscore=0.9374625, 打开 氛围灯/device_type\ttype=1\tscore=0.890596875, 打开 氛围/musicTag 灯/song\ttype=1\tscore=0.890596875, 打开 氛围灯/song_ler\ttype=1\tscore=0.890596875, 打开 氛围/musicTag 灯\ttype=1\tscore=0.84373125, 打开 氛围/video 灯\ttype=1\tscore=0.84373125, 打开/song_ler 氛围 灯\ttype=1\tscore=0.84373125, 打开 氛围 灯/device_type\ttype=1\tscore=0.796865625, 打开 氛围 灯/song\ttype=1\tscore=0.796865625, 打开 氛围/video 灯\ttype=0\tscore=0.91111, 打开 氛围/musicTag 灯/song\ttype=2\tscore=0.4]",
			"category": "[ScoredCategory [category=setting, score=0.9999069, type=CNN], ScoredCategory [category=聊天, score=0.402, ], ScoredCategory [category=应用, score=0.344, ], ScoredCategory [category=others, score=7.716967E-5, type=CNN], ScoredCategory [category=appmgr, score=1.5644857E-5, type=CNN]]",
			"NluPriority": "10",
			"MedicalNer": "{}",
			"NER": "[打开 氛围 灯\ttype=-1\tscore=1.0, 打开 氛围 灯\ttype=0\tscore=0.8771371950173317, 打开/song_ler 氛围/video 灯\ttype=1\tscore=0.84371625, 打开 氛围/video 灯\ttype=0\tscore=0.819999, 打开 氛围灯/device_type\ttype=1\tscore=0.8015371875, 打开 氛围/musicTag 灯/song\ttype=1\tscore=0.8015371875, 打开 氛围灯/song_ler\ttype=1\tscore=0.8015371875, 打开 氛围/musicTag 灯\ttype=1\tscore=0.7593581250000001, 打开/song_ler 氛围 灯\ttype=1\tscore=0.7593581250000001, 打开 氛围 灯/device_type\ttype=1\tscore=0.7171790625000001, 打开 氛围 灯/song\ttype=1\tscore=0.7171790625000001]",
			"useResult": "NLU",
			"NonRepeatText": "打开 氛围 灯",
			"weightedScoreMap": "{IDIOM_SOLITAIRE=.00,SPECIFIED_LAYER_1=.00,ONLY_NEED_WORD_STRING_TEMPLATE=.99}",
			"matchedNlgValueTemplateInfos": "1:intent~[operator:ACT_OPEN][operands:OBJ_LIGHT]\t"
		},
		"matchedType": "ONLY_NEED_WORD_STRING_TEMPLATE",
		"matchedPatternId": "cn.yunzhisheng.setting:OPEN_LIGHT@1_4",
		"matchedInput": "打开氛围灯"
	},
	"responseId": "edab706a8a9b430aacbb884bfc846120",
	"history": "cn.yunzhisheng.setting",
	"text": "打开氛围灯",
	"originIntent": {
		"nluSlotInfos": []
	},
	"service": "cn.yunzhisheng.setting",
	"asr_recongize": "打开氛围灯。",
	"code": "SETTING_EXEC",
	"rc": 0
}
```

#### 打开低音 cn.yunzhisheng.setting
```json
{
	"semantic": {
		"intent": {
			"operations": [{
				"operator": "ACT_SET",
				"value": "MODE_BASS",
				"operands": "ATTR_MODE",
				"valueExpr": "低音增强"
			}]
		}
	},
	"source": "nlu",
	"general": {
		"quitDialog": "true",
		"text": "好的，已为您打开低音增强模式",
		"type": "T",
		"actionAble": "true"
	},
	"displayProcessRecord": {
		"nluSlotInfos": [],
		"matchResult": {
			"ackType": "ELLIPSIS",
			"operations": [{
				"operator": "ACT_SET",
				"value": "MODE_BASS",
				"operands": "ATTR_MODE",
				"valueExpr": "低音增强"
			}],
			"service": "cn.yunzhisheng.setting",
			"code": "SETTING_EXEC"
		},
		"timeCosts": {
			"to do slot filling ": "0",
			"createResultFromMatch": "16",
			"prepare parameters": "0",
			"modify nlp resource": "0",
			"total time cost": "381",
			"nlp doAnalysis": "19",
			"preprocess": "0",
			"nlp post": "10",
			"time cost in nlu exec": "392",
			"createNlpRequest": "10",
			"prepare praram for nlu exec": "0",
			"executeNlu": "370",
			"dispatch": "55",
			"modify and filter score": "0",
			"post process origin ner result": "0",
			"text rewriter": "257",
			"create analyzed request": "0",
			"split": "0",
			"writeContext": "11"
		},
		"confidence": "0.99",
		"otherRecord": {
			"OriginalNER": "[打开 低音 增强\ttype=0\tscore=0.98888, 打开/song_ler 低音 增强\ttype=1\tscore=0.8289315789473684, 打开 低 音/novel 增强\ttype=2\tscore=0.28946578947368423, 打开 低音增强/app\ttype=0\tscore=0.016688679432875816, 打开/song_ler 低音 增强\ttype=2\tscore=0.4]",
			"category": "[ScoredCategory [category=setting, score=0.97342163, type=CNN], ScoredCategory [category=应用, score=0.5208, ], ScoredCategory [category=设置, score=0.3329, ], ScoredCategory [category=appmgr, score=0.01837788, type=CNN], ScoredCategory [category=others, score=0.006778031, type=CNN]]",
			"NluPriority": "10",
			"MedicalNer": "{}",
			"NER": "[打开 低音 增强\ttype=-1\tscore=1.0, 打开 低音 增强\ttype=0\tscore=0.889992, 打开/song_ler 低音 增强\ttype=1\tscore=0.7460384210526315]",
			"useResult": "NLU",
			"NonRepeatText": "打开 低音 增强",
			"weightedScoreMap": "{IDIOM_SOLITAIRE=.00,SPECIFIED_LAYER_1=.00,ONLY_NEED_WORD_STRING_TEMPLATE=.99}",
			"matchedNlgValueTemplateInfos": "1:intent~[operator:ACT_SET][operands:ATTR_MODE][value:][valueExpr:]\t"
		},
		"matchedType": "ONLY_NEED_WORD_STRING_TEMPLATE",
		"matchedPatternId": "cn.yunzhisheng.setting:OPEN_MODEL_EXTENDNOT@2_1",
		"matchedInput": "打开低音增强"
	},
	"responseId": "decaa2ca61924e72a521db3e873a62ab",
	"history": "cn.yunzhisheng.setting",
	"text": "打开低音增强",
	"originIntent": {
		"nluSlotInfos": []
	},
	"service": "cn.yunzhisheng.setting",
	"asr_recongize": "打开低音增强。",
	"code": "SETTING_EXEC",
	"rc": 0
}
```

#### 讲个笑话  cn.yunzhisheng.chat
```json
{
	"source": "nlu",
	"general": {
		"quitDialog": "true",
		"title": "如此爸爸",
		"audio": "http://resources.hivoice.cn/audio/bsQuvv5442YazYHbszMAiQ.mp3",
		"url": "http://resources.hivoice.cn/audio/bsQuvv5442YazYHbszMAiQ.mp3",
		"style": "joke",
		"text": "哎呦，今天我有点头疼，亲爱的，你没事吧？没事，就是有些不想动，今天的中饭怎么办呢？今天中午，我来做回大厨，你就先休息吧，爸爸，你会做饭吗？别小瞧爸爸了，爸爸做饭可好吃了，好嘞，那就尝一尝爸爸的手艺吧，你们等着吧，爸爸马上就给你们做好。",
		"imageUrl": "",
		"type": "T",
		"actionAble": "true"
	},
	"responseId": "7ae07b2dd9654e92bcf9d3e6175c893c",
	"history": "cn.yunzhisheng.joke",
	"text": "讲个笑话",
	"originIntent": {
		"nluSlotInfos": []
	},
	"service": "cn.yunzhisheng.chat",
	"asr_recongize": "讲个笑话。",
	"code": "ANSWER",
	"rc": 0
}
```

#### 深圳天气 cn.yunzhisheng.weather
```json
{
  "semantic": {
    "intent": {
      "province": "广东省",
      "city": "深圳市",
      "cityCode": "101280601",
      "topic": "WEATHER",
      "focusDate": "2021-04-21"
    }
  },
  "code": "FORECAST",
  "data": {
    "result": {
      "cityName": "深圳",
      "focusDateIndex": 0,
      "cityCode": "101280601",
      "totalTime": 2,
      "errorCode": 0,
      "updateTime": "2021-04-21 14:03:55",
      "source": 1,
      "weatherDays": [{
        "carWashIndex": "适宜",
        "year": 2021,
        "highestTemperature": 29,
        "imageTitleOfNight": "",
        "comfortIndexDesc": "紫外线较强，外出要涂防晒霜，戴遮阳帽。上午10点至下午4点尽量避免阳光直射。紫外线过敏人群外出注意防护。",
        "sunBlockIndexDesc": "紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。",
        "umbrellaIndex": "一般舒适",
        "lowestAQI": "",
        "sportIndexDesc": "这样的天气非常适宜外出钓鱼，约个钓友出去钓鱼吧。",
        "datingIndexDesc": "预计今日天气炎热，室外极易引起重症中暑，建议尽量避免外出，户外人员适当调整时间，备好遮阳设施，及时补充盐份。",
        "dayOfWeek": 4,
        "pm2_5": 20,
        "datingIndex": "高发中暑",
        "weather": "晴",
        "morningExerciseIndexDesc": "天气炎热，建议穿着薄款，透气的衣物。推荐：T恤、裙装、短裤等。",
        "humidity": "58",
        "AQI": "52",
        "highestAQI": "",
        "dressIndexDesc": "天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。",
        "coldIndex": "少发",
        "coldIndexDesc": "各项气象条件适宜，无明显降温过程，发生感冒机率较低。",
        "windDirection": "西南风",
        "morningExerciseIndex": "清凉夏装",
        "day": 21,
        "dryingIndex": "少发感冒",
        "comfortIndex": "紫外线很强",
        "currentTemperature": 27,
        "umbrellaIndexDesc": "天气变热，少部分人可能会不舒适。",
        "sunBlockIndex": "很强",
        "highestAQIDesc": "",
        "suggest": "",
        "quality": "良",
        "primaryPollutants": "PM10",
        "carWashIndexDesc": "适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。",
        "dryingIndexDesc": "感冒低发期，天气舒适，请注意多吃蔬菜水果，多喝水哦。",
        "month": 4,
        "imageTitleOfDay": "",
        "lowestAQIDesc": "",
        "dressIndex": "热",
        "lowestTemperature": 21,
        "wind": "3级(西南风)",
        "sportIndex": "",
        "travelIndex": "很适宜晾晒",
        "travelIndexDesc": "天气晴朗，温度适宜，很适合晾晒。"
      }, {
        "carWashIndex": "适宜",
        "year": 2021,
        "highestTemperature": 30,
        "imageTitleOfNight": "",
        "comfortIndexDesc": "",
        "sunBlockIndexDesc": "",
        "umbrellaIndex": "",
        "lowestAQI": "",
        "sportIndexDesc": "",
        "datingIndexDesc": "",
        "dayOfWeek": 5,
        "datingIndex": "",
        "weather": "晴",
        "morningExerciseIndexDesc": "",
        "humidity": "",
        "AQI": "",
        "highestAQI": "",
        "dressIndexDesc": "",
        "coldIndex": "",
        "coldIndexDesc": "",
        "windDirection": "无持续风向",
        "morningExerciseIndex": "",
        "day": 22,
        "dryingIndex": "",
        "comfortIndex": "",
        "umbrellaIndexDesc": "",
        "sunBlockIndex": "",
        "highestAQIDesc": "",
        "suggest": "",
        "quality": "",
        "primaryPollutants": "",
        "carWashIndexDesc": "适宜洗车，未来三天无雨，风力较小，擦洗一新的汽车至少能保持三天。",
        "dryingIndexDesc": "",
        "month": 4,
        "imageTitleOfDay": "",
        "lowestAQIDesc": "",
        "dressIndex": "",
        "lowestTemperature": 22,
        "wind": "微风(无持续风向)",
        "sportIndex": "",
        "travelIndex": "",
        "travelIndexDesc": ""
      }, {
        "carWashIndex": "较适宜",
        "year": 2021,
        "highestTemperature": 31,
        "imageTitleOfNight": "",
        "comfortIndexDesc": "",
        "sunBlockIndexDesc": "",
        "umbrellaIndex": "",
        "lowestAQI": "",
        "sportIndexDesc": "",
        "datingIndexDesc": "",
        "dayOfWeek": 6,
        "datingIndex": "",
        "weather": "晴",
        "morningExerciseIndexDesc": "",
        "humidity": "",
        "AQI": "",
        "highestAQI": "",
        "dressIndexDesc": "",
        "coldIndex": "",
        "coldIndexDesc": "",
        "windDirection": "无持续风向",
        "morningExerciseIndex": "",
        "day": 23,
        "dryingIndex": "",
        "comfortIndex": "",
        "umbrellaIndexDesc": "",
        "sunBlockIndex": "",
        "highestAQIDesc": "",
        "suggest": "",
        "quality": "",
        "primaryPollutants": "",
        "carWashIndexDesc": "较适宜洗车，未来两天无雨，风力较小，擦洗一新的汽车至少能保持两天。",
        "dryingIndexDesc": "",
        "month": 4,
        "imageTitleOfDay": "",
        "lowestAQIDesc": "",
        "dressIndex": "",
        "lowestTemperature": 23,
        "wind": "微风(无持续风向)",
        "sportIndex": "",
        "travelIndex": "",
        "travelIndexDesc": ""
      }, {
        "carWashIndex": "较适宜",
        "year": 2021,
        "highestTemperature": 30,
        "imageTitleOfNight": "",
        "comfortIndexDesc": "",
        "sunBlockIndexDesc": "",
        "umbrellaIndex": "",
        "lowestAQI": "",
        "sportIndexDesc": "",
        "datingIndexDesc": "",
        "dayOfWeek": 7,
        "datingIndex": "",
        "weather": "多云",
        "morningExerciseIndexDesc": "",
        "humidity": "",
        "AQI": "",
        "highestAQI": "",
        "dressIndexDesc": "",
        "coldIndex": "",
        "coldIndexDesc": "",
        "windDirection": "无持续风向",
        "morningExerciseIndex": "",
        "day": 24,
        "dryingIndex": "",
        "comfortIndex": "",
        "umbrellaIndexDesc": "",
        "sunBlockIndex": "",
        "highestAQIDesc": "",
        "suggest": "",
        "quality": "",
        "primaryPollutants": "",
        "carWashIndexDesc": "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。",
        "dryingIndexDesc": "",
        "month": 4,
        "imageTitleOfDay": "",
        "lowestAQIDesc": "",
        "dressIndex": "",
        "lowestTemperature": 24,
        "wind": "微风(无持续风向)",
        "sportIndex": "",
        "travelIndex": "",
        "travelIndexDesc": ""
      }, {
        "carWashIndex": "较适宜",
        "year": 2021,
        "highestTemperature": 26,
        "imageTitleOfNight": "",
        "comfortIndexDesc": "",
        "sunBlockIndexDesc": "",
        "umbrellaIndex": "",
        "lowestAQI": "",
        "sportIndexDesc": "",
        "datingIndexDesc": "",
        "dayOfWeek": 1,
        "datingIndex": "",
        "weather": "多云转阵雨",
        "morningExerciseIndexDesc": "",
        "humidity": "",
        "AQI": "",
        "highestAQI": "",
        "dressIndexDesc": "",
        "coldIndex": "",
        "coldIndexDesc": "",
        "windDirection": "无持续风向",
        "morningExerciseIndex": "",
        "day": 25,
        "dryingIndex": "",
        "comfortIndex": "",
        "umbrellaIndexDesc": "",
        "sunBlockIndex": "",
        "highestAQIDesc": "",
        "suggest": "",
        "quality": "",
        "primaryPollutants": "",
        "carWashIndexDesc": "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。",
        "dryingIndexDesc": "",
        "month": 4,
        "imageTitleOfDay": "",
        "lowestAQIDesc": "",
        "dressIndex": "",
        "lowestTemperature": 21,
        "wind": "微风(无持续风向)",
        "sportIndex": "",
        "travelIndex": "",
        "travelIndexDesc": ""
      }],
      "focusDate": "今天",
      "dataSourceName": "云知声"
    },
    "header": "深圳今天天气情况是：晴，21至29℃，3级(西南风)。"
  },
  "originIntent": {
    "nluSlotInfos": []
  },
  "history": "cn.yunzhisheng.weather",
  "source": "nlu",
  "uniCarRet": {
    "result": {},
    "returnCode": 609,
    "message": "aios-home.hivoice.cn"
  },
  "asr_recongize": "深圳天气。",
  "rc": 0,
  "general": {
    "actionAble": "true",
    "quitDialog": "true",
    "text": "深圳今天天气情况是：晴，21至29℃，3级(西南风)。",
    "type": "T"
  },
  "returnCode": 0,
  "audioUrl": "http://asrv3.hivoice.cn/trafficRouter/r/6pKjq3",
  "retTag": "nlu",
  "service": "cn.yunzhisheng.weather",
  "nluProcessTime": "184",
  "taskName": "query",
  "text": "深圳天气",
  "responseId": "c5c9462250ca47a39c6b0842f53aed19"
}
```
#### 你在干什么 cn.yunzhisheng.chat
```json 
{
  "source": "krc",
  "general": {
    "baseId": "2",
    "originalQuestion": "你在干什么",
    "style": "CHAT_SCENARIO_CUSTOMIZED",
    "text": "我正在想你，你就来找我聊天了。",
    "mood": "思念",
    "emoji": "亲亲"
  },
  "responseId": "92cf805976a547879e41ceffaee9b76d",
  "history": "cn.yunzhisheng.chat",
  "text": "你在干什么",
  "originIntent": {
    "nluSlotInfos": []
  },
  "service": "cn.yunzhisheng.chat",
  "asr_recongize": "你在干什么。",
  "code": "ANSWER",
  "rc": 0
}
```

#### 设置闹钟
```json
{
	"source": "nlu",
	"general": {
		"text": "请问设置什么时间的闹钟呢？",
		"type": "T"
	},
	"displayProcessRecord": {
		"nluSlotInfos": [],
		"matchResult": {
			"ackType": "START_TASK",
			"service": "cn.yunzhisheng.alarm",
			"taskName": "query",
			"code": "ALARM_SET"
		},
		"timeCosts": {
			"to do slot filling ": "1",
			"createResultFromMatch": "1",
			"prepare parameters": "1",
			"modify nlp resource": "0",
			"total time cost": "108",
			"nlp doAnalysis": "15",
			"preprocess": "0",
			"nlp post": "11",
			"time cost in nlu exec": "116",
			"createNlpRequest": "7",
			"prepare praram for nlu exec": "0",
			"executeNlu": "95",
			"dispatch": "55",
			"modify and filter score": "0",
			"post process origin ner result": "0",
			"text rewriter": "5",
			"create analyzed request": "0",
			"split": "0",
			"writeContext": "12"
		},
		"confidence": "0.99",
		"otherRecord": {
			"OriginalNER": "[设置 闹钟\ttype=0\tscore=0.98888, 设置 闹钟/audio_name\ttype=1\tscore=0.8653615384615385, 设置 闹钟/song_ler\ttype=1\tscore=0.8653615384615385, 设置 闹钟/app\ttype=0\tscore=7.082305808507178E-4, 设置 闹钟/song_ler\ttype=2\tscore=0.4]",
			"category": "[ScoredCategory [category=others, score=0.99267733, type=CNN], ScoredCategory [category=设置, score=0.7397, ], ScoredCategory [category=闹铃, score=0.2329, ], ScoredCategory [category=appmgr, score=0.0044320333, type=CNN], ScoredCategory [category=agent.profiling, score=9.3549973E-4, type=CNN]]",
			"NluPriority": "10",
			"MedicalNer": "{}",
			"NER": "[设置 闹钟\ttype=-1\tscore=1.0, 设置 闹钟\ttype=0\tscore=0.889992, 设置 闹钟/audio_name\ttype=1\tscore=0.7788253846153846, 设置 闹钟/song_ler\ttype=1\tscore=0.7788253846153846]",
			"useResult": "NLU",
			"NonRepeatText": "设置 闹钟",
			"weightedScoreMap": "{IDIOM_SOLITAIRE=.00,SPECIFIED_LAYER_1=.00,ONLY_NEED_WORD_STRING_TEMPLATE=.99}"
		},
		"matchedType": "ONLY_NEED_WORD_STRING_TEMPLATE",
		"matchedPatternId": "cn.yunzhisheng.alarm:OPEN_ALARM_STRING@4_1",
		"matchedInput": "设置闹钟"
	},
	"responseId": "18158aee79514c09acb9f571aede5a36",
	"history": "cn.yunzhisheng.alarm",
	"text": "设置闹钟",
	"originIntent": {
		"nluSlotInfos": []
	},
	"sessionId": "0f5cfab3-b844-4df8-a545-4d7e72c675ac",
	"service": "cn.yunzhisheng.alarm",
	"asr_recongize": "设置闹钟。",
	"code": "ANSWER",
	"rc": 0
}
```

#### 查询股票中国平安
```json
{"result":{"mName":"平安保险","errorCode":0,"source":1,"mTodayOpeningPrice":"75.610","mUpdateTime":"2021-04-21 15:00:00","mChangeRate":"-0.50","mHighestPrice":"75.860","mChangeAmount":"-0.380","mLowestPrice":"75.280","mChartImgUrl":"http://image.sinajs.cn/newchart/wap/cn_min_other/min_wap/big/sh601318.gif?r=0.45807125313510055","mCode":"sh601318","mtradingVolume":"41,754,851股","totalTime":96,"dataSourceName":"新浪股票","mYesterdayClosingPrice":"76.000","mCurrentPrice":"75.620"}}
```

#### 播放音乐
```json
{"source":"nlu","general":{"text":"为您播放：你是谁","type":"T"},"history":"cn.yunzhisheng.music","data":{"result":{"errorCode":0,"dataType":"child","totalTime":17,"musicinfo":[{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"000LIe1m1lrJ9Q","url":"http://113.105.167.147/amobile.music.tc.qq.com/C400000LIe1m1lrJ9Q.m4a?guid=3982823384&vkey=801FDF6AD17AE5599F301CB24D286B0BAECD493DB1A3BC2893FE94E3FEC28403372A477CAB883C2FEBDA0F38F2DD4AF076296FC5632E0887&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"尹姝贻"},{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"0047CVC04UTM0U","url":"http://113.106.207.150/amobile.music.tc.qq.com/C400003mIDji3zu64c.m4a?guid=3982823384&vkey=DCEDAC55F08F2F7D5877AC85ABF26BDF44BC7D36B3F0875D4483B007B9C5CD872C7E7C0CF73338510EEBF840C3D5079BFDE141A321214BE1&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"陈鹏"},{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"000iJE4N2Csdi9","url":"http://113.105.167.147/amobile.music.tc.qq.com/C400000LpnVH2V8dVv.m4a?guid=3982823384&vkey=6186A43BDFFC8D5A4CCCD5C4C3B49558F63874E5929F46AA1AA7D39A32FD4706EB70736A3B8D900F462E15712D4D999EAD5E103729DCAEA4&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"景甜"},{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"004Gyi5c3A7G68","url":"http://113.105.167.143/amobile.music.tc.qq.com/C400001WsH042YW96e.m4a?guid=3982823384&vkey=B1ACC5E7BD5EAF49EA04FABC53A6D5B979DE6A029F5BC3FC208DC732E7D81839C08CC781FC9417DA6CFCC248DED76B0203DA887FA508ADC0&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"涓子"},{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"004LcWvK1W97rr","url":"http://113.105.167.141/amobile.music.tc.qq.com/C400000I7Tj32ybWcr.m4a?guid=3982823384&vkey=A58188EAD2A4F16A1327D5DB628EB42F38628A58C8677C0090E803C470D8FB9116E6024E87FE4854BB6A9B45C85AF4137FBF52291818B8E6&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"Linda Sundblad"},{"play_count":10000,"audioType":1,"title":"你以为你是谁 (DJ何鹏版)","domainName":"music","id":"001OWh0m2LPoWt","url":"http://113.105.167.151/amobile.music.tc.qq.com/C400001OWh0m2LPoWt.m4a?guid=3982823384&vkey=B25382ACF4E1DDC5DA32595033C5F393CE1060A98E3F55CDB8BBDDD52FEBF87AD68983E7C3A3CB9DA22846610DAB0C4309506A555C39524B&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"门丽"},{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"001nzQZT3ybVsZ","url":"","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"郑俊弘"},{"play_count":10000,"audioType":1,"title":"Who You Are","domainName":"music","id":"003bMzZf2OBEcN","url":"","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"Jessie J"},{"play_count":10000,"audioType":1,"title":"你是谁","domainName":"music","id":"000Wyf3l373upX","url":"http://113.106.207.142/amobile.music.tc.qq.com/C400000Wyf3l373upX.m4a?guid=3982823384&vkey=75AECB47C9F05F67E5F244C21B9D7F1150B6A8822590771A1BA7CC927E660EE7B3FC6CCA529F8863754A59969B68CAB68C465D98BF993D8B&uin=&fromtag=66","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"杨学超,申静"},{"play_count":10000,"audioType":1,"title":"유후 (你是谁) (You, Who?)","domainName":"music","id":"0003mgZ12EllTK","url":"","duration":312,"tags":"怀旧,经典,老歌","episode":200,"resourceType":2,"artist":"Eric Nam,全昭弥"}],"searchType":"common","intent":"NAME","dataSourceCode":"child"}},"originIntent":{"nluSlotInfos":[]},"service":"cn.yunzhisheng.music","taskName":"search","code":"SEARCH_SONG","semantic":{"intent":{"song":"你是谁","keyword":"你是谁"}}}
```
