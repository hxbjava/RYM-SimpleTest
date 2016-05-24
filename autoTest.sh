#!/bin/bash

sed "s/includes=\"testng-6.xml\"/includes=\"${xmlName}\"/g" build.xml>build.xml.tmp

sed "s/value=\"\/Users\/rymtest\/Desktop\/app_package\/\"/value=\"${app_package}\"/g" testng-6.xml>testng-6.xml.tmp