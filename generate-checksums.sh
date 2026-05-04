#!/bin/bash

VERSION=6.0.0-0

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
cp ~/IdeaProjects/jaylib/jaylib-${VERSION}.pom .

for FILE in *.jar *.pom
do
    md5sum $FILE > $FILE.md5
    sha1sum $FILE > $FILE.sha1
    sha512sum $FILE > $FILE.sha512
    sha256sum $FILE > $FILE.sha256
    gpg -ab $FILE
done

jar -cvf bundle.jar *
