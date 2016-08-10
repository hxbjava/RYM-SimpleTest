sed -i "" "s/value=\"PAAnydoorSDK1130-1108.ipa\"/value=\"${appName}\"/g" testng-6.xml
sed -i "" "s/value=\"\/Users\/rymtest\/Desktop\/app_package\/\"/value=\"${app_package}\"/g" testng-6.xml
sed -i "" "s/value=\"娱乐 (2)\"/value=\"${deviceName}\"/g" testng-6.xml
sed -i "" "s/value=\"9.0\"/value=\"${platformVersion}\"/g" testng-6.xml
sed -i "" "s/value=\"8f023bbd000d58e34cfea2fdca1a8901d3123291\"/value=\"${udid}\"/g" testng-6.xml
sed -i "" "s/includes=\"testng-6.xml\"/includes=\"${testngxml}\"/g" build.xml
sed -i "" "s/value=\"\/Users\/rymtest\/Desktop\/app_package\/\"/value=\"${app_package}\"/g" testng-android-rongyao6.xml