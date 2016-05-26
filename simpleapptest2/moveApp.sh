datea=$(date +%y%m%d)
mv ${app_package}/PAAnydoorSDK_New.ipa ${app_package}/PA${datea}-old.ipa
cp -rp ${app_path}/PAAnydoorSDK_New.ipa ${app_package}