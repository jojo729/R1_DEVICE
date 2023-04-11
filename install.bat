:: adb push app\build\outputs\apk\debug\app-debug.apk /data/local/tmp/app-debug.apk

adb shell busybox wget -O /data/local/tmp/app-debug.apk http://192.168.11.155:8000/NewPackage/build/outputs/apk/debug/NewPackage-debug.apk"
adb shell /system/bin/pm install -r -t  /data/local/tmp/app-debug.apk