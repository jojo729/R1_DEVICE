package cn.yunzhisheng.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PinyinConverter {
    private static final int MANDPY_END = 40869;
    private static final int MANDPY_LEN = 20902;
    private static final int MANDPY_START = 19968;
    public static final String PINYIN_EXCLUDE = "|";
    public static final String PINYIN_SEPARATOR = " ";
    public static final String TAG = "PinyinConverter";
    private static byte[] mPinyinList = null;
    private static int mPinyinNum = 0;
    private static int mPinyinSize = 0;
    private static String mSimplified = "丑世丢丘两酉并井虎乘丐纠始进乾龟乾乱事于四岁亘亚斋亚大享京夜飞廉集集亡冰佛仙长刃仓低役夫帑倅徇信你侐胄似伫布占法酩并来觥阴米仑存似戴佞尽僒侣局男俣系辅侠修伡私效备鬻欣伥并俩俫仓个倏们幸仿值伦俱睬俭佞前伟赝侃奓逼侧侦偷备咱倢伪叟骂杰伧滀伞备效伐并家佣偬传伛债伤倾偻备仅佥仙动侨窘仆敞伪几惠舛侥偾恶雇仙僩价仪俊侬亿侩俭傻傤傧俦侪拟尽你偿质优豪储倏俪攒傩傥俨傫簪凶兑兑兔长儿儿兖始兜晃亡内两俞天冀艺帽冉册剐冒胄宜适蒙最寇富写幂讶泮泯浼涂净冻皑沧准凛渎凡凡居处风风风凯尴凭函刃创刉伶别劫劫删剖剁劫刺刭则锉克刹创劙刬创刚剥黥剑剂剐剩剑剀创铲戮划札斀镰剧刘刽刿剑剑剂剑剑质劳劝逼效劲敕勇剋倦戮来勉动勖务勋胜劳戮剽势绩犟剿劝勚跷剧劢勋勋励劝匀两匈丐丐匑脑柩匠炕笲箧匡匦汇匮奁奁椟柩区迅廿卒世协单率西节邛恤却即腭膝庞崖厍厅厕原历仅廒厌历厂厉严厣源去惠参参参收夬吏假叟尉丛寸吣吴呐吕吴尺呃嗜咒和啕讻员诱呗唣哑启吣问启启啖哑启哲衔咱唤岩喻丧吃乔单哟啼呛啬唝吗呜酪唢哔嗷叹哮喽呼啯呕啧尝唛啸哗然唠啸叽哓嗥呒啴恶吃啖器嗥嘘噅吟啮咝应哒哝哕嗳哙喷道吨当咛吓咄嗅哜尝嚏噜嘒啮哲浏囊咽呖咙啖颦向亸喾严馋嘤嚃嚣啭嗫嚣冁呓啰苏艰嘱谰啮回因渊囱回国图国日国胎国函囵国围圆圃园圆图图团圞土墣殁埙阱经忌坻巠堂墌台丘垢穴附雉坳堆垛守坝垂峡哲埒野堤垭碗采陷执泥坚垩岸垴突塍埚堡阶尧报场界垒块茔垲塑埘塍涂冢塳葬堆填坞埙阵隘盐埳场尘卤堑砖垫塈塔增坠硗垡墩地堕坛坟隍坛垯烨垦埤疆野学坛坏垱壤堑埙玺压垒圹垆坛坏垄垄睿坜廛埙坝塆壮壹卖壮壶壶婿壶寿寿壹斋变夏卯多晴够梦梦太比夹点奂畚奥奖奁夺奖奋姹媸妆妒你侄姊姗妊垓娟奸奸侄妍姆娱妆嫔妩娱娄斐姻妇淫娅丽偠偷亲妇娲婕偷妫媪妈媲妪傲嫩嬷妩娴娴婳妫媭娆嬿婵娇美嫱袅嫒娘嬷嫔奶婴婶兴昵懒娘懒婴娈孓信嗣胎孙掔孳学擘孽婴孪冗穹肉宜阱实屎宫采密寇采冤青鹤寝宁置宽宁寝实宁审写宽宠宝对叵专克将专寻对导尔尔菽尚么鲜鲜尪尥尪尪尴尴尴届尸屃漏屏屉屉屡层屦屩属屃仙岸会会出冈岭岫法峒卡峛峨峰岘嵘岛峡崇崃崑昆崖岗崙仑峥崒崛岽崟嵛峙嵇岚岩岁崔溪巅嵯嵃嵝崭崭岖岛岛隆嵚崦崂嶛峤峣嶕嶢峄峃险地嵘岛岭巀屿岳岿岩峦巅巅岩岩巘巢巯卮昭卺巽卷匝纸袋帅师裙带归帐带帧帏帮徽帼帻帮帜币币蒙帮帱襕并干几仄广么庄库松庶寓庙廋厕厢废厩厦庼厩厩荫厨厮庙厂庑废广廪庐厅厅巡迪迫回乃廻奘壹贰叁贰弑吊弪张强弼韘弹發别弹弥弯汇彙彝彝彝蒦蒦彦雕彨佛往陟径从从从徕借健遍复彷征德征彻侥仁仁帆应悟匆悴尤匆恍恿恒思怪吝耻悦德惠恘悦吝怖伣悮匆恼恶误德怅闷凄和惕欣恶揔恿德恼恽恻惛悖韪勉憩恪爱惬懦悫嫉惧怆惄恺整愑慎博忾恿栗态愠愩剽惨惭惭恸惯悫怄怂虑伤悭慑庆眷戚戚欲凭忧恸瞠憩惫怜凭愦慭慭惮惰憝愤憥悯怃宪忐忆坦恳应怿懔怀僾恹呆拟蒙怼懑恹忧怤惩忏懒怀悬忏惧欢慑恋戆戆戋贼戛战戗戬戏战戏户户厄卯在於拘捊拔拗秉柷抛拖拜抵据扩迁挆弄拿抉举挲弄搒挟插救旅搜擒舍扪卷栖扫抡抵挜挣挂采剔扒揭捷搔碰拣捏扬换掩揪挥插背摇总构揿损摇捣捶扇拓掏抢拳榨捂扛搜掴掼搂捴揸挚挚抠抟掺牵击撇捞揖挦撑挠捻挢操掸掸拨扯抚擆扑揿划搅挞挝捡拥掳择扑击挡接担携据掹挤抬捣揽举拟护截摈拧搁掷扩摩撷摊摆擞撸扰擂摅攒撵拢拦撄搀撺携摄捃攒挛摊搲搅揽考叩叙教败叙典掇扬敌数驱敿缮敛毙敩敩学齐齐斋斓升斝斩斫斫斫断旆旗旒敷旖帜幡幡旃既祸协春升昃昉慎昏温冬昂旷时晃皎晋曈赅昼澈晢晚晰晓晕晖映晴旸普曒皓畅气历暂亵暤昵暨晔晔历昙晓向星暴暧晔旷叠昽晒烛曳书曹曾勖会裨朗明望胴朗胧术朵东松楠丫桦杕栌栴拐枼柿蘖楙松楠栅查柩柳梅荣契桭刊柏枻筏拶樱柳栈杯栝柳杆栀杒枧条枭松梨槟梨弃柄棋枨枣栋樊栈栖梾碗乘桠楚检规棬棕笺匾杨煜枫椭榆柍桢业梅极乐槚艗梓干杩荣桤槁槙耨构枪梅慉杠桌橐桥样栊桑梿椠椁概概椮桨椢槔规桩乐枞灌橹梁楼标柘枢样条权棇丛朴树桦椫桡榴桥蕣橛机椭柠横柳槔檩杉柽朴档桧槚检樯栎梼枱棋槟柠槛棋榓棹柜椿凳橱藟橹槥榈栉椟橼栎梿橱槠栌枥橥榇蘖栊樵榉棂樱檃栏榉丛权郁椤櫕栾欋榄郁棂罐款钦款叹欧欢啸缮敛斁欤欢步步前涩齿踵岁岁历历归歹殁夭残殒殇鸱殚斁僵殓殡歼歼杀壳壳毁殴每毗鞠球鞠屄毿毹毵牦毡毡氇橛气氖氖气氢氩氡氧氲冰水溺泛沔泛泅污污氿阱决冱没冲霟派况滹溯盥洛泄汹净漘浃渗泾莅泪涨凉凄泪浙渌净凌沦渊涞清浅饮泊渊渊渴济涉涩漪溪渊涣减沨涡濡测浑凑餐洦浈涌湫汤涎淳涅淄浲沩波盈蒙准沟渽温浉涢湿沧灭涤荥汇淫泷漘沪滞渗卤浒浟浐滚满渔涯溇溉沤汉涟洼渍涨溆渐浆颍洪漱灌溆泼洁妫潜润浔溃滗潺涠涩涩澄浇涝浛溜涧潟泞凛渑泽滪泶浍淀漫浊浓浸涩渚沵湿泞溁浕济涛滥浚潍滨潜阔溅泺滤澛滢渎泻沈浏瀸涩澄濒泸沥净潇潆滈潴泷濑濑弥潋澜沣滠法滟瀅洒濽滟漓滩瀛灏湾滦滟赣滟光灱辉赤灾气炎光光照为焕乌灭灾恢烈炯烃赤熙焰无腐爝煜辉炼炜熙暖暖烟炸茕焕烦燧炀烈熙熊荧焰炝爝炙热颎焜炽烨焰灯炖焚磷烔烧焚烂烫焖营灿熜毁烛爧照烩熇熏烬焘熏烁炉爝燎燮烨烂烂烛争称为攫爷俎尔墉墙笺窗闸榜牖牍牁抵牵犁奔荦牦牺犊牺犨豺状貉尨狭狈悍羌狰猎猿貒犹狲呆狱狮嗥羌嗥奖貘獒嗥兽独狯猃狝狞猵获猎犷兽獭献狝猡妙珏瓷珍琴宝佩璎玟玫现璃盏圣雕琴珐珲玳玮玚琉琐瑶莹玛琅玱琨璟琏烨瑰琎琉玑瑷珰环玙瑸玺璇璃瓒琼瑰珑璎瓒瓶瓷瓯砖瓮甜尝晴产产苏町圳畀亩留亩耕界逼亩疆亩亩垓毕略番画畲畬亩异留叠画当疆叠疆畴副疊叠疏肛瘁胝痱恫蛔痉秃酸瘦胀痹哑愈疯疡痪苦喑瘗疮疟胀嗽瘘瘘疗憔瘤痨痫瘅痫瘝愈疠凛凛瘪痴痒疖症癞癣瘿瘾痈瘫癫瘰发皂貌即皋皑皓皞晓晔皞星皝疱毬齇鼓皲皲皱齇鼓盈杯盍钵碗簠盗盏尽监盘卢荡明视仿视慎真视眦众眽眉睁睐睥睾眯瞠翳瞒了瞆睑曌瞅蒙矏瞢眬瞰瞩矜矧侯矫碇砌玟碎砣炮矿铨朱碍研硁硖砗砚峪碜崟硕砧砀磌确码砒硙陨砖砖硵碜碛矶涧碇硗涧硚硷坠碣础碍磕磁礴矿砺砾矾炮砻砻罐礛衽佑秘算祗捆禘禄褚祸祯祎褙稷祰祃祺御祀禅礼祢祷秃籼年秋耘粳饫拌耠稿税秆粳棱禀糯秸种称稻稚稿谷穗糠稚稣积颖稳秋稆秾穑秽糯颓稳称获稆穑挖阱牢窗宬窝洼穷窑窑窎窭窥窗巢窾寮灶窜窍穷窦灶窃奇升妙伫龙并谊竖歪竞竞篷篪箍骼矢篪笔笋笄箂策管笕筒箸笮筐篦个笺篪筝帚劄筍筅椸节范筑箧嵌箍饐筼箬涅笃筛垄彗筚箦筘篓蓑篡蓑築箫箪简篑箫簪箄虡簬檐筜签帘篮簣筹藤籀籀签藤箓篯箨籯籁笼奁签奁笾簖篱筛箩吁籸糁秕粹米黐肃磷妆粤稗糁粽谷妆糖糁粪糨馍糁糯粮粽团粝籴粜纠功纠纪纣约红纡纥纨纫纹纳纽纾纯纰纼纱纮纸级纷纭纴纺綷扎紘扎细绂绁绅纻碱绍绀绋绐绌终弦组绊帞纩经纴绗绁结绝绦洁绔绞络绚线给绒绖统丝绛绘绝茧绢纼绑统绡绠绨绣绤绥捆经织缝继综缍绿绸绻绍线绶维绹绾纲网绷缀彩纶绺绮绽绰绫绵绲缁紧绯缗总繁绿绪缨绱绪缃缄缂线绵缉缎缔缗缘褓缌编缓縆总缅纬缑缈练缏缇致缊赪缠绐鞋缘总绳萦缙缢缒绉缣缊缞绦缚缜缟缛县纵绦缝缡缩纵缧纤缦絷缕缥总绩绷缫缪繦纤系绣穗缯织缮伞翻缭绰绕绣缋纂嬉绳绘系茧缰缳缲缴绎继缤缱褴蓬颣缬缵纩续累纤缠缠缨才纤缵缆缧卸缶缺钵瓶缸罐樽坛瓮罂坛深挂罚骂罢罚罶罩罗罴羁芈菱羌绒群羟羡义羹膻膻翅翠习翕翃翅玩翚翱翘翱翙耇耇耋专耧耢获聃职婿圣闻婿聪联联联联聪声耸听聩聂职聍听听聋肃肇臆肯胳胣胚胖疣吻肯拇骼膀胸脆脢胁胁脉胁吻胫唇脱脑痞胀胼肾脾脶脑肿脚肠腽瘦嗉膂肠腘膀肤胶胴腻髓胆脍脓腊脸臀脐膑腊胪裸脏脔臜卧临皋臭台陷舄与兴举旧舐铺舖馆辖舤船舻造舶舱樯橹舣楫舰橹舻艰艳艳艽芋春刍刈苎菰兹葱荔萸兹荆筋庄豆庄蓬荇茎菡荚苋節菟莽灾果芲芹苔干华庵帚春苌莱苕荠莞策蓖艺箔万萱荝莴菹叶庵荭莚参盖苇药荤篆搜莼莳莅芻芸苍荪席盖蓨萏参菱庵莲苁莼荜妍菱荻卜蒂参蒌蒋葱茑荫蔻蒨麻藏箦藜荨蒇蕊藟荞华莸荛萼蒉荡芜萧薩蓣萱荟蓟芗姜参蔷园荙剃莶荐萨薰药稗苧荠藻蔤蓝荩艺药藨薮樵稿蕴苈蔼蔺萱櫬萚蕊蕊蕲芦苏蕴苹薰骥絅萱苏蓬藓蔹蔹茏繇兰荡夔蓠藟萝齑夔藟处呼虚虚虏虎号亏虬蛇蚊蛔鼢蚺鲍萤蛔珕蛱蜕贝蜭蚬蛔蜞蚌蚋蚕蜡蚀猬虾虱猿虻蜗蝇蛳融蠹蚂蚊萤蝼螀蚊蛰蟆蝈蟛虮蟨蝉蛲蟏虫蛏蠡蚁蚃蝇虿蛎蝎蟹蛴蝾茧蚝玭蜡蛎蟗蠹蟏蝫蜂蛊蚕蠼蚕蛮衄众脉喀炫术同胡卫道冲卫道衮帙帙衽绗袅夹里补装裙里褒裈褫裴裴制裉复裈袖袆裤裢褛亵褒幞裥禅杂袯袄裣裆袒褴幞襡袜衬袭襕褶襕覆羁见观弁规觉觅觅视觇诊觉觋觍觎览睹亲觊觏觑觐观瞪觑觉觑览觌观筋粗觯抵解觲角觞觥觯触觿觿订讣叫计讯讧讨讦讱训誾讪讫讬记讯讹讶讼矧吟欣诀讷讻访信设詽许译诉诃诊注证诟诂诋咒讵诈诧诒诏评诐诇诎诅词咏诩询诣谎试察诗诧诟诡诠诘话该详诜酬谜诙讻诖诔诛诓夸诬志认诳诶诞悖诱诮语诚诫诬误诰诵诲说说读唱谁课诎谇诽谊訚调谄谆谈诿请诤诹愆诼谅论谂话谀谍谞谝喧谥诨谔谛谐谱谏启谕谘讳谙谌讽诸谚谖诺谋谒谓誊诌谎歌谜谧嗔谑啼谡谤谦谥讲谢谣谣谟谟商谪谬讴谨呼谩嘲哗着嘻证谰讹应谲讥撰谮识谯谭谮谱噪谠谇詀哝噫蔼谵毁译议善让谴怼护诪号誉谫慧读谪赞谉变詟宴谔雠讆仇谗让谰谶赞呓谠谳芊溪谿渎岂登竖丰秩艳艳猪蹢豮貔狗貊豻狸猊猫獾贝贞负财贡玩贫货贩贪贯责质贰贮贳赀贰贵贬买贷贶费贴贻贸贺贲赂赁贿赅资贾恤贼赃贱赈赊宾宾赇赊赃赒赉赞赐琛赏赔赓贤卖贱赋赕质赍账赌赆赖赗剩赚赙购赛赜败贽赘赟赠赞赝赡裸氽赢赆贤赃赑赎赝赏赣赃赪走趑趁赶赵趑趋躁趱刖迹跺踩胫疏脚践踪碰逾遁踊腿跄蹄溜跸迹跖蹒踪跹蹴蹶踏跷跷跶躄趸踌跻跃踯跞踬蹰躏跹躗蹑蹋蹿躜躏耽体躲躬裸矮軂躯偻亸职体车轧轨军轪轩轫軑轭软转轰轸辈轭轴轵轺轲轶轻轼较辂辁辀载轾辆輶辄挽辅轻辄辆辎辉辋辍辊辇辈轮辌软辑辏轰输辐辒辗舆辒毂辖辕辘转辙轿辚舆轰辔轹轳辞罪辩辣辞办辨辥斑辞辫辩农农迂迂迤币达逃迤回移迥递迳这连回奔错迸遊周进逖达远随迟侦游运过达违遥逊递迟远溯适迟绕迁选遗辽迈还迩边边迟游逻逦邦邢邽那郎郎郏邮部乡郓乡乡邹邬乡郧鄡邓郑邻郸邺郐酆邝酂廛呵酂郦醉酬腌盏醇酝酦丑酝酱医酱拨酿酬宴醾酿衅酾醾酽辨释释厘钆钇钌钊钉钋针釜钓钐仿后征钏钒钎铓钗钍钕钯钫钘钭钺铅钚钠钝钩钤钣钑钞钮钧钓钟钙钬钛钪铌铈钶铃钴钹铍钰钸铀钿钾铁钜狝伙仅钻铊铉刨铋锄铂钷钳铆铅钺钵钩钲玺玺钼钽矿铏铰铒铬铪银钧铳铜铚钐铣钘铨铁铢铭铫铦衔钧铑铷铱铟铵铥铕铯铐钱铞焊锐销锈锑锉铦汞铝锒锌钡铤铗锋志钕铻矿锊锓锊铘锄锃锔锇铓铺锐铖锆锂铽铸铥锍锯钢锞录锖锫锩沈铔锥锕锟锤锱铮锛锬锭锜钱锦锚锠锡炼锢错珍录锰表钆铼锨钔锴锳炼锅镀锷铡钖锻锽锸锲戣锘锹宁锹锾鉴键锶锗针钟锱镁镅镑辖耨镕锁枪镉锤镈盘锝钢镃钨蓥镏铠铩锼錽镐镇镇榔镒镋镍镓镌锁罅锋镎锁镞罐镟链镆镙镠镝铿锵鋿镗镘镛铲锋镜镖镂锈錾罅镚铮铧镤镪锈铙铴镣铹镦镡锏钟镫铔镢鐕镨鼎锎锏镄燧镌镰川玡锳锹镯镭铁镮铎铛钽镱锿铸镬镔鉴鉴镲锧钻矿鋿镴铄镳刨镥鉴镧钥镵镶镊襻锣钻銮凿镋镧桌长弥门闩闪门闫闭闬闭开闶闳闰闲闲间闵斗闸闹闰阂关阁合阀哄闺闽阃阆闾阅阅阐阒躏阊杀壸阉阎阏阍阈阌哄褒阒阌板闱阔阕矩阑阇阗阘闿阖阙闯关斗启窥关嫖阚阓阐辟阛闼阴阳陡升坑址垝峻峭陉陕陕升阵陷岛阴陈陆升堙阳狭阴堤陧队昏暗阶陨坞堂际岛隙隐邻陴随险陑隐骘陇隶隶只隽杂虽双雏杂鸡离难集氛云电宕灵沾零雾灵霢雾叇霁蒙雷雳霭叇灵叆宝青靓静靔面疱腼靥韧韧纽鞍巩琫绱于秋鞘韫韬鞋鞟鞒靴鞯缰鞑千袜鞯韦韧鞘韩韪革韬鞲韫袜韭韱齑韵韸响页顶顷项顺顸须颐顼颂颀聿颃预顽颁顿颇领颈颌额颉颐颐颏类俯颊头颊颋颖颔颈颓颓频赖颓髭髭颔颗悴颕颐腮题额颚颜颙颛颜囟愿颡颠颠类颟颢憔顾巽颤显颦颅颞颧风飐飑飒台刮飓飙飔飏飖飕飗飘飘飗飙飙飚飚飞翻餐饥饤饲饦饘饨饪饫饬饮饭饭餐饮饴饕饱餮饲饱饰饪饺饸饼钟糍饷养饵饽馁饿哺馂饾余饰肴馄馃饰饯啜饼馅喂馆暖糊糇饧喂糖馎糕饩馈馏馊馌馍糖馒馐馑馓馈馔膳饥饶飨餀餍馍馋馕穣马驭冯驮驰驯驲驴驳驱驱驻驽驹驵驾骀驸驶驼驼驷骝骂骈腾骇骃骆骝骎臞夹骏骋骍骓骈骒骑骐验骚验骛骢骗駩鬃骙骞骘骝腾驺骚骟骦骡蓦骜骖骠骢驱跸骅骕骁骣骝骄验骡惊驿驼骤驴腾骧骥骦欢骊肮体腿鲠腢髓膀髅脏体髌髋颅高髡髹髯髡鬓发发剃剃鬓松鬃胡须鬣鬓斗斗闹哄阋斗斗斗阄郁郁釜魅尬魉尴丑魇鱼鱽鲨鲀鲁鲈鲂鱿鲄曲鲅鲷鲧鲇鲐鲍鲋鲝鳎鲒鲕鲖鲔鲛鲑鲜鲪鳀鳘鲧鲠稣鲩鲤鲨鲨鲻鲯鲭鲞鲷鲱鲵鲲鲳鲸鲮鲰鲶鲩鳀蠩鲗鳊鲩鲽鳇鳅鳄鳆鳃鳒鳑鲥鳏藤鳎鳐鳍鲢鳌鳓鳘鲦鲣鳗鳛鳔鳕鳖鲅鲟鳇鳟鳝鳝鳜鳞鲟鲎鲙鳣鳢鲿鲚魣鳄鲈鲡鲜么齇鸟凫鸠雁凫鸡凤鸤凤鸣鸢鸩鸨雁鸦凤鸥玳鸰鸵鸳雌鸲鸮鸱鸪鸯鴥鸭鴥莺鸸鸹枭鸿鹭鸽鸺鸼鸱鸾鹃鹆鹁鵔鹈鹅鹅鹄鹉鹌鹏鳹鹎雕鹊鸦鹓鸫鹑鹒鸡鹌鹙鹕鹗鹖鹍鹛鹜鸧鹤莺鹟鹤鹠鹡鹘鹣隼鹚鹚鹢鹞鸡鷏鹝鹧鹥鸥鸷鹨鸶鹪莺鹔鹩鹫鹇鹬鹰鹭鸴鹯莺鹲鸬鶴鹦鹳鹂鸾卤矜硷咸鹾硷碱盐粗粗麇麟麇丽獐羚粗麦麸面面麸曲麸面面么縻黄黉黑默点党黛黪霉黩黾鼋蛙蛛蜘鳌鳖鼍鼎鼓冬鼘鼠貂鼹嗅齐斋齑齿龀龀龁牙龂龅齰龇龃龆龄出齛龈啮咬龊龉龋腭龌噤龙庞恭庞龙龚龛灵龟秋鳖吹和角";
    private static Map<Character, String> mSpecialXingMap;
    private static String mTraditional = "丒丗丟丠両丣並丼乕乗乢乣乨乵乹亀亁亂亊亐亖亗亙亜亝亞亣亯亰亱亴亷亼亽亾仌仏仚仧仭仺仾伇伕伖伜伨伩伱伵伷佀佇佈佔佱佲併來侊侌侎侖侟価侢侫侭侰侶侷侽俁係俌俠俢俥俬俲俻俼俽倀倂倆倈倉個倐們倖倣値倫倶倸倹倿偂偉偐偘偧偪側偵偸偹偺偼偽傁傌傑傖傗傘備傚傠傡傢傭傯傳傴債傷傾僂僃僅僉僊働僑僒僕僘僞僟僡僢僥僨僫僱僲僴價儀儁儂億儈儉儍儎儐儔儕儗儘儞償儨優儫儲儵儷儹儺儻儼儽兂兇兊兌兎兏児兒兗兘兠兤兦內兩兪兲兾兿冃冄冊冎冐冑冝冟冡冣冦冨冩冪冴冸冺凂凃凈凍凒凔凖凜凟凢凣凥処凨凬凮凱凲凴凾刄刅刏刢別刦刧刪刯刴刼刾剄則剉剋剎剏剓剗剙剛剝剠剣剤剮剰剱剴創剷剹劃劄劅劆劇劉劊劌劍劎劑劒劔劕労劷劸効勁勅勈勊勌勎勑勔動勗務勛勝勞勠勡勢勣勥勦勧勩勪勮勱勲勳勵勸勻匁匂匃匄匔匘匛匞匟匥匧匩匭匯匱匲匳匵匶區卂卄卆卋協単卛卥卪卭卹卻卽卾厀厐厓厙厛厠厡厤厪厫厭厯厰厲厳厴厵厺叀參叄叅収叏叓叚叜叞叢吋吢吳吶呂呉呎呝呩呪咊咷哅員唀唄唕唖唘唚問啓啔啗啞啟啠啣喒喚喦喩喪喫喬單喲嗁嗆嗇嗊嗎嗚嗠嗩嗶嗸嘆嘋嘍嘑嘓嘔嘖嘗嘜嘨嘩嘫嘮嘯嘰嘵嘷嘸嘽噁噄噉噐噑噓噕噖噛噝噟噠噥噦噯噲噴噵噸噹嚀嚇嚉嚊嚌嚐嚔嚕嚖嚙嚞嚠嚢嚥嚦嚨嚪嚬嚮嚲嚳嚴嚵嚶嚺嚻囀囁囂囅囈囉囌囏囑囒囓囘囙囦囪囬囯図囶囸囻囼圀圅圇國圍圎圑園圓圖圗團圝圡圤圽坃坓坕坖坘坙坣坧坮坵坸坹坿垁垇垖垜垨垻埀埉埑埓埜埞埡埦埰埳執埿堅堊堓堖堗堘堝堢堦堯報場堺塁塊塋塏塐塒塖塗塚塜塟塠塡塢塤塦塧塩塪塲塵塷塹塼墊墍墖増墜墝墢墪墬墮墰墳墴墵墶墷墾壀壃壄壆壇壊壋壌壍壎壐壓壘壙壚壜壞壟壠壡壢壥壦壩壪壯壱売壵壷壺壻壼壽夀夁夈変夓夘夛夝夠夢夣夳夶夾奌奐奙奧奨奩奪奬奮奼妛妝妬妳妷姉姍姙姟姢姦姧姪姸娒娛娤娦娬娯婁婔婣婦婬婭婯婹婾媇媍媧媫媮媯媼媽嫓嫗嫯嫰嫲嫵嫺嫻嫿嬀嬃嬈嬊嬋嬌嬍嬙嬝嬡嬢嬤嬪嬭嬰嬸嬹嬺嬾孃孄孆孌孒孞孠孡孫孯孶學孹孼孾孿宂宆宍宐宑実宩宮宷宻宼寀寃寈寉寑寕寘寛寜寢實寧審寫寬寵寶対尀専尅將專尋對導尒尓尗尙尛尟尠尣尦尩尫尲尶尷屆屍屓屚屛屜屟屢層屨屫屬屭屳屵屶屷岀岡岺峀峜峝峠峢峩峯峴峵島峽崈崍崐崑崕崗崘崙崢崪崫崬崯崳崻嵆嵐嵒嵗嵟嵠嵮嵳嵼嶁嶃嶄嶇嶋嶌嶐嶔嶖嶗嶚嶠嶢嶣嶤嶧嶨嶮嶳嶸嶹嶺嶻嶼嶽巋巌巒巓巔巖巗巚巣巰巵巶巹巺巻帀帋帒帥師帬帯帰帳帶幀幃幇幑幗幘幚幟幣幤幪幫幬幱幷幹幾庂広庅庒庫庺庻庽庿廀廁廂廃廄廈廎廏廐廕廚廝廟廠廡廢廣廩廬廰廳廵廸廹廻廼廽弉弌弍弎弐弒弔弳張強弻弽弾彂彆彈彌彎彙彚彛彜彞彟彠彥彫彲彿徃徏徑従徔從徠徣徤徧復徬徰徳徴徹徺忈忎忛応忢忩忰怣怱怳怺恆恖恠恡恥恱恴恵恷悅悋悑悓悞悤悩悪悮悳悵悶悽惒惖惞惡惣惥惪惱惲惻惽愂愇愐愒愙愛愜愞愨愱愳愴愵愷愸愹愼愽愾慂慄態慍慐慓慘慙慚慟慣慤慪慫慮慯慳慴慶慻慼慽慾慿憂憅憆憇憊憐憑憒憖憗憚憜憞憤憦憫憮憲憳憶憻懇應懌懍懐懓懕懛懝懞懟懣懨懮懯懲懴懶懷懸懺懼懽懾戀戅戇戔戝戞戦戧戩戯戰戲戶戸戹戼扗扵抅抙抜抝抦拀拋拕拝拞拠拡拪挅挊挐挗挙挱挵挷挾挿捄捛捜捦捨捫捲捿掃掄掋掗掙掛採掦掱掲掶掻掽揀揑揚換揜揫揮揷揹揺搃搆搇損搖搗搥搧搨搯搶搼搾摀摃摉摑摜摟摠摣摯摰摳摶摻撁撃撆撈撎撏撐撓撚撟撡撢撣撥撦撫撯撲撳撶撹撻撾撿擁擄擇擈擊擋擑擔擕據擝擠擡擣擥擧擬擭擮擯擰擱擲擴擵擷擹擺擻擼擾攂攄攅攆攏攔攖攙攛攜攝攟攢攣攤攨攪攬攷敂敍敎敗敘敟敪敭敵數敺敽敾斂斃斅斆斈斉斊斎斕斘斚斬斮斲斵斷斾旂旈旉旑旘旙旛旜旣旤旪旾昇昗昘昚昬昷昸昻昿時晄晈晉晍晐晝晢晣晩晳暁暈暉暎暒暘暜暞暠暢暣暦暫暬暭暱曁曄曅曆曇曉曏曐曓曖曗曠曡曨曬曯曵書曺曽朂會朇朖朙朢朣朤朧朮朶東枀枏枒枠枤枦枬枴枽枾枿柕柗柟柵査柾栁栂栄栔栕栞栢栧栰桚桜桞桟桮桰桺桿梔梕梘條梟梥梸梹棃棄棅棊棖棗棟棥棧棲棶椀椉椏椘検椝椦椶椾楄楊楍楓楕楡楧楨業楳極楽榎榏榟榦榪榮榿槀槇槈構槍槑槒槓槕槖槗様槞槡槤槧槨槩槪槮槳槶槹槼樁樂樅樌樐樑樓標樜樞樣樤権樬樷樸樹樺樿橈橊橋橓橜機橢橣橫橮橰檁檆檉檏檔檜檟檢檣檪檮檯檱檳檸檻櫀櫁櫂櫃櫄櫈櫉櫐櫓櫘櫚櫛櫝櫞櫟櫣櫥櫧櫨櫪櫫櫬櫱櫳櫵櫸櫺櫻櫽欄欅欉權欎欏欑欒欔欖欝欞欟欵欽歀歎歐歓歗歚歛歝歟歡歨歩歬歮歯歱歲歳歴歷歸歺歿殀殘殞殤殦殫殬殭殮殯殱殲殺殻殼毀毆毎毘毩毬毱毴毶毺毿氂氈氊氌氒気氝氞氣氫氬氭氱氳氷氺氼氾汅汎汓汙汚汣汬決沍沒沖沗沠況泘泝泴洜洩洶浄浱浹涁涇涖涙涱涼淒淚淛淥淨淩淪淵淶淸淺淾淿渁渆渇済渉渋渏渓渕渙減渢渦渪測渾湊湌湐湞湧湬湯湺湻湼湽溄溈溊溋溕準溝溨溫溮溳溼滄滅滌滎滙滛滝滣滬滯滲滷滸滺滻滾滿漁漄漊漑漚漢漣漥漬漲漵漸漿潁潂潄潅潊潑潔潙潛潤潯潰潷潹潿澀澁澂澆澇澏澑澗澙澝澟澠澤澦澩澮澱澷濁濃濅濇濐濔濕濘濚濜濟濤濫濬濰濱濳濶濺濼濾瀂瀅瀆瀉瀋瀏瀐瀒瀓瀕瀘瀝瀞瀟瀠瀥瀦瀧瀨瀬瀰瀲瀾灃灄灋灎灐灑灒灔灕灘灜灝灣灤灧灨灩灮灲灳灻災炁炏炗炛炤為烉烏烕烖烣烮烱烴烾焈焔無焤焳焴煇煉煒煕煖煗煙煠煢煥煩煫煬煭熈熋熒熖熗熦熫熱熲熴熾燁燄燈燉燌燐燑燒燓燗燙燜營燦燪燬燭燯燳燴燺燻燼燾爋爍爐爑爒爕爗爛爤爥爭爯爲爴爺爼爾牅牆牋牎牐牓牗牘牱牴牽犂犇犖犛犠犢犧犫犲狀狢狵狹狽猂猐猙猟猨猯猶猻獃獄獅獆獇獋獎獏獓獔獣獨獪獫獮獰獱獲獵獷獸獺獻獼玀玅玨珁珎珡珤珮珱珳珻現琍琖琞琱琹琺琿瑇瑋瑒瑠瑣瑤瑩瑪瑯瑲瑻璄璉璍璝璡璢璣璦璫環璵璸璽璿瓈瓉瓊瓌瓏瓔瓚甁甆甌甎甕甛甞甠產産甦甼甽畁畂畄畆畊畍畐畒畕畝畞畡畢畧畨畫畬畭畮異畱畳畵當畺疂疅疇疈疉疊疎疘疩疷疿痌痐痙痜痠痩痮痺瘂瘉瘋瘍瘓瘔瘖瘞瘡瘧瘬瘶瘺瘻療癄癅癆癇癉癎癏癒癘癛癝癟癡癢癤癥癩癬癭癮癰癱癲癳發皁皃皍皐皚皜皡皢皣皥皨皩皰皳皶皷皸皹皺皻皼盁盃盇盋盌盙盜盞盡監盤盧盪眀眂眆眎眘眞眡眥眾眿睂睜睞睤睪瞇瞋瞖瞞瞭瞶瞼瞾矁矇矈矒矓矙矚矝矤矦矯矴矵砇砕砤砲砿硂硃硋硏硜硤硨硯硲硶碒碩碪碭碵確碼磇磑磒磗磚磠磣磧磯磵磸磽礀礄礆礈礍礎礙礚礠礡礦礪礫礬礮礱礲礶礷祍祐祕祘祬祵祶祿禇禍禎禕禙禝禞禡禥禦禩禪禮禰禱禿秈秊秌秐秔秗秚秴稁稅稈稉稜稟稬稭種稱稲稺稾穀穂穅穉穌積穎穏穐穞穠穡穢穤穨穩穪穫穭穯穵穽窂窓窚窩窪窮窯窰窵窶窺窻窼窽竂竃竄竅竆竇竈竊竒竔竗竚竜竝竩竪竵競竸竼竾笟胳笶筂筆筍筓筙筞筦筧筩筯筰筺箆箇箋箎箏箒箚箰箲箷節範築篋篏篐篒篔篛篞篤篩篭篲篳簀簆簍簑簒簔簗簘簞簡簣簫簮簰簴簵簷簹簽簾籃籄籌籐籒籕籖籘籙籛籜籝籟籠籢籤籨籩籪籬籭籮籲籶籸粃粋粎粚粛粦粧粵粺糂糉糓糚糛糝糞糡糢糣糥糧糭糰糲糴糶糺糼糾紀紂約紅紆紇紈紉紋納紐紓純紕紖紗紘紙級紛紜紝紡紣紥紬紭紮細紱紲紳紵硷紹紺紼紿絀終絃組絆絈絋経絍絎絏結絕絛絜絝絞絡絢絤給絨絰統絲絳絵絶絸絹絼綁綂綃綆綈綉綌綏綑經綕綘継綜綞綠綢綣綤綫綬維綯綰綱網綳綴綵綸綹綺綻綽綾綿緄緇緊緋緍総緐緑緒緓緔緖緗緘緙線緜緝緞締緡緣緥緦編緩緪緫緬緯緱緲練緶緹緻緼緽緾緿縀縁縂縄縈縉縊縋縐縑縕縗縚縛縝縞縟縣縦縧縫縭縮縱縲縴縵縶縷縹總績繃繅繆繈繊繋繍繐繒織繕繖繙繚繛繞繡繢繤繥繩繪繫繭繮繯繰繳繹繼繽繾繿纄纇纈纉纊續纍纎纏纒纓纔纖纘纜纝缷缻缼缽缾罁罆罇罈罋罌罎罙罣罰罵罷罸羀羄羅羆羈羋羐羗羢羣羥羨義羮羴羶翄翆習翖翝翤翫翬翶翹翺翽耈耉耊耑耬耮耯耼聀聓聖聞聟聡聨聫聮聯聰聲聳聴聵聶職聹聼聽聾肅肈肊肎肐肔肧肨肬肳肻胟胢胮胷脃脄脅脇脈脋脗脛脣脫脳脴脹腁腎腗腡腦腫腳腸膃膄膆膐膓膕膖膚膞膠膧膩膸膽膾膿臈臉臋臍臏臘臚臝臟臠臢臥臨臯臰臺臽舃與興舉舊舓舖舗舘舝舧舩舮艁艊艙艢艣艤艥艦艪艫艱艶艷芁芌芚芻苅苧苽茊茐茘茰茲荊荕荘荳莊莑莕莖莟莢莧莭莵莾菑菓菕菦菭乾華菴菷萅萇萊萔萕萖萗萞萟萡萬萲萴萵葅葉葊葒葕葠葢葦葯葷蒃蒐蒓蒔蒞蒭蒷蒼蓀蓆蓋蓚蓞蓡蓤蓭蓮蓯蓴蓽蔅蔆蔐蔔蔕蔘蔞蔣蔥蔦蔭蔲蔳蔴蔵蔶蔾蕁蕆蕋蕌蕎蕐蕕蕘蕚蕢蕩蕪蕭蕯蕷蕿薈薊薌薑薓薔薗薘薙薟薦薩薫薬薭薴薺薻藌藍藎藝藥藧藪藮藳藴藶藹藺藼藽蘀蘂蘃蘄蘆蘇蘊蘋蘍蘎蘏蘐蘓蘕蘚蘝蘞蘢蘨蘭蘯蘷蘺蘽蘿虀虁虆處虖虗虛虜虝號虧虯虵蚉蚘蚠蚦蚫蛍蛕蛠蛺蛻蛽蛿蜆蜖蜝蜯蜹蝅蝋蝕蝟蝦蝨蝯蝱蝸蝿螄螎螙螞螡螢螻螿蟁蟄蟇蟈蟚蟣蟩蟬蟯蟰蟲蟶蟸蟻蠁蠅蠆蠇蠍蠏蠐蠑蠒蠔蠙蠟蠣蠤蠧蠨蠩蠭蠱蠶蠷蠺蠻衂衆衇衉衒術衕衚衛衜衝衞衟袞袟袠袵裄裊裌裏補裝裠裡裦裩裭裵裶製褃複褌褎褘褲褳褸褻襃襆襉襌襍襏襖襝襠襢襤襥襩襪襯襲襴襵襽覄覉見覌覍規覐覓覔視覘覙覚覡覥覦覧覩親覬覯覰覲観覴覷覺覻覽覿觀觔觕觗觝觧觪觮觴觵觶觸觹觽訂訃訆計訊訌討訐訒訓訔訕訖託記訙訛訝訟訠訡訢訣訥訩訪訫設訮許訳訴訶診註証訽詁詆詋詎詐詑詒詔評詖詗詘詛詞詠詡詢詣詤試詧詩詫詬詭詮詰話該詳詵詶詸詼詾詿誄誅誆誇誈誌認誑誒誕誖誘誚語誠誡誣誤誥誦誨說説読誯誰課誳誶誹誼誾調諂諄談諉請諍諏諐諑諒論諗諙諛諜諝諞諠諡諢諤諦諧諩諫諬諭諮諱諳諶諷諸諺諼諾謀謁謂謄謅謊謌謎謐謓謔謕謖謗謙謚講謝謠謡謨謩謪謫謬謳謹謼謾謿譁著譆證譋譌譍譎譏譔譖識譙譚譛譜譟譡譢譧譨譩譪譫譭譯議譱譲譴譵護譸譹譽譾譿讀讁讃讅變讋讌讍讎讏讐讒讓讕讖讚讛讜讞谸谿豀豄豈豋豎豐豒豓豔豬豴豶豼豿貃貋貍貎貓貛貝貞負財貢貦貧貨販貪貫責貭貮貯貰貲貳貴貶買貸貺費貼貽貿賀賁賂賃賄賅資賈賉賊賍賎賑賒賓賔賕賖賘賙賚賛賜賝賞賠賡賢賣賤賦賧質賫賬賭賮賴賵賸賺賻購賽賾贁贄贅贇贈贊贋贍倮汆贏贐贒贓贔贖贗贘贛贜赬赱赼趂趕趙趦趨趮趲跀跡跥跴踁踈踋踐踨踫踰踲踴蹆蹌蹏蹓蹕蹟蹠蹣蹤蹮蹵蹷蹹蹺蹻躂躃躉躊躋躍躑躒躓躕躙躚躛躡躢躥躦躪躭躰躱躳躶躷躼軀軁軃軄軆車軋軌軍軑軒軔軚軛軟転軣軫軰軶軸軹軺軻軼軽軾較輅輇輈載輊輌輏輒輓輔輕輙輛輜輝輞輟輥輦輩輪輬輭輯輳輷輸輻輼輾輿轀轂轄轅轆轉轍轎轔轝轟轡轢轤辝辠辡辢辤辦辧辪辬辭辮辯農辳込迃迆迊迏迯迱迴迻逈逓逕這連逥逩逪逬逰週進逷逹逺遀遅遉遊運過達違遙遜遞遟遠遡適遲遶遷選遺遼邁還邇邉邊邌邎邏邐邫郉郌郍郒郞郟郵郶郷鄆鄉鄊鄒鄔鄕鄖鄥鄧鄭鄰鄲鄴鄶鄷鄺鄼鄽嗬酇酈酔酧醃醆醕醖醗醜醞醤醫醬醱醸醻醼醿釀釁釃釄釅釆釈釋釐釓釔釕釗釘釙針釡釣釤彷後徵釧釩釬釯釵釷釹鈀鈁鈃鈄鈅鈆鈈鈉鈍鈎鈐鈑鈒鈔鈕鈞鈟鈡鈣鈥鈦鈧鈮鈰鈳鈴鈷鈸鈹鈺鈽鈾鈿鉀鉄鉅猕夥廑鉆鉈鉉鉋鉍鉏鉑鉕鉗鉚鉛鉞鉢鉤鉦鉨鉩鉬鉭鉱鉶鉸鉺鉻鉿銀銁銃銅銍銏銑銒銓銕銖銘銚銛銜銞銠銣銥銦銨銩銪銫銬銭銱銲銳銷銹銻銼銽銾鋁鋃鋅鋇鋌鋏鋒鋕鋖鋙鋛鋝鋟鋢鋣鋤鋥鋦鋨鋩鋪鋭鋮鋯鋰鋱鋳鋵鋶鋸鋼錁錄錆錇錈渖錏錐錒錕錘錙錚錛錟錠錡錢錦錨錩錫錬錮錯錱録錳錶錷錸鍁鍆鍇鍈鍊鍋鍍鍔鍘鍚鍛鍠鍤鍥鍨鍩鍫甯鍬鍰鍳鍵鍶鍺鍼鍾鍿鎂鎇鎊鎋鎒鎔鎖鎗鎘鎚鎛鎜鎝鎠鎡鎢鎣鎦鎧鎩鎪鎫鎬鎭鎮鎯鎰鎲鎳鎵鎸鎻鎼鎽鎿鏁鏃鏆鏇鏈鏌鏍鏐鏑鏗鏘鏛鏜鏝鏞鏟鏠鏡鏢鏤鏥鏨鏬鏰鏳鏵鏷鏹鏺鏽鐃鐋鐐鐒鐓鐔鐗鐘鐙鐚鐝鐟鐠鐤鐦鐧鐨鐩鐫鐮巛琊瑛鐰鐲鐳鐵鐶鐸鐺鐽鐿鑀鑄鑊鑌鑑鑒鑔鑕鑚鑛鑜鑞鑠鑣鑤鑥鑬鑭鑰鑱鑲鑷鑻鑼鑽鑾鑿钁钂钄棹長镾門閂閃閅閆閇閈閉開閌閎閏閑閒間閔閗閘閙閠閡関閣閤閥閧閨閩閫閬閭閱閲閳閴閵閶閷閸閹閻閼閽閾閿闀闁闃闅闆闈闊闋榘闌闍闐闒闓闔闕闖闗闘闙闚關闝闞闠闡闢闤闥阥阦阧阩阬阯陒陖陗陘陜陝陞陣陥陦陰陳陸陹陻陽陿隂隄隉隊曛隌階隕隖隚際隝隟隠隣隦隨險隭隱隲隴隷隸隻雋雑雖雙雛雜雞離難雧雰雲電雼霊霑霗霚霛霡霧霴霽靀靁靂靄靆靈靉靌靑靚靜靝靣靤靦靨靭靱靵鞌鞏鞛鞝於鞦鞩鞰鞱鞵鞹鞽鞾韀韁韃韆韈韉韋韌韒韓韙韚韜韝韞韤韮韯韲韻韼響頁頂頃項順頇須頉頊頌頎肀頏預頑頒頓頗領頚頜頟頡頤頥頦頪頫頬頭頰頲頴頷頸頹頺頻頼頽頾頿顄顆顇顈顊顋題額顎顏顒顓顔顖願顙顚顛類顢顥顦顧顨顫顯顰顱顳顴風颭颮颯颱颳颶颷颸颺颻颼飀飃飄飅飆飇飈飊飛飜飡飢飣飤飥飦飩飪飫飭飮飯飰飱飲飴飸飹飻飼飽飾餁餃餄餅锺餈餉養餌餑餒餓餔餕餖餘餙餚餛餜餝餞餟餠餡餧館餪餬餱餳餵餹餺餻餼餽餾餿饁饃饄饅饈饉饊饋饌饍饑饒饗饚饜饝饞饢穰馬馭馮馱馳馴馹馿駁駆駈駐駑駒駔駕駘駙駛駝駞駟駠駡駢駦駭駰駱駵駸癯袷駿騁騂騅騈騍騎騏騐騒験騖騘騙騡騣騤騫騭騮騰騶騷騸騻騾驀驁驂驃驄驅驆驊驌驍驏驑驕驗驘驚驛驝驟驢驣驤驥驦驩驪骯骵骽骾髃髄髈髏髒體髕髖髗髙髠髤髥髨髩髪髮髰鬀鬂鬆鬉鬍鬚鬛鬢鬥鬦鬧鬨鬩鬪鬬鬭鬮鬰鬱鬴鬽魀魎魐魗魘魚魛魦魨魯魲魴魷魺麴鮁鮉鮌鮎鮐鮑鮒鮓鮙鮚鮞鮦鮪鮫鮭鮮鮶鮷鮸鯀鯁鯂鯇鯉鯊鯋鯔鯕鯖鯗鯛鯡鯢鯤鯧鯨鯪鯫鯰鯶鯷鯺鯽鯿鰀鰈鰉鰍鰐鰒鰓鰜鰟鰣鰥鰧鰨鰩鰭鰱鰲鰳鰵鰷鰹鰻鰼鰾鱈鱉鱍鱏鱑鱒鱓鱔鱖鱗鱘鱟鱠鱣鱧鱨鱭鱮鱷鱸鱺鱻麽齄鳥鳧鳩鳫鳬鳮鳯鳲鳳鳴鳶鴆鴇鴈鴉鴌鴎鴏鴒鴕鴛鴜鴝鴞鴟鴣鴦鴧鴨鴪鴬鴯鴰鴵鴷鴻鴼鴿鵂鵃鵄鵉鵑鵒鵓鵕鵜鵝鵞鵠鵡鵪鵬鵭鵯鵰鵲鵶鵷鶄鶇鶉鶊鶏鶕鶖鶘鶚鶡鶤鶥鶩鶪鶬鶮鶯鶲鶴鶹鶺鶻鶼鶽鶿鷀鷁鷂鷄鷆鷊鷓鷖鷗鷙鷚鷥鷦鷪鷫鷯鷲鷴鷸鷹鷺鷽鷿鸇鸎鸏鸕鸖鸚鸛鸝鸞鹵鹶鹸鹹鹺鹻鹼鹽麁麄麏麐麕麗麞麢麤麥麩麪麫麬麯麱麵麺麼麿黃黌黒黙點黨黱黲黴黷黽黿鼃鼄鼅鼇鼈鼉鼑鼔鼕鼝鼡鼦鼴齅齊齋齏齒齓齔齕齖齗齙齚齜齟齠齡齣齥齦齧齩齪齬齲齶齷齽龍龎龏龐龒龔龕龗龜龝龞龡龢龣";
    private static Map<Character, String> mUncommonWordsMap;
    private static byte[] mUnit2PinyinIndex = null;

    static {
        mUncommonWordsMap = null;
        mSpecialXingMap = null;
        mUncommonWordsMap = new HashMap();
        mSpecialXingMap = new HashMap();
        mUncommonWordsMap.put((char) 27018, "shen2");
        mSpecialXingMap.put((char) 26366, "zeng1");
        mSpecialXingMap.put((char) 35852, "chen2");
        mSpecialXingMap.put((char) 20167, "qiu2");
        mSpecialXingMap.put((char) 21333, "shan4");
        mSpecialXingMap.put((char) 20048, "yue4");
        mSpecialXingMap.put((char) 32735, "zhai2");
        mSpecialXingMap.put((char) 21306, "ou1");
        mSpecialXingMap.put((char) 26597, "zha1");
        mSpecialXingMap.put((char) 23561, "yu4");
        mSpecialXingMap.put((char) 26420, "piao2");
        mSpecialXingMap.put((char) 35299, "xie4");
        mSpecialXingMap.put((char) 35203, "qin2");
        mSpecialXingMap.put((char) 20613, "fu4");
        mSpecialXingMap.put((char) 32918, "xiao1");
        mSpecialXingMap.put((char) 36807, "guo1");
    }

    private static String filterChar(String str) {
        return (str == null || str.equals("")) ? "" : str.replaceAll("( )+", PINYIN_SEPARATOR).replaceAll("[ ]*[|][ ]*", PINYIN_EXCLUDE).trim();
    }

    public static void getCartesianPinyin(int i, ArrayList<String[]> arrayList, String str, ArrayList<String> arrayList2) {
        int i2 = 0;
        if (i == arrayList.size() - 1) {
            String[] strArr = arrayList.get(i);
            while (i2 < strArr.length) {
                arrayList2.add((String.valueOf(str) + PINYIN_SEPARATOR + strArr[i2]).trim());
                i2++;
            }
            return;
        }
        String[] strArr2 = arrayList.get(i);
        while (i2 < strArr2.length) {
            getCartesianPinyin(i + 1, arrayList, String.valueOf(str) + PINYIN_SEPARATOR + strArr2[i2], arrayList2);
            i2++;
        }
    }

    public static void getCartesianPinyin(int i, String[][] strArr, String str, ArrayList<String> arrayList) {
        int i2 = 0;
        if (i == strArr.length - 1) {
            String[] strArr2 = strArr[i];
            while (i2 < strArr2.length) {
                arrayList.add(String.valueOf(str) + PINYIN_SEPARATOR + strArr2[i2]);
                i2++;
            }
            return;
        }
        String[] strArr3 = strArr[i];
        while (i2 < strArr3.length) {
            getCartesianPinyin(i + 1, strArr, String.valueOf(str) + PINYIN_SEPARATOR + strArr3[i2], arrayList);
            i2++;
        }
    }

    private static int getIntFrombytes(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length < i + i2) {
            return -1;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < i2) {
            int i6 = i3 * 8;
            i3++;
            i4 = (i4 & i5) | (bArr[i + i3] << i6);
            i5 = (255 << i6) | i5;
        }
        return i4;
    }

    private static ArrayList<String> getMultiPinYin(String str) {
        String replaceAll = str.replaceAll("[^一-龥]", "");
        if (replaceAll == null || replaceAll.equals("")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < replaceAll.length(); i++) {
            arrayList.add(transWord2MultiPinyin(Character.valueOf(replaceAll.charAt(i))).trim().split(PINYIN_SEPARATOR));
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        getCartesianPinyin(0, arrayList, "", arrayList2);
        return arrayList2;
    }

    public static String[] getMultiSpell(String str, int i) {
        if (str != null && !str.equals("")) {
            new ArrayList();
            ArrayList<String> multiPinYin = i != 1 ? getMultiPinYin(str) : getNameMultiPinYin(str);
            if (multiPinYin != null && multiPinYin.size() > 0) {
                String[] strArr = new String[multiPinYin.size()];
                for (int i2 = 0; i2 < multiPinYin.size(); i2++) {
                    strArr[i2] = multiPinYin.get(i2);
                }
                return strArr;
            }
        }
        return null;
    }

    private static ArrayList<String> getNameMultiPinYin(String str) {
        String replaceAll = str.replaceAll("[^一-龥]", "");
        if (replaceAll == null || replaceAll.equals("")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < replaceAll.length()) {
            Character valueOf = Character.valueOf(replaceAll.charAt(i));
            arrayList.add((i != 0 || !mSpecialXingMap.containsKey(valueOf)) ? transWord2MultiPinyin(valueOf).trim().split(PINYIN_SEPARATOR) : new String[]{mSpecialXingMap.get(valueOf)});
            i++;
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        getCartesianPinyin(0, arrayList, "", arrayList2);
        return arrayList2;
    }

    public static String[] getNameMultiSpell(String str) {
        return getMultiSpell(str, 0);
    }

    private static String getNamePinYin(String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (int i = 0; i < str.length(); i++) {
            Character valueOf = Character.valueOf(str.charAt(i));
            if (valueOf == null || valueOf.charValue() != PINYIN_SEPARATOR.charAt(0)) {
                String transWord2Pinyin = transWord2Pinyin(valueOf);
                if (transWord2Pinyin != null && !transWord2Pinyin.equals("")) {
                    sb.append(PINYIN_SEPARATOR);
                    if (!z || !mSpecialXingMap.containsKey(valueOf)) {
                        sb.append(transWord2Pinyin);
                    } else {
                        sb.append(mSpecialXingMap.get(valueOf));
                        z = false;
                    }
                    sb.append(PINYIN_SEPARATOR);
                }
                if ((valueOf.charValue() < '0' || valueOf.charValue() > '9') && ((valueOf.charValue() < 'a' || valueOf.charValue() > 'z') && (valueOf.charValue() < 'A' || valueOf.charValue() > 'Z'))) {
                    if (valueOf.charValue() == PINYIN_EXCLUDE.charAt(0)) {
                        sb.append(valueOf);
                        z = true;
                    }
                }
            }
            sb.append(valueOf);
        }
        return filterChar(sb.toString());
    }

    public static String getNameSpell(String str) {
        return (str == null || str.equals("")) ? "" : getSpell2(str, 1)[0];
    }

    public static String[] getNameSpell1(String str) {
        return getSpell(str, 1);
    }

    public static String[] getNameSpell2(String str) {
        return getSpell2(str, 1);
    }

    private static String getPinYin(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            Character valueOf = Character.valueOf(str.charAt(i));
            if (valueOf == null || valueOf.charValue() != PINYIN_SEPARATOR.charAt(0)) {
                String transWord2Pinyin = transWord2Pinyin(valueOf);
                if (transWord2Pinyin != null && !transWord2Pinyin.equals("")) {
                    sb.append(PINYIN_SEPARATOR);
                    sb.append(transWord2Pinyin);
                    sb.append(PINYIN_SEPARATOR);
                }
                if ((valueOf.charValue() < '0' || valueOf.charValue() > '9') && ((valueOf.charValue() < 'a' || valueOf.charValue() > 'z') && ((valueOf.charValue() < 'A' || valueOf.charValue() > 'Z') && valueOf.charValue() != PINYIN_EXCLUDE.charAt(0)))) {
                }
            }
            sb.append(valueOf);
        }
        return filterChar(sb.toString());
    }

    private static String getPinYinbyIndex(int i) {
        byte[] bArr = mPinyinList;
        if (bArr != null) {
            int i2 = mPinyinSize;
            int i3 = i * i2;
            if (i2 + i3 <= bArr.length) {
                StringBuilder sb = new StringBuilder();
                for (int i4 = i3; i4 < mPinyinSize + i3; i4++) {
                    byte[] bArr2 = mPinyinList;
                    if (bArr2[i4] == 0) {
                        break;
                    }
                    sb.append((char) bArr2[i4]);
                }
                return sb.toString();
            }
        }
        return "";
    }

    public static String[] getSpell(String str, int i) {
        String[] spell2 = getSpell2(str, i);
        if (spell2 == null) {
            return null;
        }
        spell2[0] = spell2[0].replaceAll("([a-zA-Z]+)[0-4]", "$1");
        return spell2;
    }

    public static String[] getSpell2(String str, int i) {
        if (str == null) {
            return null;
        }
        String pinYin = i != 1 ? getPinYin(str) : getNamePinYin(str);
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        for (int i2 = 0; i2 < pinYin.length(); i2++) {
            char charAt = pinYin.charAt(i2);
            if (z && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                stringBuffer.append(charAt);
            }
            if (charAt != PINYIN_SEPARATOR.charAt(0)) {
                if (charAt == PINYIN_EXCLUDE.charAt(0)) {
                    stringBuffer.append(charAt);
                } else {
                    z = false;
                }
            }
            z = true;
        }
        return new String[]{pinYin, stringBuffer.toString()};
    }

    private static int getUIntFrombytes(byte[] bArr, int i, int i2) {
        if (bArr == null || bArr.length < i + i2) {
            return -1;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 |= (bArr[i + i4] & 255) << (i4 * 8);
        }
        return i3;
    }

    public static String[] getWordMultiSpell(String str) {
        return getMultiSpell(str, 1);
    }

    public static String getWordSpell(String str) {
        return getSpell2(str, 2)[0];
    }

    public static String[] getWordSpell1(String str) {
        return getSpell(str, 2);
    }

    public static String[] getWordSpell2(String str) {
        return getSpell2(str, 2);
    }

    public static void init(InputStream inputStream) throws IOException {
        if (mUnit2PinyinIndex == null && inputStream != null) {
            try {
                int available = inputStream.available();
                byte[] bArr = new byte[available];
                inputStream.read(bArr);
                inputStream.close();
                if (available < 12) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    mPinyinNum = getUIntFrombytes(bArr, 0, 4);
                    int uIntFrombytes = getUIntFrombytes(bArr, 4, 4);
                    mPinyinSize = uIntFrombytes;
                    int i = mPinyinNum * uIntFrombytes;
                    if (i > 0) {
                        byte[] bArr2 = new byte[i];
                        mPinyinList = bArr2;
                        System.arraycopy(bArr, 8, bArr2, 0, i);
                    }
                    int i2 = i + 8;
                    int i3 = available - i2;
                    if (i3 > 0) {
                        byte[] bArr3 = new byte[i3];
                        mUnit2PinyinIndex = bArr3;
                        System.arraycopy(bArr, i2, bArr3, 0, i3);
                    }
                    inputStream.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                inputStream.close();
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        }
    }

    public static char transT2S(char c) {
        int indexOf = mTraditional.indexOf(c);
        return indexOf >= 0 ? mSimplified.charAt(indexOf) : c;
    }

    private static String transWord2MultiPinyin(Character ch) {
        return transWord2Pinyin(ch, true);
    }

    private static String transWord2Pinyin(Character ch) {
        return transWord2Pinyin(ch, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String transWord2Pinyin(java.lang.Character r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 179
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.yunzhisheng.common.PinyinConverter.transWord2Pinyin(java.lang.Character, boolean):java.lang.String");
    }
}
