#!/bin/bash
for FILE in *.jar *.pom
do
    md5sum $FILE > $FILE.md5
    sha1sum $FILE > $FILE.sha1
    sha512sum $FILE > $FILE.sha512
    sha256sum $FILE > $FILE.sha256
    gpg -ab $FILE
done
jar -cvf bundle.jar *