datea=$(date +%y%m%d)
mv ${app_package}/PA.ipa ${app_package}/PA${datea}-old.ipa
cp -rp ${app_path}/PA.ipa ${app_package}