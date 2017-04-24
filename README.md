# Bots Network
Powerful platform that aimed to help everyone who is tired of the mess with various social networks and messengers. The main idea behind this project is to gather all the important functionality in one place and to use it from suitable for you tool. Now it's just a simple vk bot with couple of commands, but any of them have very complex realization under the hood.

`Say` command is generating speech (text-to-speach) from your message, `login` and `logout` are used to manage your instagram account, and finally `post` is used to post in your instagram account. Except this functionality project covers different tricky situations (posting without logining, login with wrong credentials and many else) and works correct.
### Live Demo
This project is hosted on DigitalOcean and accessible at https://vk.com/im?sel=-142765838 . Send a message to VK bot to give it a try. If you encounter any problems (bot does not work or some command does not work properly), please do not hesitate to [contact me](#contact). Also, for the security of your private data you can use a test Instagram account:

username: *henaltestbot*

password: *amazingbot*
### Commands
##### Instagram Commands:
- login [username] [password]
- logout
- post [message] (you should also upload image, or forward message with image)
##### Other Commands:
- say [message] 
- help
# Quick start
It is a standard Maven project and can be imported into your favorite IDE.You can deploy project by typing ```mvn spring-boot:run ``` in a root directory of the project.However, configuration of the bot is a bit trickier. In order to do so, you need to create your own VK application, set required parameters listed in `application.properties` , deploy the project and set a specific url as a callback in the VK application settings.
# Technologies:
- Spring Boot
- Spring Data
- Spring MVC
- Hibernate
- Redis
- Docker
# Libraries:
- GSON
- Logback
- VK SDK
- Instagram private api
- Amazon SDK
- Polly
# Contact
Feel free to contact me if you have any questions. Also, I am friendly and open for any kind of communication. The best way to reach me is to send a message in [VK](https://vk.com/genal_igor). Also, I am available at:
- gmail: grom3198@gmail.com
- vk: https://vk.com/genal_igor
- github: https://github.com/amowel
