#!/bin/bash

VERSION=6.0.1-0
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

jar xf jaylib-natives-linux-arm64-${VERSION}.jar
rm jaylib-natives-linux-arm64-${VERSION}.jar
jar xf jaylib-natives-linux-x86-${VERSION}.jar
rm jaylib-natives-linux-x86-${VERSION}.jar
jar xf jaylib-natives-linux-x86_64-${VERSION}.jar
rm jaylib-natives-linux-x86_64-${VERSION}.jar
jar xf jaylib-natives-macosx-arm64-${VERSION}.jar
rm jaylib-natives-macosx-arm64-${VERSION}.jar
jar xf jaylib-natives-macosx-x86_64-${VERSION}.jar
rm jaylib-natives-macosx-x86_64-${VERSION}.jar
jar xf jaylib-natives-windows-x86-${VERSION}.jar
rm jaylib-natives-windows-x86-${VERSION}.jar
jar xf jaylib-natives-windows-x86_64-${VERSION}.jar
rm jaylib-natives-windows-x86_64-${VERSION}.jar

jar uf jaylib-${VERSION}.jar com

rm -rf com META-INF


OUT_DIR="uk/co/electronstudio/jaylib/jaylib/${VERSION}"
mkdir -p ${OUT_DIR}

cp "${SCRIPT_DIR}/jaylib-${VERSION}.pom" "${OUT_DIR}"
mv *.jar ${OUT_DIR}

for FILE in "${OUT_DIR}"/*
do
    echo "processing ${FILE}"
    md5sum "${FILE}" | cut -d ' ' -f 1 > "${FILE}".md5
    sha1sum "${FILE}" | cut -d ' ' -f 1 > "${FILE}".sha1
    sha512sum "${FILE}" | cut -d ' ' -f 1 > "${FILE}".sha512
    sha256sum "${FILE}" | cut -d ' ' -f 1 > "${FILE}".sha256
    gpg -ab $FILE
done

zip -r bundle uk
