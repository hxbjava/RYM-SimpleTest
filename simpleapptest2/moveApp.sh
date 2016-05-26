datea=$(date +%y%m%d%H%M)
mv ${app_NewPath}/PAAnydoorSDK_New.ipa ${app_NewPath}/PA${datea}-old.ipa
cp -rp ${app_oldpath}/PAAnydoorSDK_New.ipa ${app_NewPath}