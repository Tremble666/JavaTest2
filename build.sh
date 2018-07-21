cd Exam1

mvn clean package

cd target

java -jar getpdf.jar


cd ../..

cd Exam2

mvn package

cd target

 java -cp server.jar com.hand.Server

 java -cp server.jar com.hand.Client

cd ../..

cd Exam3

mvn clean package

cd target

java -jar getpdf.jar



