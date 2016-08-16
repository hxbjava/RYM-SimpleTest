datea=$(date +%y%m%d%H%M)
mv ${app_NewPath}/simpleapp-New.apk ${app_NewPath}/PA${datea}-old.apk
cp -rp ${app_oldpath}/simpleapp-New.apk ${app_NewPath}