for var in 01 02 03 04 05 06 07 08 09
do
   echo $var
   curl -X POST \
  http://localhost:8080/springboot-crud-rest/api/v1/branch \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: d4d6d34b-37b8-f7a7-e4a5-1fdc2d84edf8' \
  -d '{
  "address": "PALAVA CITY, '$var'",
  "branchManager": "AKASH SINGH",
  "branchName": "Fortis Gurgaon",
  "contactNumber": "81049126'$var'",
  "emailAddress": "akashec1004@gmail.com",
  "slotAvailable": "5",
  "vaccine": [
    {
      "barcode": "AEM600'$var'",
      "manufacturedBy": "RANBAXY",
      "vaccineName": "COVAC19"
    }
  ]
}'
done
