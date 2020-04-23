# zoom_api_java MILESTONE 2

Hello!

**Build Instructions** 

[NOTE] This project was built using Gradle. Script found in root directory, build.gradle

[NOTE] Make sure that your IDE or console is operating on jdk 11 or higher.
1) On your command terminal move to the project directory /zoom_api_java

        Enter ./gradlew build

**Test Bot Instructions**

1) Open ngrok tunnel on your console:
           
Assuming you are able to run ngrok in your terminal enter:

        ngrok start --none
       
This should open ngrok without any current tunnels.

2a. Preferably run the TestBot.java class in directory /zoom_api_java/src/main/java/bots

2b. If you have a second terminal (ngrok will be running on the first) with jdk 11 or higher
you can move to the directory /zoom_api_java/src/main/java/bots
      
       Enter javac TestBot.java
       Enter java TestBot 
2c. I believe you can also run the bot from the root directory /zoom_api_java and perform these commands:

        ./gradlew build
        .gradlew run

3. You may first be prompted to sign into Zoom. Then, TestBot will take you through a series of commands exercising zoom api calls.

**Configuration Instructions**

1) Locate the bot.ini file in the src/main/java/bots directory. 

2) Under [Oauth]

        Replace YOUR_CLIENT_ID with your api key obtained from Zoom developers
        Replace YOUR_CLIENT_SECRET with your api secret obtained from Zoom developers


**Additional Notes**

I was able to get the bot to run and perform functions using a combination of ngrok 
tunneling on my command terminal and running TestBot.main() in my IDE. Please let me 
know if there is any issues. I would be happy to demo in person via Zoom.
