if [ $# -ne 1 ];
then
  echo "File name required"
else
  cp -v -r ./CodeJamTemplate ./$1
  mv ./$1/.idea/CodeJamTemplate.iml ./$1/.idea/$1.iml
  sed -i '' "s/CodeJamTemplate/$1/g" ./$1/.idea/workspace.xml
  sed -i '' "s/CodeJamTemplate/$1/g" ./$1/.idea/modules.xml
fi